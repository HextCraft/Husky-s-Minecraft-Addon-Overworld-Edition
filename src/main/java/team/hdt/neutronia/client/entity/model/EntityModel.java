package team.hdt.neutronia.client.entity.model;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class EntityModel<T extends Entity> extends Model {
    public float swingProgress;
    public boolean isRiding;
    public boolean isChild = true;

    public EntityModel() {
    }

    public void render(T entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
    }

    public void setAngles(T entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
    }

    public void animateModel(T entity_1, float float_1, float float_2, float float_3) {
    }

    public void method_17081(EntityModel<T> entityModel_1) {
        entityModel_1.swingProgress = this.swingProgress;
        entityModel_1.isRiding = this.isRiding;
        entityModel_1.isChild = this.isChild;
    }
}
