package team.abnormal.neutronia.blocks;

import com.google.common.base.Predicate;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockPattern.PatternHelper;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.blocks.BlockModHorizontal;
import team.abnormal.neutronia.base.blocks.IMinecraftBlock;
import team.abnormal.neutronia.init.NBlocks;

import java.util.Iterator;

public class BlockCarvedPumpkin extends BlockModHorizontal implements IMinecraftBlock {

    private BlockPattern snowmanBasePattern;
    private BlockPattern snowmanPattern;
    private BlockPattern golemBasePattern;
    private BlockPattern golemPattern;
    private static final Predicate<IBlockState> IS_PUMPKIN = p_apply_1_ ->
                    p_apply_1_ != null && (p_apply_1_.getBlock() == NBlocks.CARVED_PUMPKIN || p_apply_1_.getBlock() == NBlocks.JACK_O_LANTERN);

    public BlockCarvedPumpkin(String name) {
        super(name, Material.GOURD);
        this.setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if(worldIn.getBlockState(pos).getBlock() != state.getBlock()) {
            this.trySpawnGolem(worldIn, pos);
        }
    }

    public boolean canDispenserPlace(World worldIn, BlockPos pos) {
        return this.getSnowmanBasePattern().match(worldIn, pos) != null || this.getGolemBasePattern().match(worldIn, pos) != null;
    }

    private void trySpawnGolem(World p_196358_1_, BlockPos p_196358_2_) {
        PatternHelper lvt_3_1_ = this.getSnowmanPattern().match(p_196358_1_, p_196358_2_);
        int lvt_4_3_;
        Iterator var6;
        EntityPlayerMP lvt_7_3_;
        BlockWorldState lvt_8_2_;
        int lvt_6_3_;
        int lvt_7_4_;
        if (lvt_3_1_ != null) {
            for(lvt_4_3_ = 0; lvt_4_3_ < this.getSnowmanPattern().getThumbLength(); ++lvt_4_3_) {
                BlockWorldState lvt_5_1_ = lvt_3_1_.translateOffset(0, lvt_4_3_, 0);
                p_196358_1_.setBlockState(lvt_5_1_.getPos(), Blocks.AIR.getDefaultState(), 2);
            }

            EntitySnowman lvt_4_2_ = new EntitySnowman(p_196358_1_);
            BlockPos lvt_5_2_ = lvt_3_1_.translateOffset(0, 2, 0).getPos();
            lvt_4_2_.setLocationAndAngles((double)lvt_5_2_.getX() + 0.5D, (double)lvt_5_2_.getY() + 0.05D, (double)lvt_5_2_.getZ() + 0.5D, 0.0F, 0.0F);
            p_196358_1_.spawnEntity(lvt_4_2_);
            var6 = p_196358_1_.getEntitiesWithinAABB(EntityPlayerMP.class, lvt_4_2_.getCollisionBoundingBox().grow(5.0D)).iterator();

            while(var6.hasNext()) {
                lvt_7_3_ = (EntityPlayerMP)var6.next();
                CriteriaTriggers.SUMMONED_ENTITY.trigger(lvt_7_3_, lvt_4_2_);
            }

            lvt_6_3_ = Block.getStateId(Blocks.SNOW.getDefaultState());
            p_196358_1_.playEvent(2001, lvt_5_2_, lvt_6_3_);
            p_196358_1_.playEvent(2001, lvt_5_2_.up(), lvt_6_3_);

            for(lvt_7_4_ = 0; lvt_7_4_ < this.getSnowmanPattern().getThumbLength(); ++lvt_7_4_) {
                lvt_8_2_ = lvt_3_1_.translateOffset(0, lvt_7_4_, 0);
                p_196358_1_.notifyNeighborsRespectDebug(lvt_8_2_.getPos(), Blocks.AIR, false);
            }
        } else {
            lvt_3_1_ = this.getGolemPattern().match(p_196358_1_, p_196358_2_);
            if (lvt_3_1_ != null) {
                for(lvt_4_3_ = 0; lvt_4_3_ < this.getGolemPattern().getPalmLength(); ++lvt_4_3_) {
                    for(int lvt_5_3_ = 0; lvt_5_3_ < this.getGolemPattern().getThumbLength(); ++lvt_5_3_) {
                        p_196358_1_.setBlockState(lvt_3_1_.translateOffset(lvt_4_3_, lvt_5_3_, 0).getPos(), Blocks.AIR.getDefaultState(), 2);
                    }
                }

                BlockPos lvt_4_4_ = lvt_3_1_.translateOffset(1, 2, 0).getPos();
                EntityIronGolem lvt_5_4_ = new EntityIronGolem(p_196358_1_);
                lvt_5_4_.setPlayerCreated(true);
                lvt_5_4_.setLocationAndAngles((double)lvt_4_4_.getX() + 0.5D, (double)lvt_4_4_.getY() + 0.05D, (double)lvt_4_4_.getZ() + 0.5D, 0.0F, 0.0F);
                p_196358_1_.spawnEntity(lvt_5_4_);
                var6 = p_196358_1_.getEntitiesWithinAABB(EntityPlayerMP.class, lvt_5_4_.getCollisionBoundingBox().grow(5.0D)).iterator();

                while(var6.hasNext()) {
                    lvt_7_3_ = (EntityPlayerMP)var6.next();
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(lvt_7_3_, lvt_5_4_);
                }

                for(lvt_6_3_ = 0; lvt_6_3_ < 120; ++lvt_6_3_) {
                    p_196358_1_.spawnParticle(EnumParticleTypes.SNOWBALL, (double)lvt_4_4_.getX() + p_196358_1_.rand.nextDouble(), (double)lvt_4_4_.getY() + p_196358_1_.rand.nextDouble() * 3.9D, (double)lvt_4_4_.getZ() + p_196358_1_.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
                }

                for(lvt_6_3_ = 0; lvt_6_3_ < this.getGolemPattern().getPalmLength(); ++lvt_6_3_) {
                    for(lvt_7_4_ = 0; lvt_7_4_ < this.getGolemPattern().getThumbLength(); ++lvt_7_4_) {
                        lvt_8_2_ = lvt_3_1_.translateOffset(lvt_6_3_, lvt_7_4_, 0);
                        p_196358_1_.notifyNeighborsRespectDebug(lvt_8_2_.getPos(), Blocks.AIR, false);
                    }
                }
            }
        }

    }

    /**
     * Checks if this block can be placed exactly at the given position.
     */
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.isSideSolid(pos.down(), EnumFacing.UP);
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
     * fine.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(FACING)).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    protected BlockPattern getSnowmanBasePattern() {
        if (this.snowmanBasePattern == null) {
            this.snowmanBasePattern = FactoryBlockPattern.start().aisle(" ", "#", "#").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SNOW))).build();
        }

        return this.snowmanBasePattern;
    }

    protected BlockPattern getSnowmanPattern() {
        if (this.snowmanPattern == null) {
            this.snowmanPattern = FactoryBlockPattern.start().aisle("^", "#", "#").where('^', BlockWorldState.hasState(IS_PUMPKIN)).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SNOW))).build();
        }

        return this.snowmanPattern;
    }

    protected BlockPattern getGolemBasePattern() {
        if (this.golemBasePattern == null) {
            this.golemBasePattern = FactoryBlockPattern.start().aisle("~ ~", "###", "~#~").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemBasePattern;
    }

    protected BlockPattern getGolemPattern() {
        if (this.golemPattern == null) {
            this.golemPattern = FactoryBlockPattern.start().aisle("~^~", "###", "~#~").where('^', BlockWorldState.hasState(IS_PUMPKIN)).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemPattern;
    }

}
