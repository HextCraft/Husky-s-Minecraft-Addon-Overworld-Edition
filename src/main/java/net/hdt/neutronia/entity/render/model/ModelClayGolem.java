package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelClayGolem - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelClayGolem extends ModelBase {
    public ModelRenderer Head2;
    public ModelRenderer Head4;
    public ModelRenderer Body1;
    public ModelRenderer RightArm1;
    public ModelRenderer LeftArm1;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Head1;
    public ModelRenderer Head3;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer RightArm2;
    public ModelRenderer LeftArm2;

    public ModelClayGolem() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.Head4 = new ModelRenderer(this, 32, 0);
        this.Head4.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Head4.addBox(-4.0F, -9.0F, -4.0F, 8, 10, 8, -0.25F);
        this.Head3 = new ModelRenderer(this, 0, 30);
        this.Head3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head3.addBox(-5.0F, -6.0F, -5.0F, 10, 6, 10, 0.0F);
        this.RightArm2 = new ModelRenderer(this, 0, 70);
        this.RightArm2.setRotationPoint(-1.0F, 16.5F, 0.0F);
        this.RightArm2.addBox(-2.5F, 0.0F, -4.0F, 5, 5, 8, 0.0F);
        this.LeftArm2 = new ModelRenderer(this, 0, 70);
        this.LeftArm2.mirror = true;
        this.LeftArm2.setRotationPoint(1.0F, 16.5F, 0.0F);
        this.LeftArm2.addBox(-2.5F, 0.0F, -4.0F, 5, 5, 8, 0.0F);
        this.Body1 = new ModelRenderer(this, 30, 20);
        this.Body1.setRotationPoint(0.0F, -4.5F, 0.0F);
        this.Body1.addBox(-5.0F, 0.0F, -3.5F, 10, 1, 7, 0.0F);
        this.Head2 = new ModelRenderer(this, 0, 0);
        this.Head2.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Head2.addBox(-4.0F, -9.0F, -4.0F, 8, 10, 8, 0.0F);
        this.Body3 = new ModelRenderer(this, 32, 30);
        this.Body3.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.Body3.addBox(-4.0F, 0.0F, -3.0F, 8, 4, 6, 0.0F);
        this.RightArm1 = new ModelRenderer(this, 0, 46);
        this.RightArm1.setRotationPoint(-7.5F, -2.5F, 0.0F);
        this.RightArm1.addBox(-3.0F, -2.0F, -2.5F, 4, 18, 5, 0.0F);
        this.Head1 = new ModelRenderer(this, 0, 18);
        this.Head1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head1.addBox(-5.0F, -9.0F, -5.0F, 10, 2, 10, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 84);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.5F, 11.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -3.5F, 4, 13, 7, 0.0F);
        this.Body2 = new ModelRenderer(this, 22, 46);
        this.Body2.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.Body2.addBox(-6.0F, 0.0F, -4.5F, 12, 10, 9, 0.0F);
        this.LeftArm1 = new ModelRenderer(this, 0, 46);
        this.LeftArm1.mirror = true;
        this.LeftArm1.setRotationPoint(7.5F, -2.5F, 0.0F);
        this.LeftArm1.addBox(-1.0F, -2.0F, -2.5F, 4, 18, 5, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 84);
        this.RightLeg.setRotationPoint(-2.5F, 11.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -3.5F, 4, 13, 7, 0.0F);
        this.Head2.addChild(this.Head3);
        this.RightArm1.addChild(this.RightArm2);
        this.LeftArm1.addChild(this.LeftArm2);
        this.Body2.addChild(this.Body3);
        this.Head2.addChild(this.Head1);
        this.Body1.addChild(this.Body2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Head4.render(f5);
        this.Body1.render(f5);
        this.Head2.render(f5);
        this.RightArm1.render(f5);
        this.LeftLeg.render(f5);
        this.LeftArm1.render(f5);
        this.RightLeg.render(f5);
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
