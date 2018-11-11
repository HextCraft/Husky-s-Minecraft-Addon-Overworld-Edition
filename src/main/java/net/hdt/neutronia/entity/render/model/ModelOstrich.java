package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelOstrich - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelOstrich extends ModelBase {
    public ModelRenderer BodyNeck;
    public ModelRenderer Body;
    public ModelRenderer Tail;
    public ModelRenderer RightWing;
    public ModelRenderer LeftWing;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Neck1;
    public ModelRenderer Neck2;
    public ModelRenderer Head;
    public ModelRenderer Beak;

    public ModelOstrich() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Body = new ModelRenderer(this, 20, 40);
        this.Body.setRotationPoint(0.0F, 11.0F, 11.0F);
        this.Body.addBox(-4.0F, -10.0F, -7.0F, 8, 10, 14, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 53);
        this.Tail.setRotationPoint(0.0F, 6.0F, 17.5F);
        this.Tail.addBox(-2.5F, -1.0F, -1.0F, 5, 8, 3, 0.0F);
        this.setRotateAngle(Tail, 0.08726646259971647F, 0.0F, 0.0F);
        this.Beak = new ModelRenderer(this, 26, 33);
        this.Beak.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Beak.addBox(-2.5F, -9.0F, -5.0F, 5, 2, 5, 0.0F);
        this.RightLeg = new ModelRenderer(this, 48, 9);
        this.RightLeg.setRotationPoint(-2.5F, 10.0F, 11.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -4.0F, 4, 14, 4, 0.0F);
        this.BodyNeck = new ModelRenderer(this, 0, 0);
        this.BodyNeck.setRotationPoint(0.0F, 9.5F, 4.0F);
        this.BodyNeck.addBox(-3.5F, -6.0F, -3.5F, 7, 7, 4, 0.0F);
        this.Neck1 = new ModelRenderer(this, 0, 11);
        this.Neck1.setRotationPoint(0.0F, -3.0F, -3.5F);
        this.Neck1.addBox(-2.0F, -2.0F, -3.5F, 4, 4, 4, 0.0F);
        this.setRotateAngle(Neck1, -0.03490658503988659F, 0.0F, 0.0F);
        this.RightWing = new ModelRenderer(this, 46, 27);
        this.RightWing.mirror = true;
        this.RightWing.setRotationPoint(3.5F, 5.9F, 11.5F);
        this.RightWing.addBox(0.0F, 0.0F, -4.0F, 1, 5, 8, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 48, 9);
        this.LeftLeg.setRotationPoint(2.5F, 10.0F, 11.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -4.0F, 4, 14, 4, 0.0F);
        this.Neck2 = new ModelRenderer(this, 0, 19);
        this.Neck2.setRotationPoint(0.0F, 0.0F, -4.5F);
        this.Neck2.addBox(-2.0F, -6.0F, -3.0F, 4, 8, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 31);
        this.Head.setRotationPoint(-0.01F, -5.0F, -3.5F);
        this.Head.addBox(-2.0F, -11.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftWing = new ModelRenderer(this, 46, 27);
        this.LeftWing.setRotationPoint(-3.5F, 5.9F, 11.5F);
        this.LeftWing.addBox(-1.0F, 0.0F, -4.0F, 1, 5, 8, 0.0F);
        this.Head.addChild(this.Beak);
        this.BodyNeck.addChild(this.Neck1);
        this.Neck1.addChild(this.Neck2);
        this.Neck1.addChild(this.Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
        this.Tail.render(f5);
        this.RightLeg.render(f5);
        this.BodyNeck.render(f5);
        this.RightWing.render(f5);
        this.LeftLeg.render(f5);
        this.LeftWing.render(f5);
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
