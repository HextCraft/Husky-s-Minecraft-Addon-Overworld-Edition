package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * parasight - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelParasight extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer tentacles;
    public ModelRenderer pupilSingle;
    public ModelRenderer pupilMulti01;
    public ModelRenderer pupilMulti02;
    public ModelRenderer pupilMulti03;
    public ModelRenderer pupilMulti04;
    public ModelRenderer pupilMulti05;
    public ModelRenderer pupilMulti06;

    public ModelParasight() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.pupilMulti03 = new ModelRenderer(this, 48, 0);
        this.pupilMulti03.setRotationPoint(4.0F, 0.0F, -4.0F);
        this.pupilMulti03.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
        this.base = new ModelRenderer(this, 0, 21);
        this.base.setRotationPoint(0.0F, 16.0F, 7.8F);
        this.base.addBox(-6.0F, -6.0F, -3.99F, 12, 12, 4, 0.0F);
        this.pupilMulti06 = new ModelRenderer(this, 48, 0);
        this.pupilMulti06.setRotationPoint(-4.0F, -4.0F, -4.0F);
        this.pupilMulti06.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.pupilMulti05 = new ModelRenderer(this, 48, 0);
        this.pupilMulti05.setRotationPoint(4.0F, 4.0F, -4.0F);
        this.pupilMulti05.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.pupilSingle = new ModelRenderer(this, 43, 0);
        this.pupilSingle.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.pupilSingle.addBox(-0.5F, -2.0F, -0.5F, 1, 4, 1, 0.0F);
        this.pupilMulti02 = new ModelRenderer(this, 48, 0);
        this.pupilMulti02.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.pupilMulti02.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
        this.tentacles = new ModelRenderer(this, 0, 0);
        this.tentacles.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tentacles.addBox(-9.0F, -9.0F, 0.0F, 18, 18, 0, 0.0F);
        this.pupilMulti01 = new ModelRenderer(this, 48, 0);
        this.pupilMulti01.setRotationPoint(-3.0F, 3.0F, -4.0F);
        this.pupilMulti01.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
        this.pupilMulti04 = new ModelRenderer(this, 48, 0);
        this.pupilMulti04.setRotationPoint(0.5F, 1.5F, -4.0F);
        this.pupilMulti04.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.base.addChild(this.pupilMulti03);
        this.base.addChild(this.pupilMulti06);
        this.base.addChild(this.pupilMulti05);
        this.base.addChild(this.pupilSingle);
        this.base.addChild(this.pupilMulti02);
        this.base.addChild(this.tentacles);
        this.base.addChild(this.pupilMulti01);
        this.base.addChild(this.pupilMulti04);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.base.render(f5);
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
