package net.hdt.neutronia.blocks.overworld;

import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.blocks.base.BlockModColoredStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOverworldColoredStair extends BlockModColoredStairs implements INeutroniaBlock {

    public BlockOverworldColoredStair(String name, IBlockState state, EnumDyeColor color, CreativeTabs tab) {
        super(color.getName() + "_" + name + "_stairs", state, color);
        setCreativeTab(tab);
    }

    @Override
    public String getModNamespace() {
        return Reference.MOD_ID;
    }

    @Override
    public String getPrefix() {
        return Reference.MOD_ID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    /**
     * Check if the face of a block should block rendering.
     * <p>
     * Faces which are fully opaque should return true, faces with transparency
     * or faces which do not span the full size of the block should return false.
     *
     * @param state The current block state
     * @param world The current world
     * @param pos   Block position in world
     * @param face  The side to check
     * @return True if the block is opaque on the specified side.
     */
    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        BlockPos offSetPos = pos.offset(face);
        IBlockState offSetState = world.getBlockState(offSetPos);
        Material offSetMaterial = offSetState.getMaterial();
        return !offSetMaterial.isOpaque();
    }

}