package sssss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMiniEnderMan - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMiniEnderMan extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Mouth;
    public ModelRenderer Body;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;

    public ModelMiniEnderMan() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.LeftArm = new ModelRenderer(this, 28, 6);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(2.5F, 9.0F, 0.0F);
        this.LeftArm.addBox(-0.5F, 0.0F, -0.5F, 1, 12, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.Head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);
        this.RightArm = new ModelRenderer(this, 28, 6);
        this.RightArm.setRotationPoint(-2.5F, 9.0F, 0.0F);
        this.RightArm.addBox(-0.5F, 0.0F, -0.5F, 1, 12, 1, 0.0F);
        this.Body = new ModelRenderer(this, 18, 0);
        this.Body.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.Body.addBox(-2.0F, 0.0F, -1.0F, 4, 4, 2, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 28, 6);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(1.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-0.5F, 0.0F, -0.5F, 1, 12, 1, 0.0F);
        this.Mouth = new ModelRenderer(this, 0, 12);
        this.Mouth.setRotationPoint(0.0F, 9.25F, 0.0F);
        this.Mouth.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, -0.25F);
        this.RightLeg = new ModelRenderer(this, 28, 6);
        this.RightLeg.setRotationPoint(-1.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-0.5F, 0.0F, -0.5F, 1, 12, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LeftArm.render(f5);
        this.Head.render(f5);
        this.RightArm.render(f5);
        this.Body.render(f5);
        this.LeftLeg.render(f5);
        this.Mouth.render(f5);
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
