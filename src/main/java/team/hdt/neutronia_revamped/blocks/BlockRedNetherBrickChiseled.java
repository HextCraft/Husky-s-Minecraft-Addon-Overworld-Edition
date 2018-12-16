package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.huskylib.block.BlockMod;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;
import team.hdt.neutronia_legacy.groups.decoration.features.ChiseledBlocks;

public class BlockRedNetherBrickChiseled extends BlockMod implements INeutroniaBlock {

    private boolean filled;

    public BlockRedNetherBrickChiseled(String name, boolean filled) {
        super(name, Material.ROCK);

        this.filled = filled;

        setCreativeTab(filled ? CreativeTabs.SEARCH : CreativeTabs.DECORATIONS);
        setHardness(2.0F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Item heldItem = playerIn.getHeldItem(hand).getItem();

        if (heldItem == Item.getItemFromBlock(Blocks.GLOWSTONE) && !this.filled) {
            worldIn.setBlockState(pos, ChiseledBlocks.chiseledRedNetherBrickFilled.getDefaultState(), 2);
            playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            return true;
        }
        if (playerIn.getHeldItem(hand).isEmpty() && this.filled) {
            worldIn.setBlockState(pos, ChiseledBlocks.chiseledRedNetherBrick.getDefaultState(), 2);
            playerIn.setHeldItem(hand, new ItemStack(Item.getItemFromBlock(Blocks.GLOWSTONE)));
            return false;
        }
        return false;
    }

}