package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Netherreal - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelNetherreal extends ModelBase {
    public ModelRenderer BodyLayer;
    public ModelRenderer HeadLayer;
    public ModelRenderer Head;
    public ModelRenderer RightJaw;
    public ModelRenderer LeftJaw;
    public ModelRenderer RightWing1;
    public ModelRenderer RightWing2;
    public ModelRenderer LeftWing1;
    public ModelRenderer LeftWing2;
    public ModelRenderer RightAntenna;
    public ModelRenderer LeftAntenna;
    public ModelRenderer Body;
    public ModelRenderer Tongue1;
    public ModelRenderer Tongue2;
    public ModelRenderer Tongue3;
    public ModelRenderer Tongue4;
    public ModelRenderer Tongue5;

    public ModelNetherreal() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.LeftWing1 = new ModelRenderer(this, 66, 14);
        this.LeftWing1.mirror = true;
        this.LeftWing1.setRotationPoint(7.0F, -6.5F, -3.0F);
        this.LeftWing1.addBox(0.0F, 0.0F, -5.0F, 20, 0, 10, 0.0F);
        this.setRotateAngle(LeftWing1, 0.0F, 0.0F, -0.4363323129985824F);
        this.BodyLayer = new ModelRenderer(this, 56, 64);
        this.BodyLayer.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.BodyLayer.addBox(-6.0F, -6.0F, 0.0F, 12, 12, 24, 0.25F);
        this.setRotateAngle(BodyLayer, -0.5235987755982988F, 0.0F, 0.0F);
        this.RightJaw = new ModelRenderer(this, 0, 6);
        this.RightJaw.setRotationPoint(-3.0F, 5.0F, -20.0F);
        this.RightJaw.addBox(-2.0F, -4.0F, -6.0F, 2, 8, 8, 0.0F);
        this.setRotateAngle(RightJaw, 0.5235987755982988F, -0.17453292519943295F, 0.2617993877991494F);
        this.Tongue5 = new ModelRenderer(this, 0, 63);
        this.Tongue5.setRotationPoint(0.0F, 9.0F, -0.75F);
        this.Tongue5.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 5, 0.0F);
        this.Body = new ModelRenderer(this, 56, 28);
        this.Body.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Body.addBox(-6.0F, -6.0F, 0.0F, 12, 12, 24, 0.0F);
        this.setRotateAngle(Body, -0.5235987755982988F, 0.0F, 0.0F);
        this.LeftAntenna = new ModelRenderer(this, 54, 12);
        this.LeftAntenna.mirror = true;
        this.LeftAntenna.setRotationPoint(1.0F, -5.0F, -20.5F);
        this.LeftAntenna.addBox(-0.5F, -10.0F, 0.0F, 1, 10, 0, 0.0F);
        this.setRotateAngle(LeftAntenna, 0.8726646259971648F, -0.4363323129985824F, 0.0F);
        this.Tongue4 = new ModelRenderer(this, 6, 51);
        this.Tongue4.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Tongue4.addBox(-0.5F, 0.0F, -0.5F, 1, 10, 1, 0.0F);
        this.LeftWing2 = new ModelRenderer(this, 65, 0);
        this.LeftWing2.mirror = true;
        this.LeftWing2.setRotationPoint(7.0F, 5.0F, -2.0F);
        this.LeftWing2.addBox(-1.0F, 0.0F, -5.5F, 22, 0, 11, 0.0F);
        this.setRotateAngle(LeftWing2, 0.0F, 0.0F, 0.17453292519943295F);
        this.Tongue2 = new ModelRenderer(this, 0, 44);
        this.Tongue2.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.Tongue2.addBox(-0.5F, 0.0F, -0.5F, 1, 14, 1, 0.0F);
        this.RightWing1 = new ModelRenderer(this, 66, 14);
        this.RightWing1.setRotationPoint(-7.0F, -6.5F, -3.0F);
        this.RightWing1.addBox(-20.0F, 0.0F, -5.0F, 20, 0, 10, 0.0F);
        this.setRotateAngle(RightWing1, 0.0F, 0.0F, 0.4363323129985824F);
        this.RightWing2 = new ModelRenderer(this, 65, 0);
        this.RightWing2.setRotationPoint(-7.0F, 4.0F, -2.0F);
        this.RightWing2.addBox(-21.0F, 0.0F, -5.5F, 22, 0, 11, 0.0F);
        this.setRotateAngle(RightWing2, 0.0F, 0.0F, -0.2617993877991494F);
        this.Tongue1 = new ModelRenderer(this, 0, 38);
        this.Tongue1.setRotationPoint(0.0F, 4.0F, -21.0F);
        this.Tongue1.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.RightAntenna = new ModelRenderer(this, 54, 12);
        this.RightAntenna.setRotationPoint(-1.0F, -5.0F, -20.5F);
        this.RightAntenna.addBox(-0.5F, -10.0F, 0.0F, 1, 10, 0, 0.0F);
        this.setRotateAngle(RightAntenna, 0.8726646259971648F, 0.4363323129985824F, 0.0F);
        this.LeftJaw = new ModelRenderer(this, 0, 6);
        this.LeftJaw.mirror = true;
        this.LeftJaw.setRotationPoint(3.0F, 5.0F, -20.0F);
        this.LeftJaw.addBox(0.0F, -4.0F, -6.0F, 2, 8, 8, 0.0F);
        this.setRotateAngle(LeftJaw, 0.5235987755982988F, 0.17453292519943295F, -0.2617993877991494F);
        this.HeadLayer = new ModelRenderer(this, 0, 48);
        this.HeadLayer.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.HeadLayer.addBox(-7.0F, -7.0F, -21.0F, 14, 14, 24, 0.25F);
        this.Tongue3 = new ModelRenderer(this, 6, 38);
        this.Tongue3.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.Tongue3.addBox(-0.5F, 0.0F, -0.5F, 1, 12, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.Head.addBox(-7.0F, -7.0F, -21.0F, 14, 14, 24, 0.0F);
        this.Head.addChild(this.LeftWing1);
        this.Head.addChild(this.RightJaw);
        this.Tongue4.addChild(this.Tongue5);
        this.Head.addChild(this.Body);
        this.Head.addChild(this.LeftAntenna);
        this.Tongue3.addChild(this.Tongue4);
        this.Head.addChild(this.LeftWing2);
        this.Tongue1.addChild(this.Tongue2);
        this.Head.addChild(this.RightWing1);
        this.Head.addChild(this.RightWing2);
        this.Head.addChild(this.Tongue1);
        this.Head.addChild(this.RightAntenna);
        this.Head.addChild(this.LeftJaw);
        this.Tongue2.addChild(this.Tongue3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.BodyLayer.render(f5);
        this.HeadLayer.render(f5);
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
