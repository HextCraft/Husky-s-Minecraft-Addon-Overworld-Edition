package team.hdt.neutronia_legacy.groups.misc.feature;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.lib.LibMisc;
import team.hdt.neutronia_legacy.groups.misc.item.ItemEnchantedScroll;

import java.util.*;

public class EnchantedScrolls extends Component {

    public static Item enchanted_scroll;
    public static List<Enchantment> validEnchants = new ArrayList<>();
    int dungeonWeight, libraryWeight, itemQuality, mergeScrollCost, applyScrollCost;
    private String[] enchantNames;

    @Override
    public void setupConfig() {
        enchantNames = loadPropStringList("Valid Enchantments", "", generateDefaultEnchantmentList());
        dungeonWeight = loadPropInt("Dungeon loot weight", "", 8);
        libraryWeight = loadPropInt("Stronghold Library loot weight", "", 12);
        itemQuality = loadPropInt("Item quality for loot", "", 2);
        mergeScrollCost = loadPropInt("Cost to apply scroll", "", 35);
        applyScrollCost = loadPropInt("Cost to apply upgraded book to item", "", 35);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        enchanted_scroll = new ItemEnchantedScroll();

        LootFunctionManager.registerFunction(new EnchantScrollFunction.Serializer());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        validEnchants.clear();
        for (String s : enchantNames) {
            ResourceLocation r = new ResourceLocation(s);
            Enchantment e = Enchantment.REGISTRY.getObject(r);
            if (e != null)
                validEnchants.add(e);
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY))
            event.getTable().getPool("main").addEntry(new LootEntryItem(enchanted_scroll, libraryWeight, itemQuality, new LootFunction[]{new EnchantScrollFunction()}, new LootCondition[0], "neutronia_legacy:enchanted_scroll"));
        else if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON))
            event.getTable().getPool("main").addEntry(new LootEntryItem(enchanted_scroll, dungeonWeight, itemQuality, new LootFunction[]{new EnchantScrollFunction()}, new LootCondition[0], "neutronia_legacy:enchanted_scroll"));
    }

    @SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (!left.isEmpty() && !right.isEmpty()) {
            if (left.getItem() == Items.ENCHANTED_BOOK && right.getItem() == enchanted_scroll)
                handleScroll(left, right, event);
            else if (right.getItem() == Items.ENCHANTED_BOOK && left.getItem() == enchanted_scroll)
                handleScroll(right, left, event);

            else if (right.getItem() == Items.ENCHANTED_BOOK) {
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(right);
                Map<Enchantment, Integer> currentEnchants = EnchantmentHelper.getEnchantments(left);
                boolean hasOverLevel = false;
                boolean hasMatching = false;
                for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                    Enchantment ench = entry.getKey();
                    if (ench == null)
                        continue;

                    int level = entry.getValue();
                    if (level > ench.getMaxLevel()) {
                        hasOverLevel = true;
                        if (ench.canApply(left)) {
                            hasMatching = true;
                            for (Iterator<Enchantment> iterator = currentEnchants.keySet().iterator(); iterator.hasNext(); ) {
                                Enchantment enchCompare = iterator.next();
                                if (enchCompare == ench)
                                    continue;

                                if (!enchCompare.isCompatibleWith(ench)) {
                                    iterator.remove();
                                }
                            }
                            currentEnchants.put(ench, level);
                        }
                    } else if (ench.canApply(left)) {
                        boolean compatible = true;
                        for (Enchantment enchCompare : currentEnchants.keySet()) {
                            if (enchCompare == ench)
                                continue;

                            if (enchCompare != null && !enchCompare.isCompatibleWith(ench)) {
                                compatible = false;
                                break;
                            }
                        }
                        if (compatible) {
                            currentEnchants.put(ench, level);
                        }
                    }
                }
                if (hasOverLevel) {
                    if (hasMatching) {
                        ItemStack out = left.copy();
                        EnchantmentHelper.setEnchantments(currentEnchants, out);
                        String name = event.getName();
                        int cost = applyScrollCost;
                        if (name != null && !name.isEmpty()) {
                            out.setStackDisplayName(name);
                            cost++;
                        }
                        event.setOutput(out);
                        event.setCost(cost);
                    } else {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    private void handleScroll(ItemStack book, ItemStack tome, AnvilUpdateEvent event) {
        Map<Enchantment, Integer> enchantsBook = EnchantmentHelper.getEnchantments(book);
        Map<Enchantment, Integer> enchantsScroll = EnchantmentHelper.getEnchantments(tome);
        for (Map.Entry<Enchantment, Integer> entry : enchantsScroll.entrySet()) {
            if (enchantsBook.getOrDefault(entry.getKey(), 0).equals(entry.getValue())) {
                enchantsBook.put(entry.getKey(), entry.getValue() + 1);
            } else {
                return;
            }
        }
        ItemStack output = ProxyRegistry.newStack(Items.ENCHANTED_BOOK);
        for (Map.Entry<Enchantment, Integer> entry : enchantsBook.entrySet()) {
            ItemEnchantedBook.addEnchantment(output, new EnchantmentData(entry.getKey(), entry.getValue()));
        }
        event.setOutput(output);
        event.setCost(mergeScrollCost);
    }

    private String[] generateDefaultEnchantmentList() {
        Enchantment[] enchants = new Enchantment[]{
                Enchantments.FEATHER_FALLING,
                Enchantments.THORNS,
                Enchantments.SHARPNESS,
                Enchantments.SMITE,
                Enchantments.BANE_OF_ARTHROPODS,
                Enchantments.KNOCKBACK,
                Enchantments.FIRE_ASPECT,
                Enchantments.LOOTING,
                Enchantments.SWEEPING,
                Enchantments.EFFICIENCY,
                Enchantments.UNBREAKING,
                Enchantments.FORTUNE,
                Enchantments.POWER,
                Enchantments.PUNCH,
                Enchantments.LUCK_OF_THE_SEA,
                Enchantments.LURE
        };

        List<String> strings = new ArrayList<>();
        for (Enchantment e : enchants)
            if (e != null && e.getRegistryName() != null)
                strings.add(e.getRegistryName().toString());

        return strings.toArray(new String[strings.size()]);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    public static class EnchantScrollFunction extends LootFunction {

        protected EnchantScrollFunction() {
            super(new LootCondition[0]);
        }

        @Override
        public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
            Enchantment enchantment = validEnchants.get(rand.nextInt(validEnchants.size()));
            stack.addEnchantment(enchantment, enchantment.getMaxLevel());
            return stack;
        }

        public static class Serializer extends LootFunction.Serializer<EnchantScrollFunction> {

            protected Serializer() {
                super(new ResourceLocation(LibMisc.MOD_ID, "enchant_scroll"), EnchantScrollFunction.class);
            }

            @Override
            public void serialize(JsonObject object, EnchantScrollFunction functionClazz,
                                  JsonSerializationContext serializationContext) {
            }

            @Override
            public EnchantScrollFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext,
                                                     LootCondition[] conditionsIn) {
                return new EnchantScrollFunction();
            }
        }
    }

}
