package sss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMiniCow - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMiniCow extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer RightHorn;
    public ModelRenderer LeftHorn;
    public ModelRenderer Udder;

    public ModelMiniCow() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.Body = new ModelRenderer(this, 0, 11);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 6, 0.0F);
        this.RightFrontLeg = new ModelRenderer(this, 24, 0);
        this.RightFrontLeg.setRotationPoint(-1.4F, 20.0F, 1.0F);
        this.RightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.RightHorn = new ModelRenderer(this, 0, 0);
        this.RightHorn.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightHorn.addBox(-4.0F, -4.0F, -3.0F, 1, 3, 1, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 24, 0);
        this.RightBackLeg.setRotationPoint(-1.4F, 20.0F, 4.5F);
        this.RightBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.LeftBackLeg = new ModelRenderer(this, 24, 0);
        this.LeftBackLeg.setRotationPoint(1.4F, 20.0F, 4.5F);
        this.LeftBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.LeftHorn = new ModelRenderer(this, 0, 0);
        this.LeftHorn.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftHorn.addBox(3.0F, -4.0F, -3.0F, 1, 3, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Head.addBox(-3.0F, -3.0F, -5.0F, 6, 6, 5, 0.0F);
        this.LeftFrontLeg = new ModelRenderer(this, 24, 0);
        this.LeftFrontLeg.setRotationPoint(1.4F, 20.0F, 1.0F);
        this.LeftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.Udder = new ModelRenderer(this, 16, 13);
        this.Udder.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Udder.addBox(-1.0F, 1.5F, 2.9F, 2, 1, 3, 0.0F);
        this.Head.addChild(this.RightHorn);
        this.Head.addChild(this.LeftHorn);
        this.Body.addChild(this.Udder);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
        this.RightFrontLeg.render(f5);
        this.RightBackLeg.render(f5);
        this.LeftBackLeg.render(f5);
        this.Head.render(f5);
        this.LeftFrontLeg.render(f5);
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
