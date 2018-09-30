package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelStoneGolem - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelStoneGolem extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Hair;
    public ModelRenderer RightFoot;
    public ModelRenderer LeftFoot;

    public ModelStoneGolem() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.RightFoot = new ModelRenderer(this, 0, 17);
        this.RightFoot.setRotationPoint(-1.0F, 11.0F, 0.0F);
        this.RightFoot.addBox(-2.5F, -1.0F, -5.5F, 5, 3, 8, 0.0F);
        this.RightLeg = new ModelRenderer(this, 48, 0);
        this.RightLeg.setRotationPoint(-4.0F, 11.0F, 0.0F);
        this.RightLeg.addBox(-3.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 48, 0);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(4.0F, 11.0F, 0.0F);
        this.LeftLeg.addBox(-1.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Hair = new ModelRenderer(this, 0, 0);
        this.Hair.setRotationPoint(-0.9F, -4.5F, -1.4F);
        this.Hair.addBox(0.0F, -4.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(Hair, 0.08726646259971647F, 0.4363323129985824F, 0.0F);
        this.LeftFoot = new ModelRenderer(this, 0, 17);
        this.LeftFoot.mirror = true;
        this.LeftFoot.setRotationPoint(1.0F, 11.0F, 0.0F);
        this.LeftFoot.addBox(-2.5F, -1.0F, -5.5F, 5, 3, 8, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 12.5F, -1.0F);
        this.Head.addBox(-6.0F, -4.5F, -6.0F, 12, 5, 12, 0.0F);
        this.RightLeg.addChild(this.RightFoot);
        this.Head.addChild(this.Hair);
        this.LeftLeg.addChild(this.LeftFoot);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.RightLeg.render(f5);
        this.LeftLeg.render(f5);
        this.Head.render(f5);
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
