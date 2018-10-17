package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelSealtailHorbug - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelSealtailHorbug extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape13;
    public ModelRenderer shape13_1;
    public ModelRenderer shape13_2;
    public ModelRenderer shape13_3;
    public ModelRenderer shape13_4;
    public ModelRenderer shape13_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;

    public ModelSealtailHorbug() {
        this.textureWidth = 104;
        this.textureHeight = 82;
        this.shape13_4 = new ModelRenderer(this, 0, 29);
        this.shape13_4.setRotationPoint(-1.5F, 20.5F, -3.0F);
        this.shape13_4.addBox(-9.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.setRotateAngle(shape13_4, 0.0F, -0.31869712141416456F, -0.18203784098300857F);
        this.shape1_3 = new ModelRenderer(this, 0, 34);
        this.shape1_3.setRotationPoint(-7.0F, 23.9F, 15.0F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 14, 0, 10, 0.0F);
        this.shape1_6 = new ModelRenderer(this, 0, 0);
        this.shape1_6.setRotationPoint(0.0F, -5.0F, -4.0F);
        this.shape1_6.addBox(0.0F, 0.0F, 0.0F, 4, 5, 9, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 29);
        this.shape13.setRotationPoint(-2.0F, 20.5F, -1.0F);
        this.shape13.addBox(-9.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.setRotateAngle(shape13, 0.0F, 0.0F, -0.18203784098300857F);
        this.shape1_5 = new ModelRenderer(this, 61, 0);
        this.shape1_5.setRotationPoint(-2.0F, 15.0F, -8.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 4, 7, 5, 0.0F);
        this.setRotateAngle(shape1_5, 0.22759093446006054F, 0.0F, 0.0F);
        this.shape1 = new ModelRenderer(this, 29, 0);
        this.shape1.setRotationPoint(-3.0F, 17.0F, -5.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 6, 7, 10, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 46, 17);
        this.shape1_1.setRotationPoint(-2.0F, 18.5F, 5.0F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 0, 14);
        this.shape1_7.setRotationPoint(0.0F, 1.0F, -3.0F);
        this.shape1_7.addBox(0.0F, 0.0F, 0.0F, 4, 4, 3, 0.0F);
        this.shape13_2 = new ModelRenderer(this, 0, 29);
        this.shape13_2.setRotationPoint(2.0F, 20.5F, -3.0F);
        this.shape13_2.addBox(0.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.setRotateAngle(shape13_2, 0.0F, 0.31869712141416456F, 0.18203784098300857F);
        this.shape13_3 = new ModelRenderer(this, 0, 29);
        this.shape13_3.setRotationPoint(2.0F, 20.5F, -1.0F);
        this.shape13_3.addBox(0.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.setRotateAngle(shape13_3, 0.0F, 0.0F, 0.18203784098300857F);
        this.shape13_5 = new ModelRenderer(this, 0, 29);
        this.shape13_5.setRotationPoint(-2.0F, 20.5F, 1.0F);
        this.shape13_5.addBox(-9.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.setRotateAngle(shape13_5, 0.0F, 0.31869712141416456F, -0.18203784098300857F);
        this.shape13_1 = new ModelRenderer(this, 0, 29);
        this.shape13_1.setRotationPoint(2.0F, 20.5F, 1.0F);
        this.shape13_1.addBox(0.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.setRotateAngle(shape13_1, 0.0F, -0.31869712141416456F, 0.22759093446006054F);
        this.shape1_4 = new ModelRenderer(this, 10, 45);
        this.shape1_4.setRotationPoint(-3.5F, 19.9F, 17.0F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 7, 0, 10, 0.0F);
        this.setRotateAngle(shape1_4, 0.36425021489121656F, 0.0F, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 29, 17);
        this.shape1_2.setRotationPoint(-2.0F, 18.0F, 9.0F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 9, 0.0F);
        this.shape1_5.addChild(this.shape1_6);
        this.shape1_6.addChild(this.shape1_7);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape13_4.render(f5);
        this.shape1_3.render(f5);
        this.shape13.render(f5);
        this.shape1_5.render(f5);
        this.shape1.render(f5);
        this.shape1_1.render(f5);
        this.shape13_2.render(f5);
        this.shape13_3.render(f5);
        this.shape13_5.render(f5);
        this.shape13_1.render(f5);
        this.shape1_4.render(f5);
        this.shape1_2.render(f5);
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
