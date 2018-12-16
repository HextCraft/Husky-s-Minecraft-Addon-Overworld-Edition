package team.hdt.neutronia_legacy.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import team.hdt.neutronia_legacy.entity.EntityScorp;

public class EntityAIScorpTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
    public EntityAIScorpTarget(EntityScorp scorp, Class<T> classTarget) {
        super(scorp, classTarget, true);
    }

    public boolean shouldExecute() {
        return super.shouldExecute();
    }
}
