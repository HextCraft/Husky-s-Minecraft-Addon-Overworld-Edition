package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelWolfArtic - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelWolfArtic extends ModelBase {
    public ModelRenderer field_78179_f;
    public ModelRenderer field_78186_h;
    public ModelRenderer field_78183_b;
    public ModelRenderer field_78181_d;
    public ModelRenderer field_78184_c;
    public ModelRenderer field_78182_e;
    public ModelRenderer field_78180_g;
    public ModelRenderer field_78185_a0;
    public ModelRenderer field_78185_a1;
    public ModelRenderer field_78185_a2;
    public ModelRenderer field_78185_a3;

    public ModelWolfArtic() {
        this.textureWidth = 66;
        this.textureHeight = 34;
        this.field_78185_a2 = new ModelRenderer(this, 15, 14);
        this.field_78185_a2.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a2.addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.field_78185_a3 = new ModelRenderer(this, 0, 10);
        this.field_78185_a3.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a3.addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
        this.field_78183_b = new ModelRenderer(this, 18, 17);
        this.field_78183_b.setRotationPoint(0.0F, 15.0F, 2.0F);
        this.field_78183_b.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 7, 0.0F);
        this.setRotateAngle(field_78183_b, 1.5707963267948966F, 0.0F, 0.0F);
        this.field_78185_a0 = new ModelRenderer(this, 0, 0);
        this.field_78185_a0.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a0.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.field_78179_f = new ModelRenderer(this, 0, 18);
        this.field_78179_f.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.field_78179_f.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78181_d = new ModelRenderer(this, 0, 18);
        this.field_78181_d.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.field_78181_d.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78185_a1 = new ModelRenderer(this, 15, 14);
        this.field_78185_a1.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a1.addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.field_78180_g = new ModelRenderer(this, 9, 18);
        this.field_78180_g.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.field_78180_g.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78184_c = new ModelRenderer(this, 0, 18);
        this.field_78184_c.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.field_78184_c.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78186_h = new ModelRenderer(this, 21, 0);
        this.field_78186_h.setRotationPoint(-1.0F, 15.0F, -3.0F);
        this.field_78186_h.addBox(-3.0F, -3.0F, -3.0F, 8, 9, 8, 0.0F);
        this.setRotateAngle(field_78186_h, 1.5707963267948966F, 0.0F, 0.0F);
        this.field_78182_e = new ModelRenderer(this, 0, 18);
        this.field_78182_e.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.field_78182_e.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.field_78185_a2.render(f5);
        this.field_78185_a3.render(f5);
        this.field_78183_b.render(f5);
        this.field_78185_a0.render(f5);
        this.field_78179_f.render(f5);
        this.field_78181_d.render(f5);
        this.field_78185_a1.render(f5);
        this.field_78180_g.render(f5);
        this.field_78184_c.render(f5);
        this.field_78186_h.render(f5);
        this.field_78182_e.render(f5);
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
