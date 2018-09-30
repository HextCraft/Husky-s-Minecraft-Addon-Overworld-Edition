package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Netherpillar - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelNetherpillar extends ModelBase {
    public ModelRenderer Body2;
    public ModelRenderer Body1;
    public ModelRenderer Leg3;
    public ModelRenderer Leg4;
    public ModelRenderer Leg5;
    public ModelRenderer Leg6;
    public ModelRenderer Head;
    public ModelRenderer Leg1;
    public ModelRenderer Leg2;
    public ModelRenderer Tongue1;
    public ModelRenderer Tongue2;
    public ModelRenderer Tongue3;
    public ModelRenderer Tongue4;

    public ModelNetherpillar() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Leg4 = new ModelRenderer(this, 0, 5);
        this.Leg4.mirror = true;
        this.Leg4.setRotationPoint(4.0F, 1.5F, 3.0F);
        this.Leg4.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
        this.Tongue4 = new ModelRenderer(this, 52, 15);
        this.Tongue4.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.Tongue4.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Tongue4, 0.08726646259971647F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 24);
        this.Body1.setRotationPoint(0.05F, 0.5F, 3.0F);
        this.Body1.addBox(-4.0F, -12.0F, -4.0F, 8, 12, 8, 0.0F);
        this.setRotateAngle(Body1, 0.6981317007977318F, 0.0F, 0.0F);
        this.Tongue1 = new ModelRenderer(this, 60, 0);
        this.Tongue1.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.Tongue1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Tongue1, -0.6981317007977318F, 0.0F, 0.0F);
        this.Tongue3 = new ModelRenderer(this, 60, 10);
        this.Tongue3.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.Tongue3.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Tongue3, 0.17453292519943295F, 0.0F, 0.0F);
        this.Leg2 = new ModelRenderer(this, 36, 3);
        this.Leg2.mirror = true;
        this.Leg2.setRotationPoint(4.0F, -4.0F, -2.5F);
        this.Leg2.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(Leg2, -1.3089969389957472F, 0.0F, 0.0F);
        this.Leg3 = new ModelRenderer(this, 0, 5);
        this.Leg3.setRotationPoint(-4.0F, 1.5F, 3.0F);
        this.Leg3.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
        this.Tongue2 = new ModelRenderer(this, 60, 5);
        this.Tongue2.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.Tongue2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Tongue2, 0.3490658503988659F, 0.0F, 0.0F);
        this.Leg5 = new ModelRenderer(this, 0, 5);
        this.Leg5.setRotationPoint(-4.0F, 1.5F, 9.0F);
        this.Leg5.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 44);
        this.Body2.setRotationPoint(-0.05F, 18.75F, -1.25F);
        this.Body2.addBox(-4.0F, -4.0F, 0.0F, 8, 7, 12, 0.0F);
        this.Leg6 = new ModelRenderer(this, 0, 5);
        this.Leg6.mirror = true;
        this.Leg6.setRotationPoint(4.0F, 1.5F, 9.0F);
        this.Leg6.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
        this.Leg1 = new ModelRenderer(this, 36, 3);
        this.Leg1.setRotationPoint(-4.0F, -4.0F, -2.5F);
        this.Leg1.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(Leg1, -1.3089969389957472F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -10.0F, 4.0F);
        this.Head.addBox(-6.0F, -6.0F, -10.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(Head, -0.6981317007977318F, 0.0F, 0.0F);
        this.Body2.addChild(this.Leg4);
        this.Tongue3.addChild(this.Tongue4);
        this.Body2.addChild(this.Body1);
        this.Head.addChild(this.Tongue1);
        this.Tongue2.addChild(this.Tongue3);
        this.Body1.addChild(this.Leg2);
        this.Body2.addChild(this.Leg3);
        this.Tongue1.addChild(this.Tongue2);
        this.Body2.addChild(this.Leg5);
        this.Body2.addChild(this.Leg6);
        this.Body1.addChild(this.Leg1);
        this.Body1.addChild(this.Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body2.render(f5);
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
