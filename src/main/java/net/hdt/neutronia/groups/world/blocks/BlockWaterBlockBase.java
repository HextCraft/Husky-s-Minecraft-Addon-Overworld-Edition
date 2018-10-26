package net.hdt.neutronia.groups.world.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWaterBlockBase extends BlockMod implements INeutroniaBlock {

    public BlockWaterBlockBase(String name) {
        super(name, Material.PLANTS);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        switch (face) {
            case DOWN:
                return false;
            case UP:
                return isWater(world, pos.add(0, 1, 0));
            case NORTH:
                return isWater(world, pos.add(0, 0, -1));
            case SOUTH:
                return isWater(world, pos.add(0, 0, 1));
            case EAST:
                return isWater(world, pos.add(1, 0, 0));
            case WEST:
                return isWater(world, pos.add(-1, 0, 0));
        }
        return false;
    }

    private boolean isWater(IBlockAccess world, BlockPos pos) {
        return world.getBlockState(pos).getMaterial().isLiquid();
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

}
