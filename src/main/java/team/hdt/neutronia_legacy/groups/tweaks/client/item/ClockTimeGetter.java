package team.hdt.neutronia_legacy.groups.tweaks.client.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.groups.tweaks.util.ItemNBTHelper;

import javax.annotation.Nullable;

public class ClockTimeGetter implements IItemPropertyGetter {

    private static final String TAG_CALCULATED = "neutronia_legacy:clock_calculated";

    double rotation;
    double rota;
    long lastUpdateTick;

    public static void tickClock(EntityPlayer player, ItemStack stack) {
        boolean calculated = isCalculated(stack);
        boolean nether = player.world.provider.getDimensionType() == DimensionType.NETHER;
        if (!calculated)
            ItemNBTHelper.setBoolean(stack, TAG_CALCULATED, true);
    }

    static boolean isCalculated(ItemStack stack) {
        return stack.hasTagCompound() && ItemNBTHelper.getBoolean(stack, TAG_CALCULATED, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
        if (!isCalculated(stack))
            return 0F;

        boolean carried = entityIn != null;
        Entity entity = carried ? entityIn : stack.getItemFrame();

        if (worldIn == null && entity != null)
            worldIn = entity.world;

        if (worldIn == null)
            return 0F;
        else {
            double angle;

            if (worldIn.provider.isSurfaceWorld())
                angle = (double) worldIn.getCelestialAngle(1.0F);
            else
                angle = Math.random();

            angle = wobble(worldIn, angle);
            return (float) angle;
        }
    }

    @SideOnly(Side.CLIENT)
    private double wobble(World world, double time) {
        if (world.getTotalWorldTime() != lastUpdateTick) {
            lastUpdateTick = world.getTotalWorldTime();
            double d0 = time - rotation;
            d0 = MathHelper.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
            rota += d0 * 0.1D;
            rota *= 0.9D;
            rotation = MathHelper.positiveModulo(rotation + rota, 1.0D);
        }

        return rotation;
    }

}
