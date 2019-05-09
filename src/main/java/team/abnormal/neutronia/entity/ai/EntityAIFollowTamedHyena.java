package team.abnormal.neutronia.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.Vec3d;
import team.abnormal.neutronia.entity.passive.EntityHyena;

import java.util.List;

public class EntityAIFollowTamedHyena extends EntityAIBase
{
    public EntityHyena hyena;
    private double speedModifier;
    private int distCheckCounter;

    public EntityAIFollowTamedHyena(EntityHyena hyenaIn, double speedModifierIn)
    {
        this.hyena = hyenaIn;
        this.speedModifier = speedModifierIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.hyena.isTamed() && !this.hyena.inMenber())
        {
            List<EntityHyena> list = this.hyena.world.<EntityHyena>getEntitiesWithinAABB(this.hyena.getClass(), this.hyena.getEntityBoundingBox().grow(9.0D, 4.0D, 9.0D));
            EntityHyena entityhyena = null;
            double d0 = Double.MAX_VALUE;

            for (EntityHyena entityhyena1 : list)
            {
                if (entityhyena1.inMenber() && !entityhyena1.hasMenberTrail())
                {
                    double d1 = this.hyena.getDistanceSq(entityhyena1);

                    if (d1 <= d0)
                    {
                        d0 = d1;
                        entityhyena = entityhyena1;
                    }
                }
            }

            if (entityhyena == null)
            {
                for (EntityHyena entityhyena2 : list)
                {
                    if (entityhyena2.isTamed() && !entityhyena2.isSitting() && !entityhyena2.hasMenberTrail())
                    {
                        double d2 = this.hyena.getDistanceSq(entityhyena2);

                        if (d2 <= d0)
                        {
                            d0 = d2;
                            entityhyena = entityhyena2;
                        }
                    }
                }
            }

            if (entityhyena == null)
            {
                return false;
            }
            else if (!this.firstIsLeader(entityhyena, 1))
            {
                return false;
            }else if(entityhyena.isSitting()){
                return false;
            }
            else
            {
                this.hyena.joinMenber(entityhyena);
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        if (this.hyena.inMenber() && this.hyena.getMenberHead().isEntityAlive() && this.firstIsLeader(this.hyena, 0))
        {
            double d0 = this.hyena.getDistanceSq(this.hyena.getMenberHead());

            if (d0 > 676.0D)
            {
                if (this.speedModifier <= 3.0D)
                {
                    this.speedModifier *= 1.2D;
                    this.distCheckCounter = 40;
                    return true;
                }

                if (this.distCheckCounter == 0)
                {
                    return false;
                }
            }

            if (this.distCheckCounter > 0)
            {
                --this.distCheckCounter;
            }

            return true;
        }else {
            return false;
        }

    }


    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.hyena.leaveMenber();
        this.speedModifier = 2.0D;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        if (this.hyena.inMenber())
        {
            EntityHyena entityhyena = this.hyena.getMenberHead();
            double d0 = (double)this.hyena.getDistance(entityhyena);
            float f = 2.0F;
            Vec3d vec3d = (new Vec3d(entityhyena.posX - this.hyena.posX, entityhyena.posY - this.hyena.posY, entityhyena.posZ - this.hyena.posZ)).normalize().scale(Math.max(d0 - 2.0D, 0.0D));
            if (d0 > 4.0D) {

                this.hyena.getNavigator().tryMoveToXYZ(this.hyena.posX + vec3d.x, this.hyena.posY + vec3d.y, this.hyena.posZ + vec3d.z, this.speedModifier);
            }
        }
    }

    private boolean firstIsLeader(EntityHyena p_190858_1_, int p_190858_2_)
    {
        if (p_190858_2_ > 6)
        {
            return false;
        }
        else if (p_190858_1_.inMenber())
        {
            if (p_190858_1_.getMenberHead().isTamed())
            {
                return true;
            }else if(p_190858_1_.getMenberHead().isLeader()){
                return true;
            }
            else
            {
                EntityHyena entityhyena = p_190858_1_.getMenberHead();
                ++p_190858_2_;
                return this.firstIsLeader(entityhyena, p_190858_2_);
            }
        }
        else
        {
            return false;
        }
    }
}