package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelShroomGlutton - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelShroomGlutton extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Mushroom1;
    public ModelRenderer Mushroom2;
    public ModelRenderer Mushroom3;
    public ModelRenderer Mushroom4;
    public ModelRenderer Mushroom5;
    public ModelRenderer Mushroom6;
    public ModelRenderer Mushroom7;
    public ModelRenderer Mushroom8;

    public ModelShroomGlutton() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new ModelRenderer(this, 40, 16);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Mushroom3 = new ModelRenderer(this, 36, 16);
        this.Mushroom3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom3.addBox(-4.0F, -5.0F, -1.0F, 3, 3, 1, 0.0F);
        this.Mushroom6 = new ModelRenderer(this, 45, 1);
        this.Mushroom6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom6.addBox(1.0F, -5.0F, -3.0F, 1, 3, 3, 0.0F);
        this.Body = new ModelRenderer(this, 16, 16);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.Mushroom2 = new ModelRenderer(this, 32, 1);
        this.Mushroom2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom2.addBox(-2.5F, -14.0F, -6.0F, 1, 6, 6, 0.0F);
        this.Mushroom4 = new ModelRenderer(this, 46, 10);
        this.Mushroom4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom4.addBox(-2.5F, -5.0F, -2.5F, 1, 3, 3, 0.0F);
        this.Mushroom5 = new ModelRenderer(this, 40, 0);
        this.Mushroom5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom5.addBox(-0.5F, -5.0F, -1.5F, 3, 3, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Mushroom8 = new ModelRenderer(this, 54, 5);
        this.Mushroom8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom8.addBox(2.0F, -6.0F, -1.0F, 1, 4, 4, 0.0F);
        this.RightArm = new ModelRenderer(this, 40, 16);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 16);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 32);
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Mushroom7 = new ModelRenderer(this, 54, 0);
        this.Mushroom7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom7.addBox(0.0F, -6.0F, 1.0F, 4, 4, 1, 0.0F);
        this.Mushroom1 = new ModelRenderer(this, 24, 0);
        this.Mushroom1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom1.addBox(-5.5F, -14.0F, -3.0F, 6, 6, 1, 0.0F);
        this.RightArm.addChild(this.Mushroom3);
        this.LeftArm.addChild(this.Mushroom6);
        this.Head.addChild(this.Mushroom2);
        this.RightArm.addChild(this.Mushroom4);
        this.LeftArm.addChild(this.Mushroom5);
        this.LeftArm.addChild(this.Mushroom8);
        this.LeftArm.addChild(this.Mushroom7);
        this.Head.addChild(this.Mushroom1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftArm.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.RightArm.render(f5);
        this.RightLeg.render(f5);
        this.LeftLeg.render(f5);
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
