package team.abnormal.neutronia.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import team.abnormal.neutronia.entity.passive.EntityHyena;

import java.util.List;

public class EntityAIFollowWildLeader extends EntityAIBase {
    public EntityHyena owner;
    public double spead;
    private int moveDelay;

    public EntityAIFollowWildLeader(EntityHyena abstractHyenaGroup, double spead) {
        this.owner = abstractHyenaGroup;
        this.spead = spead;
    }

    @Override
    public boolean shouldExecute() {
        if (this.owner.isTamed()) {
            return false;
        } else if (!this.owner.isEntityAlive()) {
            return false;
        } else if (this.owner.hasLeader()) {
            return true;
        } else {
            List<EntityHyena> list = this.owner.world.<EntityHyena>getEntitiesWithinAABB(this.owner.getClass(), this.owner.getEntityBoundingBox().grow(64.0D, 12.0D, 64.0D));
            EntityHyena entityhyena = null;
            double d0 = Double.MAX_VALUE;

            for (EntityHyena entityhyena1 : list) {
                if (entityhyena1.isLeader()) {
                    double d1 = this.owner.getDistanceSq(entityhyena1);

                    if (d1 <= d0) {
                        d0 = d1;
                        entityhyena = entityhyena1;
                    }
                }
            }

            if (entityhyena == null) {
                for (EntityHyena entityhyena2 : list) {
                    if (!this.owner.isLeader() && !entityhyena2.isLeader()) {
                        double d1 = this.owner.getDistanceSq(entityhyena2);

                        if (d1 <= d0) {
                            d0 = d1;
                            entityhyena = entityhyena2;
                        }
                    }
                }
            }

            /*
             * Allocate a random number to avoid confusion with other Hyena leaders when deciding on a leader
             */
            if (entityhyena != null && this.owner.world.rand.nextInt(10) == 0) {
                if (entityhyena.isLeader() && this.owner.isLeader()) {
                    this.owner.setLeader(false);
                }

                if (!entityhyena.isLeader() && !this.owner.isLeader()) {
                    this.owner.setLeader(true);
                }
                this.owner.setLeaderHyena(entityhyena);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.owner.isEntityAlive() && this.owner.getLeaderHyena().isEntityAlive();
    }

    public void startExecuting() {
        this.moveDelay = 0;

    }

    public void resetTask() {
        this.owner.setLeaderHyena(null);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        double d1 = this.owner.getDistanceSq(this.owner.getLeaderHyena());

        if (this.moveDelay++ >= 10) {
            this.moveDelay = 0;
            if (d1 > 16.0F) {
                this.owner.getNavigator().tryMoveToEntityLiving(this.owner.getLeaderHyena(), 1.0D);
            }
        }
    }


}
