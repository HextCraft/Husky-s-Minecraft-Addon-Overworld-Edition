package team.abnormal.neutronia.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import team.abnormal.neutronia.entity.passive.EntityHyena;

/**
 * ModelHyena - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelHyena extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer Head;
    public ModelRenderer Tail;
    public ModelRenderer Fluff;
    public ModelRenderer Snout;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;

    public ModelHyena() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Fluff = new ModelRenderer(this, 10, -14);
        this.Fluff.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Fluff.addBox(0.0F, -5.0F, 0.0F, 0, 2, 14, 0.0F);
        this.LeftFrontLeg = new ModelRenderer(this, 0, 0);
        this.LeftFrontLeg.setRotationPoint(2.0F, 14.0F, -3.0F);
        this.LeftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 3, 0.0F);
        this.RightEar = new ModelRenderer(this, 44, 0);
        this.RightEar.setRotationPoint(-3.0F, -2.0F, 1.0F);
        this.RightEar.addBox(-1.0F, -4.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(RightEar, -0.3490658503988659F, 0.17453292519943295F, 0.0F);
        this.LeftBackLeg = new ModelRenderer(this, 0, 0);
        this.LeftBackLeg.setRotationPoint(2.0F, 14.0F, 7.0F);
        this.LeftBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 3, 0.0F);
        this.Body = new ModelRenderer(this, 0, 12);
        this.Body.setRotationPoint(0.0F, 12.0F, -5.0F);
        this.Body.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 14, 0.0F);
        this.RightFrontLeg = new ModelRenderer(this, 0, 0);
        this.RightFrontLeg.setRotationPoint(-2.0F, 14.0F, -3.0F);
        this.RightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 3, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 0, 0);
        this.RightBackLeg.setRotationPoint(-2.0F, 14.0F, 7.0F);
        this.RightBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 3, 0.0F);
        this.Snout = new ModelRenderer(this, 50, 0);
        this.Snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Snout.addBox(-2.0F, 0.0F, -6.0F, 4, 3, 3, 0.0F);
        this.LeftEar = new ModelRenderer(this, 44, 0);
        this.LeftEar.mirror = true;
        this.LeftEar.setRotationPoint(3.0F, -2.0F, 1.0F);
        this.LeftEar.addBox(-1.0F, -4.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(LeftEar, -0.3490658503988659F, -0.17453292519943295F, 0.0F);
        this.Head = new ModelRenderer(this, 38, 8);
        this.Head.setRotationPoint(0.0F, 8.0F, -5.0F);
        this.Head.addBox(-3.5F, -3.0F, -3.0F, 7, 6, 6, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 14);
        this.Tail.setRotationPoint(0.0F, 0.0F, 13.0F);
        this.Tail.addBox(-1.0F, 0.0F, -0.5F, 2, 10, 2, 0.0F);
        this.Body.addChild(this.Fluff);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.Snout);
        this.Head.addChild(this.LeftEar);
        this.Body.addChild(this.Tail);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        if (this.isChild) {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 7.0F * scale, 2.0F * scale);
            this.Head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.LeftFrontLeg.render(scale);
            this.LeftBackLeg.render(scale);
            this.Body.render(scale);
            this.RightFrontLeg.render(scale);
            this.RightBackLeg.render(scale);

            GlStateManager.popMatrix();
        } else {
            this.LeftFrontLeg.render(scale);
            this.LeftBackLeg.render(scale);
            this.Body.render(scale);
            this.RightFrontLeg.render(scale);
            this.RightBackLeg.render(scale);
            this.Head.render(scale);
        }
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        EntityHyena entityhyena = (EntityHyena) entitylivingbaseIn;

        if (entityhyena.isSitting())
        {
            this.Body.setRotationPoint(0.0F, 12.0F, -3.0F);
            this.Body.rotateAngleX = -((float)Math.PI / 4F);
            this.RightFrontLeg.setRotationPoint(-2.0F, 14.0F, -5.0F);
            this.LeftFrontLeg.setRotationPoint(2.0F, 14.0F, -5.0F);
            this.RightFrontLeg.rotateAngleX = 0.0F;
            this.LeftFrontLeg.rotateAngleX = 0.0F;
            this.RightBackLeg.setRotationPoint(-2.0F, 21.0F, 7.0F);
            this.RightBackLeg.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.LeftBackLeg.setRotationPoint(2.0F, 21.0F, 7.0F);
            this.LeftBackLeg.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.Tail.rotateAngleX = ((float)Math.PI / 1.8F);
        }
        else
        {
            this.Body.setRotationPoint(0.0F, 14.0F, -5.0F);
            this.Body.rotateAngleX = 0.0F;
            this.RightFrontLeg.setRotationPoint(-2.0F, 14.0F, -3.0F);
            this.RightBackLeg.setRotationPoint(-2.0F, 14.0F, 7.0F);
            this.LeftFrontLeg.setRotationPoint(2.0F, 14.0F, -3.0F);
            this.LeftBackLeg.setRotationPoint(2.0F, 14.0F, 7.0F);
            this.RightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.RightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.LeftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.LeftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.Tail.rotateAngleX = 0.0F;
        }
        this.Head.rotateAngleZ = entityhyena.getInterestedAngle(partialTickTime) + entityhyena.getShakeAngle(partialTickTime, 0.0F);
        this.Body.rotateAngleZ = entityhyena.getShakeAngle(partialTickTime, -0.16F);
        this.Tail.rotateAngleZ = entityhyena.getShakeAngle(partialTickTime, -0.2F);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.Head.rotateAngleX = headPitch * 0.017453292F;
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
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
