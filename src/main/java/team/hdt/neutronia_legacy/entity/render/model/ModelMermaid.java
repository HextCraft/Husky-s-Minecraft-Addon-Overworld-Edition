package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMermaid - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMermaid extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer HairLayer;
    public ModelRenderer Body;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer Tail1;
    public ModelRenderer Hair;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;

    public ModelMermaid() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.HairLayer = new ModelRenderer(this, 32, 0);
        this.HairLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HairLayer.addBox(-4.0F, -8.0F, -4.0F, 8, 12, 8, 0.25F);
        this.Body = new ModelRenderer(this, 40, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 14, 4, 0.0F);
        this.Hair = new ModelRenderer(this, 0, 51);
        this.Hair.setRotationPoint(0.0F, 0.0F, -2.1F);
        this.Hair.addBox(-4.5F, 0.0F, 0.0F, 9, 12, 1, 0.0F);
        this.setRotateAngle(Hair, -0.03490658503988659F, 0.0F, 0.0F);
        this.RightArm = new ModelRenderer(this, 0, 17);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Tail1 = new ModelRenderer(this, 20, 20);
        this.Tail1.setRotationPoint(0.0F, 13.0F, 1.0F);
        this.Tail1.addBox(-3.0F, 0.0F, -2.0F, 6, 8, 3, 0.0F);
        this.setRotateAngle(Tail1, 0.3490658503988659F, 0.0F, 0.0F);
        this.Tail2 = new ModelRenderer(this, 23, 31);
        this.Tail2.setRotationPoint(0.0F, 7.0F, -1.0F);
        this.Tail2.addBox(-2.0F, 0.0F, -1.0F, 4, 8, 2, 0.0F);
        this.setRotateAngle(Tail2, 0.3490658503988659F, 0.0F, 0.0F);
        this.Tail3 = new ModelRenderer(this, 20, 51);
        this.Tail3.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.Tail3.addBox(-6.0F, 0.0F, -1.0F, 12, 12, 1, 0.0F);
        this.setRotateAngle(Tail3, 0.4363323129985824F, 0.0F, 0.0F);
        this.LeftArm = new ModelRenderer(this, 0, 17);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.Body.addChild(this.Hair);
        this.Body.addChild(this.Tail1);
        this.Tail1.addChild(this.Tail2);
        this.Tail2.addChild(this.Tail3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.HairLayer.render(f5);
        this.Body.render(f5);
        this.RightArm.render(f5);
        this.Head.render(f5);
        this.LeftArm.render(f5);
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
