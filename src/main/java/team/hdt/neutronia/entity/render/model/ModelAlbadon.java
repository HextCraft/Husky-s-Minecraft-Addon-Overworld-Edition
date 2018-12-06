//Made with Blockbench

package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlbadon extends ModelBase {

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

    public ModelAlbadon() {
        this.textureWidth = 16;
        this.textureHeight = 16;

        this.e0 = new ModelRenderer(this, 0, 0);
        this.e0.addBox(4F, 5F, -7F, 8, 5, 8, 1.0F);
        this.e1 = new ModelRenderer(this, 0, 0);
        this.e1.addBox(4F, 4F, -6F, 8, 1, 7, 1.0F);
        this.e1.setRotationPoint(4F, 4F, -6F);
        this.setRotateAngle(e1, 0F, 0.0F, 0.0F);
        this.e2 = new ModelRenderer(this, 0, 0);
        this.e2.addBox(2F, 4F, 1F, 12, 8, 11, 1.0F);
        this.e3 = new ModelRenderer(this, 0, 0);
        this.e3.addBox(4F, 5F, 12F, 8, 5, 4, 1.0F);
        this.e4 = new ModelRenderer(this, 0, 0);
        this.e4.addBox(6F, 5F, 16F, 4, 3, 5, 1.0F);
        this.e5 = new ModelRenderer(this, 0, 0);
        this.e5.addBox(3F, 6F, 20F, 10, 1, 6, 1.0F);
        this.e6 = new ModelRenderer(this, 0, 0);
        this.e6.addBox(-6F, 7F, 3F, 8, 1, 5, 1.0F);
        this.e6.setRotationPoint(-6F, 7F, 3F);
        this.setRotateAngle(e6, 0.0F, 0.0F, 0F);
        this.e7 = new ModelRenderer(this, 0, 0);
        this.e7.addBox(14F, 7F, 3F, 8, 1, 5, 1.0F);
        this.e8 = new ModelRenderer(this, 0, 0);
        this.e8.addBox(7.5F, 10.75F, 5F, 1, 6, 4, 1.0F);
        this.e8.setRotationPoint(8F, 13.75F, 7F);
        this.setRotateAngle(e8, 22.5F, 0.0F, 0.0F);
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
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}