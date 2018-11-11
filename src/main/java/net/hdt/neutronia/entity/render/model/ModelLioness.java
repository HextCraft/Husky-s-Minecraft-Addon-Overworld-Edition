package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Modellion - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelLioness extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;
    public ModelRenderer shape1_10;
    public ModelRenderer shape1_11;

    public ModelLioness() {
        this.textureWidth = 70;
        this.textureHeight = 50;
        this.shape1_4 = new ModelRenderer(this, 12, 12);
        this.shape1_4.setRotationPoint(1.5F, 14.0F, 4.5F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.shape1_6 = new ModelRenderer(this, 12, 12);
        this.shape1_6.setRotationPoint(-4.5F, 14.0F, 4.5F);
        this.shape1_6.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 0, 13);
        this.shape1_2.setRotationPoint(-4.5F, 15.0F, -7.5F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.shape1_10 = new ModelRenderer(this, 0, 25);
        this.shape1_10.setRotationPoint(-0.5F, 10.0F, 7.0F);
        this.shape1_10.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 42, 18);
        this.shape1_5.setRotationPoint(-4.0F, 9.0F, 2.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 8, 7, 6, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 32, 0);
        this.shape1_7.setRotationPoint(1.5F, 14.0F, -9.0F);
        this.shape1_7.addBox(-3.0F, -3.0F, -6.0F, 3, 3, 3, 0.0F);
        this.shape1_11 = new ModelRenderer(this, 34, 31);
        this.shape1_11.setRotationPoint(-5.0F, 6.0F, -10.0F);
        this.shape1_11.addBox(0.0F, 0.0F, 0.0F, 10, 10, 7, 0.0F);
        this.shape1 = new ModelRenderer(this, 34, 0);
        this.shape1.setRotationPoint(-4.0F, 9.0F, -8.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 8, 8, 10, 0.0F);
        this.shape1_8 = new ModelRenderer(this, 40, 18);
        this.shape1_8.setRotationPoint(-0.5F, 9.0F, -5.0F);
        this.shape1_8.addBox(-3.0F, -3.0F, -6.0F, 3, 3, 1, 0.0F);
        this.shape1_9 = new ModelRenderer(this, 40, 18);
        this.shape1_9.setRotationPoint(3.5F, 9.0F, -5.0F);
        this.shape1_9.addBox(-3.0F, -3.0F, -6.0F, 3, 3, 1, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(0.0F, 11.0F, -7.0F);
        this.shape1_1.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 0, 13);
        this.shape1_3.setRotationPoint(1.5F, 15.0F, -7.5F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1_4.render(f5);
        this.shape1_6.render(f5);
        this.shape1_2.render(f5);
        this.shape1_10.render(f5);
        this.shape1_5.render(f5);
        this.shape1_7.render(f5);
        this.shape1_11.render(f5);
        this.shape1.render(f5);
        this.shape1_8.render(f5);
        this.shape1_9.render(f5);
        this.shape1_1.render(f5);
        this.shape1_3.render(f5);
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
