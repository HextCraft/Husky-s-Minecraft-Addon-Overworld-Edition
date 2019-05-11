package team.abnormal.neutronia.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import team.abnormal.neutronia.entity.passive.EntityHyena;

public class EntityAIOwnerHyenaHurtByTarget extends EntityAITarget
{
    EntityHyena tameable;
    EntityLivingBase attacker;
    private int timestamp;

    public EntityAIOwnerHyenaHurtByTarget(EntityHyena theDefendingHyenaIn)
    {
        super(theDefendingHyenaIn, false);
        this.tameable = theDefendingHyenaIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.tameable.isTamed()) {
            EntityLivingBase entitylivingbase = this.tameable.getMenberHead();

            EntityLivingBase entitylivingbase2 = this.tameable.getLeaderHyena();

            if (entitylivingbase == null) {
                if (entitylivingbase2 == null) {
                    return false;
                } else {
                    this.attacker = entitylivingbase2.getRevengeTarget();
                    int i = entitylivingbase2.getRevengeTimer();
                    return i != this.timestamp && this.isSuitableTarget(this.attacker, false) && this.tameable.shouldAttackEntity(this.attacker, entitylivingbase2);

                }
            } else {
                this.attacker = entitylivingbase.getRevengeTarget();
                int i = entitylivingbase.getRevengeTimer();
                return i != this.timestamp && this.isSuitableTarget(this.attacker, false) && this.tameable.shouldAttackEntity(this.attacker, entitylivingbase);
            }
        }else {
            return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.attacker);
        EntityLivingBase entitylivingbase = this.tameable.getOwner();

        if (entitylivingbase != null)
        {
            this.timestamp = entitylivingbase.getRevengeTimer();
        }

        super.startExecuting();
    }
}