package net.hdt.neutronia.init;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.items.*;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NItems {

    public static final ItemArmor.ArmorMaterial ACACIA_ARMOR = EnumHelper.addArmorMaterial("acacia", "acacia", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static final ItemArmor.ArmorMaterial DARK_OAK_ARMOR = EnumHelper.addArmorMaterial("dark_oak", "big_oak", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static final ItemArmor.ArmorMaterial BIRCH_ARMOR = EnumHelper.addArmorMaterial("birch", "birch", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static final ItemArmor.ArmorMaterial JUNGLE_ARMOR = EnumHelper.addArmorMaterial("jungle", "jungle", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static final ItemArmor.ArmorMaterial OAK_ARMOR = EnumHelper.addArmorMaterial("oak", "oak", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static final ItemArmor.ArmorMaterial SPRUCE_ARMOR = EnumHelper.addArmorMaterial("spruce", "spruce", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static final Item ancientSword, bandage/*, witherBone, witherBonemeal,
            phantomMembrane, nautilusShell, heartOfTheSea, heartOfTheNether, heartOfTheEnd, scute*/;

    /*public static final Item ACACIA_HELMET, ACACIA_CHESTPLATE, ACACIA_LEGGINGS, ACACIA_BOOTS;
    public static final Item BIRCH_HELMET, BIRCH_CHESTPLATE, BIRCH_LEGGINGS, BIRCH_BOOTS;
    public static final Item DARK_OAK_HELMET, DARK_OAK_CHESTPLATE, DARK_OAK_LEGGINGS, DARK_OAK_BOOTS;
    public static final Item JUNGLE_HELMET, JUNGLE_CHESTPLATE, JUNGLE_LEGGINGS, JUNGLE_BOOTS;
    public static final Item OAK_HELMET, OAK_CHESTPLATE, OAK_LEGGINGS, OAK_BOOTS;
    public static final Item SPRUCE_HELMET, SPRUCE_CHESTPLATE, SPRUCE_LEGGINGS, SPRUCE_BOOTS;*/

    public static final Item woodSpear;
    public static final Item stoneSpear;
    public static final Item ironSpear;
    public static final Item goldSpear;
    public static final Item diamondSpear;

    public static final Item trident;
    public static final Item anchor;
    public static final Item chisel;
    public static final Item logStripper;

    public static Item[] barkItem = new Item[6];
    public static Item[] logShields = new Item[6];
    public static Item[] planksShields = new Item[6];

    static {
        ancientSword = new ItemAncientSword();
        bandage = new ItemBandage();
//        witherBone = new ItemNeutroniaBase("wither_bone");
//        witherBonemeal = new ItemNeutroniaBase("wither_bonemeal");
//        phantomMembrane = new ItemPhantomMembrane();
//        nautilusShell = new ItemNeutroniaBase("nautilus_shell");
//        heartOfTheSea = new ItemNeutroniaBase("heart_of_the_sea");
//        heartOfTheNether = new ItemNeutroniaBase("heart_of_the_nether");
//        heartOfTheEnd = new ItemNeutroniaBase("heart_of_the_end");
//        scute = new ItemNeutroniaBase("scute");
        chisel = new ItemNeutroniaBase("chisel");
        logStripper = new ItemNeutroniaBase("log_stripper");
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(chisel, 1), "I", "S", 'I', Items.IRON_INGOT, 'S', Items.STICK);
        RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(logStripper, 1), "II ", " I ", " S ", 'I', Items.IRON_INGOT, 'S', Items.STICK);

        /*ACACIA_HELMET = new ItemArmorBase("acacia", ACACIA_ARMOR, 1, EntityEquipmentSlot.HEAD);
        ACACIA_CHESTPLATE = new ItemArmorBase("acacia", ACACIA_ARMOR, 1, EntityEquipmentSlot.CHEST);
        ACACIA_LEGGINGS = new ItemArmorBase("acacia", ACACIA_ARMOR, 1, EntityEquipmentSlot.LEGS);
        ACACIA_BOOTS = new ItemArmorBase("acacia", ACACIA_ARMOR, 1, EntityEquipmentSlot.FEET);

        BIRCH_HELMET = new ItemArmorBase("birch", BIRCH_ARMOR, 1, EntityEquipmentSlot.HEAD);
        BIRCH_CHESTPLATE = new ItemArmorBase("birch", BIRCH_ARMOR, 1, EntityEquipmentSlot.CHEST);
        BIRCH_LEGGINGS = new ItemArmorBase("birch", BIRCH_ARMOR, 1, EntityEquipmentSlot.LEGS);
        BIRCH_BOOTS = new ItemArmorBase("birch", BIRCH_ARMOR, 1, EntityEquipmentSlot.FEET);

        DARK_OAK_HELMET = new ItemArmorBase("dark_oak", DARK_OAK_ARMOR, 1, EntityEquipmentSlot.HEAD);
        DARK_OAK_CHESTPLATE = new ItemArmorBase("dark_oak", DARK_OAK_ARMOR, 1, EntityEquipmentSlot.CHEST);
        DARK_OAK_LEGGINGS = new ItemArmorBase("dark_oak", DARK_OAK_ARMOR, 1, EntityEquipmentSlot.LEGS);
        DARK_OAK_BOOTS = new ItemArmorBase("dark_oak", DARK_OAK_ARMOR, 1, EntityEquipmentSlot.FEET);

        JUNGLE_HELMET = new ItemArmorBase("jungle", JUNGLE_ARMOR, 1, EntityEquipmentSlot.HEAD);
        JUNGLE_CHESTPLATE = new ItemArmorBase("jungle", JUNGLE_ARMOR, 1, EntityEquipmentSlot.CHEST);
        JUNGLE_LEGGINGS = new ItemArmorBase("jungle", JUNGLE_ARMOR, 1, EntityEquipmentSlot.LEGS);
        JUNGLE_BOOTS = new ItemArmorBase("jungle", JUNGLE_ARMOR, 1, EntityEquipmentSlot.FEET);

        OAK_HELMET = new ItemArmorBase("oak", OAK_ARMOR, 1, EntityEquipmentSlot.HEAD);
        OAK_CHESTPLATE = new ItemArmorBase("oak", OAK_ARMOR, 1, EntityEquipmentSlot.CHEST);
        OAK_LEGGINGS = new ItemArmorBase("oak", OAK_ARMOR, 1, EntityEquipmentSlot.LEGS);
        OAK_BOOTS = new ItemArmorBase("oak", OAK_ARMOR, 1, EntityEquipmentSlot.FEET);

        SPRUCE_HELMET = new ItemArmorBase("spruce", SPRUCE_ARMOR, 1, EntityEquipmentSlot.HEAD);
        SPRUCE_CHESTPLATE = new ItemArmorBase("spruce", SPRUCE_ARMOR, 1, EntityEquipmentSlot.CHEST);
        SPRUCE_LEGGINGS = new ItemArmorBase("spruce", SPRUCE_ARMOR, 1, EntityEquipmentSlot.LEGS);
        SPRUCE_BOOTS = new ItemArmorBase("spruce", SPRUCE_ARMOR, 1, EntityEquipmentSlot.FEET);*/

        woodSpear = new ItemSpear("wood_spear", Item.ToolMaterial.WOOD).setCreativeTab(null);
        stoneSpear = new ItemSpear("stone_spear", Item.ToolMaterial.STONE).setCreativeTab(null);
        ironSpear = new ItemSpear("iron_spear", Item.ToolMaterial.IRON).setCreativeTab(null);
        goldSpear = new ItemSpear("gold_spear", Item.ToolMaterial.GOLD).setCreativeTab(null);
        diamondSpear = new ItemSpear("diamond_spear", Item.ToolMaterial.DIAMOND).setCreativeTab(null);
        trident = new ItemTrident().setCreativeTab(null);
        anchor = new ItemSpear("anchor", Item.ToolMaterial.IRON).setCreativeTab(null);

        for (BlockPlanks.EnumType woodTypes : BlockPlanks.EnumType.values()) {
//            logShields[woodTypes.getMetadata()] = new ItemShieldBase(String.format("shield_log_%s", woodTypes.getName()));
//            planksShields[woodTypes.getMetadata()] = new ItemShieldBase(String.format("shield_planks_%s", woodTypes.getName()));
            barkItem[woodTypes.getMetadata()] = new ItemNeutroniaBase(String.format("%s_bark_item", woodTypes.getName()));
        }

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

    }

}
