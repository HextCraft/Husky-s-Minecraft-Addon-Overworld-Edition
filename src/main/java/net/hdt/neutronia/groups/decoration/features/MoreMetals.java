package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaOre;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.hdt.neutronia.init.NCreativeTabs;
import net.hdt.neutronia.items.ItemArmorBase;
import net.hdt.neutronia.items.ItemBase;
import net.hdt.neutronia.items.base.tools.BaseAxe;
import net.hdt.neutronia.items.base.tools.BasePickaxe;
import net.hdt.neutronia.items.base.tools.BaseShovel;
import net.hdt.neutronia.items.base.tools.BaseSword;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoreMetals extends Component {

    public static Block brassBlock, steelBlock, copperBlock/*, bronzeBlock*/;
    public static Block brassOre, steelOre, copperOre/*, bronzeOre, tinOre*/, zincOre;

    public static final Item.ToolMaterial BRASS = EnumHelper.addToolMaterial("brass", 1, 240, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial STEEL = EnumHelper.addToolMaterial("steel", 3, 502, 10F, 6.0F, 22);
    public static final Item.ToolMaterial BRONZE = EnumHelper.addToolMaterial("bronze", 1, 131, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial COPPER = EnumHelper.addToolMaterial("copper", 1, 172, 4.0F, 1.0F, 5);

    public static final ItemArmor.ArmorMaterial BRASS_ARMOR = EnumHelper.addArmorMaterial("brass", "brass", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static final ItemArmor.ArmorMaterial STEEL_ARMOR = EnumHelper.addArmorMaterial("steel", "steel", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static final ItemArmor.ArmorMaterial BRONZE_ARMOR = EnumHelper.addArmorMaterial("bronze", "bronze", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static final ItemArmor.ArmorMaterial COPPER_ARMOR = EnumHelper.addArmorMaterial("copper", "copper", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static Item BRASS_AXE, BRASS_PICKAXE, BRASS_SHOVEL, BRASS_SWORD;
    public static Item STEEL_AXE, STEEL_PICKAXE, STEEL_SHOVEL, STEEL_SWORD;
    public static Item COPPER_AXE, COPPER_PICKAXE, COPPER_SHOVEL, COPPER_SWORD;
    //    public static Item BRONZE_AXE, BRONZE_PICKAXE, BRONZE_SHOVEL, BRONZE_SWORD;
    public static Item brassIngot, brassNugget;
    public static Item copperIngot, copperNugget;
    public static Item steelIngot, steelNugget;
    public static Item zincChunk;
//    public static Item bronzeIngot, bronzeNugget;
//    public static Item tinIngot, tinNugget;

    public static Item BRASS_HELMET, BRASS_CHESTPLATE, BRASS_LEGGINGS, BRASS_BOOTS;
    public static Item STEEL_HELMET, STEEL_CHESTPLATE, STEEL_LEGGINGS, STEEL_BOOTS;
    public static Item COPPER_HELMET, COPPER_CHESTPLATE, COPPER_LEGGINGS, COPPER_BOOTS;
//    public static Item BRONZE_HELMET, BRONZE_CHESTPLATE, BRONZE_LEGGINGS, BRONZE_BOOTS;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        CreativeTabs tab = NCreativeTabs.ITEM_EXPANSION_TAB;

        brassBlock = new BlockOverworldBase(Material.IRON, "brass_block", false);
        steelBlock = new BlockOverworldBase(Material.IRON, "steel_block", false);
        copperBlock = new BlockOverworldBase(Material.IRON, "copper_block", false);
//        bronzeBlock = new BlockOverworldBase(Material.IRON, "bronze_block", false);

        brassOre = new BlockNeutroniaOre("brass_ore").setIngot(brassIngot);
        steelOre = new BlockNeutroniaOre("steel_ore").setIngot(steelIngot);
        copperOre = new BlockNeutroniaOre("copper_ore").setIngot(copperIngot);
//        bronzeOre = new BlockNeutroniaOre("bronze_ore").setIngot(bronzeIngot);
//        tinOre = new BlockNeutroniaOre("tin_ore").setIngot(tinIngot);
        zincOre = new BlockNeutroniaOre("zinc_ore").setIngot(zincChunk);

        BRASS_AXE = new BaseAxe("brass_axe", BRASS);
        BRASS_PICKAXE = new BasePickaxe("brass_pickaxe", BRASS);
        BRASS_SHOVEL = new BaseShovel("brass_shovel", BRASS);
        BRASS_SWORD = new BaseSword("brass_sword", BRASS);

        STEEL_AXE = new BaseAxe("steel_axe", STEEL);
        STEEL_PICKAXE = new BasePickaxe("steel_pickaxe", STEEL);
        STEEL_SHOVEL = new BaseShovel("steel_shovel", STEEL);
        STEEL_SWORD = new BaseSword("steel_sword", STEEL);

        COPPER_AXE = new BaseAxe("copper_axe", COPPER);
        COPPER_PICKAXE = new BasePickaxe("copper_pickaxe", COPPER);
        COPPER_SHOVEL = new BaseShovel("copper_shovel", COPPER);
        COPPER_SWORD = new BaseSword("copper_sword", COPPER);

//        BRONZE_AXE = new BaseAxe("bronze_axe", BRONZE);
//        BRONZE_PICKAXE = new BasePickaxe("bronze_pickaxe", BRONZE);
//        BRONZE_SHOVEL = new BaseShovel("bronze_shovel", BRONZE);
//        BRONZE_SWORD = new BaseSword("bronze_sword", BRONZE);

        BRASS_HELMET = new ItemArmorBase("brass", BRASS_ARMOR, 1, EntityEquipmentSlot.HEAD);
        BRASS_CHESTPLATE = new ItemArmorBase("brass", BRASS_ARMOR, 1, EntityEquipmentSlot.CHEST);
        BRASS_LEGGINGS = new ItemArmorBase("brass", BRASS_ARMOR, 1, EntityEquipmentSlot.LEGS);
        BRASS_BOOTS = new ItemArmorBase("brass", BRASS_ARMOR, 1, EntityEquipmentSlot.FEET);

        STEEL_HELMET = new ItemArmorBase("steel", STEEL_ARMOR, 1, EntityEquipmentSlot.HEAD);
        STEEL_CHESTPLATE = new ItemArmorBase("steel", STEEL_ARMOR, 1, EntityEquipmentSlot.CHEST);
        STEEL_LEGGINGS = new ItemArmorBase("steel", STEEL_ARMOR, 1, EntityEquipmentSlot.LEGS);
        STEEL_BOOTS = new ItemArmorBase("steel", STEEL_ARMOR, 1, EntityEquipmentSlot.FEET);

        COPPER_HELMET = new ItemArmorBase("copper", COPPER_ARMOR, 1, EntityEquipmentSlot.HEAD);
        COPPER_CHESTPLATE = new ItemArmorBase("copper", COPPER_ARMOR, 1, EntityEquipmentSlot.CHEST);
        COPPER_LEGGINGS = new ItemArmorBase("copper", COPPER_ARMOR, 1, EntityEquipmentSlot.LEGS);
        COPPER_BOOTS = new ItemArmorBase("copper", COPPER_ARMOR, 1, EntityEquipmentSlot.FEET);

//        BRONZE_HELMET = new ItemArmorBase("bronze", BRONZE_ARMOR, 1, EntityEquipmentSlot.HEAD);
//        BRONZE_CHESTPLATE = new ItemArmorBase("bronze", BRONZE_ARMOR, 1, EntityEquipmentSlot.CHEST);
//        BRONZE_LEGGINGS = new ItemArmorBase("bronze", BRONZE_ARMOR, 1, EntityEquipmentSlot.LEGS);
//        BRONZE_BOOTS = new ItemArmorBase("bronze", BRONZE_ARMOR, 1, EntityEquipmentSlot.FEET);

        brassIngot = new ItemBase("brass_ingot", tab);
        brassNugget = new ItemBase("brass_nugget", tab);

        copperIngot = new ItemBase("copper_ingot", tab);
        copperNugget = new ItemBase("copper_nugget", tab);

        steelIngot = new ItemBase("steel_ingot", tab);
        steelNugget = new ItemBase("steel_nugget", tab);

        zincChunk = new ItemBase("zinc_chunk", tab);

//        tinIngot = new ItemBase("tin_ingot", tab);
//        tinNugget = new ItemBase("tin_nugget", tab);

//        bronzeIngot = new ItemBase("bronze_ingot", tab);
//        bronzeNugget = new ItemBase("bronze_nugget", tab);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
