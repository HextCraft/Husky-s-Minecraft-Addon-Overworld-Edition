package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Necro - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelNecro extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Nose;
    public ModelRenderer Hat;
    public ModelRenderer Robes;
    public ModelRenderer Body;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer MiddleCrossArm;
    public ModelRenderer RightCrossarm;
    public ModelRenderer LeftCrossArm;

    public ModelNecro() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.RightArm = new ModelRenderer(this, 40, 46);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftCrossArm = new ModelRenderer(this, 44, 22);
        this.LeftCrossArm.mirror = true;
        this.LeftCrossArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.LeftCrossArm.addBox(4.0F, -5.0F, -1.0F, 4, 8, 4, 0.0F);
        this.Body = new ModelRenderer(this, 16, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.Robes = new ModelRenderer(this, 0, 38);
        this.Robes.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Robes.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.MiddleCrossArm = new ModelRenderer(this, 40, 38);
        this.MiddleCrossArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleCrossArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleCrossArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Hat = new ModelRenderer(this, 32, 0);
        this.Hat.setRotationPoint(0.0F, -9.75F, 0.5F);
        this.Hat.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.5F);
        this.setRotateAngle(Hat, -0.17453292519943295F, 0.0F, 0.0F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, -4.0F);
        this.Nose.addBox(-1.0F, -1.0F, -2.0F, 2, 4, 2, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 22);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightCrossarm = new ModelRenderer(this, 44, 22);
        this.RightCrossarm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.RightCrossarm.addBox(-8.0F, -5.0F, -1.0F, 4, 8, 4, 0.0F);
        this.LeftArm = new ModelRenderer(this, 40, 46);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 22);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleCrossArm.addChild(this.LeftCrossArm);
        this.MiddleCrossArm.addChild(this.RightCrossarm);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Head.render(f5);
        this.RightArm.render(f5);
        this.Body.render(f5);
        this.Robes.render(f5);
        this.MiddleCrossArm.render(f5);
        this.Hat.render(f5);
        this.Nose.render(f5);
        this.RightLeg.render(f5);
        this.LeftArm.render(f5);
        this.LeftLeg.render(f5);
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
