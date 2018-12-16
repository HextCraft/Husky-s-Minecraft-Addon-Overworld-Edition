package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Drowned Scuba Villager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelDrownedScubaVillager extends ModelBiped {

    public ModelRenderer Tank1;
    public ModelRenderer Tank2;
    public ModelRenderer Body;
    public ModelRenderer Leaf;
    public ModelRenderer Pipe2;
    public ModelRenderer Head;
    public ModelRenderer Pipe1;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Mask;
    public ModelRenderer Nose;
    public ModelRenderer RightFlipper;
    public ModelRenderer LeftFlipper;

    public ModelDrownedScubaVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new ModelRenderer(this, 44, 20);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftArm, -1.5707963267948966F, 0.0F, 0.0F);
        this.Tank2 = new ModelRenderer(this, 4, 55);
        this.Tank2.setRotationPoint(0.0F, 9.5F, 6.0F);
        this.Tank2.addBox(-4.0F, -2.0F, -2.5F, 8, 4, 5, 0.0F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, -4.0F);
        this.Nose.addBox(-1.0F, -1.0F, -2.0F, 2, 4, 2, 0.0F);
        this.Tank1 = new ModelRenderer(this, 32, 44);
        this.Tank1.setRotationPoint(0.0F, 0.0F, 3.1F);
        this.Tank1.addBox(-5.0F, -2.0F, 0.0F, 10, 14, 6, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.Pipe2 = new ModelRenderer(this, 60, 0);
        this.Pipe2.setRotationPoint(4.5F, -2.5F, -4.5F);
        this.Pipe2.addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1, 0.0F);
        this.RightArm = new ModelRenderer(this, 44, 20);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightArm, -1.5707963267948966F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 16, 18);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 20);
        this.RightLeg.setRotationPoint(-2.0F, 11.9F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightLeg, 0.0F, 0.17453292519943295F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 20);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 11.9F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftLeg, 0.0F, -0.17453292519943295F, 0.0F);
        this.Mask = new ModelRenderer(this, 34, 0);
        this.Mask.setRotationPoint(0.0F, -4.5F, -3.5F);
        this.Mask.addBox(-4.5F, -2.5F, -1.0F, 9, 5, 1, 0.0F);
        this.Leaf = new ModelRenderer(this, 60, 10);
        this.Leaf.setRotationPoint(5.0F, -6.0F, -4.5F);
        this.Leaf.addBox(0.0F, -4.5F, -0.5F, 1, 9, 1, 0.0F);
        this.LeftFlipper = new ModelRenderer(this, 0, 37);
        this.LeftFlipper.mirror = true;
        this.LeftFlipper.setRotationPoint(0.0F, 11.6F, 0.0F);
        this.LeftFlipper.addBox(-3.0F, -0.5F, -9.0F, 6, 1, 12, 0.0F);
        this.RightFlipper = new ModelRenderer(this, 0, 37);
        this.RightFlipper.setRotationPoint(0.0F, 11.6F, 0.0F);
        this.RightFlipper.addBox(-3.0F, -0.5F, -9.0F, 6, 1, 12, 0.0F);
        this.Pipe1 = new ModelRenderer(this, 36, 9);
        this.Pipe1.setRotationPoint(4.5F, -2.0F, 3.0F);
        this.Pipe1.addBox(-0.5F, -0.5F, -8.0F, 1, 1, 8, 0.0F);
        this.Head.addChild(this.Nose);
        this.Head.addChild(this.Mask);
        this.LeftLeg.addChild(this.LeftFlipper);
        this.RightLeg.addChild(this.RightFlipper);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftArm.render(f5);
        this.Tank2.render(f5);
        this.Tank1.render(f5);
        this.Head.render(f5);
        this.Pipe2.render(f5);
        this.RightArm.render(f5);
        this.Body.render(f5);
        this.RightLeg.render(f5);
        this.LeftLeg.render(f5);
        this.Leaf.render(f5);
        this.Pipe1.render(f5);
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
