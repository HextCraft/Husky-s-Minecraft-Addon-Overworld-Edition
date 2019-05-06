package team.abnormal.neutronia.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import team.abnormal.neutronia.entity.ai.EntityAIMoveToLeader;
import team.abnormal.neutronia.init.NSoundEvents;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractFishGroup extends AbstractFish{
    private int groupSize;
    public AbstractFishGroup leader;

    public AbstractFishGroup(World worldIn) {
        super(worldIn);
    }

    protected void initEntityAI(){
        this.tasks.addTask(0, new EntityAIMoveToLeader(this,1.0));
    }

    protected SoundEvent getDeathSound()
    {
        return NSoundEvents.ENTITY_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return NSoundEvents.ENTITY_FISH_HURT;
    }

    public void moveTowardLeader() {
        if (this.hasLeader()) {
            this.getNavigator().tryMoveToEntityLiving(this.leader, 1.0D);
        }

    }

    public int getMaxGroupSize() {
        return 4;
    }

    protected boolean hasSelfControl() {
        return !this.hasLeader();
    }

    public boolean hasLeader() {
        return this.leader != null && this.leader.isEntityAlive();
    }

    public AbstractFishGroup joinGroupOf(AbstractFishGroup groupFish) {
        this.leader = groupFish;
        groupFish.increaseGroupSize();
        return groupFish;
    }

    public void leaveGroup() {
        this.leader.decreaseGroupSize();
        this.leader = null;
    }

    private void increaseGroupSize() {
        ++this.groupSize;
    }

    private void decreaseGroupSize() {
        --this.groupSize;
    }

    public boolean canHaveMoreFishInGroup() {
        return this.hasOtherFishInGroup() && this.groupSize < this.getMaxGroupSize();
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.hasOtherFishInGroup() && this.world.rand.nextInt(200) == 1) {
            List<Entity> list_1 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(8.0D, 8.0D, 8.0D));
            if (list_1.size() <= 1) {
                this.groupSize = 1;
            }
        }

    }

    public boolean hasOtherFishInGroup() {
        return this.groupSize > 1;
    }

    public boolean isCloseEnoughToLeader() {
        return this.getDistanceSq(this.leader) <= 121.0D;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    { super.onInitialSpawn(difficulty,livingdata);
        if (livingdata == null) {
            livingdata = new GroupData(this);
        } else {
            this.joinGroupOf(((GroupData)livingdata).leader);
        }

        return (IEntityLivingData) livingdata;
    }


public static class GroupData implements IEntityLivingData
    {
        public AbstractFishGroup leader;

        public GroupData(AbstractFishGroup leader)
        {
            this.leader = leader;
        }
    }
}
