package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelRetromop - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelRetromop extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLegs;
    public ModelRenderer Snout;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer Body2;
    public ModelRenderer Tail;

    public ModelRetromop() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Tail = new ModelRenderer(this, 22, 18);
        this.Tail.setRotationPoint(0.0F, -1.5F, 8.0F);
        this.Tail.addBox(0.0F, -4.0F, 0.0F, 1, 5, 5, 0.0F);
        this.setRotateAngle(Tail, 1.3089969389957472F, 0.0F, 0.0F);
        this.Body2 = new ModelRenderer(this, 36, 19);
        this.Body2.setRotationPoint(0.0F, 0.25F, -0.5F);
        this.Body2.addBox(-3.0F, -2.5F, 0.0F, 6, 5, 8, 0.0F);
        this.LeftBackLegs = new ModelRenderer(this, 46, 10);
        this.LeftBackLegs.setRotationPoint(2.4F, 17.0F, 2.4F);
        this.LeftBackLegs.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.Head.addBox(-2.5F, -4.0F, -3.5F, 5, 5, 5, 0.0F);
        this.LeftFrontLeg = new ModelRenderer(this, 46, 10);
        this.LeftFrontLeg.setRotationPoint(2.4F, 17.0F, -3.4F);
        this.LeftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 46, 10);
        this.RightBackLeg.setRotationPoint(-2.4F, 17.0F, 2.4F);
        this.RightBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.RightEar = new ModelRenderer(this, 15, 0);
        this.RightEar.setRotationPoint(-2.0F, -4.0F, -0.5F);
        this.RightEar.addBox(-1.0F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(RightEar, -0.17453292519943295F, 0.0F, 0.08726646259971647F);
        this.LeftEar = new ModelRenderer(this, 15, 0);
        this.LeftEar.mirror = true;
        this.LeftEar.setRotationPoint(2.0F, -4.0F, -0.5F);
        this.LeftEar.addBox(0.0F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(LeftEar, -0.17453292519943295F, 0.0F, -0.08726646259971647F);
        this.Body1 = new ModelRenderer(this, 0, 20);
        this.Body1.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.Body1.addBox(-3.5F, -2.0F, 0.0F, 7, 4, 8, 0.0F);
        this.Snout = new ModelRenderer(this, 0, 10);
        this.Snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Snout.addBox(-1.5F, -1.1F, -5.0F, 3, 2, 2, 0.0F);
        this.RightFrontLeg = new ModelRenderer(this, 46, 10);
        this.RightFrontLeg.setRotationPoint(-2.4F, 17.0F, -3.4F);
        this.RightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.Body1.addChild(this.Tail);
        this.Body1.addChild(this.Body2);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.LeftEar);
        this.Head.addChild(this.Snout);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftBackLegs.render(f5);
        this.Head.render(f5);
        this.LeftFrontLeg.render(f5);
        this.RightBackLeg.render(f5);
        this.Body1.render(f5);
        this.RightFrontLeg.render(f5);
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
