package net.thegaminghuskymc.mcaddon.init;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thegaminghuskymc.mcaddon.Main;
import net.thegaminghuskymc.mcaddon.items.*;
import net.thegaminghuskymc.mcaddon.items.base.tools.*;

import static net.thegaminghuskymc.mcaddon.util.Reference.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class MCAddonItems
{
    public static final Item.ToolMaterial CAXE = EnumHelper.addToolMaterial("caxe", 3, 350, 7.0F, 4.0F, 12);
    public static final Item.ToolMaterial CPICK = EnumHelper.addToolMaterial("cpick", 3, 350, 7.0F, 4.0F, 12);
    public static final Item.ToolMaterial CHOE = EnumHelper.addToolMaterial("choe", 3, 325, 7.0F, 4.0F, 12);
    public static final Item.ToolMaterial CSHOVEL = EnumHelper.addToolMaterial("cshovel", 3, 325, 7.0F, 4.0F, 12);
    public static final Item.ToolMaterial CSWORD = EnumHelper.addToolMaterial("csword", 3, 325, 7.0F, 8.0F, 12);
    public static final ItemArmor.ArmorMaterial TEST = EnumHelper.addArmorMaterial("test", "test", 100, new int[] {10, 10, 10, 10}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3F);

    public static final Item ANCIENT_SWORD;
    public static final Item BANDAGE;
    public static final Item witherBone, witherBonemeal;
    public static final Item driedKelp;
    public static final Item STINGER;
    public static final Item CHITIN;

    public static final Item AXE_CHITIN;
    public static final Item PICKAXE_CHITIN;
    public static final Item HOE_CHITIN;
    public static final Item SHOVEL_CHITIN;
    public static final Item SWORD_CHITIN;

    public static final ArmorTM healmet;
    public static final ArmorTM chestplate;
    public static final ArmorTM leggings;
    public static final ArmorTM boots;

    static {
        ANCIENT_SWORD = new ItemAncientSword();
        BANDAGE = new ItemBandage();
        witherBone = new ItemBase("wither_bone", Main.NETHER_EXPANSION_TAB);
        witherBonemeal = new ItemBase("wither_bonemeal", Main.NETHER_EXPANSION_TAB);
        driedKelp = new ItemFood("dried_kelp", Main.OVERWORLD_EXPANSION_TAB);
        STINGER = new ItemBase("stinger", Main.OVERWORLD_EXPANSION_TAB);
        CHITIN = new ItemBase("chitin", Main.OVERWORLD_EXPANSION_TAB);

        AXE_CHITIN = new BaseAxe("axe_chitin", CAXE);
        PICKAXE_CHITIN = new BasePickaxe("pickaxe_chitin", CPICK);
        HOE_CHITIN = new BaseHoe("hoe_chitin", CHOE);
        SHOVEL_CHITIN = new BaseShovel("shovel_chitin", CSHOVEL);
        SWORD_CHITIN = new BaseSword("sword_chitin", CSWORD);
        healmet = new ArmorTM("test_helmet", TEST, 0, EntityEquipmentSlot.HEAD);
        chestplate = new ArmorTM("test_chestplate", TEST, 0, EntityEquipmentSlot.CHEST);
        leggings = new ArmorTM("test_leggings", TEST, 0, EntityEquipmentSlot.LEGS);
        boots = new ArmorTM("test_boots", TEST, 0, EntityEquipmentSlot.FEET);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

    }

}
