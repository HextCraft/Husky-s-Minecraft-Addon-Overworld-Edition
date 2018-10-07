package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * The Wrapped -
 * Created using Tabula 7.0.0
 */
public class ModelTheWrapped extends ModelBase {
    public ModelRenderer UpperTorso;
    public ModelRenderer LowerTorso;
    public ModelRenderer Head;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;

    public ModelTheWrapped() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(Head, -0.2617993877991494F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 16);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(1.9F, 2.0F, 0.1F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftLeg, -0.17453292519943295F, 0.0F, 0.0F);
        this.LowerTorso = new ModelRenderer(this, 32, 10);
        this.LowerTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LowerTorso.addBox(-4.0F, 0.0F, -2.0F, 8, 2, 4, 0.0F);
        this.setRotateAngle(LowerTorso, 0.17453292519943295F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 16);
        this.RightLeg.setRotationPoint(-1.9F, 2.0F, 0.1F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightLeg, -0.17453292519943295F, 0.0F, 0.0F);
        this.UpperTorso = new ModelRenderer(this, 16, 16);
        this.UpperTorso.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.UpperTorso.addBox(-6.0F, -10.0F, -3.0F, 12, 10, 6, 0.0F);
        this.setRotateAngle(UpperTorso, 0.17453292519943295F, 0.0F, 0.0F);
        this.UpperTorso.addChild(this.Head);
        this.UpperTorso.addChild(this.LeftLeg);
        this.UpperTorso.addChild(this.LowerTorso);
        this.UpperTorso.addChild(this.RightLeg);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.UpperTorso.render(f5);
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
