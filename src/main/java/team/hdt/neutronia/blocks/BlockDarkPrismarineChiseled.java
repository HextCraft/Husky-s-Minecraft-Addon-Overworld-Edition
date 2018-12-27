package team.hdt.neutronia.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;

public class BlockDarkPrismarineChiseled extends BlockNeutroniaBase {

    public static final PropertyBool FILLED = PropertyBool.create("filled");

    public BlockDarkPrismarineChiseled(String name) {
        super(Material.ROCK, name, CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItem(hand);
        EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(heldItem.getMetadata());

        if (heldItem.getItem() == Items.DYE && enumdyecolor == EnumDyeColor.BLACK && !state.getValue(FILLED)) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(FILLED, true));
            playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            return true;
        }
        if (state.getValue(FILLED)) {
            if (playerIn.getHeldItem(hand).isEmpty()) {
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(FILLED, false));
                playerIn.setHeldItem(hand, new ItemStack(Items.DYE));
            }
            if (heldItem.getItem() == Items.DYE && enumdyecolor == EnumDyeColor.BLACK) {
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(FILLED, false));
                playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() + 1);
            }
            return true;
        }
        return false;
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FILLED, false);
    }

    public int getMetaFromState(IBlockState state) {
        int i;

        if (state.getValue(FILLED)) {
            i = 1;
        } else {
            i = 0;
        }
        return i;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FILLED);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this.getDefaultState().getBlock());
    }
}
