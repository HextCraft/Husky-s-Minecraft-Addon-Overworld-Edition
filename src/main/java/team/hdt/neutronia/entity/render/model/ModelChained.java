package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Chained - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelChained extends ModelBase {
    public ModelRenderer BodyLayer;
    public ModelRenderer RightArm2;
    public ModelRenderer LeftArm2;
    public ModelRenderer RightLeg2;
    public ModelRenderer LeftLeg2;
    public ModelRenderer Ball2;
    public ModelRenderer Hat;
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer RightArm1;
    public ModelRenderer LeftArm1;
    public ModelRenderer RightLeg1;
    public ModelRenderer LeftLeg1;
    public ModelRenderer Ball1;

    public ModelChained() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftLeg1 = new ModelRenderer(this, 40, 16);
        this.LeftLeg1.mirror = true;
        this.LeftLeg1.setRotationPoint(2.0F, 12.0F, 0.1F);
        this.LeftLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.Body = new ModelRenderer(this, 0, 16);
        this.Body.setRotationPoint(0.0F, 2.0F, -2.0F);
        this.Body.addBox(-4.0F, -2.0F, -2.0F, 8, 12, 4, 0.0F);
        this.setRotateAngle(Body, 0.2617993877991494F, 0.0F, 0.0F);
        this.LeftArm2 = new ModelRenderer(this, 32, 16);
        this.LeftArm2.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm2.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.25F);
        this.setRotateAngle(LeftArm2, -0.8726646259971648F, 0.17453292519943295F, 0.0F);
        this.Ball1 = new ModelRenderer(this, 0, 48);
        this.Ball1.setRotationPoint(0.0F, 8.0F, -8.0F);
        this.Ball1.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.Ball2 = new ModelRenderer(this, 24, 48);
        this.Ball2.setRotationPoint(0.0F, 8.0F, -8.0F);
        this.Ball2.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.25F);
        this.Hat = new ModelRenderer(this, 32, 0);
        this.Hat.setRotationPoint(0.0F, 0.0F, -2.5F);
        this.Hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
        this.RightArm2 = new ModelRenderer(this, 32, 16);
        this.RightArm2.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm2.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.25F);
        this.setRotateAngle(RightArm2, -0.8726646259971648F, -0.17453292519943295F, 0.0F);
        this.LeftLeg2 = new ModelRenderer(this, 56, 16);
        this.LeftLeg2.setRotationPoint(2.0F, 12.0F, 0.1F);
        this.LeftLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.25F);
        this.LeftArm1 = new ModelRenderer(this, 24, 16);
        this.LeftArm1.mirror = true;
        this.LeftArm1.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm1.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(LeftArm1, -0.8726646259971648F, 0.17453292519943295F, 0.0F);
        this.RightArm1 = new ModelRenderer(this, 24, 16);
        this.RightArm1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm1.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(RightArm1, -0.8726646259971648F, -0.17453292519943295F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, -2.5F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.BodyLayer = new ModelRenderer(this, 0, 32);
        this.BodyLayer.setRotationPoint(0.0F, 2.0F, -2.0F);
        this.BodyLayer.addBox(-4.0F, -2.0F, -2.0F, 8, 12, 4, 0.25F);
        this.setRotateAngle(BodyLayer, 0.2617993877991494F, 0.0F, 0.0F);
        this.RightLeg1 = new ModelRenderer(this, 40, 16);
        this.RightLeg1.setRotationPoint(-2.0F, 12.0F, 0.1F);
        this.RightLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.RightLeg2 = new ModelRenderer(this, 48, 16);
        this.RightLeg2.setRotationPoint(-2.0F, 12.0F, 0.1F);
        this.RightLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftLeg1.render(f5);
        this.Body.render(f5);
        this.LeftArm2.render(f5);
        this.Ball1.render(f5);
        this.Ball2.render(f5);
        this.Hat.render(f5);
        this.RightArm2.render(f5);
        this.LeftLeg2.render(f5);
        this.LeftArm1.render(f5);
        this.RightArm1.render(f5);
        this.Head.render(f5);
        this.BodyLayer.render(f5);
        this.RightLeg1.render(f5);
        this.RightLeg2.render(f5);
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
