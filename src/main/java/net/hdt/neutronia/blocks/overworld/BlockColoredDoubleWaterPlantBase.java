package net.hdt.neutronia.blocks.overworld;

import net.hdt.neutronia.blocks.base.BlockModBush;
import net.hdt.neutronia.init.NCreativeTabs;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

import static net.minecraft.block.BlockLiquid.LEVEL;

public class BlockColoredDoubleWaterPlantBase extends BlockModBush {

    public static final PropertyEnum<BlockColoredDoubleWaterPlantBase.EnumBlockHalf> HALF = PropertyEnum.create("half", BlockColoredDoubleWaterPlantBase.EnumBlockHalf.class);
    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;

    public BlockColoredDoubleWaterPlantBase(EnumCoralColor coralColor, String name) {
        super(coralColor + "_" + name, Material.WATER);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockColoredDoubleWaterPlantBase.EnumBlockHalf.LOWER).withProperty(FACING, EnumFacing.NORTH).withProperty(LEVEL, 15));
        setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        switch (face) {
            case DOWN:
                return false;
            case UP:
                return isWater(world, pos.add(0, 1, 0));
            case NORTH:
                return isWater(world, pos.add(0, 0, -1));
            case SOUTH:
                return isWater(world, pos.add(0, 0, 1));
            case EAST:
                return isWater(world, pos.add(1, 0, 0));
            case WEST:
                return isWater(world, pos.add(-1, 0, 0));
        }
        return false;
    }

    private boolean isWater(IBlockAccess world, BlockPos pos) {
        return world.getBlockState(pos).getMaterial() == Material.WATER;
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return true;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[]{LEVEL};
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            boolean flag = state.getValue(HALF) == BlockColoredDoubleWaterPlantBase.EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = (flag ? this : worldIn.getBlockState(blockpos).getBlock());
            Block block1 = (flag ? worldIn.getBlockState(blockpos1).getBlock() : this);

            if (!flag) this.dropBlockAsItem(worldIn, pos, state, 0); //Forge move above the setting to air.

            if (block == this) {
                worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            }

            if (block1 == this) {
                worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    public void placeAt(World worldIn, BlockPos lowerPos, int flags) {
        worldIn.setBlockState(lowerPos, this.getDefaultState().withProperty(HALF, BlockColoredDoubleWaterPlantBase.EnumBlockHalf.LOWER), flags);
        worldIn.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(HALF, BlockColoredDoubleWaterPlantBase.EnumBlockHalf.UPPER), flags);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BlockColoredDoubleWaterPlantBase.EnumBlockHalf.UPPER), 2);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (state.getValue(HALF) == BlockColoredDoubleWaterPlantBase.EnumBlockHalf.UPPER) {
            if (worldIn.getBlockState(pos.down()).getBlock() == this) {
                if (player.capabilities.isCreativeMode) {
                    worldIn.setBlockToAir(pos.down());
                } else {
                    IBlockState iblockstate = worldIn.getBlockState(pos.down());

                    if (worldIn.isRemote) {
                        worldIn.setBlockToAir(pos.down());
                    } else if (!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == Items.SHEARS) {
                        this.onHarvest(worldIn, pos, iblockstate, player);
                        worldIn.setBlockToAir(pos.down());
                    } else {
                        worldIn.destroyBlock(pos.down(), true);
                    }
                }
            }
        } else if (worldIn.getBlockState(pos.up()).getBlock() == this) {
            worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    private boolean onHarvest(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.addStat(Objects.requireNonNull(StatList.getBlockStats(this)));
        return true;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this, 1);
    }

    public IBlockState getStateFromMeta(int meta) {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, BlockColoredDoubleWaterPlantBase.EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(HALF, BlockColoredDoubleWaterPlantBase.EnumBlockHalf.LOWER);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF) == BlockColoredDoubleWaterPlantBase.EnumBlockHalf.UPPER ? 8 : (state.getValue(FACING)).getHorizontalIndex() & state.getValue(LEVEL);
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HALF, FACING, LEVEL);
    }

    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        Block block = worldIn.getBlockState(new BlockPos(pos.add(0, -1, 0))).getBlock();
        return (block == Blocks.DIRT || block == Blocks.SAND || block == Blocks.SPONGE || block == Blocks.STONE || block == Blocks.CLAY || block == Blocks.GRAVEL || block == Blocks.GRASS) && worldIn.getBlockState(new BlockPos(pos.add(0, 2, 0))).getBlock() != Blocks.AIR;
    }

    public boolean canBlockStay(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
        Block block = worldIn.getBlockState(new BlockPos(pos.add(0, -1, 0))).getBlock();
        return (block == Blocks.DIRT || block == Blocks.SAND || block == Blocks.SPONGE || block == Blocks.STONE || block == Blocks.CLAY || block == Blocks.GRAVEL || block == Blocks.GRASS) && worldIn.getBlockState(new BlockPos(pos.add(0, 2, 0))).getBlock() != Blocks.AIR;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        Block ground = worldIn.getBlockState(pos.add(0, -1, 0)).getBlock();
        return ground == Blocks.SAND || ground == Blocks.GRASS || ground == Blocks.DIRT || ground == Blocks.GRAVEL && worldIn.getBlockState(pos.add(0, 2, 0)).getBlock() != Blocks.AIR;
    }

    public boolean isReplaceable(IBlockAccess access, BlockPos pos) {
        return access.getBlockState(pos).getBlock() == Blocks.WATER && canBlockStay(access, pos, getDefaultState()) && access.getBlockState(pos.add(0, 1, 0)).getBlock() != Blocks.AIR;
    }

    protected boolean canSustainBush(IBlockState state) {
        Block ground = state.getBlock();
        return ground == Blocks.SAND || ground == Blocks.GRASS || ground == Blocks.DIRT || ground == Blocks.GRAVEL;
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        checkAndDropBlock(worldIn, pos, state);
        super.onBlockAdded(worldIn, pos, state);
    }

    public boolean isReplaceable(World worldIn, BlockPos pos) {
        return false;
    }

    public enum EnumBlockHalf implements IStringSerializable {
        UPPER,
        LOWER;

        public String toString() {
            return this.getName();
        }

        public String getName() {
            return this == UPPER ? "upper" : "lower";
        }
    }
}