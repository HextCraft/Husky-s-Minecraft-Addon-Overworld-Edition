package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Panda Bear - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelPandaBear extends ModelBase {
    public ModelRenderer Body2;
    public ModelRenderer Body1;
    public ModelRenderer RightBackFoot;
    public ModelRenderer LeftBackFoot;
    public ModelRenderer RightFrontFoot;
    public ModelRenderer LeftFrontFoot;
    public ModelRenderer Head;
    public ModelRenderer snout;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;

    public ModelPandaBear() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.RightEar = new ModelRenderer(this, 28, 0);
        this.RightEar.setRotationPoint(-5.0F, -2.0F, -2.0F);
        this.RightEar.addBox(-1.5F, -3.0F, -1.0F, 3, 3, 2, 0.0F);
        this.RightFrontFoot = new ModelRenderer(this, 50, 42);
        this.RightFrontFoot.setRotationPoint(-4.0F, -21.0F, -4.0F);
        this.RightFrontFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 5, 0.0F);
        this.setRotateAngle(RightFrontFoot, -1.5707963267948966F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -27.0F, 0.0F);
        this.Head.addBox(-5.0F, -3.5F, -5.0F, 10, 7, 8, 0.0F);
        this.setRotateAngle(Head, -1.5707963267948966F, 0.0F, 0.0F);
        this.LeftFrontFoot = new ModelRenderer(this, 50, 42);
        this.LeftFrontFoot.setRotationPoint(4.0F, -21.0F, -4.0F);
        this.LeftFrontFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 5, 0.0F);
        this.setRotateAngle(LeftFrontFoot, -1.5707963267948966F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 0, 44);
        this.snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.snout.addBox(-2.5F, 0.0F, -8.0F, 5, 3, 3, 0.0F);
        this.Body1 = new ModelRenderer(this, 40, 0);
        this.Body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body1.addBox(-6.5F, -26.0F, -6.0F, 13, 13, 11, 0.0F);
        this.RightBackFoot = new ModelRenderer(this, 52, 24);
        this.RightBackFoot.setRotationPoint(4.5F, -5.0F, -4.0F);
        this.RightBackFoot.addBox(-2.0F, 0.0F, -3.0F, 4, 10, 8, 0.0F);
        this.setRotateAngle(RightBackFoot, -1.5707963267948966F, 0.0F, 0.0F);
        this.LeftBackFoot = new ModelRenderer(this, 52, 24);
        this.LeftBackFoot.setRotationPoint(-4.5F, -5.0F, -4.0F);
        this.LeftBackFoot.addBox(-2.0F, 0.0F, -3.0F, 4, 10, 8, 0.0F);
        this.setRotateAngle(LeftBackFoot, -1.5707963267948966F, 0.0F, 0.0F);
        this.LeftEar = new ModelRenderer(this, 28, 0);
        this.LeftEar.mirror = true;
        this.LeftEar.setRotationPoint(5.0F, -2.0F, -2.0F);
        this.LeftEar.addBox(-1.5F, -3.0F, -1.0F, 3, 3, 2, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 18);
        this.Body2.setRotationPoint(0.0F, 10.0F, 12.0F);
        this.Body2.addBox(-7.0F, -13.0F, -6.0F, 14, 14, 12, 0.0F);
        this.setRotateAngle(Body2, 1.5707963267948966F, 0.0F, 0.0F);
        this.Head.addChild(this.RightEar);
        this.Body1.addChild(this.RightFrontFoot);
        this.Body1.addChild(this.Head);
        this.Body1.addChild(this.LeftFrontFoot);
        this.Head.addChild(this.snout);
        this.Body2.addChild(this.Body1);
        this.Body2.addChild(this.RightBackFoot);
        this.Body2.addChild(this.LeftBackFoot);
        this.Head.addChild(this.LeftEar);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body2.render(f5);
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
