package team.hdt.neutronia.groups.world.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.huskylib.block.BlockFacing;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;
import team.hdt.neutronia.groups.world.blocks.tileEntity.TileEntityJigsaw;

public class BlockJigsaw extends BlockFacing implements INeutroniaBlock, ITileEntityProvider {

    public BlockJigsaw() {
        super("jigsaw", Material.ROCK);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityJigsaw();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileEntityJigsaw && playerIn.isCreative()) {
            return true;
        } else {
            return false;
        }
    }
}