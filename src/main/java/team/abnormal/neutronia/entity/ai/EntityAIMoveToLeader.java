package team.abnormal.neutronia.entity.ai;

import com.google.common.base.Predicate;
import net.minecraft.entity.ai.EntityAIBase;
import team.abnormal.neutronia.entity.passive.AbstractFishGroup;

import java.util.List;

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
        } else if (!this.owner.isEntityAlive()) {
            return false;
        }else {
            Predicate<AbstractFishGroup> predicate_1 = (schoolingFishEntity_1x) -> {
                return schoolingFishEntity_1x.canHaveMoreFishInGroup() || !schoolingFishEntity_1x.hasLeader();
            };
            List<AbstractFishGroup> list_1 = this.owner.world.<AbstractFishGroup>getEntitiesWithinAABB(AbstractFishGroup.class, this.owner.getEntityBoundingBox().grow(8.0D, 8.0D, 8.0D), predicate_1);
            AbstractFishGroup abstractFishGroup_1 = (AbstractFishGroup) list_1.stream().filter(AbstractFishGroup::canHaveMoreFishInGroup).findAny().orElse(this.owner);
            abstractFishGroup_1.pullInOtherFish(list_1.stream().filter((abstractFishGroup_1x) -> {
                return !abstractFishGroup_1x.hasLeader();
            }));
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
