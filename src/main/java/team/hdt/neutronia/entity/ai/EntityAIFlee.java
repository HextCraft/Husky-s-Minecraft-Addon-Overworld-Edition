package team.hdt.neutronia.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

/**
 * The Entity will flee from threats in the opposite direction.
 *
 * @author Koward
 */
public class EntityAIFlee extends EntityAIBase {
    protected final double speed;
    private final EntityCreature theEntityCreature;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    public EntityAIFlee(EntityCreature creature, double speedIn) {
        this.theEntityCreature = creature;
        this.speed = speedIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        Vec3d vec3d = null;

        if (this.theEntityCreature.isBurning()) {
            vec3d = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);
        } else if (this.theEntityCreature.getRevengeTarget() != null) {
            vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntityCreature, 5, 0,
                    new Vec3d(this.theEntityCreature.getRevengeTarget().posX, this.theEntityCreature.getRevengeTarget().posY, this.theEntityCreature.getRevengeTarget().posZ));
        }

        if (vec3d != null) {
            this.randPosX = vec3d.x;
            this.randPosY = vec3d.y;
            this.randPosZ = vec3d.z;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        if (!this.theEntityCreature.getNavigator().noPath() && this.theEntityCreature.getRevengeTarget() != null) {
            EntityLivingBase target = this.theEntityCreature.getRevengeTarget();

            if (target == null) {
                return true;
            }

            double sqDistToPos = this.theEntityCreature.getDistanceSq(this.randPosX, this.randPosY, this.randPosZ);

            if (sqDistToPos > 2.0D) {
                double sqDistToTarget = this.theEntityCreature.getDistanceSq(target);
                double sqDistOfTargetToPos = target.getDistanceSq(this.randPosX, this.randPosY, this.randPosZ);

                if (sqDistToTarget < sqDistOfTargetToPos) {
                    return true;
                }
            }
        }

        return false;
    }
}