package team.hdt.neutronia.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.enchantments.EnchantmentChanneling;
import team.hdt.neutronia.enchantments.EnchantmentImpaling;
import team.hdt.neutronia.enchantments.EnchantmentLoyalty;
import team.hdt.neutronia.enchantments.EnchantmentRiptide;

import static team.hdt.neutronia.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class NEnchantments {

    public static final Enchantment LOYALTY;
    public static final Enchantment IMPALING;
    public static final Enchantment RIPTIDE;
    public static final Enchantment CHANNELING;

    static {
        LOYALTY = new EnchantmentLoyalty(Enchantment.Rarity.UNCOMMON, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "loyalty")).setName("Loyalty");
        IMPALING = new EnchantmentImpaling(Enchantment.Rarity.RARE, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "impaling")).setName("Impaling");
        RIPTIDE = new EnchantmentRiptide(Enchantment.Rarity.RARE, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "riptide")).setName("Riptide");
        CHANNELING = new EnchantmentChanneling(Enchantment.Rarity.VERY_RARE, EntityEquipmentSlot.MAINHAND).setRegistryName(new ResourceLocation(MOD_ID, "channeling")).setName("Channeling");
    }

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(LOYALTY, IMPALING, RIPTIDE, CHANNELING);
    }

}
