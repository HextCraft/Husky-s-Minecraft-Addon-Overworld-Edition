package team.abnormal.neutronia.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import team.abnormal.neutronia.entity.passive.AbstractFishGroup;

public class EntityAIMoveToLeader extends EntityAIBase {
    public AbstractFishGroup owner;
    public double spead;
    private int moveDelay;

    public EntityAIMoveToLeader(AbstractFishGroup abstractFishGroup, double spead) {
        this.owner = abstractFishGroup;
        this.spead = spead;
    }

    @Override
    public boolean shouldExecute() {
        if (this.owner.hasOtherFishInGroup()) {
            return false;
        } else if (this.owner.hasLeader()) {
            return true;
        }else
        if(!this.owner.isEntityAlive()) {
            return false;
        }else {
            return this.owner.hasLeader();
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.owner.isEntityAlive()&&this.owner.hasLeader() && this.owner.isCloseEnoughToLeader();
    }

    public void startExecuting()
    {
        this.moveDelay = 0;

    }

    public void resetTask()
    {
        this.owner.leaveGroup();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        if (--this.moveDelay <= 0) {
            this.moveDelay = 10;
            this.owner.moveTowardLeader();
        }
    }


}
