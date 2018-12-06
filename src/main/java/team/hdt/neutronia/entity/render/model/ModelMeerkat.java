package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMeerkat - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMeerkat extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer Tail;
    public ModelRenderer Ears;
    public ModelRenderer Snout;
    public ModelRenderer RightFrontFoot;
    public ModelRenderer LeftFrontFoot;
    public ModelRenderer RightBackFoot;
    public ModelRenderer LeftBackFoot;

    public ModelMeerkat() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LeftBackFoot = new ModelRenderer(this, 35, 0);
        this.LeftBackFoot.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.LeftBackFoot.addBox(-1.0F, 1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.LeftFrontLeg = new ModelRenderer(this, 0, 18);
        this.LeftFrontLeg.setRotationPoint(1.9F, 16.0F, -0.5F);
        this.LeftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.LeftBackLeg = new ModelRenderer(this, 0, 18);
        this.LeftBackLeg.setRotationPoint(1.9F, 16.0F, 8.0F);
        this.LeftBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.RightBackFoot = new ModelRenderer(this, 35, 0);
        this.RightBackFoot.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.RightBackFoot.addBox(-1.0F, 1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 0, 18);
        this.RightBackLeg.setRotationPoint(-1.9F, 16.0F, 8.0F);
        this.RightBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.Ears = new ModelRenderer(this, 17, 0);
        this.Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Ears.addBox(-3.5F, -1.5F, 0.4F, 7, 3, 2, 0.0F);
        this.Tail = new ModelRenderer(this, 23, 15);
        this.Tail.setRotationPoint(0.0F, 13.0F, 9.0F);
        this.Tail.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(Tail, -0.17453292519943295F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 12.0F, -4.0F);
        this.Head.addBox(-3.0F, -2.5F, -2.5F, 6, 5, 5, 0.0F);
        this.Snout = new ModelRenderer(this, 0, 10);
        this.Snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Snout.addBox(-1.5F, -0.75F, -5.5F, 3, 3, 3, 0.0F);
        this.RightFrontLeg = new ModelRenderer(this, 0, 18);
        this.RightFrontLeg.setRotationPoint(-1.9F, 16.0F, -0.5F);
        this.RightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.LeftFrontFoot = new ModelRenderer(this, 35, 0);
        this.LeftFrontFoot.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.LeftFrontFoot.addBox(-1.0F, 1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.RightFrontFoot = new ModelRenderer(this, 35, 0);
        this.RightFrontFoot.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.RightFrontFoot.addBox(-1.0F, 1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.Body = new ModelRenderer(this, 0, 16);
        this.Body.setRotationPoint(0.0F, 13.0F, 7.5F);
        this.Body.addBox(-3.0F, -2.0F, -9.0F, 6, 5, 11, 0.0F);
        this.LeftBackLeg.addChild(this.LeftBackFoot);
        this.RightBackLeg.addChild(this.RightBackFoot);
        this.Head.addChild(this.Ears);
        this.Head.addChild(this.Snout);
        this.LeftFrontLeg.addChild(this.LeftFrontFoot);
        this.RightFrontLeg.addChild(this.RightFrontFoot);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftFrontLeg.render(f5);
        this.LeftBackLeg.render(f5);
        this.RightBackLeg.render(f5);
        this.Tail.render(f5);
        this.Head.render(f5);
        this.RightFrontLeg.render(f5);
        this.Body.render(f5);
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
