package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelFox - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelFox extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer Tail;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer Snout;

    public ModelFox() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.RightFrontLeg = new ModelRenderer(this, 0, 17);
        this.RightFrontLeg.setRotationPoint(-1.9F, 19.0F, 1.1F);
        this.RightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.LeftEar = new ModelRenderer(this, 0, 0);
        this.LeftEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftEar.addBox(1.5F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.Body = new ModelRenderer(this, 0, 18);
        this.Body.setRotationPoint(0.0F, 17.5F, 2.0F);
        this.Body.addBox(-3.0F, -2.0F, -2.0F, 6, 4, 10, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Head.addBox(-4.0F, -3.0F, -4.0F, 8, 5, 6, 0.0F);
        this.RightEar = new ModelRenderer(this, 0, 0);
        this.RightEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightEar.addBox(-3.5F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.Snout = new ModelRenderer(this, 0, 11);
        this.Snout.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.Snout.addBox(-1.5F, -0.5F, -8.0F, 3, 2, 4, 0.0F);
        this.Tail = new ModelRenderer(this, 32, 22);
        this.Tail.setRotationPoint(0.0F, 15.5F, 10.0F);
        this.Tail.addBox(-1.5F, -1.0F, -1.0F, 3, 3, 7, 0.0F);
        this.setRotateAngle(Tail, 0.6108652381980153F, 0.0F, 0.0F);
        this.LeftFrontLeg = new ModelRenderer(this, 0, 17);
        this.LeftFrontLeg.setRotationPoint(1.9F, 19.0F, 1.1F);
        this.LeftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 0, 17);
        this.RightBackLeg.setRotationPoint(-1.9F, 19.0F, 8.5F);
        this.RightBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.LeftBackLeg = new ModelRenderer(this, 0, 17);
        this.LeftBackLeg.setRotationPoint(1.9F, 19.0F, 8.5F);
        this.LeftBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.Head.addChild(this.LeftEar);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.Snout);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.RightFrontLeg.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.Tail.render(f5);
        this.LeftFrontLeg.render(f5);
        this.RightBackLeg.render(f5);
        this.LeftBackLeg.render(f5);
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
