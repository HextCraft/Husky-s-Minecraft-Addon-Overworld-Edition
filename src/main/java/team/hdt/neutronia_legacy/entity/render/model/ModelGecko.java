package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Gecko - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelGecko extends ModelBase {
    public ModelRenderer FrontRightLeg;
    public ModelRenderer FrontLeftLeg;
    public ModelRenderer HeadLayer;
    public ModelRenderer BackRightFoot;
    public ModelRenderer BackLeftFoot;
    public ModelRenderer Head;
    public ModelRenderer Tongue;
    public ModelRenderer BodyLayer;
    public ModelRenderer Body;
    public ModelRenderer TailLayer;
    public ModelRenderer Tail;
    public ModelRenderer FrontRightFoot;
    public ModelRenderer FrontLeftFoot;
    public ModelRenderer BackRightFoot_1;
    public ModelRenderer BackLeftFoot_1;

    public ModelGecko() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.FrontRightFoot = new ModelRenderer(this, 12, 9);
        this.FrontRightFoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.FrontRightFoot.addBox(-1.5F, 2.0F, -3.0F, 3, 0, 3, 0.0F);
        this.setRotateAngle(FrontRightFoot, 0.2617993877991494F, 0.0F, 0.0F);
        this.BackLeftFoot = new ModelRenderer(this, 19, 0);
        this.BackLeftFoot.mirror = true;
        this.BackLeftFoot.setRotationPoint(1.5F, 21.9F, 5.0F);
        this.BackLeftFoot.addBox(-0.5F, -1.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(BackLeftFoot, -0.17453292519943295F, -0.3490658503988659F, 0.0F);
        this.Tongue = new ModelRenderer(this, -2, 0);
        this.Tongue.setRotationPoint(0.0F, 22.5F, -5.0F);
        this.Tongue.addBox(-0.5F, 0.0F, -1.5F, 1, 0, 2, 0.0F);
        this.setRotateAngle(Tongue, 0.4363323129985824F, 0.0F, 0.0F);
        this.BodyLayer = new ModelRenderer(this, 18, 9);
        this.BodyLayer.setRotationPoint(0.0F, 21.5F, 0.0F);
        this.BodyLayer.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 6, 0.25F);
        this.FrontLeftLeg = new ModelRenderer(this, 13, 0);
        this.FrontLeftLeg.mirror = true;
        this.FrontLeftLeg.setRotationPoint(1.5F, 21.9F, 0.0F);
        this.FrontLeftLeg.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(FrontLeftLeg, -0.2617993877991494F, -0.4363323129985824F, 0.0F);
        this.BackRightFoot_1 = new ModelRenderer(this, 12, 9);
        this.BackRightFoot_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackRightFoot_1.addBox(-1.5F, 2.0F, -2.25F, 3, 0, 3, 0.0F);
        this.setRotateAngle(BackRightFoot_1, 0.17453292519943295F, 0.0F, 0.0F);
        this.FrontRightLeg = new ModelRenderer(this, 13, 0);
        this.FrontRightLeg.setRotationPoint(-1.5F, 21.9F, 0.0F);
        this.FrontRightLeg.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(FrontRightLeg, -0.2617993877991494F, 0.4363323129985824F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 9);
        this.Body.setRotationPoint(0.0F, 21.5F, 0.0F);
        this.Body.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 6, 0.0F);
        this.Tail = new ModelRenderer(this, 36, 0);
        this.Tail.setRotationPoint(0.0F, 21.75F, 6.0F);
        this.Tail.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 8, 0.0F);
        this.setRotateAngle(Tail, -0.17453292519943295F, 0.0F, 0.0F);
        this.BackRightFoot = new ModelRenderer(this, 19, 0);
        this.BackRightFoot.setRotationPoint(-1.5F, 21.9F, 5.0F);
        this.BackRightFoot.addBox(-0.5F, -1.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(BackRightFoot, -0.17453292519943295F, 0.3490658503988659F, 0.0F);
        this.BackLeftFoot_1 = new ModelRenderer(this, 12, 9);
        this.BackLeftFoot_1.mirror = true;
        this.BackLeftFoot_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackLeftFoot_1.addBox(-1.5F, 2.0F, -2.25F, 3, 0, 3, 0.0F);
        this.setRotateAngle(BackLeftFoot_1, 0.17453292519943295F, 0.0F, 0.0F);
        this.HeadLayer = new ModelRenderer(this, 18, 0);
        this.HeadLayer.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.HeadLayer.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.25F);
        this.FrontLeftFoot = new ModelRenderer(this, 12, 9);
        this.FrontLeftFoot.mirror = true;
        this.FrontLeftFoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.FrontLeftFoot.addBox(-1.5F, 2.0F, -3.0F, 3, 0, 3, 0.0F);
        this.setRotateAngle(FrontLeftFoot, 0.2617993877991494F, 0.0F, 0.0F);
        this.TailLayer = new ModelRenderer(this, 36, 9);
        this.TailLayer.setRotationPoint(0.0F, 21.75F, 6.0F);
        this.TailLayer.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 8, 0.25F);
        this.setRotateAngle(TailLayer, -0.17453292519943295F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.Head.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.0F);
        this.FrontRightLeg.addChild(this.FrontRightFoot);
        this.BackRightFoot.addChild(this.BackRightFoot_1);
        this.BackLeftFoot.addChild(this.BackLeftFoot_1);
        this.FrontLeftLeg.addChild(this.FrontLeftFoot);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.BackLeftFoot.render(f5);
        this.Tongue.render(f5);
        this.BodyLayer.render(f5);
        this.FrontLeftLeg.render(f5);
        this.FrontRightLeg.render(f5);
        this.Body.render(f5);
        this.Tail.render(f5);
        this.BackRightFoot.render(f5);
        this.HeadLayer.render(f5);
        this.TailLayer.render(f5);
        this.Head.render(f5);
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
