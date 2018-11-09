package sssss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelReceptionistVillager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelReceptionistVillager extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer MiddleArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer HeadLayer;
    public ModelRenderer BodyLayer;
    public ModelRenderer Nose;
    public ModelRenderer LeftArm;
    public ModelRenderer RightArm;

    public ModelReceptionistVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftLeg = new ModelRenderer(this, 48, 0);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.BodyLayer = new ModelRenderer(this, 0, 36);
        this.BodyLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BodyLayer.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.Nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
        this.LeftArm = new ModelRenderer(this, 32, 0);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.RightArm = new ModelRenderer(this, 32, 0);
        this.RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.MiddleArm = new ModelRenderer(this, 28, 14);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 48, 0);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Body = new ModelRenderer(this, 0, 18);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.HeadLayer = new ModelRenderer(this, 30, 45);
        this.HeadLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadLayer.addBox(-4.0F, -10.0F, -4.5F, 8, 10, 9, 0.5F);
        this.Head.addChild(this.Nose);
        this.MiddleArm.addChild(this.LeftArm);
        this.MiddleArm.addChild(this.RightArm);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LeftLeg.render(f5);
        this.BodyLayer.render(f5);
        this.MiddleArm.render(f5);
        this.RightLeg.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.HeadLayer.render(f5);
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
