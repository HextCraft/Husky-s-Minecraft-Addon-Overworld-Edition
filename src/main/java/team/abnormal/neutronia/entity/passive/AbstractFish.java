package team.abnormal.neutronia.entity.passive;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class AbstractFish extends EntityCreature implements IAnimals {
    public AbstractFish(World worldIn) {
        super(worldIn);
        this.moveHelper = new MoveHelper(this);
    }


    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateSwimmer(this, worldIn);
    }

    public void travel(float strafe, float vertical, float forward) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(strafe, vertical, forward, 0.01F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)0.9F;
            this.motionY *= (double)0.9F;
            this.motionZ *= (double)0.9F;
            if (this.getAttackTarget() == null) {
                this.motionY -= 0.005D;
            }
        } else {
            super.travel(strafe, vertical, forward);
        }

    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    public boolean canDespawn() {
        return !this.hasCustomName();
    }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos blockpos = new BlockPos(this);
        return (world.getBlockState(blockpos).getBlock() == Blocks.WATER && world.getBlockState(blockpos.up()).getBlock() == Blocks.WATER) && super.getCanSpawnHere();
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    /**
     * Checks that the entity is not colliding with any blocks / liquids
     */
    public boolean isNotColliding()
    {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 120;
    }


    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        return 1 + this.world.rand.nextInt(3);
    }

    /**
     * Gets called every tick from main Entity class
     */
    public void onEntityUpdate()
    {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater())
        {
            --i;
            this.setAir(i);

            if (this.getAir() == -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        }
        else
        {
            this.setAir(300);
        }
    }

    public boolean isPushedByWater()
    {
        return false;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk() {
        return 4;
    }

    static class MoveHelper extends EntityMoveHelper {
        private final AbstractFish fish;

        MoveHelper(AbstractFish p_i48857_1_) {
            super(p_i48857_1_);
            this.fish = p_i48857_1_;
        }

        public void onUpdateMoveHelper() {
            if (this.fish.isInWater()) {
                this.fish.motionY += 0.005D;
            }

            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
                double d0 = this.posX - this.fish.posX;
                double d1 = this.posY - this.fish.posY;
                double d2 = this.posZ - this.fish.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.fish.rotationYaw = this.limitAngle(this.fish.rotationYaw, f, 90.0F);
                this.fish.renderYawOffset = this.fish.rotationYaw;
                float f1 = (float)(this.speed * this.fish.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue());
                this.fish.setAIMoveSpeed(this.fish.getAIMoveSpeed() + (f1 - this.fish.getAIMoveSpeed()) * 0.125F);
                this.fish.motionY += (double)this.fish.getAIMoveSpeed() * d1 * 0.1D;
            } else {
                this.fish.setAIMoveSpeed(0.0F);
            }
        }
    }
}
