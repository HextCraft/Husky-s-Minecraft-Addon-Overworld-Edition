package net.hdt.neutronia.blocks.overworld;

import net.hdt.huskylib2.block.BlockModSlab;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOverworldSlabBase extends BlockModSlab implements INeutroniaBlock {

    public BlockOverworldSlabBase(String name, Material material, boolean isDouble, CreativeTabs tab) {
        super(name + "_slab", material, isDouble);
        setCreativeTab(isDouble ? null : tab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (blockState.getMaterial() == Material.GLASS) {
            if (this.isDouble()) {
                return true;
            } else
                return side == EnumFacing.UP || side == EnumFacing.DOWN || super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }

        return true;
    }

    @Override
    public String getTranslationKey(int meta) {
        return getTranslationKey();
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
        return !isSideSolid(state, world, pos, face);
    }

}
