package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * host - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelHost extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer jaw;

    public ModelHost() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 16.0F, -0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.jaw = new ModelRenderer(this, 0, 16);
        this.jaw.setRotationPoint(0.0F, 16.0F, -0.0F);
        this.jaw.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.render(f5);
        this.jaw.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
