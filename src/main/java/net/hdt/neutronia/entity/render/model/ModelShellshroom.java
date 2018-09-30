package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Shellshroom - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelShellshroom extends ModelBase {
    public ModelRenderer RightFrontFoot;
    public ModelRenderer LeftFrontFoot;
    public ModelRenderer RightBackFoot;
    public ModelRenderer LeftBackFoot;
    public ModelRenderer ShellBottom;
    public ModelRenderer ShellMiddle;
    public ModelRenderer ShellTop;
    public ModelRenderer Neck;
    public ModelRenderer Head;

    public ModelShellshroom() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.RightFrontFoot = new ModelRenderer(this, 101, 22);
        this.RightFrontFoot.setRotationPoint(-7.0F, 20.0F, -7.0F);
        this.RightFrontFoot.addBox(-2.0F, -1.0F, -2.0F, 4, 5, 4, 0.0F);
        this.RightBackFoot = new ModelRenderer(this, 101, 33);
        this.RightBackFoot.setRotationPoint(-7.0F, 20.0F, 7.0F);
        this.RightBackFoot.addBox(-2.0F, -1.0F, -2.0F, 4, 5, 4, 0.0F);
        this.ShellMiddle = new ModelRenderer(this, 0, 23);
        this.ShellMiddle.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.ShellMiddle.addBox(-9.5F, -10.0F, -9.5F, 19, 11, 19, 0.0F);
        this.Neck = new ModelRenderer(this, 85, 27);
        this.Neck.setRotationPoint(0.0F, 18.0F, -10.0F);
        this.Neck.addBox(-2.0F, -11.0F, -1.5F, 4, 12, 3, 0.0F);
        this.setRotateAngle(Neck, 0.7853981633974483F, 0.0F, 0.0F);
        this.LeftFrontFoot = new ModelRenderer(this, 101, 22);
        this.LeftFrontFoot.setRotationPoint(7.0F, 20.0F, -7.0F);
        this.LeftFrontFoot.addBox(-2.0F, -1.0F, -2.0F, 4, 5, 4, 0.0F);
        this.ShellBottom = new ModelRenderer(this, 0, 0);
        this.ShellBottom.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.ShellBottom.addBox(-10.5F, -1.0F, -10.5F, 21, 2, 21, 0.0F);
        this.Head = new ModelRenderer(this, 59, 30);
        this.Head.setRotationPoint(0.0F, 10.5F, -18.0F);
        this.Head.addBox(-2.5F, -2.0F, -5.0F, 5, 3, 7, 0.0F);
        this.ShellTop = new ModelRenderer(this, 60, 44);
        this.ShellTop.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.ShellTop.addBox(-8.5F, -2.0F, -8.5F, 17, 3, 17, 0.0F);
        this.LeftBackFoot = new ModelRenderer(this, 101, 33);
        this.LeftBackFoot.setRotationPoint(7.0F, 20.0F, 7.0F);
        this.LeftBackFoot.addBox(-2.0F, -1.0F, -2.0F, 4, 5, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.RightFrontFoot.render(f5);
        this.RightBackFoot.render(f5);
        this.ShellMiddle.render(f5);
        this.Neck.render(f5);
        this.LeftFrontFoot.render(f5);
        this.ShellBottom.render(f5);
        this.Head.render(f5);
        this.ShellTop.render(f5);
        this.LeftBackFoot.render(f5);
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
