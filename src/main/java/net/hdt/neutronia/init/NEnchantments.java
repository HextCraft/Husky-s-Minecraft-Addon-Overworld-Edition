package net.hdt.neutronia.init;

import net.hdt.neutronia.enchantments.EnchantmentChanneling;
import net.hdt.neutronia.enchantments.EnchantmentImpaling;
import net.hdt.neutronia.enchantments.EnchantmentLoyalty;
import net.hdt.neutronia.enchantments.EnchantmentRiptide;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class NEnchantments {

    public static final Enchantment LOYALTY;
    public static final Enchantment IMPALING;
    public static final Enchantment RIPTIDE;
    public static final Enchantment CHANNELING;

    static {
        LOYALTY = new EnchantmentLoyalty(Enchantment.Rarity.UNCOMMON, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "loyalty"));
        IMPALING = new EnchantmentImpaling(Enchantment.Rarity.RARE, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "impaling"));
        RIPTIDE = new EnchantmentRiptide(Enchantment.Rarity.RARE, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "riptide"));
        CHANNELING = new EnchantmentChanneling(Enchantment.Rarity.VERY_RARE, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "channeling"));
    }

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(LOYALTY, IMPALING, RIPTIDE, CHANNELING);
    }

}
