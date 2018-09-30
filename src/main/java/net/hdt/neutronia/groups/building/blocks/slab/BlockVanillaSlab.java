package net.hdt.neutronia.groups.building.blocks.slab;

import net.hdt.neutronia.base.blocks.BlockNeutroniaSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVanillaSlab extends BlockNeutroniaSlab {

    public BlockVanillaSlab(String name, IBlockState state, boolean doubleSlab) {
        super(name, state.getMaterial(), doubleSlab);

        setHardness(state.getBlockHardness(null, new BlockPos(0, 0, 0)));
        setResistance(state.getBlock().getExplosionResistance(null) * 5F / 3F);
        setSoundType(state.getBlock().getSoundType());
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

}
