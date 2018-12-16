package team.hdt.neutronia_legacy.groups.tweaks.features;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.tweaks.util.HelperFunctions;

import java.util.Random;

public class MobDropBuffs extends Component {

    private boolean chickenFeathers, cowLeather, skeletonBones, squidSacs, enderPearls, enableHorseGlue;

    @Override
    public void setupConfig() {
        chickenFeathers = loadPropBool("Chicken Feathers", "", true);
        cowLeather = loadPropBool("Cow Leather", "", true);
        skeletonBones = loadPropBool("Skeleton Bones", "", true);
        squidSacs = loadPropBool("Squid Ink Sacs", "", true);
        enderPearls = loadPropBool("Enderman Ender Pearls", "", true);
        enableHorseGlue = loadPropBool("Enable \"glue\" drops", "Changes horse drops under \"hot\" conditions", true);
    }

    @SubscribeEvent
    public void registerTweak(LivingDropsEvent event) {
        Random random = new Random();

        if (event.getEntity() == null) {
            return;
        }

        if (event.getEntity() instanceof EntityChicken && chickenFeathers) {
            ItemStack dropStack = new ItemStack(Items.FEATHER, 1 + random.nextInt(4));
            event.getDrops().add(HelperFunctions.createItemEntity(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), dropStack));
        } else if (event.getEntity() instanceof EntityCow && cowLeather) {
            ItemStack dropStack = new ItemStack(Items.LEATHER, 1 + random.nextInt(3));
            event.getDrops().add(HelperFunctions.createItemEntity(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), dropStack));
        } else if (event.getEntity() instanceof EntitySkeleton && skeletonBones) {
            ItemStack dropStack = new ItemStack(Items.BONE, 1 + random.nextInt(2));
            event.getDrops().add(HelperFunctions.createItemEntity(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), dropStack));
        } else if (event.getEntity() instanceof EntitySquid && squidSacs) {
            ItemStack dropStack = new ItemStack(Items.DYE, 1 + random.nextInt(3));
            event.getDrops().add(HelperFunctions.createItemEntity(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), dropStack));
        } else if (event.getEntity() instanceof EntityEnderman && enderPearls) {
            ItemStack dropStack = new ItemStack(Items.ENDER_PEARL, 1 + random.nextInt(1));
            event.getDrops().add(HelperFunctions.createItemEntity(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), dropStack));
        } else if (event.getEntity() instanceof EntityHorse && enableHorseGlue && random.nextInt(4 - event.getLootingLevel()) == 0) {
            if (event.getSource().getTrueSource() instanceof EntityPlayer && EnchantmentHelper.getFireAspectModifier((EntityPlayer) event.getSource().getTrueSource()) > 0) {
                ItemStack dropStack = new ItemStack(Items.SLIME_BALL).setStackDisplayName(TextFormatting.RESET + "Glue Ball");
                event.getDrops().add(HelperFunctions.createItemEntity(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), dropStack));
            }
        }
    }
}