package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMahiMahi - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMahiMahi extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Forehead;
    public ModelRenderer TopFin1;
    public ModelRenderer TopFin2;
    public ModelRenderer RightFin;
    public ModelRenderer LeftFin;
    public ModelRenderer TopFin3;
    public ModelRenderer TopFin4;
    public ModelRenderer Tail;

    public ModelMahiMahi() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Forehead = new ModelRenderer(this, 24, 0);
        this.Forehead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Forehead.addBox(-3.0F, -5.0F, -10.0F, 6, 5, 2, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.Head.addBox(-3.0F, -5.0F, -8.0F, 6, 10, 8, 0.0F);
        this.Body2 = new ModelRenderer(this, 32, 48);
        this.Body2.setRotationPoint(0.0F, 1.0F, 10.0F);
        this.Body2.addBox(-3.0F, -2.5F, 0.0F, 6, 6, 10, 0.0F);
        this.TopFin4 = new ModelRenderer(this, 78, 27);
        this.TopFin4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TopFin4.addBox(0.0F, -8.0F, 0.0F, 1, 14, 10, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 46);
        this.Body1.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.Body1.addBox(-3.0F, -4.0F, 0.0F, 6, 8, 10, 0.0F);
        this.RightFin = new ModelRenderer(this, 0, 20);
        this.RightFin.setRotationPoint(-2.9F, 0.0F, 0.0F);
        this.RightFin.addBox(-1.0F, -1.0F, 0.0F, 1, 2, 5, 0.0F);
        this.setRotateAngle(RightFin, 0.4363323129985824F, -0.4363323129985824F, 0.0F);
        this.TopFin2 = new ModelRenderer(this, 22, 20);
        this.TopFin2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TopFin2.addBox(0.0F, -9.5F, 0.0F, 1, 18, 10, 0.0F);
        this.LeftFin = new ModelRenderer(this, 0, 20);
        this.LeftFin.mirror = true;
        this.LeftFin.setRotationPoint(2.9F, 0.0F, 0.0F);
        this.LeftFin.addBox(0.0F, -1.0F, 0.0F, 1, 2, 5, 0.0F);
        this.setRotateAngle(LeftFin, 0.4363323129985824F, 0.4363323129985824F, 0.0F);
        this.Body3 = new ModelRenderer(this, 64, 51);
        this.Body3.setRotationPoint(0.0F, 2.5F, 20.0F);
        this.Body3.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 10, 0.0F);
        this.TopFin3 = new ModelRenderer(this, 54, 26);
        this.TopFin3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TopFin3.addBox(0.0F, -8.5F, 0.0F, 1, 15, 10, 0.0F);
        this.Tail = new ModelRenderer(this, 90, 40);
        this.Tail.setRotationPoint(0.0F, 0.0F, 8.5F);
        this.Tail.addBox(0.0F, -6.0F, 0.0F, 1, 12, 12, 0.0F);
        this.TopFin1 = new ModelRenderer(this, 0, 30);
        this.TopFin1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TopFin1.addBox(0.0F, -9.5F, -10.0F, 1, 5, 10, 0.0F);
        this.Head.addChild(this.Forehead);
        this.Body3.addChild(this.TopFin4);
        this.Body1.addChild(this.RightFin);
        this.Body1.addChild(this.TopFin2);
        this.Body1.addChild(this.LeftFin);
        this.Body2.addChild(this.TopFin3);
        this.Body3.addChild(this.Tail);
        this.Head.addChild(this.TopFin1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Head.render(f5);
        this.Body2.render(f5);
        this.Body1.render(f5);
        this.Body3.render(f5);
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
