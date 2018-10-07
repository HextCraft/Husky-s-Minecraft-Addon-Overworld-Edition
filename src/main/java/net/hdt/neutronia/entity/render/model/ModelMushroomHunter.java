package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMushroomHunter - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMushroomHunter extends ModelBase {
    public ModelRenderer Hat;
    public ModelRenderer Head;
    public ModelRenderer BodyLayer;
    public ModelRenderer RightArmLayer;
    public ModelRenderer LeftArmLayer;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Body;
    public ModelRenderer Mushroom1;
    public ModelRenderer Mushroom2;

    public ModelMushroomHunter() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.Hat = new ModelRenderer(this, 32, 0);
        this.Hat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.BodyLayer = new ModelRenderer(this, 16, 32);
        this.BodyLayer.setRotationPoint(0.0F, 0.0F, 0.05F);
        this.BodyLayer.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.Mushroom2 = new ModelRenderer(this, 0, 63);
        this.Mushroom2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom2.addBox(-5.0F, -7.0F, -5.0F, 10, 1, 10, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 16);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftArmLayer = new ModelRenderer(this, 40, 32);
        this.LeftArmLayer.mirror = true;
        this.LeftArmLayer.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArmLayer.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.25F);
        this.RightArmLayer = new ModelRenderer(this, 40, 32);
        this.RightArmLayer.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArmLayer.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.25F);
        this.RightLeg = new ModelRenderer(this, 0, 16);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Body = new ModelRenderer(this, 16, 16);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.RightArm = new ModelRenderer(this, 40, 16);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.Mushroom1 = new ModelRenderer(this, 16, 48);
        this.Mushroom1.setRotationPoint(0.0F, -0.1F, 0.05F);
        this.Mushroom1.addBox(-6.0F, -6.0F, -6.0F, 12, 3, 12, 0.0F);
        this.setRotateAngle(Mushroom1, 0.005235987755982988F, 0.0F, 0.0F);
        this.LeftArm = new ModelRenderer(this, 40, 16);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.Head.addChild(this.Mushroom2);
        this.Head.addChild(this.Mushroom1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Hat.render(f5);
        this.BodyLayer.render(f5);
        this.Head.render(f5);
        this.LeftLeg.render(f5);
        this.LeftArmLayer.render(f5);
        this.RightArmLayer.render(f5);
        this.RightLeg.render(f5);
        this.Body.render(f5);
        this.RightArm.render(f5);
        this.LeftArm.render(f5);
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
