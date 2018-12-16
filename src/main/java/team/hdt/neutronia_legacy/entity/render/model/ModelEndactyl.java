package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Endactyl - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelEndactyl extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Mouth;
    public ModelRenderer LeftWing;
    public ModelRenderer RightWing;
    public ModelRenderer Tail;
    public ModelRenderer LeftSkin;
    public ModelRenderer RightSkin;
    public ModelRenderer TailSkin;

    public ModelEndactyl() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.Tail = new ModelRenderer(this, 0, 40);
        this.Tail.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Tail.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 14, 0.0F);
        this.LeftWing = new ModelRenderer(this, 0, 33);
        this.LeftWing.mirror = true;
        this.LeftWing.setRotationPoint(9.0F, 19.5F, -5.5F);
        this.LeftWing.addBox(0.0F, -0.5F, -3.0F, 11, 1, 6, 0.0F);
        this.RightWing = new ModelRenderer(this, 0, 33);
        this.RightWing.setRotationPoint(-9.0F, 19.5F, -5.5F);
        this.RightWing.addBox(-11.0F, -0.5F, -3.0F, 11, 1, 6, 0.0F);
        this.LeftSkin = new ModelRenderer(this, 0, 56);
        this.LeftSkin.mirror = true;
        this.LeftSkin.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.LeftSkin.addBox(-3.0F, -0.25F, 3.0F, 12, 1, 14, 0.0F);
        this.RightSkin = new ModelRenderer(this, 0, 56);
        this.RightSkin.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.RightSkin.addBox(-9.0F, -0.25F, 3.0F, 12, 1, 14, 0.0F);
        this.TailSkin = new ModelRenderer(this, 0, 71);
        this.TailSkin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TailSkin.addBox(-6.0F, -0.75F, 1.0F, 12, 1, 10, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Head.addBox(-9.0F, -1.0F, -11.0F, 18, 5, 12, 0.0F);
        this.Mouth = new ModelRenderer(this, 0, 17);
        this.Mouth.setRotationPoint(0.0F, 20.1F, 0.1F);
        this.Mouth.addBox(-8.5F, -1.0F, -11.0F, 17, 4, 12, 0.0F);
        this.LeftWing.addChild(this.LeftSkin);
        this.RightWing.addChild(this.RightSkin);
        this.Tail.addChild(this.TailSkin);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Tail.render(f5);
        this.LeftWing.render(f5);
        this.RightWing.render(f5);
        this.Head.render(f5);
        this.Mouth.render(f5);
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
