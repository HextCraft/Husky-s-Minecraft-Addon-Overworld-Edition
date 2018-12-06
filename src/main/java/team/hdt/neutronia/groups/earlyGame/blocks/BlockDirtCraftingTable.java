package team.hdt.neutronia.groups.earlyGame.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia.base.Neutronia;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;
import team.hdt.neutronia.base.util.Reference;

public class BlockDirtCraftingTable extends BlockMod implements INeutroniaBlock {

    public BlockDirtCraftingTable() {
        super("crafting_table_dirt", Material.GROUND);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.isSneaking()) {
            playerIn.openGui(Neutronia.instance, Reference.GUI_DIRT_CRAFTING_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}