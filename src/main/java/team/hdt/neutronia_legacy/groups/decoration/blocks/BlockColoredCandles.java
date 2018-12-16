package team.hdt.neutronia_legacy.groups.decoration.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaColored;
import team.hdt.neutronia_legacy.groups.decoration.features.ColoredCandles;

import java.util.Random;

public class BlockColoredCandles extends BlockNeutroniaColored {

    public EnumDyeColor color;

    public BlockColoredCandles(EnumDyeColor color) {
        super("candle", color);
        this.color = color;
        setCreativeTab(CreativeTabs.DECORATIONS);
        this.setLightLevel(1.0F);
        this.setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.55D;
        double d2 = (double) pos.getZ() + 0.5D;

        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ColoredCandles.coloredCandles[color.getMetadata()]);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ColoredCandles.coloredCandles[color.getMetadata()]);
    }

    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(ColoredCandles.coloredCandles[color.getMetadata()]);
    }

}