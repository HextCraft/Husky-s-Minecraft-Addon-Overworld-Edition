package team.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Axolotl - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelAxolotl extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Fins;
    public ModelRenderer Body;
    public ModelRenderer RightFrontFoot;
    public ModelRenderer LeftFrontFoot;
    public ModelRenderer RightBackFoot;
    public ModelRenderer LeftBackFoot;
    public ModelRenderer Tail1;
    public ModelRenderer TopHat1;
    public ModelRenderer Tail2;
    public ModelRenderer TopHat2;

    public ModelAxolotl() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 21.0F, -4.0F);
        this.Head.addBox(-2.5F, -2.0F, -4.0F, 5, 4, 4, 0.0F);
        this.Tail1 = new ModelRenderer(this, 16, 2);
        this.Tail1.setRotationPoint(0.0F, 20.5F, 2.0F);
        this.Tail1.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 6, 0.0F);
        this.setRotateAngle(Tail1, -0.17453292519943295F, 0.0F, 0.0F);
        this.TopHat1 = new ModelRenderer(this, 20, 23);
        this.TopHat1.setRotationPoint(-2.0F, 19.5F, -6.0F);
        this.TopHat1.addBox(-1.5F, -1.0F, -1.5F, 3, 1, 3, 0.0F);
        this.setRotateAngle(TopHat1, 0.0F, 0.0F, -0.4363323129985824F);
        this.TopHat2 = new ModelRenderer(this, 20, 27);
        this.TopHat2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TopHat2.addBox(-1.0F, -3.5F, -1.0F, 2, 3, 2, 0.0F);
        this.Tail2 = new ModelRenderer(this, 17, 2);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 2.5F);
        this.Tail2.addBox(0.0F, -1.5F, -3.5F, 0, 3, 7, 0.0F);
        this.Fins = new ModelRenderer(this, 0, 8);
        this.Fins.setRotationPoint(0.0F, 20.5F, -4.25F);
        this.Fins.addBox(-4.0F, -6.0F, 0.0F, 8, 8, 0, 0.0F);
        this.Body = new ModelRenderer(this, 0, 16);
        this.Body.setRotationPoint(0.0F, 21.0F, -4.0F);
        this.Body.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 6, 0.0F);
        this.LeftFrontFoot = new ModelRenderer(this, 14, 0);
        this.LeftFrontFoot.setRotationPoint(2.0F, 22.0F, -2.5F);
        this.LeftFrontFoot.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LeftFrontFoot, 0.0F, -0.4363323129985824F, -0.2617993877991494F);
        this.RightBackFoot = new ModelRenderer(this, 14, 0);
        this.RightBackFoot.setRotationPoint(-2.0F, 22.0F, 1.5F);
        this.RightBackFoot.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(RightBackFoot, 0.0F, 0.4363323129985824F, 0.0F);
        this.LeftBackFoot = new ModelRenderer(this, 14, 0);
        this.LeftBackFoot.setRotationPoint(2.0F, 22.0F, 1.5F);
        this.LeftBackFoot.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LeftBackFoot, 0.0F, -0.4363323129985824F, 0.0F);
        this.RightFrontFoot = new ModelRenderer(this, 14, 0);
        this.RightFrontFoot.setRotationPoint(-2.0F, 22.0F, -2.5F);
        this.RightFrontFoot.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(RightFrontFoot, 0.0F, 0.4363323129985824F, 0.2617993877991494F);
        this.TopHat1.addChild(this.TopHat2);
        this.Tail1.addChild(this.Tail2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Head.render(f5);
        this.Tail1.render(f5);
        this.Fins.render(f5);
        this.Body.render(f5);
        this.LeftFrontFoot.render(f5);
        this.RightBackFoot.render(f5);
        this.LeftBackFoot.render(f5);
        this.RightFrontFoot.render(f5);
        this.TopHat1.render(f5);
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
