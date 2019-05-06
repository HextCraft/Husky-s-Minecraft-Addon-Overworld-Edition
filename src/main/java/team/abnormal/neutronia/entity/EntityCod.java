package team.abnormal.neutronia.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;
import team.abnormal.neutronia.entity.ai.EntityAIWanderSwim;

public class EntityCod extends AbstractFishGroup implements IAnimals {

    public EntityCod(World worldIn) {
        super(worldIn);

        this.setSize(0.5F,0.3F);
    }

    protected void initEntityAI(){
        super.initEntityAI();
        this.tasks.addTask(3, new EntityAIWanderSwim(this,1.0,20));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.58D);
    }

    public float getEyeHeight() {
        return this.height * 0.65F;
    }

}