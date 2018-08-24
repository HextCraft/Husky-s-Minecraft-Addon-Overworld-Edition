package net.hdt.neutronia.blocks.overworld;

import net.hdt.huskylib2.interf.IBlockColorProvider;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.blocks.base.BlockModColoredSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOverworldColoredSlab extends BlockModColoredSlab implements INeutroniaBlock, IBlockColorProvider {

    public BlockOverworldColoredSlab(String name, EnumDyeColor color, Material materialIn, boolean doubleSlab) {
        super(color.getName() + "_" + name + "_slab", color, materialIn, doubleSlab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (blockState.getMaterial() == Material.GLASS) {
            if (this.isDouble()) {
                return this.originalShouldSideBeRendered(blockState, blockAccess, pos, side);
            } else
                return side == EnumFacing.UP || side == EnumFacing.DOWN || super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }

        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean originalShouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        AxisAlignedBB axisalignedbb = blockState.getBoundingBox(blockAccess, pos);

        switch (side) {
            case DOWN:

                if (axisalignedbb.minY > 0.0D) {
                    return true;
                }

                break;
            case UP:

                if (axisalignedbb.maxY < 1.0D) {
                    return true;
                }

                break;
            case NORTH:

                if (axisalignedbb.minZ > 0.0D) {
                    return true;
                }

                break;
            case SOUTH:

                if (axisalignedbb.maxZ < 1.0D) {
                    return true;
                }

                break;
            case WEST:

                if (axisalignedbb.minX > 0.0D) {
                    return true;
                }

                break;
            case EAST:

                if (axisalignedbb.maxX < 1.0D) {
                    return true;
                }
        }

        IBlockState sideBlockState = blockAccess.getBlockState(pos.offset(side));

        Material material = sideBlockState.getMaterial();

        // Glass and other transparent materials force this side to be transparent.
        if (material == Material.GLASS) {
            if (!material.isOpaque() && material != Material.AIR) {
                return false;
            }
        }

        return !sideBlockState.doesSideBlockRendering(blockAccess, pos.offset(side), side.getOpposite());
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

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

}
