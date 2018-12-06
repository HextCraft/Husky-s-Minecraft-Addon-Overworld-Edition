package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelSpider - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelScorp extends ModelBase {

    public ModelRenderer field_78210_j;
    public ModelRenderer field_78209_a;
    public ModelRenderer field_78208_c;
    public ModelRenderer field_78206_e;
    public ModelRenderer field_78204_g;
    public ModelRenderer field_78213_i;
    public ModelRenderer field_78211_k;
    public ModelRenderer field_78203_f;
    private ModelRenderer field_78207_b;
    private ModelRenderer field_78205_d;
    private ModelRenderer field_78212_h;

    public ModelScorp() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.field_78210_j = new ModelRenderer(this, 18, 0);
        this.field_78210_j.setRotationPoint(-4.0F, 15.0F, -1.0F);
        this.field_78210_j.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78210_j, 0.0F, -0.7853981852531433F, -0.7853981852531433F);
        this.field_78209_a = new ModelRenderer(this, 32, 4);
        this.field_78209_a.setRotationPoint(0.0F, 15.0F, -3.0F);
        this.field_78209_a.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.field_78211_k = new ModelRenderer(this, 18, 0);
        this.field_78211_k.setRotationPoint(4.0F, 15.0F, -1.0F);
        this.field_78211_k.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78211_k, 0.0F, 0.7853981852531433F, 0.7853981852531433F);
        this.field_78213_i = new ModelRenderer(this, 18, 0);
        this.field_78213_i.setRotationPoint(4.0F, 15.0F, 0.0F);
        this.field_78213_i.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78213_i, 0.0F, 0.39269909262657166F, 0.5811946392059326F);
        this.field_78205_d = new ModelRenderer(this, 18, 0);
        this.field_78205_d.setRotationPoint(-4.0F, 15.0F, 2.0F);
        this.field_78205_d.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78205_d, 0.0F, 0.7853981852531433F, -0.7853981852531433F);
        this.field_78203_f = new ModelRenderer(this, 18, 0);
        this.field_78203_f.setRotationPoint(-4.0F, 15.0F, 1.0F);
        this.field_78203_f.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78203_f, 0.0F, 0.39269909262657166F, -0.5811946392059326F);
        this.field_78212_h = new ModelRenderer(this, 18, 0);
        this.field_78212_h.setRotationPoint(-4.0F, 15.0F, 0.0F);
        this.field_78212_h.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78212_h, 0.0F, -0.39269909262657166F, -0.5811946392059326F);
        this.field_78206_e = new ModelRenderer(this, 18, 0);
        this.field_78206_e.setRotationPoint(4.0F, 15.0F, 2.0F);
        this.field_78206_e.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78206_e, 0.0F, -0.7853981852531433F, 0.7853981852531433F);
        this.field_78207_b = new ModelRenderer(this, 0, 0);
        this.field_78207_b.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.field_78207_b.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.field_78208_c = new ModelRenderer(this, 0, 12);
        this.field_78208_c.setRotationPoint(0.0F, 15.0F, 9.0F);
        this.field_78208_c.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
        this.field_78204_g = new ModelRenderer(this, 18, 0);
        this.field_78204_g.setRotationPoint(4.0F, 15.0F, 1.0F);
        this.field_78204_g.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78204_g, 0.0F, -0.39269909262657166F, 0.5811946392059326F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.field_78210_j.render(f5);
        this.field_78209_a.render(f5);
        this.field_78211_k.render(f5);
        this.field_78213_i.render(f5);
        this.field_78205_d.render(f5);
        this.field_78203_f.render(f5);
        this.field_78212_h.render(f5);
        this.field_78206_e.render(f5);
        this.field_78207_b.render(f5);
        this.field_78208_c.render(f5);
        this.field_78204_g.render(f5);
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
