package team.abnormal.neutronia.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import team.abnormal.abnormalib.block.BlockMod;

public class BlockChiseled extends BlockMod implements INeutroniaBlock {

    public static final PropertyBool FILLED = PropertyBool.create("filled");
    protected Item chiselItem;

    public BlockChiseled(Material material, String name, float hardness, float resistance, SoundType soundType, Item chiselItem) {
        super(name, material);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(hardness);
        setResistance(resistance);
        setLightLevel(0.0F);
        setSoundType(soundType);
        setDefaultState(this.getDefaultState().withProperty(FILLED, false));
        this.chiselItem = chiselItem;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Item heldItem = playerIn.getHeldItem(hand).getItem();

        if (heldItem == chiselItem && !state.getValue(FILLED)) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(FILLED, true));
            playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            return true;
        }
        if (state.getValue(FILLED)) {
            if (playerIn.getHeldItem(hand).isEmpty()) {
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(FILLED, false));
                playerIn.setHeldItem(hand, new ItemStack(chiselItem));
            }
            if (heldItem == chiselItem) {
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
