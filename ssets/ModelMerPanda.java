package ssss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * MerPanda - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMerPanda extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer Tail1;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer Snout;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;

    public ModelMerPanda() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.LeftArm = new ModelRenderer(this, 40, 73);
        this.LeftArm.setRotationPoint(5.0F, -4.0F, -6.0F);
        this.LeftArm.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6, 0.0F);
        this.setRotateAngle(LeftArm, -0.8726646259971648F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 19);
        this.Body.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.Body.addBox(-9.5F, 0.0F, -6.5F, 19, 21, 13, 0.0F);
        this.RightArm = new ModelRenderer(this, 40, 73);
        this.RightArm.setRotationPoint(-5.0F, -4.0F, -6.0F);
        this.RightArm.addBox(-3.0F, 0.0F, -3.2F, 6, 9, 6, 0.0F);
        this.setRotateAngle(RightArm, -0.8726646259971648F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -13.0F, 0.0F);
        this.Head.addBox(-6.5F, -6.0F, -4.5F, 13, 10, 9, 0.0F);
        this.RightEar = new ModelRenderer(this, 44, 8);
        this.RightEar.setRotationPoint(-6.0F, -5.0F, 0.5F);
        this.RightEar.addBox(-2.5F, -4.0F, 0.0F, 5, 4, 1, 0.0F);
        this.setRotateAngle(RightEar, -0.2617993877991494F, 0.0F, 0.0F);
        this.Tail2 = new ModelRenderer(this, 0, 73);
        this.Tail2.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.Tail2.addBox(-4.5F, 0.0F, -3.0F, 9, 12, 6, 0.0F);
        this.setRotateAngle(Tail2, 0.4363323129985824F, 0.0F, 0.0F);
        this.Tail3 = new ModelRenderer(this, 0, 91);
        this.Tail3.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Tail3.addBox(-12.0F, 0.0F, 0.0F, 24, 24, 1, 0.0F);
        this.setRotateAngle(Tail3, 0.4363323129985824F, 0.0F, 0.0F);
        this.Snout = new ModelRenderer(this, 35, 0);
        this.Snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Snout.addBox(-3.5F, -1.0F, -7.5F, 7, 5, 3, 0.0F);
        this.Tail1 = new ModelRenderer(this, 0, 53);
        this.Tail1.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.Tail1.addBox(-8.0F, 0.0F, -5.0F, 16, 10, 10, 0.0F);
        this.setRotateAngle(Tail1, 0.4363323129985824F, 0.0F, 0.0F);
        this.LeftEar = new ModelRenderer(this, 44, 8);
        this.LeftEar.setRotationPoint(6.0F, -5.0F, 0.5F);
        this.LeftEar.addBox(-2.5F, -4.0F, 0.0F, 5, 4, 1, 0.0F);
        this.setRotateAngle(LeftEar, -0.2617993877991494F, 0.0F, 0.0F);
        this.Head.addChild(this.RightEar);
        this.Tail1.addChild(this.Tail2);
        this.Tail2.addChild(this.Tail3);
        this.Head.addChild(this.Snout);
        this.Head.addChild(this.LeftEar);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LeftArm.render(f5);
        this.Body.render(f5);
        this.RightArm.render(f5);
        this.Head.render(f5);
        this.Tail1.render(f5);
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
