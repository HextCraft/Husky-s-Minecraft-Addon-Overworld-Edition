package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Salamander - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelSalamander extends ModelBase {
    public ModelRenderer Body1;
    public ModelRenderer Head;
    public ModelRenderer Body2;
    public ModelRenderer FrontRightFoot;
    public ModelRenderer FrontLeftFoot;
    public ModelRenderer RightFin1;
    public ModelRenderer RightFin2;
    public ModelRenderer LeftFin1;
    public ModelRenderer LeftFin2;
    public ModelRenderer RightWisker;
    public ModelRenderer LeftWisker;
    public ModelRenderer BackRightFoot;
    public ModelRenderer BackLeftFoot;
    public ModelRenderer Tail1;
    public ModelRenderer TailFin1;
    public ModelRenderer Tail2;
    public ModelRenderer TailFin2;

    public ModelSalamander() {
        this.textureWidth = 96;
        this.textureHeight = 64;
        this.LeftFin1 = new ModelRenderer(this, 44, 0);
        this.LeftFin1.mirror = true;
        this.LeftFin1.setRotationPoint(4.5F, 0.0F, -1.0F);
        this.LeftFin1.addBox(-1.0F, 0.0F, -2.0F, 8, 0, 4, 0.0F);
        this.setRotateAngle(LeftFin1, 0.2617993877991494F, 0.0F, -0.8726646259971648F);
        this.RightWisker = new ModelRenderer(this, 40, 0);
        this.RightWisker.setRotationPoint(-4.5F, 1.0F, -5.0F);
        this.RightWisker.addBox(-1.0F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RightWisker, 0.2617993877991494F, -1.1344640137963142F, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 14);
        this.Body1.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.Body1.addBox(-5.5F, -3.5F, 0.0F, 11, 7, 14, 0.0F);
        this.RightFin1 = new ModelRenderer(this, 44, 0);
        this.RightFin1.setRotationPoint(-4.5F, 0.0F, -1.0F);
        this.RightFin1.addBox(-7.0F, 0.0F, -2.0F, 8, 0, 4, 0.0F);
        this.setRotateAngle(RightFin1, 0.2617993877991494F, 0.0F, 0.8726646259971648F);
        this.LeftWisker = new ModelRenderer(this, 40, 0);
        this.LeftWisker.mirror = true;
        this.LeftWisker.setRotationPoint(4.5F, 1.0F, -5.0F);
        this.LeftWisker.addBox(0.0F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(LeftWisker, 0.2617993877991494F, 1.1344640137963142F, 0.0F);
        this.BackRightFoot = new ModelRenderer(this, 66, 4);
        this.BackRightFoot.setRotationPoint(-3.0F, 2.0F, 10.0F);
        this.BackRightFoot.addBox(-1.0F, -1.0F, -6.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(BackRightFoot, 2.1816615649929116F, -0.6981317007977318F, 0.0F);
        this.TailFin1 = new ModelRenderer(this, 32, 23);
        this.TailFin1.setRotationPoint(0.0F, -0.5F, 6.0F);
        this.TailFin1.addBox(0.0F, -6.0F, -6.0F, 0, 12, 12, 0.0F);
        this.TailFin2 = new ModelRenderer(this, 56, 15);
        this.TailFin2.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.TailFin2.addBox(0.0F, -6.0F, -6.0F, 0, 10, 20, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-5.0F, -3.0F, -8.0F, 10, 6, 8, 0.0F);
        this.RightFin2 = new ModelRenderer(this, 44, 0);
        this.RightFin2.setRotationPoint(-5.0F, 0.0F, -1.0F);
        this.RightFin2.addBox(-7.0F, 0.0F, -2.0F, 8, 0, 4, 0.0F);
        this.setRotateAngle(RightFin2, 0.2617993877991494F, 0.0F, 0.4363323129985824F);
        this.Body2 = new ModelRenderer(this, 0, 35);
        this.Body2.setRotationPoint(0.0F, 0.0F, 14.0F);
        this.Body2.addBox(-5.0F, -3.0F, 0.0F, 10, 6, 12, 0.0F);
        this.BackLeftFoot = new ModelRenderer(this, 66, 4);
        this.BackLeftFoot.setRotationPoint(3.0F, 2.0F, 10.0F);
        this.BackLeftFoot.addBox(-1.0F, -1.0F, -6.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(BackLeftFoot, 2.1816615649929116F, 0.6981317007977318F, 0.0F);
        this.FrontRightFoot = new ModelRenderer(this, 48, 4);
        this.FrontRightFoot.setRotationPoint(-5.5F, 3.0F, 0.0F);
        this.FrontRightFoot.addBox(-2.0F, -1.0F, -6.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(FrontRightFoot, 0.3490658503988659F, 0.0F, 0.0F);
        this.Tail1 = new ModelRenderer(this, 64, 48);
        this.Tail1.setRotationPoint(0.0F, 0.0F, 12.0F);
        this.Tail1.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 12, 0.0F);
        this.Tail2 = new ModelRenderer(this, 32, 49);
        this.Tail2.setRotationPoint(0.0F, -0.5F, 12.0F);
        this.Tail2.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 12, 0.0F);
        this.FrontLeftFoot = new ModelRenderer(this, 48, 4);
        this.FrontLeftFoot.setRotationPoint(5.5F, 3.0F, 0.0F);
        this.FrontLeftFoot.addBox(0.0F, -1.0F, -6.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(FrontLeftFoot, 0.3490658503988659F, 0.0F, 0.0F);
        this.LeftFin2 = new ModelRenderer(this, 44, 0);
        this.LeftFin2.mirror = true;
        this.LeftFin2.setRotationPoint(5.0F, 0.0F, -1.0F);
        this.LeftFin2.addBox(-1.0F, 0.0F, -2.0F, 8, 0, 4, 0.0F);
        this.setRotateAngle(LeftFin2, 0.2617993877991494F, 0.0F, -0.4363323129985824F);
        this.Head.addChild(this.LeftFin1);
        this.Head.addChild(this.RightWisker);
        this.Head.addChild(this.RightFin1);
        this.Head.addChild(this.LeftWisker);
        this.Body2.addChild(this.BackRightFoot);
        this.Tail1.addChild(this.TailFin1);
        this.Tail2.addChild(this.TailFin2);
        this.Body1.addChild(this.Head);
        this.Head.addChild(this.RightFin2);
        this.Body1.addChild(this.Body2);
        this.Body2.addChild(this.BackLeftFoot);
        this.Body1.addChild(this.FrontRightFoot);
        this.Body2.addChild(this.Tail1);
        this.Tail1.addChild(this.Tail2);
        this.Body1.addChild(this.FrontLeftFoot);
        this.Head.addChild(this.LeftFin2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body1.render(f5);
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
