package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * firefly - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelFireflyAlt extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer butt;
    public ModelRenderer head;
    public ModelRenderer lWing01;
    public ModelRenderer rWing01;
    public ModelRenderer lWing02;
    public ModelRenderer rWing02;
    public ModelRenderer lLeg01a;
    public ModelRenderer lLeg02a;
    public ModelRenderer lLeg03a;
    public ModelRenderer rLeg01a;
    public ModelRenderer rLeg02a;
    public ModelRenderer rLeg03a;
    public ModelRenderer lAntenna;
    public ModelRenderer rAntenna;
    public ModelRenderer lEye;
    public ModelRenderer rEye;
    public ModelRenderer lLeg01b;
    public ModelRenderer lLeg02b;
    public ModelRenderer lLeg03b;
    public ModelRenderer rLeg01b;
    public ModelRenderer rLeg02b;
    public ModelRenderer rLeg03b;

    public ModelFireflyAlt() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rWing02 = new ModelRenderer(this, 33, 2);
        this.rWing02.mirror = true;
        this.rWing02.setRotationPoint(-1.3F, -0.8F, 1.9F);
        this.rWing02.addBox(0.2F, -3.0F, 0.0F, 0, 6, 12, 0.0F);
        this.setRotateAngle(rWing02, 0.0F, -0.7285004297824331F, 0.0F);
        this.rLeg01b = new ModelRenderer(this, 23, 22);
        this.rLeg01b.mirror = true;
        this.rLeg01b.setRotationPoint(0.0F, 0.0F, -2.9F);
        this.rLeg01b.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rLeg01b, 0.0F, -0.36425021489121656F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -3.9F, 0.1F);
        this.head.addBox(-1.5F, -1.9F, -3.9F, 3, 3, 4, 0.0F);
        this.setRotateAngle(head, -0.5462880558742251F, 0.0F, 0.0F);
        this.lLeg03b = new ModelRenderer(this, 23, 22);
        this.lLeg03b.setRotationPoint(0.0F, 0.0F, -2.9F);
        this.lLeg03b.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lLeg03b, 0.0F, 0.36425021489121656F, 0.0F);
        this.lLeg02a = new ModelRenderer(this, 23, 17);
        this.lLeg02a.setRotationPoint(1.7F, -2.4F, 0.0F);
        this.lLeg02a.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lLeg02a, 0.4553564018453205F, -0.5462880558742251F, 0.0F);
        this.rLeg03b = new ModelRenderer(this, 23, 22);
        this.rLeg03b.mirror = true;
        this.rLeg03b.setRotationPoint(0.0F, 0.0F, -2.9F);
        this.rLeg03b.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rLeg03b, 0.0F, -0.36425021489121656F, 0.0F);
        this.rLeg02b = new ModelRenderer(this, 23, 22);
        this.rLeg02b.mirror = true;
        this.rLeg02b.setRotationPoint(0.0F, 0.0F, -2.9F);
        this.rLeg02b.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rLeg02b, 0.0F, -0.36425021489121656F, 0.0F);
        this.rLeg02a = new ModelRenderer(this, 23, 17);
        this.rLeg02a.mirror = true;
        this.rLeg02a.setRotationPoint(-1.7F, -2.4F, 0.0F);
        this.rLeg02a.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rLeg02a, 0.4553564018453205F, 0.5462880558742251F, 0.0F);
        this.rEye = new ModelRenderer(this, 18, 0);
        this.rEye.mirror = true;
        this.rEye.setRotationPoint(-0.9F, -1.0F, -2.6F);
        this.rEye.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.rLeg01a = new ModelRenderer(this, 23, 17);
        this.rLeg01a.mirror = true;
        this.rLeg01a.setRotationPoint(-1.7F, -3.5F, 0.0F);
        this.rLeg01a.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rLeg01a, 0.22759093446006054F, 0.5462880558742251F, 0.0F);
        this.rLeg03a = new ModelRenderer(this, 23, 17);
        this.rLeg03a.mirror = true;
        this.rLeg03a.setRotationPoint(-1.7F, -1.2F, 0.0F);
        this.rLeg03a.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rLeg03a, 0.6373942428283291F, 0.6829473363053812F, 0.0F);
        this.rAntenna = new ModelRenderer(this, 20, 0);
        this.rAntenna.mirror = true;
        this.rAntenna.setRotationPoint(-0.7F, -1.2F, -3.0F);
        this.rAntenna.addBox(0.0F, -5.0F, -3.6F, 0, 6, 5, 0.0F);
        this.setRotateAngle(rAntenna, 0.5235987755982988F, 0.13962634015954636F, 0.0F);
        this.rWing01 = new ModelRenderer(this, 33, 0);
        this.rWing01.mirror = true;
        this.rWing01.setRotationPoint(-0.8F, -3.1F, 1.7F);
        this.rWing01.addBox(0.2F, -1.5F, 0.0F, 0, 4, 9, 0.0F);
        this.setRotateAngle(rWing01, 0.27314402793711257F, -0.5462880558742251F, 0.0F);
        this.butt = new ModelRenderer(this, 0, 19);
        this.butt.setRotationPoint(0.0F, 1.1F, 0.0F);
        this.butt.addBox(-2.5F, -0.6F, -2.5F, 5, 8, 5, 0.0F);
        this.setRotateAngle(butt, -0.5462880558742251F, 0.0F, 0.0F);
        this.lAntenna = new ModelRenderer(this, 20, 0);
        this.lAntenna.setRotationPoint(0.7F, -1.2F, -3.0F);
        this.lAntenna.addBox(0.0F, -5.0F, -3.6F, 0, 6, 5, 0.0F);
        this.setRotateAngle(lAntenna, 0.5235987755982988F, -0.13962634015954636F, 0.0F);
        this.lLeg02b = new ModelRenderer(this, 23, 22);
        this.lLeg02b.setRotationPoint(0.0F, 0.0F, -2.9F);
        this.lLeg02b.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lLeg02b, 0.0F, 0.36425021489121656F, 0.0F);
        this.lWing01 = new ModelRenderer(this, 33, 0);
        this.lWing01.setRotationPoint(0.8F, -3.1F, 1.7F);
        this.lWing01.addBox(-0.2F, -1.5F, 0.0F, 0, 4, 9, 0.0F);
        this.setRotateAngle(lWing01, 0.27314402793711257F, 0.5462880558742251F, 0.0F);
        this.body = new ModelRenderer(this, 0, 8);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-2.0F, -5.0F, -1.8F, 4, 7, 4, 0.0F);
        this.setRotateAngle(body, 0.31869712141416456F, 0.0F, 0.0F);
        this.lLeg03a = new ModelRenderer(this, 23, 17);
        this.lLeg03a.setRotationPoint(1.7F, -1.2F, 0.0F);
        this.lLeg03a.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lLeg03a, 0.6373942428283291F, -0.6829473363053812F, 0.0F);
        this.lLeg01b = new ModelRenderer(this, 23, 22);
        this.lLeg01b.setRotationPoint(0.0F, 0.0F, -2.9F);
        this.lLeg01b.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lLeg01b, 0.0F, 0.36425021489121656F, 0.0F);
        this.lEye = new ModelRenderer(this, 18, 0);
        this.lEye.setRotationPoint(0.9F, -1.0F, -2.6F);
        this.lEye.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.lWing02 = new ModelRenderer(this, 33, 2);
        this.lWing02.setRotationPoint(1.3F, -0.8F, 1.9F);
        this.lWing02.addBox(-0.2F, -3.0F, 0.0F, 0, 6, 12, 0.0F);
        this.setRotateAngle(lWing02, 0.0F, 0.7285004297824331F, 0.0F);
        this.lLeg01a = new ModelRenderer(this, 23, 17);
        this.lLeg01a.setRotationPoint(1.7F, -3.5F, 0.0F);
        this.lLeg01a.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lLeg01a, 0.22759093446006054F, -0.5462880558742251F, 0.0F);
        this.body.addChild(this.rWing02);
        this.rLeg01a.addChild(this.rLeg01b);
        this.body.addChild(this.head);
        this.lLeg03a.addChild(this.lLeg03b);
        this.body.addChild(this.lLeg02a);
        this.rLeg03a.addChild(this.rLeg03b);
        this.rLeg02a.addChild(this.rLeg02b);
        this.body.addChild(this.rLeg02a);
        this.head.addChild(this.rEye);
        this.body.addChild(this.rLeg01a);
        this.body.addChild(this.rLeg03a);
        this.head.addChild(this.rAntenna);
        this.body.addChild(this.rWing01);
        this.body.addChild(this.butt);
        this.head.addChild(this.lAntenna);
        this.lLeg02a.addChild(this.lLeg02b);
        this.body.addChild(this.lWing01);
        this.body.addChild(this.lLeg03a);
        this.lLeg01a.addChild(this.lLeg01b);
        this.head.addChild(this.lEye);
        this.body.addChild(this.lWing02);
        this.body.addChild(this.lLeg01a);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
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
