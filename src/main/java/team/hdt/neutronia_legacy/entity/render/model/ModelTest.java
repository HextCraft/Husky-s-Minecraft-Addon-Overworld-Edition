//Made with Blockbench

package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTest extends ModelBase {

    //fields
    ModelRenderer e0;
    ModelRenderer e1;
    ModelRenderer e2;
    ModelRenderer e3;
    ModelRenderer e4;
    ModelRenderer e5;
    ModelRenderer e6;
    ModelRenderer e7;
    ModelRenderer e8;
    ModelRenderer e9;
    ModelRenderer e10;
    ModelRenderer e11;
    ModelRenderer e12;
    ModelRenderer e13;
    ModelRenderer e14;
    ModelRenderer e15;
    ModelRenderer e16;

    public ModelTest() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.e0 = new ModelRenderer(this, 0, 0);
        this.e0.addBox(-4F, 12F, -2F, 8, 12, 4, 1.0F);
        this.e1 = new ModelRenderer(this, 0, 0);
        this.e1.addBox(-4F, 24F, -4F, 8, 8, 8, 1.0F);
        this.e2 = new ModelRenderer(this, 0, 0);
        this.e2.addBox(-8F, 12F, -2F, 4, 12, 4, 1.0F);
        this.e3 = new ModelRenderer(this, 0, 0);
        this.e3.addBox(4F, 12F, -2F, 4, 12, 4, 1.0F);
        this.e4 = new ModelRenderer(this, 0, 0);
        this.e4.addBox(-4F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e5 = new ModelRenderer(this, 0, 0);
        this.e5.addBox(0F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e6 = new ModelRenderer(this, 0, 0);
        this.e6.addBox(-4F, 12F, -2F, 8, 12, 4, 1.0F);
        this.e7 = new ModelRenderer(this, 0, 0);
        this.e7.addBox(0F, 28F, -2.5F, 5, 4, 1, 1.0F);
        this.e8 = new ModelRenderer(this, 0, 0);
        this.e8.addBox(1.5F, 28F, -5F, 1, 4, 5, 1.0F);
        this.e9 = new ModelRenderer(this, 0, 0);
        this.e9.addBox(0F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e10 = new ModelRenderer(this, 0, 0);
        this.e10.addBox(-3.9999998807907F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e11 = new ModelRenderer(this, 0, 0);
        this.e11.addBox(-10F, 24F, 1.5F, 5, 4, 1, 1.0F);
        this.e12 = new ModelRenderer(this, 0, 0);
        this.e12.addBox(-8F, 24F, -1F, 1, 4, 5, 1.0F);
        this.e13 = new ModelRenderer(this, 0, 0);
        this.e13.addBox(-7.5F, 24F, -1.5F, 3, 3, 1, 1.0F);
        this.e14 = new ModelRenderer(this, 0, 0);
        this.e14.addBox(-6.5F, 24F, -3F, 1, 3, 3, 1.0F);
        this.e15 = new ModelRenderer(this, 0, 0);
        this.e15.addBox(6F, 24F, -1.5F, 3, 3, 1, 1.0F);
        this.e16 = new ModelRenderer(this, 0, 0);
        this.e16.addBox(7F, 24F, -3F, 1, 3, 3, 1.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.e0.render(f5);
        this.e1.render(f5);
        this.e2.render(f5);
        this.e3.render(f5);
        this.e4.render(f5);
        this.e5.render(f5);
        this.e6.render(f5);
        this.e7.render(f5);
        this.e8.render(f5);
        this.e9.render(f5);
        this.e10.render(f5);
        this.e11.render(f5);
        this.e12.render(f5);
        this.e13.render(f5);
        this.e14.render(f5);
        this.e15.render(f5);
        this.e16.render(f5);

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}