package team.hdt.neutronia.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import team.hdt.neutronia.entity.EntityScorp;

public class EntityAIScorpAttack extends EntityAIAttackMelee {
    private final EntityScorp scorp;
    private int extendTailTicks;

    public EntityAIScorpAttack(EntityScorp scorpIn, double speedIn, boolean useLongMemory) {
        super(scorpIn, speedIn, useLongMemory);
        this.scorp = scorpIn;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.extendTailTicks = 0;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.scorp.setTailOut(false);
    }

    @Override
    public void updateTask() {
        super.updateTask();
        ++extendTailTicks;

        if (this.extendTailTicks >= 5 && this.attackTick < 10) {
            this.scorp.setTailOut(true);
        } else {
            this.scorp.setTailOut(false);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double) (5.0F + attackTarget.width);
    }
}
