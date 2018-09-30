package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Smol Snake - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelSmolSnake extends ModelBase {
    public ModelRenderer Tongue;
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Body4;

    public ModelSmolSnake() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.Body4 = new ModelRenderer(this, 16, 22);
        this.Body4.setRotationPoint(0.0F, -0.6F, 5.0F);
        this.Body4.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 5, 0.0F);
        this.Body3 = new ModelRenderer(this, 0, 22);
        this.Body3.setRotationPoint(0.0F, 0.1F, 5.0F);
        this.Body3.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 5, 0.0F);
        this.Tongue = new ModelRenderer(this, 12, 0);
        this.Tongue.setRotationPoint(0.0F, 24.0F, -12.0F);
        this.Tongue.addBox(-1.5F, -0.5F, -2.5F, 3, 1, 3, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 6);
        this.Body1.setRotationPoint(0.0F, 0.1F, 0.0F);
        this.Body1.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 5, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 22.5F, -9.0F);
        this.Head.addBox(-1.5F, -1.5F, -3.0F, 3, 3, 3, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 14);
        this.Body2.setRotationPoint(0.0F, -0.1F, 5.0F);
        this.Body2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 5, 0.0F);
        this.Body3.addChild(this.Body4);
        this.Body2.addChild(this.Body3);
        this.Head.addChild(this.Body1);
        this.Body1.addChild(this.Body2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Tongue.render(f5);
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
