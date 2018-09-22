package net.hdt.neutronia.groups.misc.feature;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.api.ICustomEnchantColor;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.groups.misc.item.ItemRune;
import net.hdt.neutronia.groups.tweaks.util.ItemNBTHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ColorRunes extends Component {

    public static final String TAG_RUNE_ATTACHED = "neutronia:rune_attached";
    public static final String TAG_RUNE_COLOR = "neutronia:rune_color";
    public static Item rune;
    private static ItemStack targetStack;
    int dungeonWeight, netherFortressWeight, jungleTempleWeight, desertTempleWeight, itemQuality, applyCost;
    boolean enableRainbowRuneCrafting, enableRainbowRuneChests, stackable;

    public static void setTargetStack(ItemStack stack) {
        targetStack = stack;
    }

    public static int getColor(int original) {
        if (!GroupLoader.isComponentEnabled(ColorRunes.class) || !doesStackHaveRune(targetStack) && !targetStack.isEmpty() && !(targetStack.getItem() instanceof ICustomEnchantColor))
            return original;

        return getColorFromStack(targetStack);
    }

    public static void applyColor() {
        if (!GroupLoader.isComponentEnabled(ColorRunes.class) || !doesStackHaveRune(targetStack)) {
            return;
        }

        int color = getColorFromStack(targetStack);
        float r = (color >> 16 & 0xFF) / 255F;
        float g = (color >> 8 & 0xFF) / 255F;
        float b = (color & 0xFF) / 255F;

        GlStateManager.color(r, g, b, 1F);
    }

    public static int getColorFromStack(ItemStack stack) {
        if (stack.isEmpty())
            return 0xFFFFFF;

        int retColor = 0xFFFFFF;
        boolean truncate = true;

        if (stack.getItem() instanceof ICustomEnchantColor) {
            int color = ((ICustomEnchantColor) stack.getItem()).getEnchantEffectColor(stack);
            truncate = ((ICustomEnchantColor) stack.getItem()).shouldTruncateColorBrightness(stack);
            retColor = 0xFF000000 | color;
        } else if (doesStackHaveRune(stack)) {
            int color = ItemRune.getColor(ItemNBTHelper.getInt(targetStack, TAG_RUNE_COLOR, 0));
            retColor = 0xFF000000 | color;
        }

        if (truncate) {
            int r = retColor >> 16 & 0xFF;
            int g = retColor >> 8 & 0xFF;
            int b = retColor & 0xFF;

            int t = r + g + b;
            if (t > 396) {
                float mul = 396F / t;
                r = (int) (r * mul);
                g = (int) (g * mul);
                b = (int) (b * mul);

                retColor = (0xFF << 24) + (r << 16) + (g << 8) + b;
            }
        }

        return retColor;
    }

    public static boolean doesStackHaveRune(ItemStack stack) {
        return !stack.isEmpty() && stack.hasTagCompound() && ItemNBTHelper.getBoolean(stack, TAG_RUNE_ATTACHED, false);
    }

    @Override
    public void setupConfig() {
        dungeonWeight = loadProperty("Dungeon loot weight", 20).get();
        netherFortressWeight = loadProperty("Nether Fortress loot weight", 15).get();
        jungleTempleWeight = loadProperty("Jungle Temple loot weight", 15).get();
        desertTempleWeight = loadProperty("Desert Temple loot weight", 15).get();
        itemQuality = loadProperty("Item quality for loot", 0).get();
        applyCost = loadProperty("Cost to apply rune", 15).get();
        enableRainbowRuneCrafting = loadProperty("Enable Rainbow Rune Crafting", true).get();
        enableRainbowRuneChests = loadProperty("Enable Rainbow Rune in Chests", false).get();
        stackable = loadProperty("Stackable Runes", true).get();
    }

    @Override
    public String getDescription() {
        return "Adds runes used to color items";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        rune = new ItemRune(stackable);

        if (enableRainbowRuneCrafting) {
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(rune, 7, 16),
                    "345", "2G6", "1W7",
                    'G', ProxyRegistry.newStack(Blocks.GLASS),
                    'W', ProxyRegistry.newStack(rune, 1, 0),
                    '1', ProxyRegistry.newStack(rune, 1, 14),
                    '2', ProxyRegistry.newStack(rune, 1, 1),
                    '3', ProxyRegistry.newStack(rune, 1, 4),
                    '4', ProxyRegistry.newStack(rune, 1, 5),
                    '5', ProxyRegistry.newStack(rune, 1, 3),
                    '6', ProxyRegistry.newStack(rune, 1, 11),
                    '7', ProxyRegistry.newStack(rune, 1, 2));
        }
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        LootFunction[] funcs = new LootFunction[]{new SetMetadata(new LootCondition[0], new RandomValueRange(0, enableRainbowRuneChests ? 16 : 15))};

        if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON))
            event.getTable().getPool("main").addEntry(new LootEntryItem(rune, dungeonWeight, itemQuality, funcs, new LootCondition[0], "neutronia:rune"));
        else if (event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE))
            event.getTable().getPool("main").addEntry(new LootEntryItem(rune, netherFortressWeight, itemQuality, funcs, new LootCondition[0], "neutronia:rune"));
        else if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE))
            event.getTable().getPool("main").addEntry(new LootEntryItem(rune, jungleTempleWeight, itemQuality, funcs, new LootCondition[0], "neutronia:rune"));
        else if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID))
            event.getTable().getPool("main").addEntry(new LootEntryItem(rune, desertTempleWeight, itemQuality, funcs, new LootCondition[0], "neutronia:rune"));
    }

    @SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (!left.isEmpty() && !right.isEmpty() && left.isItemEnchanted() && right.getItem() == rune) {
            ItemStack out = left.copy();
            ItemNBTHelper.setBoolean(out, TAG_RUNE_ATTACHED, true);
            ItemNBTHelper.setInt(out, TAG_RUNE_COLOR, right.getItemDamage());
            event.setOutput(out);
            event.setCost(applyCost);
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
