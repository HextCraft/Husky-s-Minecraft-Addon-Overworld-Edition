package team.hdt.neutronia.groups.tweaks.features;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.base.groups.ConfigHelper;
import team.hdt.neutronia.base.util.ReflectionLib;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class HCTools extends Component {

    private static final HashMap<Item.ToolMaterial, ToolMaterialOverride> OVERRIDES = Maps.newHashMap();

    @Override
    public String getFeatureDescription() {
        return "Overhaul the durability of tools to be more rewarding when reaching the next level. Completely disables wooden tools (other than pick) by default.";
    }

    @Override
    public void init(FMLInitializationEvent event) {
        Set<ItemTool> TOOLS = Sets.newHashSet(
                Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SWORD, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE,
                Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SWORD, Items.IRON_SHOVEL, Items.IRON_HOE,
                Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_SWORD, Items.STONE_SHOVEL, Items.STONE_HOE,
                Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_SWORD, Items.GOLDEN_SHOVEL, Items.GOLDEN_HOE,
                Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_SWORD, Items.WOODEN_SHOVEL, Items.WOODEN_HOE
        ).stream().filter(item -> item instanceof ItemTool).map(item -> (ItemTool) item).collect(Collectors.toSet());

        OVERRIDES.put(Item.ToolMaterial.WOOD, new ToolMaterialOverride("wood", 1, 1.01F, 0));
        OVERRIDES.put(Item.ToolMaterial.STONE, new ToolMaterialOverride("stone", 6, 1.01F, 5));
        OVERRIDES.put(Item.ToolMaterial.IRON, new ToolMaterialOverride("iron", 500, 6.0F, 14));
        OVERRIDES.put(Item.ToolMaterial.DIAMOND, new ToolMaterialOverride("diamond", 1561, 8.0F, 14));
        OVERRIDES.put(Item.ToolMaterial.GOLD, new ToolMaterialOverride("gold", 32, 12.0F, 22));

        TOOLS.forEach(this::loadToolMaterialOverride);
    }

    private void loadToolMaterialOverride(ItemTool tool) {
        Item.ToolMaterial material = ReflectionHelper.getPrivateValue(ItemTool.class, tool, ReflectionLib.ITEM_TOOL_TOOL_MATERIAL);
        ToolMaterialOverride override = OVERRIDES.get(material);
        ReflectionHelper.setPrivateValue(Item.ToolMaterial.class, material, override.getMaxUses(), ReflectionLib.TOOL_MATERIAL_MAX_USES);
        ReflectionHelper.setPrivateValue(Item.ToolMaterial.class, material, override.getEfficiency(), ReflectionLib.TOOL_MATERIAL_EFFICIENCY);
        ReflectionHelper.setPrivateValue(Item.ToolMaterial.class, material, override.getEnchantability(), ReflectionLib.TOOL_MATERIAL_ENCHANTABILITIY);
        ReflectionHelper.setPrivateValue(ItemTool.class, tool, material.getEfficiency(), ReflectionLib.ITEM_TOOL_EFFICIENCY);
        tool.setMaxDamage(material.getMaxUses() - 1); //subtract one from the max durability because the tool doesn't break until -1
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @SubscribeEvent
    public void onBreaking(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.isEmpty()) return;
        if (stack.getMaxDamage() == 1) {
            destroyItem(stack, player);
        }
    }

    private void destroyItem(ItemStack stack, EntityLivingBase entity) {
        int damage = stack.getMaxDamage();
        stack.damageItem(damage, entity);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    public class ToolMaterialOverride {
        private int maxUses;
        private float efficiencyOnProperMaterial;
        private int enchantability;


        ToolMaterialOverride(String name, int maxUses, float efficiencyOnProperMaterial, int enchantability) {
            this.maxUses = ConfigHelper.loadPropInt("Max Durability", configCategory + "." + name, "", maxUses);
            this.efficiencyOnProperMaterial = efficiencyOnProperMaterial;
            this.enchantability = enchantability;
        }

        int getMaxUses() {
            return maxUses;
        }

        float getEfficiency() {
            return efficiencyOnProperMaterial;
        }

        int getEnchantability() {
            return enchantability;
        }
    }

}
