package team.hdt.neutronia_legacy.groups.craftingExtension.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.huskylib.block.BlockFacing;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockBlastFurnace extends BlockFacing implements INeutroniaBlock {

    public BlockBlastFurnace() {
        super("blast_furnace", Material.ROCK);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!playerIn.isSneaking()) {
            return true;
        }
        return false;
    }
}