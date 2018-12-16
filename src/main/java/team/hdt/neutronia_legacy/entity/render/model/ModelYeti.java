package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Yeti - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelYeti extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape3;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape9;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape14;

    public ModelYeti() {
        this.textureWidth = 84;
        this.textureHeight = 42;
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(-2.5F, 15.0F, 5.5F);
        this.shape1_1.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 56, 10);
        this.shape1_2.mirror = true;
        this.shape1_2.setRotationPoint(5.0F, 10.0F, -3.0F);
        this.shape1_2.addBox(-1.0F, -1.0F, -2.0F, 4, 6, 4, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 58, 20);
        this.shape1_4.mirror = true;
        this.shape1_4.setRotationPoint(1.0F, 5.0F, 0.0F);
        this.shape1_4.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
        this.shape14 = new ModelRenderer(this, 17, 13);
        this.shape14.setRotationPoint(0.0F, 1.0F, -4.0F);
        this.shape14.addBox(-1.0F, 0.0F, -2.0F, 2, 3, 2, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 13);
        this.shape9.setRotationPoint(0.0F, 9.0F, -5.0F);
        this.shape9.addBox(-3.0F, -3.5F, -4.0F, 6, 7, 5, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 58, 20);
        this.shape1_5.setRotationPoint(-1.0F, 5.0F, 0.0F);
        this.shape1_5.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
        this.shape3 = new ModelRenderer(this, 32, 0);
        this.shape3.setRotationPoint(0.0F, 9.0F, -4.0F);
        this.shape3.addBox(-4.0F, 0.0F, -2.0F, 8, 14, 4, 0.0F);
        this.setRotateAngle(shape3, 0.9560913642424937F, 0.0F, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.mirror = true;
        this.shape1.setRotationPoint(2.5F, 15.0F, 5.5F);
        this.shape1.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 56, 10);
        this.shape1_3.setRotationPoint(-5.0F, 10.0F, -3.0F);
        this.shape1_3.addBox(-3.0F, -1.0F, -2.0F, 4, 6, 4, 0.0F);
        this.shape1_2.addChild(this.shape1_4);
        this.shape9.addChild(this.shape14);
        this.shape1_3.addChild(this.shape1_5);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1_1.render(f5);
        this.shape1_2.render(f5);
        this.shape9.render(f5);
        this.shape3.render(f5);
        this.shape1.render(f5);
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
