package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.neutronia.base.blocks.BlockNeutroniaWall;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLogWall extends BlockNeutroniaWall implements INeutroniaBlock {

    private IBlockState logWall;
    private Item barkItem;

    public BlockLogWall(IBlockState state, String name, IBlockState logWall, Item barkItem) {
        super(name, state);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.FALSE).withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
        setCreativeTab(CreativeTabs.DECORATIONS);

        this.logWall = logWall;
        this.barkItem = barkItem;
    }

    public BlockLogWall(String name, IBlockState logWall, Item barkItem) {
        super(name, Blocks.PLANKS.getDefaultState());
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.FALSE).withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
        setCreativeTab(CreativeTabs.DECORATIONS);

        this.logWall = logWall;
        this.barkItem = barkItem;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Item heldItem = playerIn.getHeldItem(hand).getItem();

        if (worldIn.isRemote) return (heldItem instanceof ItemAxe);

        if (heldItem instanceof ItemAxe) {
            worldIn.setBlockState(pos, logWall.getBlock().getDefaultState(), 2);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(barkItem, 4));
            return true;
        }
        return false;
    }

}