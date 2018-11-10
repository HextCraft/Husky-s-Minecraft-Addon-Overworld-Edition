package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelTaigaVillager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelTaigaVillager extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer MiddleArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer BodyLayer;
    public ModelRenderer HeadLayer;
    public ModelRenderer Nose;
    public ModelRenderer Belt;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;

    public ModelTaigaVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.MiddleArm = new ModelRenderer(this, 40, 56);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.LeftArm = new ModelRenderer(this, 48, 44);
        this.LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 28, 18);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.BodyLayer = new ModelRenderer(this, 0, 36);
        this.BodyLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BodyLayer.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.Body = new ModelRenderer(this, 0, 18);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.HeadLayer = new ModelRenderer(this, 32, 0);
        this.HeadLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadLayer.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.25F);
        this.RightArm = new ModelRenderer(this, 32, 44);
        this.RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.RightLeg = new ModelRenderer(this, 28, 18);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Belt = new ModelRenderer(this, 28, 34);
        this.Belt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Belt.addBox(-5.0F, 9.0F, -4.0F, 10, 2, 8, 0.0F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.Nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
        this.MiddleArm.addChild(this.LeftArm);
        this.MiddleArm.addChild(this.RightArm);
        this.Body.addChild(this.Belt);
        this.Head.addChild(this.Nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.MiddleArm.render(f5);
        this.Head.render(f5);
        this.LeftLeg.render(f5);
        this.BodyLayer.render(f5);
        this.Body.render(f5);
        this.HeadLayer.render(f5);
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
