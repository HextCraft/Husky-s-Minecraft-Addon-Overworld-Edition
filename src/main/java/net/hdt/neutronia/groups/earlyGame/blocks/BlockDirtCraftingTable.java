package net.hdt.neutronia.groups.earlyGame.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.util.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDirtCraftingTable extends BlockMod implements INeutroniaBlock {

    public BlockDirtCraftingTable() {
        super("crafting_table_dirt", Material.GROUND);
        setCreativeTab(Neutronia.NEUTRONIA_MAIN);
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