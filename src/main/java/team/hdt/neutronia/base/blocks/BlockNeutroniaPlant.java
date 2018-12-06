package team.hdt.neutronia.base.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import team.hdt.neutronia.blocks.base.BlockModBush;
import team.hdt.neutronia.groups.dimensions.features.AlienOverworld;

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

    public BlockNeutroniaPlant(String name) {
        this(name, PlantBehaviorType.FLOWER);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDS;
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == AlienOverworld.ALIEN_DIRT || state.getBlock() == AlienOverworld.ALIEN_GRASS;
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