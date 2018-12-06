package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelEagle - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelEagle extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Head;
    public ModelRenderer RightClosedWing;
    public ModelRenderer LeftClosedWing;
    public ModelRenderer RightOpenWing;
    public ModelRenderer LeftOpenWing;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Tail;
    public ModelRenderer Beak1;
    public ModelRenderer Beak2;

    public ModelEagle() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.RightClosedWing = new ModelRenderer(this, 0, 49);
        this.RightClosedWing.setRotationPoint(-3.5F, 9.25F, -3.5F);
        this.RightClosedWing.addBox(-1.0F, 0.0F, -2.5F, 1, 10, 5, 0.0F);
        this.setRotateAngle(RightClosedWing, 0.5235987755982988F, 0.1308996938995747F, 0.0F);
        this.LeftOpenWing = new ModelRenderer(this, 16, 57);
        this.LeftOpenWing.mirror = true;
        this.LeftOpenWing.setRotationPoint(3.0F, 9.75F, -3.0F);
        this.LeftOpenWing.addBox(0.0F, 0.0F, -3.0F, 18, 1, 6, 0.0F);
        this.setRotateAngle(LeftOpenWing, 0.5235987755982988F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 38, 0);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 18.0F, -0.5F);
        this.LeftLeg.addBox(-1.5F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 16);
        this.Head.setRotationPoint(0.0F, 9.5F, -3.0F);
        this.Head.addBox(-2.5F, -4.0F, -5.0F, 5, 5, 7, 0.0F);
        this.RightLeg = new ModelRenderer(this, 38, 0);
        this.RightLeg.setRotationPoint(-2.0F, 18.0F, -0.5F);
        this.RightLeg.addBox(-1.5F, 0.0F, -4.0F, 3, 6, 4, 0.0F);
        this.Beak1 = new ModelRenderer(this, 24, 0);
        this.Beak1.setRotationPoint(0.0F, -1.0F, -3.5F);
        this.Beak1.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
        this.LeftClosedWing = new ModelRenderer(this, 0, 49);
        this.LeftClosedWing.mirror = true;
        this.LeftClosedWing.setRotationPoint(3.5F, 9.25F, -3.5F);
        this.LeftClosedWing.addBox(0.0F, 0.0F, -2.5F, 1, 10, 5, 0.0F);
        this.setRotateAngle(LeftClosedWing, 0.5235987755982988F, -0.1308996938995747F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.addBox(-3.5F, -9.0F, -1.0F, 7, 11, 5, 0.0F);
        this.setRotateAngle(Body, 0.5235987755982988F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, -12, 28);
        this.Tail.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Tail.addBox(-6.0F, 0.0F, 0.0F, 12, 0, 12, 0.0F);
        this.setRotateAngle(Tail, -0.7853981633974483F, 0.0F, 0.0F);
        this.RightOpenWing = new ModelRenderer(this, 16, 57);
        this.RightOpenWing.setRotationPoint(-3.0F, 9.75F, -3.0F);
        this.RightOpenWing.addBox(-18.0F, 0.0F, -3.0F, 18, 1, 6, 0.0F);
        this.setRotateAngle(RightOpenWing, 0.5235987755982988F, 0.0F, 0.0F);
        this.Beak2 = new ModelRenderer(this, 23, 0);
        this.Beak2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Beak2.addBox(-1.0F, 1.0F, -5.0F, 2, 1, 1, 0.0F);
        this.Head.addChild(this.Beak1);
        this.Body.addChild(this.Tail);
        this.Beak1.addChild(this.Beak2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.RightClosedWing.render(f5);
        this.LeftOpenWing.render(f5);
        this.LeftLeg.render(f5);
        this.Head.render(f5);
        this.RightLeg.render(f5);
        this.LeftClosedWing.render(f5);
        this.Body.render(f5);
        this.RightOpenWing.render(f5);
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
