package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Adventurer Villager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelAdventurerVillager extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer BackPack1;
    public ModelRenderer BackPack2;
    public ModelRenderer BackPack3;
    public ModelRenderer Body;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer MiddleArm;
    public ModelRenderer Nose;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;

    public ModelAdventurerVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new ModelRenderer(this, 44, 22);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.LeftArm.addBox(4.0F, -5.0F, -1.0F, 4, 8, 4, 0.0F);
        this.BackPack1 = new ModelRenderer(this, 0, 38);
        this.BackPack1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackPack1.addBox(-4.0F, 0.0F, -3.0F, 8, 16, 6, 0.5F);
        this.RightArm = new ModelRenderer(this, 44, 22);
        this.RightArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.RightArm.addBox(-8.0F, -5.0F, -1.0F, 4, 8, 4, 0.0F);
        this.BackPack2 = new ModelRenderer(this, 40, 0);
        this.BackPack2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackPack2.addBox(-4.0F, 0.0F, 3.0F, 8, 8, 4, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 22);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.BackPack3 = new ModelRenderer(this, 38, 0);
        this.BackPack3.setRotationPoint(0.0F, 2.0F, 7.0F);
        this.BackPack3.addBox(-1.0F, -0.5F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(BackPack3, 0.17453292519943295F, 0.0F, 0.0F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.Nose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.MiddleArm = new ModelRenderer(this, 40, 38);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 16, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 22);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleArm.addChild(this.LeftArm);
        this.MiddleArm.addChild(this.RightArm);
        this.Head.addChild(this.Nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.BackPack1.render(f5);
        this.BackPack2.render(f5);
        this.RightLeg.render(f5);
        this.BackPack3.render(f5);
        this.MiddleArm.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.LeftLeg.render(f5);
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
