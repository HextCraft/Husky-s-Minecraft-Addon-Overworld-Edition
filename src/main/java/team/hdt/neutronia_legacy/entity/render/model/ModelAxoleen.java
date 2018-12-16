package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Axoleen - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelAxoleen extends ModelBase {
    public ModelRenderer BlowHole2;
    public ModelRenderer BlowHole1;
    public ModelRenderer LeftEye;
    public ModelRenderer Head;
    public ModelRenderer Lips;
    public ModelRenderer Mouth;
    public ModelRenderer Body1;
    public ModelRenderer RightFlipper;
    public ModelRenderer LeftFinger;
    public ModelRenderer Body2;
    public ModelRenderer RightEye;
    public ModelRenderer Tail;
    public ModelRenderer BackFin;
    public ModelRenderer BlowHole3;
    public ModelRenderer FrontFin;

    public ModelAxoleen() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.BlowHole2 = new ModelRenderer(this, 77, 16);
        this.BlowHole2.setRotationPoint(0.0F, -1.0F, -17.0F);
        this.BlowHole2.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 5, 0.0F);
        this.Lips = new ModelRenderer(this, 0, 46);
        this.Lips.setRotationPoint(0.0F, 15.0F, -12.5F);
        this.Lips.addBox(-12.5F, -1.0F, -8.5F, 25, 2, 17, 0.0F);
        this.BlowHole3 = new ModelRenderer(this, 86, 17);
        this.BlowHole3.setRotationPoint(0.0F, -2.0F, -20.0F);
        this.BlowHole3.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 12.5F, 0.0F);
        this.Head.addBox(-11.5F, -11.5F, -20.0F, 23, 23, 23, 0.0F);
        this.RightEye = new ModelRenderer(this, 69, 12);
        this.RightEye.setRotationPoint(-10.0F, 11.0F, -19.0F);
        this.RightEye.addBox(-2.0F, -0.5F, -1.5F, 4, 1, 3, 0.0F);
        this.setRotateAngle(RightEye, 0.0F, 0.0F, -0.04363323129985824F);
        this.Mouth = new ModelRenderer(this, 0, 65);
        this.Mouth.setRotationPoint(0.0F, 15.0F, -4.75F);
        this.Mouth.addBox(-12.0F, 0.0F, -16.0F, 24, 10, 16, 0.0F);
        this.setRotateAngle(Mouth, 0.22759093446006054F, 0.0F, 0.0F);
        this.FrontFin = new ModelRenderer(this, 114, 28);
        this.FrontFin.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.FrontFin.addBox(-1.0F, -18.0F, -8.0F, 2, 20, 16, 0.0F);
        this.setRotateAngle(FrontFin, -0.08726646259971647F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 91);
        this.Body1.setRotationPoint(0.0F, 13.5F, 0.0F);
        this.Body1.addBox(-10.0F, -8.5F, 0.0F, 20, 17, 20, 0.0F);
        this.Body2 = new ModelRenderer(this, 80, 64);
        this.Body2.setRotationPoint(0.0F, 14.0F, 17.0F);
        this.Body2.addBox(-7.0F, -7.0F, 0.0F, 14, 14, 24, 0.0F);
        this.Tail = new ModelRenderer(this, 80, 102);
        this.Tail.setRotationPoint(0.0F, 15.0F, 41.0F);
        this.Tail.addBox(-21.0F, -1.0F, -7.0F, 42, 2, 24, 0.0F);
        this.RightFlipper = new ModelRenderer(this, 95, 0);
        this.RightFlipper.setRotationPoint(-10.0F, 20.0F, 11.0F);
        this.RightFlipper.addBox(-1.0F, 0.0F, -7.5F, 1, 23, 15, 0.0F);
        this.setRotateAngle(RightFlipper, 0.0F, 0.0F, 0.6981317007977318F);
        this.LeftFinger = new ModelRenderer(this, 95, 0);
        this.LeftFinger.mirror = true;
        this.LeftFinger.setRotationPoint(10.0F, 20.0F, 11.0F);
        this.LeftFinger.addBox(0.0F, 0.0F, -7.5F, 1, 23, 15, 0.0F);
        this.setRotateAngle(LeftFinger, 0.0F, 0.0F, -0.6981317007977318F);
        this.BlowHole1 = new ModelRenderer(this, 69, 17);
        this.BlowHole1.setRotationPoint(0.0F, 2.0F, -16.0F);
        this.BlowHole1.addBox(-1.0F, -3.0F, -1.0F, 2, 4, 2, 0.0F);
        this.BackFin = new ModelRenderer(this, 69, 0);
        this.BackFin.setRotationPoint(0.0F, 8.0F, 35.0F);
        this.BackFin.addBox(-1.0F, -6.0F, -3.0F, 2, 6, 6, 0.0F);
        this.LeftEye = new ModelRenderer(this, 69, 12);
        this.LeftEye.mirror = true;
        this.LeftEye.setRotationPoint(10.0F, 11.0F, -19.0F);
        this.LeftEye.addBox(-2.0F, -0.5F, -1.5F, 4, 1, 3, 0.0F);
        this.setRotateAngle(LeftEye, 0.0F, 0.0F, 0.04363323129985824F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.BlowHole2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Lips.offsetX, this.Lips.offsetY, this.Lips.offsetZ);
        GlStateManager.translate(this.Lips.rotationPointX * f5, this.Lips.rotationPointY * f5, this.Lips.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 0.9D, 1.0D);
        GlStateManager.translate(-this.Lips.offsetX, -this.Lips.offsetY, -this.Lips.offsetZ);
        GlStateManager.translate(-this.Lips.rotationPointX * f5, -this.Lips.rotationPointY * f5, -this.Lips.rotationPointZ * f5);
        this.Lips.render(f5);
        GlStateManager.popMatrix();
        this.BlowHole3.render(f5);
        this.Head.render(f5);
        this.RightEye.render(f5);
        this.Mouth.render(f5);
        this.FrontFin.render(f5);
        this.Body1.render(f5);
        this.Body2.render(f5);
        this.Tail.render(f5);
        this.RightFlipper.render(f5);
        this.LeftFinger.render(f5);
        this.BlowHole1.render(f5);
        this.BackFin.render(f5);
        this.LeftEye.render(f5);
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
