package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import team.hdt.huskylib.block.BlockMod;

import javax.annotation.Nullable;

public class BlockChain extends BlockMod implements INeutroniaBlock {

    public BlockChain(String name) {
        super(name + "_chain", Material.IRON);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    public BlockChain(Material material, String name) {
        super(name, material);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}