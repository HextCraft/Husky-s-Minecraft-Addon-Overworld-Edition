package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Panda - HuskyTheArtist
 * Created using Tabula 7.0.0
 */
public class ModelPanda extends ModelBase {
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg0;
    public ModelRenderer head;
    public ModelRenderer shape6;
    public ModelRenderer shape6_1;
    public ModelRenderer shape6_2;

    public ModelPanda() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.shape6_1 = new ModelRenderer(this, 24, 6);
        this.shape6_1.setRotationPoint(10.0F, -3.0F, 3.0F);
        this.shape6_1.addBox(0.0F, 0.0F, 0.0F, 5, 4, 1, 0.0F);
        this.body = new ModelRenderer(this, 0, 32);
        this.body.setRotationPoint(-9.5F, 2.0F, -13.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 19, 13, 26, 0.0F);
        this.leg2 = new ModelRenderer(this, 40, 0);
        this.leg2.setRotationPoint(-8.5F, 15.0F, -12.0F);
        this.leg2.addBox(0.0F, 0.0F, 0.0F, 6, 9, 6, 0.0F);
        this.shape6_2 = new ModelRenderer(this, 24, 6);
        this.shape6_2.setRotationPoint(-2.0F, -3.0F, 3.0F);
        this.shape6_2.addBox(0.0F, 0.0F, 0.0F, 5, 4, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 2, 4);
        this.shape6.setRotationPoint(3.0F, 5.0F, -2.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 7, 5, 2, 0.0F);
        this.leg1 = new ModelRenderer(this, 40, 0);
        this.leg1.setRotationPoint(2.5F, 15.0F, 6.0F);
        this.leg1.addBox(0.0F, 0.0F, 0.0F, 6, 9, 6, 0.0F);
        this.leg0 = new ModelRenderer(this, 40, 0);
        this.leg0.setRotationPoint(-8.5F, 15.0F, 6.0F);
        this.leg0.addBox(0.0F, 0.0F, 0.0F, 6, 9, 6, 0.0F);
        this.leg3 = new ModelRenderer(this, 40, 0);
        this.leg3.setRotationPoint(2.5F, 15.0F, -12.0F);
        this.leg3.addBox(0.0F, 0.0F, 0.0F, 6, 9, 6, 0.0F);
        this.head = new ModelRenderer(this, 0, 12);
        this.head.setRotationPoint(-6.5F, 5.0F, -21.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 13, 10, 9, 0.0F);
        this.head.addChild(this.shape6_1);
        this.head.addChild(this.shape6_2);
        this.head.addChild(this.shape6);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
        this.leg2.render(f5);
        this.leg1.render(f5);
        this.leg0.render(f5);
        this.leg3.render(f5);
        this.head.render(f5);
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
