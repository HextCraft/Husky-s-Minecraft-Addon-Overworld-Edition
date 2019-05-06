package team.abnormal.neutronia.entity.passive;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import team.abnormal.neutronia.entity.ai.EntityAIWanderSwim;

public class EntitySalmon extends AbstractFishGroup {

    public EntitySalmon(World worldIn) {
        super(worldIn);

        this.setSize(0.7F,0.4F);
    }

    protected void initEntityAI(){
        super.initEntityAI();
        this.tasks.addTask(3, new EntityAIWanderSwim(this,1.0,20));
    }

    public int getMaxGroupSize() {
        return 5;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.58D);
    }

    public float getEyeHeight() {
        return this.height * 0.65F;
    }

}