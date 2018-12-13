package team.hdt.neutronia.groups.craftingExtension.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.huskylib.block.BlockFacing;
import team.hdt.neutronia.base.Neutronia;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;
import team.hdt.neutronia.groups.craftingExtension.features.NewStorageBlocks;
import team.hdt.neutronia.groups.craftingExtension.tileEntities.TileEntityBarrel;

public class BlockBarrel extends BlockFacing implements INeutroniaBlock {

    public BlockBarrel(BlockPlanks.EnumType type) {
        super(String.format("%s_barrel", type.getName()), Material.WOOD);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBarrel();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!playerIn.isSneaking()) {
            playerIn.openGui(Neutronia.instance, NewStorageBlocks.barrelGuiID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, facing.getOpposite());
    }
}