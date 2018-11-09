package ssss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMiniDrowned - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMiniDrowned extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer HeadLayer;
    public ModelRenderer Body;
    public ModelRenderer BodyLayer;
    public ModelRenderer RightLeg;
    public ModelRenderer RightLegLayer;
    public ModelRenderer LeftLeg;
    public ModelRenderer LeftLegLayer;
    public ModelRenderer RightArmLayer;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer LeftArmLayer;

    public ModelMiniDrowned() {
        this.textureWidth = 32;
        this.textureHeight = 64;
        this.RightLegLayer = new ModelRenderer(this, 8, 36);
        this.RightLegLayer.setRotationPoint(-1.0F, 20.0F, 0.0F);
        this.RightLegLayer.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.13F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);
        this.RightArm = new ModelRenderer(this, 0, 30);
        this.RightArm.setRotationPoint(-2.0F, 17.0F, 0.0F);
        this.RightArm.addBox(-2.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(RightArm, -1.5707963267948966F, 0.0F, 0.0F);
        this.LeftArmLayer = new ModelRenderer(this, 24, 30);
        this.LeftArmLayer.setRotationPoint(2.0F, 17.0F, 0.0F);
        this.LeftArmLayer.addBox(0.0F, -1.0F, -1.0F, 2, 4, 2, 0.13F);
        this.setRotateAngle(LeftArmLayer, -1.5707963267948966F, 0.0F, 0.0F);
        this.LeftLegLayer = new ModelRenderer(this, 24, 36);
        this.LeftLegLayer.setRotationPoint(1.0F, 20.0F, 0.0F);
        this.LeftLegLayer.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.13F);
        this.RightArmLayer = new ModelRenderer(this, 8, 30);
        this.RightArmLayer.setRotationPoint(-2.0F, 17.0F, 0.0F);
        this.RightArmLayer.addBox(-2.0F, -1.0F, -1.0F, 2, 4, 2, 0.13F);
        this.setRotateAngle(RightArmLayer, -1.5707963267948966F, 0.0F, 0.0F);
        this.BodyLayer = new ModelRenderer(this, 12, 24);
        this.BodyLayer.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.BodyLayer.addBox(-2.0F, 0.0F, -1.0F, 4, 4, 2, 0.13F);
        this.Body = new ModelRenderer(this, 0, 24);
        this.Body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Body.addBox(-2.0F, 0.0F, -1.0F, 4, 4, 2, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 36);
        this.RightLeg.setRotationPoint(-1.0F, 20.0F, 0.0F);
        this.RightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.HeadLayer = new ModelRenderer(this, 0, 12);
        this.HeadLayer.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.HeadLayer.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.25F);
        this.LeftLeg = new ModelRenderer(this, 16, 36);
        this.LeftLeg.setRotationPoint(1.0F, 20.0F, 0.0F);
        this.LeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.LeftArm = new ModelRenderer(this, 16, 30);
        this.LeftArm.setRotationPoint(2.0F, 17.0F, 0.0F);
        this.LeftArm.addBox(0.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(LeftArm, -1.5707963267948966F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.RightLegLayer.render(f5);
        this.Head.render(f5);
        this.RightArm.render(f5);
        this.LeftArmLayer.render(f5);
        this.LeftLegLayer.render(f5);
        this.RightArmLayer.render(f5);
        this.BodyLayer.render(f5);
        this.Body.render(f5);
        this.RightLeg.render(f5);
        this.HeadLayer.render(f5);
        this.LeftLeg.render(f5);
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
