package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * WareBat - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelWareBat extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Neck;
    public ModelRenderer RightWing1;
    public ModelRenderer LeftWing1;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Tail;
    public ModelRenderer Head;
    public ModelRenderer Snout;
    public ModelRenderer Teeth;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer RightWing2;
    public ModelRenderer LeftWing2;
    public ModelRenderer RightFoot;
    public ModelRenderer LeftFoot;

    public ModelWareBat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftLeg = new ModelRenderer(this, 0, 12);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 2.0F, 4.0F);
        this.LeftLeg.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Neck = new ModelRenderer(this, 0, 12);
        this.Neck.setRotationPoint(0.0F, 0.0F, -6.0F);
        this.Neck.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 6, 0.0F);
        this.Body = new ModelRenderer(this, 28, 9);
        this.Body.setRotationPoint(0.0F, 13.0F, 3.5F);
        this.Body.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 5, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Head.addBox(-4.0F, -3.0F, -4.0F, 8, 6, 4, 0.0F);
        this.LeftEar = new ModelRenderer(this, 56, 0);
        this.LeftEar.mirror = true;
        this.LeftEar.setRotationPoint(3.75F, -2.5F, -1.0F);
        this.LeftEar.addBox(-1.5F, -3.0F, -0.5F, 3, 4, 1, 0.0F);
        this.LeftFoot = new ModelRenderer(this, 28, 20);
        this.LeftFoot.mirror = true;
        this.LeftFoot.setRotationPoint(0.0F, 2.0F, 1.0F);
        this.LeftFoot.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
        this.RightWing1 = new ModelRenderer(this, -12, 26);
        this.RightWing1.setRotationPoint(-3.0F, -1.0F, -0.25F);
        this.RightWing1.addBox(-8.0F, 0.0F, -6.0F, 8, 0, 12, 0.0F);
        this.Snout = new ModelRenderer(this, 24, 2);
        this.Snout.setRotationPoint(0.0F, 1.5F, -4.0F);
        this.Snout.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 2, 0.0F);
        this.LeftWing1 = new ModelRenderer(this, -12, 26);
        this.LeftWing1.mirror = true;
        this.LeftWing1.setRotationPoint(3.0F, -1.0F, -0.25F);
        this.LeftWing1.addBox(0.0F, 0.0F, -6.0F, 8, 0, 12, 0.0F);
        this.RightEar = new ModelRenderer(this, 56, 0);
        this.RightEar.setRotationPoint(-3.75F, -2.5F, -1.0F);
        this.RightEar.addBox(-1.5F, -3.0F, -0.5F, 3, 4, 1, 0.0F);
        this.Teeth = new ModelRenderer(this, 36, 0);
        this.Teeth.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.Teeth.addBox(-2.0F, 0.0F, -3.0F, 4, 1, 6, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 12);
        this.RightLeg.setRotationPoint(-2.0F, 2.0F, 4.0F);
        this.RightLeg.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.RightFoot = new ModelRenderer(this, 28, 20);
        this.RightFoot.setRotationPoint(0.0F, 2.0F, 1.0F);
        this.RightFoot.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
        this.LeftWing2 = new ModelRenderer(this, 6, 26);
        this.LeftWing2.mirror = true;
        this.LeftWing2.setRotationPoint(8.0F, 0.0F, 0.0F);
        this.LeftWing2.addBox(0.0F, 0.0F, -6.0F, 10, 0, 12, 0.0F);
        this.Tail = new ModelRenderer(this, 54, 10);
        this.Tail.setRotationPoint(0.0F, 0.0F, 4.5F);
        this.Tail.addBox(-1.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
        this.RightWing2 = new ModelRenderer(this, 6, 26);
        this.RightWing2.setRotationPoint(-8.0F, 0.0F, 0.0F);
        this.RightWing2.addBox(-10.0F, 0.0F, -6.0F, 10, 0, 12, 0.0F);
        this.Body.addChild(this.LeftLeg);
        this.Body.addChild(this.Neck);
        this.Neck.addChild(this.Head);
        this.Head.addChild(this.LeftEar);
        this.LeftLeg.addChild(this.LeftFoot);
        this.Body.addChild(this.RightWing1);
        this.Head.addChild(this.Snout);
        this.Body.addChild(this.LeftWing1);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.Teeth);
        this.Body.addChild(this.RightLeg);
        this.RightLeg.addChild(this.RightFoot);
        this.LeftWing1.addChild(this.LeftWing2);
        this.Body.addChild(this.Tail);
        this.RightWing1.addChild(this.RightWing2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
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
