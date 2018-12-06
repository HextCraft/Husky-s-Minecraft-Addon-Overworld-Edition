package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelOwl - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelOwl extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;

    public ModelOwl() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1 = new ModelRenderer(this, 18, 0);
        this.shape1.setRotationPoint(-3.0F, 15.0F, 0.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 6, 8, 4, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 6, 12);
        this.shape1_7.setRotationPoint(0.0F, 23.0F, 4.0F);
        this.shape1_7.addBox(-3.0F, -4.0F, 0.0F, 6, 4, 0, 0.0F);
        this.setRotateAngle(shape1_7, -2.41309222380736F, 0.0F, 0.0F);
        this.shape1_6 = new ModelRenderer(this, 18, 0);
        this.shape1_6.setRotationPoint(2.0F, 17.0F, 1.0F);
        this.shape1_6.addBox(-2.5F, -4.0F, -2.0F, 1, 2, 1, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 38, 11);
        this.shape1_3.setRotationPoint(3.0F, 16.0F, 0.0F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotateAngle(shape1_3, 0.27314402793711257F, -0.136659280431156F, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(0.0F, 15.0F, 2.0F);
        this.shape1_1.addBox(-2.5F, -4.0F, -2.0F, 5, 4, 4, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 3, 8);
        this.shape1_5.setRotationPoint(-3.0F, 24.0F, 0.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
        this.shape1_8 = new ModelRenderer(this, 6, 11);
        this.shape1_8.setRotationPoint(0.0F, 23.0F, 3.0F);
        this.shape1_8.addBox(0.0F, 0.0F, 0.0F, 3, 1, 0, 0.0F);
        this.shape1_9 = new ModelRenderer(this, 3, 8);
        this.shape1_9.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.shape1_9.addBox(0.0F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 38, 0);
        this.shape1_2.setRotationPoint(-3.0F, 16.0F, 0.0F);
        this.shape1_2.addBox(-1.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotateAngle(shape1_2, 0.27314402793711257F, 0.136659280431156F, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 6, 11);
        this.shape1_4.setRotationPoint(-3.0F, 23.0F, 3.0F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 0, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1.render(f5);
        this.shape1_7.render(f5);
        this.shape1_6.render(f5);
        this.shape1_3.render(f5);
        this.shape1_1.render(f5);
        this.shape1_5.render(f5);
        this.shape1_8.render(f5);
        this.shape1_9.render(f5);
        this.shape1_2.render(f5);
        this.shape1_4.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    /*@Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.owlBody.offsetX, this.owlBody.offsetY, this.owlBody.offsetZ);
        GlStateManager.translate(this.owlBody.rotationPointX * scale, this.owlBody.rotationPointY * scale, this.owlBody.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.owlBody.offsetX, -this.owlBody.offsetY, -this.owlBody.offsetZ);
        GlStateManager.translate(-this.owlBody.rotationPointX * scale, -this.owlBody.rotationPointY * scale, -this.owlBody.rotationPointZ * scale);
        this.owlBody.render(scale);
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (entityIn.onGround) {
            setRotationAngles(owlHead, (float) (headPitch * Math.PI / 360), (float) (netHeadYaw * Math.PI / 360), 0);
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        EntityOwl owl = (EntityOwl) entitylivingbaseIn;
        if (owl.onGround) {
            if (owl.isSitting()) {
                setSittingStance();
                float time = (owl.ticksExisted + partialTickTime) * 0.10471975512F;
                owlBody.rotateAngleX = 0.08726646259F + 0.00484813681f * MathHelper.sin(time);


            } else {
                setWanderingStance();
                float time = (owl.ticksExisted + partialTickTime) * 0.10471975512F;
                owlBody.rotateAngleX = 0.08726646259F + 0.00484813681f * MathHelper.sin(time);
            }
        } else {
            setFlyingStance();
            float time = (owl.ticksExisted + partialTickTime) / (1.5F * ((float) Math.PI));
            wingRight01.rotateAngleY = 0.26179938779914943f + 1.047166666666666f * MathHelper.cos(time);
            wingLeft01.rotateAngleY = -wingRight01.rotateAngleY;
            wingRight02.rotateAngleY = -0.52359877559F + 0.34906585039f * MathHelper.sin(time);
            wingLeft02.rotateAngleY = -wingRight02.rotateAngleY;
            wingRight03.rotateAngleY = wingRight01.rotateAngleY / 4;
            wingLeft03.rotateAngleY = -wingRight03.rotateAngleY;
            tail.rotateAngleX = -1.0471975511965976F + wingRight03.rotateAngleY / 4;
            tailRight.rotateAngleX = -0.8726646259971648F + tail.rotateAngleX + 1.0471975511965976F;
            tailLeft.rotateAngleX = tailRight.rotateAngleX;
            owlRightClaw.rotateAngleX = wingRight03.rotateAngleY / 4;
            owlLeftClaw.rotateAngleX = owlRightClaw.rotateAngleX;

            float angleAbs = Math.abs(0.2966972222f * MathHelper.sin(3.1415f * time / 0.4363194444f));
            if (angleAbs > 0.1745277777f) {
                angleAbs = 0.1745277777f;
            }
            wingLeft02.rotateAngleY = -angleAbs;
            wingLeft03.rotateAngleY = -angleAbs;
            wingRight02.rotateAngleY = angleAbs;
            wingRight03.rotateAngleY = angleAbs;
        }
    }

    private void setFlyingStance() {
        this.wingLeftAlt03a.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.wingRightAlt01a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.owlRightClaw.setRotationPoint(-3.0F, 6.0F, -1.5F);
        this.setRotationAngles(owlRightClaw, 0.8726646259971648F, 0.08726646259971647F, 0.0F);
        this.wingRight02.setRotationPoint(-6.0F, 0.0F, 1.0F);
        this.setRotationAngles(wingRight02, 0.0F, -0.4363323129985824F, 0.0F);
        this.wingRightAlt03b.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.owlBeak.setRotationPoint(-1.0F, 0.0F, -5.0F);
        this.setRotationAngles(owlBeak, -0.08726646259971647F, 0.0F, 0.0F);
        this.owlLeftEar.setRotationPoint(4.0F, -3.4000000953674316F, 0.0F);
        this.setRotationAngles(owlLeftEar, -0.3490658503988659F, 0.0F, 0.2617993877991494F);
        this.wingRight03.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.setRotationAngles(wingRight03, 0.0F, -0.3490658503988659F, 0.0F);
        this.owlHead.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.setRotationAngles(owlHead, -1.48352986419518F, 0.0F, 0.0F);
        this.lTalon01.setRotationPoint(0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(lTalon01, -0.17453292519943295F, -0.10471975511965977F, 0.0F);
        this.wingRightAlt01b.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.wingLeftAlt01a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingLeftAlt03c.setRotationPoint(-3.0F, 0.30000001192092896F, 0.0F);
        this.wingRightAlt02c.setRotationPoint(-3.0F, 1.2999999523162842F, 0.0F);
        this.wingLeft02.setRotationPoint(6.0F, 0.0F, 0.0F);
        this.setRotationAngles(wingLeft02, 0.0F, 0.4363323129985824F, 0.0F);
        this.owlRightEar.setRotationPoint(-4.0F, -3.4000000953674316F, 0.0F);
        this.setRotationAngles(owlRightEar, -0.3490658503988659F, 0.0F, -0.2617993877991494F);
        this.wingRight01.setRotationPoint(-6.0F, -7.0F, 0.0F);
        this.setRotationAngles(wingRight01, -0.08726646259971647F, 0.6108652381980153F, 0.0F);
        this.tail.setRotationPoint(-2.0F, 2.5F, 4.0F);
        this.setRotationAngles(tail, -1.0471975511965976F, 0.0F, 0.0F);
        this.wingRightAlt02a.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.wingRightAlt02b.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.rTalon03.setRotationPoint(-0.699999988079071F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(rTalon03, -0.17453292519943295F, -0.06981317007977318F, 0.0F);
        this.wingLeftAlt03b.setRotationPoint(3.0F, 1.7999999523162842F, -1.0F);
        this.tailRight.setRotationPoint(-2.0F, 2.5F, 4.0F);
        this.setRotationAngles(tailRight, -0.8726646259971648F, -0.4363323129985824F, -0.17453292519943295F);
        this.lTalon02.setRotationPoint(-0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(lTalon02, -0.17453292519943295F, 0.10471975511965977F, 0.0F);
        this.wingLeftAlt02c.setRotationPoint(-3.0F, 1.2999999523162842F, 0.0F);
        this.wingLeftAlt01b.setRotationPoint(3.0F, 1.7999999523162842F, 0.0F);
        this.wingLeft01.setRotationPoint(6.0F, -7.0F, 0.0F);
        this.setRotationAngles(wingLeft01, -0.08726646259971647F, -0.6108652381980153F, 0.0F);
        this.owlBodyFluff.setRotationPoint(0.0F, -6.400000095367432F, -3.0999999046325684F);
        this.setRotationAngles(owlBodyFluff, -0.3665191429188092F, 0.0F, 0.0F);
        this.wingLeftAlt01c.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.wingRightAlt03c.setRotationPoint(-9.0F, 0.30000001192092896F, 0.0F);
        this.owlBody.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.setRotationAngles(owlBody, 1.48352986419518F, 0.0F, 0.0F);
        this.wingRightAlt03a.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.wingLeftAlt02b.setRotationPoint(3.0F, 1.7999999523162842F, 0.0F);
        this.tailLeft.setRotationPoint(2.0F, 2.5F, 4.0F);
        this.setRotationAngles(tailLeft, -0.8726646259971648F, 0.4363323129985824F, 0.17453292519943295F);
        this.rTalon04.setRotationPoint(0.699999988079071F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(rTalon04, -0.17453292519943295F, 0.06981317007977318F, 0.0F);
        this.wingLeft03.setRotationPoint(6.0F, 0.0F, 0.0F);
        this.setRotationAngles(wingLeft03, 0.0F, 0.3490658503988659F, 0.0F);
        this.wingLeftAlt02a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lTalon04.setRotationPoint(-0.699999988079071F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(lTalon04, -0.17453292519943295F, -0.06981317007977318F, 0.0F);
        this.wingRightAlt01c.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.owlLeftClaw.setRotationPoint(3.0F, 6.0F, -1.5F);
        this.setRotationAngles(owlLeftClaw, 0.8726646259971648F, -0.08726646259971647F, 0.0F);
        this.lTalon03.setRotationPoint(0.6000000238418579F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(lTalon03, -0.17453292519943295F, 0.06981317007977318F, 0.0F);
        this.rTalon01.setRotationPoint(-0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(rTalon01, -0.17453292519943295F, 0.10471975511965977F, 0.0F);
        this.rTalon02.setRotationPoint(0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(rTalon02, -0.17453292519943295F, -0.10471975511965977F, 0.0F);


    }

    private void setWanderingStance() {
        this.lTalon03.setRotationPoint(0.6000000238418579F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(lTalon03, -0.17453292519943295F, 0.06981317007977318F, 0.0F);
        this.wingRightAlt02a.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.wingRightAlt02b.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.owlHead.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.setRotationAngles(owlHead, -0.06981317007977318F, 0.0F, 0.0F);
        this.owlBeak.setRotationPoint(-1.0F, 0.0F, -5.0F);
        this.setRotationAngles(owlBeak, -0.08726646259971647F, 0.0F, 0.0F);
        this.wingLeftAlt01c.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.lTalon02.setRotationPoint(-0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(lTalon02, -0.17453292519943295F, 0.10471975511965977F, 0.0F);
        this.wingLeft03.setRotationPoint(6.0F, 0.0F, 0.0F);
        this.setRotationAngles(wingLeft03, 0.0F, 0.0F, 0.3490658503988659F);
        this.wingRight03.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.setRotationAngles(wingRight03, 0.0F, 0.0F, -0.3490658503988659F);
        this.wingRightAlt03b.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.tail.setRotationPoint(-2.0F, 2.5F, 4.0F);
        this.setRotationAngles(tail, -1.0471975511965976F, 0.0F, 0.0F);
        this.rTalon01.setRotationPoint(-0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(rTalon01, -0.17453292519943295F, 0.10471975511965977F, 0.0F);
        this.owlBody.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.setRotationAngles(owlBody, 0.08726646259971647F, 0.0F, 0.0F);
        this.wingRightAlt03a.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.tailRight.setRotationPoint(-2.0F, 2.5F, 4.0F);
        this.setRotationAngles(tailRight, -0.8726646259971648F, -0.4363323129985824F, -0.17453292519943295F);
        this.wingRight01.setRotationPoint(-6.0F, -7.0F, 0.0F);
        this.setRotationAngles(wingRight01, -0.08726646259971647F, 0.6108652381980153F, -1.2217304763960306F);
        this.wingLeftAlt02a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingRightAlt02b.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.owlLeftClaw.setRotationPoint(3.0F, 6.0F, -1.5F);
        this.setRotationAngles(owlLeftClaw, 0.08726646259971647F, -0.08726646259971647F, 0.0F);
        this.lTalon04.setRotationPoint(-0.699999988079071F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(lTalon04, -0.17453292519943295F, -0.06981317007977318F, 0.0F);
        this.owlLeftEar.setRotationPoint(4.0F, -3.4000000953674316F, 0.0F);
        this.setRotationAngles(owlLeftEar, -0.3490658503988659F, 0.0F, 0.2617993877991494F);
        this.wingRightAlt01b.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.wingRightAlt01a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.owlBodyFluff.setRotationPoint(0.0F, -6.400000095367432F, -3.0999999046325684F);
        this.setRotationAngles(owlBodyFluff, -0.3665191429188092F, 0.0F, 0.0F);
        this.wingLeftAlt01b.setRotationPoint(3.0F, 1.7999999523162842F, 0.0F);
        this.owlRightClaw.setRotationPoint(-3.0F, 6.0F, -1.5F);
        this.setRotationAngles(owlRightClaw, 0.08726646259971647F, 0.08726646259971647F, 0.0F);
        this.wingLeft02.setRotationPoint(6.0F, 0.0F, 0.0F);
        this.setRotationAngles(wingLeft02, 0.0F, 0.0F, 0.4363323129985824F);
        this.wingLeftAlt03a.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.wingRightAlt02c.setRotationPoint(-3.0F, 1.2999999523162842F, 0.0F);
        this.wingRightAlt01c.setRotationPoint(-3.0F, 1.7999999523162842F, 0.0F);
        this.wingLeftAlt02c.setRotationPoint(-3.0F, 1.2999999523162842F, 0.0F);
        this.wingRightAlt03c.setRotationPoint(-9.0F, 0.30000001192092896F, 0.0F);
        this.wingLeft01.setRotationPoint(6.0F, -7.0F, 0.0F);
        this.setRotationAngles(wingLeft01, -0.08726646259971647F, -0.6108652381980153F, 1.2217304763960306F);
        this.wingRight02.setRotationPoint(-6.0F, 0.0F, 1.0F);
        this.setRotationAngles(wingRight02, 0.0F, 0.0F, -0.4363323129985824F);
        this.lTalon01.setRotationPoint(0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(lTalon01, -0.17453292519943295F, -0.10471975511965977F, 0.0F);
        this.wingLeftAlt01a.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rTalon04.setRotationPoint(0.699999988079071F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(rTalon04, -0.17453292519943295F, 0.06981317007977318F, 0.0F);
        this.wingLeftAlt03b.setRotationPoint(3.0F, 1.7999999523162842F, -1.0F);
        this.wingLeftAlt02b.setRotationPoint(3.0F, 1.7999999523162842F, 0.0F);
        this.owlRightEar.setRotationPoint(-4.0F, -3.4000000953674316F, 0.0F);
        this.setRotationAngles(owlRightEar, -0.3490658503988659F, 0.0F, -0.2617993877991494F);
        this.tailLeft.setRotationPoint(2.0F, 2.5F, 4.0F);
        this.setRotationAngles(tailLeft, -0.8726646259971648F, 0.4363323129985824F, 0.17453292519943295F);
        this.wingLeftAlt03c.setRotationPoint(-3.0F, 0.30000001192092896F, 0.0F);
        this.rTalon02.setRotationPoint(0.699999988079071F, 1.0F, -3.5999999046325684F);
        this.setRotationAngles(rTalon02, -0.17453292519943295F, -0.10471975511965977F, 0.0F);
        this.rTalon03.setRotationPoint(-0.699999988079071F, 1.2000000476837158F, 0.5F);
        this.setRotationAngles(rTalon03, -0.17453292519943295F, -0.06981317007977318F, 0.0F);

    }

    private void setSittingStance() {
        setWanderingStance();
        this.setRotationAngles(owlLeftClaw, -0.08726646259971647F, -0.3f, 0);
        this.setRotationAngles(owlRightClaw, -0.08726646259971647F, 0.3f, 0);
        this.setRotationAngles(this.owlBody, 0.08726646259971647F, 0.0F, 0.0F);
    }*/

}