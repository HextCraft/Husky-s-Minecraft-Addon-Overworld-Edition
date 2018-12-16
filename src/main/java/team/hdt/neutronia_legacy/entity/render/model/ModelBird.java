package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelBird - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelBird extends ModelBase {
    public ModelRenderer body1;
    public ModelRenderer head;
    public ModelRenderer peak;
    public ModelRenderer wingright;
    public ModelRenderer wingleft;
    public ModelRenderer tail;
    public ModelRenderer legright;
    public ModelRenderer legleft;
    public ModelRenderer footleft;
    public ModelRenderer footright;

    public ModelBird() {
        this.textureWidth = 30;
        this.textureHeight = 30;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(-1.0F, 19.0F, -1.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.footright = new ModelRenderer(this, 20, 0);
        this.footright.setRotationPoint(-1.0F, 24.0F, 2.0F);
        this.footright.addBox(0.0F, 0.0F, -0.5F, 1, 0, 2, 0.0F);
        this.legleft = new ModelRenderer(this, 20, 0);
        this.legleft.setRotationPoint(0.0F, 23.0F, 4.2F);
        this.legleft.addBox(0.0F, 0.0F, -0.5F, 1, 2, 0, 0.0F);
        this.setRotateAngle(legleft, -0.7740535232594852F, 0.0F, 0.0F);
        this.footleft = new ModelRenderer(this, 20, 0);
        this.footleft.setRotationPoint(0.0F, 24.0F, 2.0F);
        this.footleft.addBox(0.0F, 0.0F, -0.5F, 1, 0, 2, 0.0F);
        this.legright = new ModelRenderer(this, 20, 0);
        this.legright.setRotationPoint(-1.0F, 23.0F, 4.2F);
        this.legright.addBox(0.0F, 0.0F, -0.5F, 1, 2, 0, 0.0F);
        this.setRotateAngle(legright, -0.7740535232594852F, 0.0F, 0.0F);
        this.wingright = new ModelRenderer(this, 0, 4);
        this.wingright.setRotationPoint(-0.5F, 21.1F, 1.0F);
        this.wingright.addBox(-1.0F, 0.0F, -0.5F, 1, 1, 3, 0.0F);
        this.wingleft = new ModelRenderer(this, 0, 4);
        this.wingleft.setRotationPoint(0.5F, 21.1F, 1.0F);
        this.wingleft.addBox(0.0F, 0.0F, -0.5F, 1, 1, 3, 0.0F);
        this.peak = new ModelRenderer(this, 16, 0);
        this.peak.setRotationPoint(-0.5F, 21.0F, -1.0F);
        this.peak.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(peak, 2.321986036853256F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 8, 0);
        this.body1.setRotationPoint(-1.0F, 21.0F, 0.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
        this.tail = new ModelRenderer(this, 8, 0);
        this.tail.setRotationPoint(-0.5F, 21.0F, 4.2F);
        this.tail.addBox(0.0F, 0.0F, -0.5F, 1, 0, 2, 0.0F);
        this.setRotateAngle(tail, 0.6829473363053812F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.render(f5);
        this.footright.render(f5);
        this.legleft.render(f5);
        this.footleft.render(f5);
        this.legright.render(f5);
        this.wingright.render(f5);
        this.wingleft.render(f5);
        this.peak.render(f5);
        this.body1.render(f5);
        this.tail.render(f5);

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
