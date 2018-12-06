package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelCod extends ModelBase {

    public ModelRenderer head0;
    public ModelRenderer head1;
    public ModelRenderer leftFin;
    public ModelRenderer rightFin;
    public ModelRenderer tailFin;
    public ModelRenderer waist;
    public ModelRenderer body0;
    public ModelRenderer body1;

    public ModelCod() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.leftFin = new ModelRenderer(this, 24, 4);
        this.leftFin.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.leftFin.addBox(1.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
        this.setRotateAngle(leftFin, -0.045553093477052F, 0.0F, 0.0F);
        this.body0 = new ModelRenderer(this, 0, 0);
        this.body0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body0.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 7, 0.0F);
        this.head1 = new ModelRenderer(this, 11, 0);
        this.head1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head1.addBox(-1.0F, 0.0F, -4.0F, 2, 4, 3, 0.0F);
        this.head0 = new ModelRenderer(this, 0, 0);
        this.head0.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.head0.addBox(-1.0F, 1.0F, -5.0F, 2, 3, 1, 0.0F);
        this.tailFin = new ModelRenderer(this, 20, 1);
        this.tailFin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailFin.addBox(0.0F, 0.0F, 6.0F, 0, 4, 6, 0.0F);
        this.waist = new ModelRenderer(this, 0, 0);
        this.waist.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.waist.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.body1 = new ModelRenderer(this, 20, -6);
        this.body1.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.body1.addBox(-0.6F, 4.0F, -2.0F, 0, 0, 6, 0.5F);
        this.rightFin = new ModelRenderer(this, 24, 1);
        this.rightFin.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.rightFin.addBox(-3.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leftFin.offsetX, this.leftFin.offsetY, this.leftFin.offsetZ);
        GlStateManager.translate(this.leftFin.rotationPointX * scale, this.leftFin.rotationPointY * scale, this.leftFin.rotationPointZ * scale);
        GlStateManager.scale(1.0D, 0.0D, 1.0D);
        GlStateManager.translate(-this.leftFin.offsetX, -this.leftFin.offsetY, -this.leftFin.offsetZ);
        GlStateManager.translate(-this.leftFin.rotationPointX * scale, -this.leftFin.rotationPointY * scale, -this.leftFin.rotationPointZ * scale);
        this.leftFin.render(scale);
        GlStateManager.popMatrix();
        this.body0.render(scale);
        this.head1.render(scale);
        this.head0.render(scale);
        this.tailFin.render(scale);
        this.waist.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.body1.offsetX, this.body1.offsetY, this.body1.offsetZ);
        GlStateManager.translate(this.body1.rotationPointX * scale, this.body1.rotationPointY * scale, this.body1.rotationPointZ * scale);
        GlStateManager.scale(0.0D, 0.9D, 1.0D);
        GlStateManager.translate(-this.body1.offsetX, -this.body1.offsetY, -this.body1.offsetZ);
        GlStateManager.translate(-this.body1.rotationPointX * scale, -this.body1.rotationPointY * scale, -this.body1.rotationPointZ * scale);
        this.body1.render(scale);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.rightFin.offsetX, this.rightFin.offsetY, this.rightFin.offsetZ);
        GlStateManager.translate(this.rightFin.rotationPointX * scale, this.rightFin.rotationPointY * scale, this.rightFin.rotationPointZ * scale);
        GlStateManager.scale(1.0D, 0.0D, 1.0D);
        GlStateManager.translate(-this.rightFin.offsetX, -this.rightFin.offsetY, -this.rightFin.offsetZ);
        GlStateManager.translate(-this.rightFin.rotationPointX * scale, -this.rightFin.rotationPointY * scale, -this.rightFin.rotationPointZ * scale);
        this.rightFin.render(scale);
        GlStateManager.popMatrix();
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
