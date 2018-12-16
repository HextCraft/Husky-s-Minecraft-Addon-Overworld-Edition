package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * bramble - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelBramble extends ModelBase {
    public ModelRenderer body01;
    public ModelRenderer head;
    public ModelRenderer thorns01;
    public ModelRenderer thorns02;
    public ModelRenderer thorns03;
    public ModelRenderer thorns04;
    public ModelRenderer lowerJaw;
    public ModelRenderer upperTeeth;
    public ModelRenderer headThorns01;
    public ModelRenderer headThorns02;
    public ModelRenderer headThorns03;
    public ModelRenderer headThorns04;
    public ModelRenderer headThorns05;
    public ModelRenderer headThorns06;
    public ModelRenderer headThorns07;
    public ModelRenderer lowerTeeth;
    public ModelRenderer lowerJawThorns01;
    public ModelRenderer lowerJawThorns02;
    public ModelRenderer lowerJawThorns03;
    public ModelRenderer lowerJawThorns04;
    public ModelRenderer lowerJawThorns05;
    public ModelRenderer lowerJawThorns06;
    public ModelRenderer lowerJawThorns07;
    public ModelRenderer lowerJawThorns08;

    public ModelBramble() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.headThorns07 = new ModelRenderer(this, 101, 35);
        this.headThorns07.mirror = true;
        this.headThorns07.setRotationPoint(-3.5F, -2.0F, -6.5F);
        this.headThorns07.addBox(-3.0F, 0.0F, -6.5F, 3, 0, 13, 0.0F);
        this.lowerJawThorns07 = new ModelRenderer(this, 85, 35);
        this.lowerJawThorns07.setRotationPoint(3.4F, 0.7F, -6.0F);
        this.lowerJawThorns07.addBox(-0.5F, 0.0F, -6.5F, 3, 0, 13, 0.0F);
        this.lowerJawThorns02 = new ModelRenderer(this, 98, 13);
        this.lowerJawThorns02.setRotationPoint(3.4F, 1.5F, -6.0F);
        this.lowerJawThorns02.addBox(0.0F, 0.0F, -6.5F, 0, 3, 13, 0.0F);
        this.setRotateAngle(lowerJawThorns02, 0.0F, 0.0F, -0.7853981633974483F);
        this.upperTeeth = new ModelRenderer(this, 0, 19);
        this.upperTeeth.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.upperTeeth.addBox(-4.0F, 0.3F, -13.0F, 8, 5, 13, 0.0F);
        this.headThorns05 = new ModelRenderer(this, 98, 36);
        this.headThorns05.setRotationPoint(-3.4F, -4.0F, -6.5F);
        this.headThorns05.addBox(0.0F, -3.0F, -6.5F, 0, 3, 13, 0.0F);
        this.setRotateAngle(headThorns05, 0.0F, 0.0F, -0.7853981633974483F);
        this.thorns01 = new ModelRenderer(this, 41, 40);
        this.thorns01.setRotationPoint(2.0F, -2.0F, 8.0F);
        this.thorns01.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns01, 0.0F, 0.0F, 0.7853981633974483F);
        this.headThorns06 = new ModelRenderer(this, 101, 35);
        this.headThorns06.setRotationPoint(3.5F, -2.0F, -6.5F);
        this.headThorns06.addBox(0.0F, 0.0F, -6.5F, 3, 0, 13, 0.0F);
        this.lowerJawThorns06 = new ModelRenderer(this, 96, 31);
        this.lowerJawThorns06.setRotationPoint(0.0F, 0.4F, -12.2F);
        this.lowerJawThorns06.addBox(-3.5F, 0.0F, -2.0F, 7, 0, 2, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 51, 21);
        this.lowerJaw.setRotationPoint(0.0F, 3.1F, 0.0F);
        this.lowerJaw.addBox(-3.5F, -1.5F, -12.2F, 7, 3, 13, 0.0F);
        this.headThorns01 = new ModelRenderer(this, 97, 8);
        this.headThorns01.setRotationPoint(4.0F, -2.0F, -13.0F);
        this.headThorns01.addBox(-0.1F, -2.5F, 0.0F, 3, 5, 0, 0.0F);
        this.setRotateAngle(headThorns01, 0.0F, 0.7853981633974483F, 0.0F);
        this.headThorns03 = new ModelRenderer(this, 95, 3);
        this.headThorns03.setRotationPoint(0.0F, -4.3F, -12.8F);
        this.headThorns03.addBox(-4.0F, 0.0F, -3.0F, 8, 0, 3, 0.0F);
        this.setRotateAngle(headThorns03, -0.7853981633974483F, 0.0F, 0.0F);
        this.thorns02 = new ModelRenderer(this, 41, 40);
        this.thorns02.setRotationPoint(-2.0F, -2.0F, 8.0F);
        this.thorns02.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns02, 0.0F, 0.0F, -0.7853981633974483F);
        this.thorns03 = new ModelRenderer(this, 41, 40);
        this.thorns03.setRotationPoint(2.0F, 2.0F, 8.0F);
        this.thorns03.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns03, 0.0F, 0.0F, 2.356194490192345F);
        this.headThorns04 = new ModelRenderer(this, 98, 36);
        this.headThorns04.setRotationPoint(3.4F, -4.0F, -6.5F);
        this.headThorns04.addBox(0.0F, -3.0F, -6.5F, 0, 3, 13, 0.0F);
        this.setRotateAngle(headThorns04, 0.0F, 0.0F, 0.7853981633974483F);
        this.lowerJawThorns04 = new ModelRenderer(this, 98, 13);
        this.lowerJawThorns04.setRotationPoint(3.5F, 0.0F, -12.1F);
        this.lowerJawThorns04.addBox(0.0F, -0.5F, -2.0F, 0, 3, 2, 0.0F);
        this.setRotateAngle(lowerJawThorns04, 0.0F, -0.7853981633974483F, 0.0F);
        this.lowerJawThorns08 = new ModelRenderer(this, 85, 35);
        this.lowerJawThorns08.mirror = true;
        this.lowerJawThorns08.setRotationPoint(-3.4F, 0.7F, -6.0F);
        this.lowerJawThorns08.addBox(-2.5F, 0.0F, -6.5F, 3, 0, 13, 0.0F);
        this.thorns04 = new ModelRenderer(this, 41, 40);
        this.thorns04.setRotationPoint(-2.0F, 2.0F, 8.0F);
        this.thorns04.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns04, 0.0F, 0.0F, -2.356194490192345F);
        this.lowerJawThorns01 = new ModelRenderer(this, 98, 21);
        this.lowerJawThorns01.setRotationPoint(0.0F, 1.2F, -11.9F);
        this.lowerJawThorns01.addBox(-3.5F, 0.0F, 0.0F, 7, 2, 0, 0.0F);
        this.setRotateAngle(lowerJawThorns01, -0.7853981633974483F, 0.0F, 0.0F);
        this.lowerJawThorns05 = new ModelRenderer(this, 98, 13);
        this.lowerJawThorns05.setRotationPoint(-3.5F, 0.0F, -12.1F);
        this.lowerJawThorns05.addBox(0.0F, -0.5F, -2.0F, 0, 3, 2, 0.0F);
        this.setRotateAngle(lowerJawThorns05, 0.0F, 0.7853981633974483F, 0.0F);
        this.lowerJawThorns03 = new ModelRenderer(this, 98, 13);
        this.lowerJawThorns03.setRotationPoint(-3.4F, 1.5F, -6.0F);
        this.lowerJawThorns03.addBox(0.0F, 0.0F, -6.5F, 0, 3, 13, 0.0F);
        this.setRotateAngle(lowerJawThorns03, 0.0F, 0.0F, 0.7853981633974483F);
        this.lowerTeeth = new ModelRenderer(this, 49, 0);
        this.lowerTeeth.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowerTeeth.addBox(-3.5F, -4.5F, -12.2F, 7, 3, 13, 0.0F);
        this.headThorns02 = new ModelRenderer(this, 97, 8);
        this.headThorns02.mirror = true;
        this.headThorns02.setRotationPoint(-4.0F, -2.0F, -13.0F);
        this.headThorns02.addBox(-2.9F, -2.5F, 0.0F, 3, 5, 0, 0.0F);
        this.setRotateAngle(headThorns02, 0.0F, -0.7853981633974483F, 0.0F);
        this.body01 = new ModelRenderer(this, 0, 40);
        this.body01.setRotationPoint(0.0F, 22.0F, -8.0F);
        this.body01.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 16, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.head.addBox(-4.0F, -4.5F, -13.0F, 8, 5, 13, 0.0F);
        this.head.addChild(this.headThorns07);
        this.lowerJaw.addChild(this.lowerJawThorns07);
        this.lowerJaw.addChild(this.lowerJawThorns02);
        this.head.addChild(this.upperTeeth);
        this.head.addChild(this.headThorns05);
        this.body01.addChild(this.thorns01);
        this.head.addChild(this.headThorns06);
        this.lowerJaw.addChild(this.lowerJawThorns06);
        this.head.addChild(this.lowerJaw);
        this.head.addChild(this.headThorns01);
        this.head.addChild(this.headThorns03);
        this.body01.addChild(this.thorns02);
        this.body01.addChild(this.thorns03);
        this.head.addChild(this.headThorns04);
        this.lowerJaw.addChild(this.lowerJawThorns04);
        this.lowerJaw.addChild(this.lowerJawThorns08);
        this.body01.addChild(this.thorns04);
        this.lowerJaw.addChild(this.lowerJawThorns01);
        this.lowerJaw.addChild(this.lowerJawThorns05);
        this.lowerJaw.addChild(this.lowerJawThorns03);
        this.lowerJaw.addChild(this.lowerTeeth);
        this.head.addChild(this.headThorns02);
        this.body01.addChild(this.head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body01.render(f5);
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
