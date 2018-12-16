package team.hdt.neutronia_legacy.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;
import team.hdt.neutronia_legacy.groups.decoration.features.ChiseledBlocks;

public class BlockEndStoneChiseled extends BlockMod implements INeutroniaBlock {

    private boolean filled;

    public BlockEndStoneChiseled(String name, boolean filled) {
        super(name, Material.ROCK);
        this.filled = filled;
        setCreativeTab(filled ? CreativeTabs.SEARCH : CreativeTabs.DECORATIONS);
        setHardness(1.5F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Item heldItem = playerIn.getHeldItem(hand).getItem();

        if (heldItem == Items.CHORUS_FRUIT_POPPED && !this.filled) {
            worldIn.setBlockState(pos, ChiseledBlocks.chiseledEndStoneBrickFilled.getDefaultState(), 2);
            playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            return true;
        }
        if (playerIn.getHeldItem(hand).isEmpty() && this.filled) {
            worldIn.setBlockState(pos, ChiseledBlocks.chiseledEndStoneBrick.getDefaultState(), 2);
            playerIn.setHeldItem(hand, new ItemStack(Items.CHORUS_FRUIT_POPPED));
            return false;
        }
        return false;
    }

}