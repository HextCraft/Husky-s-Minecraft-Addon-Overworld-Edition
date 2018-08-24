package net.hdt.neutronia.groups.vanity.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHorseOverride extends ModelBase {
    private final ModelRenderer neck;
    private final ModelRenderer head;
    private final ModelRenderer mane;
    private final ModelRenderer snout;
    private final ModelRenderer horseLeftEar;
    private final ModelRenderer horseRightEar;
    private final ModelRenderer body;
    private final ModelRenderer tail;
    private final ModelRenderer backLeftLeg;
    private final ModelRenderer backRightLeg;
    private final ModelRenderer frontLeftLeg;
    private final ModelRenderer frontRightLeg;
    private final ModelRenderer horseSaddle;
    private final ModelRenderer horseLeftFaceMetal;
    private final ModelRenderer horseRightFaceMetal;
    private final ModelRenderer horseLeftRein;
    private final ModelRenderer horseRightRein;
    private final ModelRenderer horseHeadFaceRopes;
    private final ModelRenderer horseSnoutFaceRopes;
    private final ModelRenderer muleLeftEar;
    private final ModelRenderer muleRightEar;
    private final ModelRenderer muleLeftChest;
    private final ModelRenderer muleRightChest;

    public ModelHorseOverride() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 32);
        this.body.addBox(-5.0F, -8.0F, -17.0F, 10, 10, 22, 0.05F);
        this.body.setRotationPoint(0.0F, 11.0F, 5.0F);
        this.neck = new ModelRenderer(this, 0, 35);
        this.neck.addBox(-2.05F, -6.0F, -2.0F, 4, 12, 7);
        this.body.setRotationPoint(0.0F, 11.0F, 5.0F);
        this.neck.rotateAngleX = 0.5235988F;
        this.head = new ModelRenderer(this, 0, 13);
        this.head.addBox(-3.0F, -11.0F, -2.0F, 6, 5, 7);
        this.neck.addChild(this.head);
        this.mane = new ModelRenderer(this, 56, 36);
        this.mane.addBox(-1.0F, -11.0F, 5.01F, 2, 16, 2);
        this.neck.addChild(this.mane);
        this.snout = new ModelRenderer(this, 0, 25);
        this.snout.addBox(-2.0F, -11.0F, -7.0F, 4, 5, 5);
        this.neck.addChild(this.snout);

        this.backLeftLeg = new ModelRenderer(this, 48, 21);
        this.backLeftLeg.mirror = true;
        this.backLeftLeg.addBox(-3.0F, -1.01F, -1.0F, 4, 11, 4);
        this.backLeftLeg.setRotationPoint(4.0F, 14.0F, 7.0F);
        this.backRightLeg = new ModelRenderer(this, 48, 21);
        this.backRightLeg.addBox(-1.0F, -1.01F, -1.0F, 4, 11, 4);
        this.backRightLeg.setRotationPoint(-4.0F, 14.0F, 7.0F);
        this.frontLeftLeg = new ModelRenderer(this, 48, 21);
        this.frontLeftLeg.mirror = true;
        this.frontLeftLeg.addBox(-3.0F, -1.01F, -1.9F, 4, 11, 4);
        this.frontLeftLeg.setRotationPoint(4.0F, 6.0F, -12.0F);
        this.frontRightLeg = new ModelRenderer(this, 48, 21);
        this.frontRightLeg.addBox(-1.0F, -1.01F, -1.9F, 4, 11, 4);
        this.frontRightLeg.setRotationPoint(-4.0F, 6.0F, -12.0F);

        this.tail = new ModelRenderer(this, 42, 36);
        this.tail.addBox(-1.5F, 0.0F, 0.0F, 3, 14, 4);
        this.tail.setRotationPoint(0.0F, -5.0F, 2.0F);
        this.tail.rotateAngleX = 0.5235988F;
        this.body.addChild(this.tail);

        this.horseSaddle = new ModelRenderer(this, 26, 0);
        this.horseSaddle.addBox(-5.0F, -8.0F, -9.0F, 10, 9, 9, 0.5F);
        this.body.addChild(this.horseSaddle);
        this.horseLeftFaceMetal = new ModelRenderer(this, 29, 5);
        this.horseLeftFaceMetal.addBox(2.0F, -9.0F, -6.0F, 1, 2, 2);
        this.neck.addChild(this.horseLeftFaceMetal);
        this.horseRightFaceMetal = new ModelRenderer(this, 29, 5);
        this.horseRightFaceMetal.addBox(-3.0F, -9.0F, -6.0F, 1, 2, 2);
        this.neck.addChild(this.horseRightFaceMetal);
        this.horseLeftRein = new ModelRenderer(this, 32, 2);
        this.horseLeftRein.addBox(3.1F, -6.0F, -8.0F, 0, 3, 16);
        this.horseLeftRein.rotateAngleX = -0.5235988F;
        this.neck.addChild(this.horseLeftRein);
        this.horseRightRein = new ModelRenderer(this, 32, 2);
        this.horseRightRein.addBox(-3.1F, -6.0F, -8.0F, 0, 3, 16);
        this.horseRightRein.rotateAngleX = -0.5235988F;
        this.neck.addChild(this.horseRightRein);
        this.horseHeadFaceRopes = new ModelRenderer(this, 1, 1);
        this.horseHeadFaceRopes.addBox(-3.0F, -11.0F, -1.9F, 6, 5, 6, 0.2F);
        this.head.addChild(this.horseHeadFaceRopes);
        this.horseSnoutFaceRopes = new ModelRenderer(this, 19, 0);
        this.horseSnoutFaceRopes.addBox(-2.0F, -11.0F, -4.0F, 4, 5, 2, 0.2F);
        this.head.addChild(this.horseSnoutFaceRopes);

        this.horseLeftEar = new ModelRenderer(this, 19, 16);
        this.horseLeftEar.addBox(0.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        this.head.addChild(this.horseLeftEar);
        this.horseRightEar = new ModelRenderer(this, 19, 16);
        this.horseRightEar.addBox(-2.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        this.head.addChild(this.horseRightEar);

        this.muleLeftChest = new ModelRenderer(this, 26, 21);
        this.muleLeftChest.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 3);
        this.muleLeftChest.setRotationPoint(6.0F, -8.0F, 0.0F);
        this.muleLeftChest.rotateAngleY = -1.5707964F;
        this.body.addChild(this.muleLeftChest);
        this.muleRightChest = new ModelRenderer(this, 26, 21);
        this.muleRightChest.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 3);
        this.muleRightChest.setRotationPoint(-6.0F, -8.0F, 0.0F);
        this.muleRightChest.rotateAngleY = 1.5707964F;
        this.body.addChild(this.muleRightChest);

        this.muleLeftEar = new ModelRenderer(this, 0, 12);
        this.muleLeftEar.addBox(-1.0F, -7.0F, 0.0F, 2, 7, 1);
        this.muleLeftEar.setRotationPoint(1.25F, -10.0F, 4.0F);
        this.muleRightEar = new ModelRenderer(this, 0, 12);
        this.muleRightEar.addBox(-1.0F, -7.0F, 0.0F, 2, 7, 1);
        this.muleRightEar.setRotationPoint(-1.25F, -10.0F, 4.0F);
        this.muleLeftEar.rotateAngleX = 0.2617994F;
        this.muleLeftEar.rotateAngleZ = 0.2617994F;
        this.muleRightEar.rotateAngleX = 0.2617994F;
        this.muleRightEar.rotateAngleZ = -0.2617994F;
        this.head.addChild(this.muleLeftEar);
        this.head.addChild(this.muleRightEar);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        AbstractHorse abstracthorse = (AbstractHorse) entityIn;
        boolean flag = abstracthorse.isChild();
        float f1 = abstracthorse.getHorseSize();
        boolean flag1 = !flag && abstracthorse.isHorseSaddled();
        boolean flag2 = abstracthorse instanceof AbstractChestHorse;
        boolean flag3 = !flag && flag2 && ((AbstractChestHorse) abstracthorse).hasChest();
        boolean flag4 = abstracthorse.isBeingRidden();

        if (!flag1) {
            this.horseSaddle.isHidden = true;
            this.horseLeftFaceMetal.isHidden = true;
            this.horseRightFaceMetal.isHidden = true;
            this.horseHeadFaceRopes.isHidden = true;
            this.horseSnoutFaceRopes.isHidden = true;
            this.horseLeftRein.isHidden = true;
            this.horseRightRein.isHidden = true;
        } else {
            this.horseSaddle.isHidden = false;
            this.horseLeftFaceMetal.isHidden = false;
            this.horseRightFaceMetal.isHidden = false;
            this.horseHeadFaceRopes.isHidden = false;
            this.horseSnoutFaceRopes.isHidden = false;
            if (flag4) {
                this.horseLeftRein.isHidden = false;
                this.horseRightRein.isHidden = false;
            } else {
                this.horseLeftRein.isHidden = true;
                this.horseRightRein.isHidden = true;
            }
        }

        if (flag) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(f1, 0.5F + f1 * 0.5F, f1);
            GlStateManager.translate(0.0F, 0.95F * (1.0F - f1), 0.0F);
        }

        this.backLeftLeg.render(scale);
        this.backRightLeg.render(scale);
        this.frontLeftLeg.render(scale);
        this.frontRightLeg.render(scale);
        if (flag) {
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(f1, f1, f1);
            GlStateManager.translate(0.0F, 2.3F * (1.0F - f1), 0.0F);
        }

        if (!flag3) {
            this.muleLeftChest.isHidden = true;
            this.muleRightChest.isHidden = true;
        } else {
            this.muleLeftChest.isHidden = false;
            this.muleRightChest.isHidden = false;
        }

        this.body.render(scale);
        if (flag) {
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float f2 = f1 + 0.1F * f1;
            GlStateManager.scale(f2, f2, f2);
            GlStateManager.translate(0.0F, 2.025F * (1.0F - f1), 0.1F * (1.4F - f2));
        }

        this.neck.render(scale);
        if (flag) {
            GlStateManager.popMatrix();
        }

    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        float var5 = this.updateHorseRotation(entitylivingbaseIn.prevRenderYawOffset, entitylivingbaseIn.renderYawOffset, partialTickTime);
        float var6 = this.updateHorseRotation(entitylivingbaseIn.prevRotationYawHead, entitylivingbaseIn.rotationYawHead, partialTickTime);
        float var7 = entitylivingbaseIn.prevRotationPitch + (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTickTime;
        float var8 = var6 - var5;
        float var9 = var7 * 0.017453292F;
        if (var8 > 20.0F) {
            var8 = 20.0F;
        }

        if (var8 < -20.0F) {
            var8 = -20.0F;
        }

        if (limbSwingAmount > 0.2F) {
            var9 += MathHelper.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
        }

        AbstractHorse var10 = (AbstractHorse) entitylivingbaseIn;
        float var11 = var10.getGrassEatingAmount(partialTickTime);
        float var12 = var10.getRearingAmount(partialTickTime);
        float var13 = 1.0F - var12;
        float var14 = var10.getMouthOpennessAngle(partialTickTime);
        boolean var15 = var10.tailCounter != 0;
        float var16 = (float) ((AbstractHorse) entitylivingbaseIn).ticksExisted + partialTickTime;
        this.neck.rotationPointY = 4.0F;
        this.neck.rotationPointZ = -12.0F;
        this.body.rotateAngleX = 0.0F;
        this.neck.rotateAngleX = 0.5235988F + var9;
        this.neck.rotateAngleY = var8 * 0.017453292F;
        float var24 = var10.isRearing() ? 0.2F : 1.0F;
        float var17 = MathHelper.cos(var24 * limbSwing * 0.6662F + 3.1415927F);
        float var18 = var17 * 0.8F * limbSwingAmount;
        float var19 = (1.0F - Math.max(var12, var11)) * (0.5235988F + var9 + var14 * MathHelper.sin(var16) * 0.05F);
        this.neck.rotateAngleX = var12 * (0.2617994F + var9) + var11 * (2.1816616F + MathHelper.sin(var16) * 0.05F) + var19;
        this.neck.rotateAngleY = var12 * var8 * 0.017453292F + (1.0F - Math.max(var12, var11)) * this.neck.rotateAngleY;
        this.neck.rotationPointY = var12 * -4.0F + var11 * 11.0F + (1.0F - Math.max(var12, var11)) * this.neck.rotationPointY;
        this.neck.rotationPointZ = var12 * -4.0F + var11 * -12.0F + (1.0F - Math.max(var12, var11)) * this.neck.rotationPointZ;
        this.body.rotateAngleX = var12 * -0.7853982F + var13 * this.body.rotateAngleX;
        float var23 = 0.2617994F * var12;
        float var20 = MathHelper.cos(var16 * 0.6F + 3.1415927F);
        this.frontLeftLeg.rotationPointY = 2.0F * var12 + 14.0F * var13;
        this.frontLeftLeg.rotationPointZ = -6.0F * var12 + -10.0F * var13;
        this.frontRightLeg.rotationPointY = this.frontLeftLeg.rotationPointY;
        this.frontRightLeg.rotationPointZ = this.frontLeftLeg.rotationPointZ;
        float var21 = (-1.0471976F + var20) * var12 + var18 * var13;
        float var22 = (-1.0471976F - var20) * var12 + -var18 * var13;
        this.backLeftLeg.rotateAngleX = var23 - var17 * 0.5F * limbSwingAmount * var13;
        this.backRightLeg.rotateAngleX = var23 + var17 * 0.5F * limbSwingAmount * var13;
        this.frontLeftLeg.rotateAngleX = var21;
        this.frontRightLeg.rotateAngleX = var22;
        this.tail.rotateAngleX = 0.5235988F + limbSwingAmount * 0.75F;
        this.tail.rotationPointY = -5.0F + limbSwingAmount;
        this.tail.rotationPointZ = 2.0F + limbSwingAmount * 2.0F;
        if (var15) {
            this.tail.rotateAngleY = MathHelper.cos(var16 * 0.7F);
        } else {
            this.tail.rotateAngleY = 0.0F;
        }

    }

    /**
     * Fixes and offsets a rotation in the ModelHorse class.
     */
    private float updateHorseRotation(float p_110683_1_, float p_110683_2_, float p_110683_3_) {
        float f;

        for (f = p_110683_2_ - p_110683_1_; f < -180.0F; f += 360.0F) {
            ;
        }

        while (f >= 180.0F) {
            f -= 360.0F;
        }

        return p_110683_1_ + p_110683_3_ * f;
    }
}