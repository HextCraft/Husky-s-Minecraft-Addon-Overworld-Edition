package net.hdt.neutronia.groups.building.blocks.slab;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.BlockNeutroniaSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVanillaSlab extends BlockNeutroniaSlab {

    public BlockVanillaSlab(String name, IBlockState state, boolean doubleSlab) {
        super(name, state.getMaterial(), doubleSlab);
        setCreativeTab(Neutronia.CREATIVE_TAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
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
