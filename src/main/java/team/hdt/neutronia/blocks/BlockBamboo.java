package team.hdt.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.init.NBlocks;
import team.hdt.neutronia.properties.BambooLeaves;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockBamboo extends BlockNeutroniaBase implements IGrowable {

    protected static final AxisAlignedBB field_9912 = new AxisAlignedBB(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    protected static final AxisAlignedBB field_9915 = new AxisAlignedBB(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    protected static final AxisAlignedBB field_9913 = new AxisAlignedBB(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
    public static final PropertyInteger AGE;
    public static final PropertyEnum<BambooLeaves> LEAVES;
    public static final PropertyInteger STAGE;

    public BlockBamboo() {
        super("bamboo", Material.CACTUS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0).withProperty(LEAVES, BambooLeaves.NONE).withProperty(STAGE, 0));
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        AxisAlignedBB var4 = state.getValue(LEAVES) == BambooLeaves.LARGE ? field_9915 : field_9912;
        Vec3d var5 = state.getOffset(source, pos);
        return var4.offset(var5.x, var5.y, var5.z);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        Vec3d var5 = blockState.getOffset(worldIn, pos);
        return field_9913.offset(var5.x, var5.y, var5.z);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState var3 = worldIn.getBlockState(pos.down());
        return var3 == NBlocks.BAMBOO || var3 == NBlocks.BAMBOO_SAPLING || var3 == Blocks.GRAVEL || var3.equals(Blocks.SAND) || var3 == Blocks.DIRT || var3.equals(Blocks.GRASS) || var3.equals(Blocks.MYCELIUM) || var3 == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT) || var3 == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
        return player.getHeldItemMainhand().getItem() instanceof ItemSword ? 1.0F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        IBlockState var3 = worldIn.getBlockState(pos.down());
        if (var3 == NBlocks.BAMBOO || var3 == NBlocks.BAMBOO_SAPLING || var3 == Blocks.GRAVEL || var3.equals(Blocks.SAND) || var3 == Blocks.DIRT || var3.equals(Blocks.GRASS) || var3.equals(Blocks.MYCELIUM) || var3 == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT) || var3 == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL)) {
            Block var4 = var3.getBlock();
            if (var4 == NBlocks.BAMBOO_SAPLING) {
                return this.getDefaultState().withProperty(AGE, 0);
            } else if (var4 == NBlocks.BAMBOO) {
                int var5 = var3.getValue(AGE) > 0 ? 1 : 0;
                return this.getDefaultState().withProperty(AGE, var5);
            } else {
                return NBlocks.BAMBOO_SAPLING.getDefaultState();
            }
        } else {
            return null;
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState state = world.getBlockState(pos);
        if (!canPlaceBlockAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (facing == EnumFacing.UP && state.getBlock() == NBlocks.BAMBOO && state.getValue(AGE) > getDefaultState().getValue(AGE)) {
                world.setBlockState(pos, getDefaultState().cycleProperty(AGE), 2);
            }

            return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            if (rand.nextInt(3) == 0 && worldIn.isAirBlock(pos.up()) && worldIn.getCombinedLight(pos.up(), 0) >= 9) {
                int var5 = this.method_9386(worldIn, pos) + 1;
                if (var5 < 16) {
                    this.method_9385(state, worldIn, pos, rand, var5);
                }
            }

        }
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        int var5 = this.method_9387(worldIn, pos);
        int var6 = this.method_9386(worldIn, pos);
        return var5 + var6 + 1 < 16 && worldIn.getBlockState(pos.up(var5)).getValue(STAGE) != 1;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        int var5 = this.method_9387(worldIn, pos);
        int var6 = this.method_9386(worldIn, pos);
        int var7 = var5 + var6 + 1;
        int var8 = 1 + rand.nextInt(2);

        for(int var9 = 0; var9 < var8; ++var9) {
            BlockPos var10 = pos.up(var5);
            IBlockState var11 = worldIn.getBlockState(var10);
            if (var7 >= 16 || var11.getValue(STAGE) == 1 || !worldIn.isAirBlock(var10.up())) {
                return;
            }

            this.method_9385(var11, worldIn, var10, rand, var7);
            ++var5;
            ++var7;
        }
    }

    protected void method_9385(IBlockState var1, World var2, BlockPos var3, Random var4, int var5) {
        IBlockState var6 = var2.getBlockState(var3.down());
        BlockPos var7 = var3.down(2);
        IBlockState var8 = var2.getBlockState(var7);
        BambooLeaves var9 = BambooLeaves.NONE;
        if (var5 >= 1) {
            if (var6.getBlock() == NBlocks.BAMBOO && var6.getValue(LEAVES) != BambooLeaves.NONE) {
                if (var6.getBlock() == NBlocks.BAMBOO && var6.getValue(LEAVES) != BambooLeaves.NONE) {
                    var9 = BambooLeaves.LARGE;
                    if (var8.getBlock() == NBlocks.BAMBOO) {
                        var2.setBlockState(var3.down(), var6.withProperty(LEAVES, BambooLeaves.SMALL), 3);
                        var2.setBlockState(var7, var8.withProperty(LEAVES, BambooLeaves.NONE), 3);
                    }
                }
            } else {
                var9 = BambooLeaves.SMALL;
            }
        }

        int var10 = var1.getValue(AGE) != 1 && var8.getBlock() != NBlocks.BAMBOO ? 0 : 1;
        int var11 = (var5 < 11 || var4.nextFloat() >= 0.25F) && var5 != 15 ? 0 : 1;
        var2.setBlockState(var3.up(), this.getDefaultState().withProperty(AGE, var10).withProperty(LEAVES, var9).withProperty(STAGE, var11), 3);
    }

    protected int method_9387(World var1, BlockPos var2) {
        int var3;
        for(var3 = 0; var3 < 16 && var1.getBlockState(var2.up(var3 + 1)).getBlock() == NBlocks.BAMBOO; ++var3) {
        }

        return var3;
    }

    protected int method_9386(World var1, BlockPos var2) {
        int var3;
        for(var3 = 0; var3 < 16 && var1.getBlockState(var2.down(var3 + 1)).getBlock() == NBlocks.BAMBOO; ++var3) {
        }

        return var3;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE, LEAVES, STAGE);
    }

    static {
        AGE = PropertyInteger.create("age", 0, 1);
        LEAVES = PropertyEnum.create("leaves", BambooLeaves.class);
        STAGE = PropertyInteger.create("stage", 0, 1);
    }

}