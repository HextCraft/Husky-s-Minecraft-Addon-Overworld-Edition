package team.hdt.neutronia_revamped.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockNeutroniaPlant extends BlockModBush implements IShearable {
    private static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);

    private final PlantBehaviorType behaviorType;

    public BlockNeutroniaPlant(String name, PlantBehaviorType behaviorType) {
        super(name, behaviorType.getMaterial());
        this.behaviorType = behaviorType;
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    public boolean canPlantOnTop(IBlockState state, World worldIn, BlockPos pos) {
        Block var4 = state.getBlock();
        return var4 == Blocks.GRASS || var4 == Blocks.DIRT || var4 == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT) || var4 == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL) || var4 == Blocks.FARMLAND;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        BlockPos var4 = pos.down();
        return this.canPlantOnTop(worldIn.getBlockState(var4), worldIn, var4);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDS;
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
        return this.behaviorType.isShearable();
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        if (this.behaviorType.isShearable()) {
            return NonNullList.withSize(1, new ItemStack(this));
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
        return this.behaviorType.isReplacable();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this.behaviorType.isShearable() ? Items.AIR : super.getItemDropped(state, rand, fortune);
    }
}