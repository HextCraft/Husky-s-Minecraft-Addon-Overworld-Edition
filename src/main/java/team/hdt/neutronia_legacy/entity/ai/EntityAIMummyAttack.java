package team.hdt.neutronia_legacy.entity.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import team.hdt.neutronia_legacy.entity.EntityMummy;

public class EntityAIMummyAttack extends EntityAIAttackMelee {

    private final EntityMummy mummy;
    private int raiseArmTicks;

    public EntityAIMummyAttack(EntityMummy mummyIn, double speedIn, boolean longMemoryIn) {
        super(mummyIn, speedIn, longMemoryIn);
        this.mummy = mummyIn;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.mummy.setArmsRaised(false);
    }

    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.mummy.setArmsRaised(true);
        } else {
            this.mummy.setArmsRaised(false);
        }
    }
}
