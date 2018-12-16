package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SeaTurtle - TheGamingHuskyMC
 * Created using Tabula 7.0.0
 */
public class ModelSeaTurtle extends ModelBase {

    public ModelRenderer head;
    public ModelRenderer shellRing;
    public ModelRenderer frontRightFin;
    public ModelRenderer shell;
    public ModelRenderer backLeftFin;
    public ModelRenderer frontLeftFin;
    public ModelRenderer body;
    public ModelRenderer backRightFin;

    public ModelSeaTurtle() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.frontLeftFin = new ModelRenderer(this, 18, 22);
        this.frontLeftFin.setRotationPoint(-4.0F, 0.0F, 4.0F);
        this.frontLeftFin.addBox(-1.0F, -1.5F, -1.5F, 1, 6, 3, 0.0F);
        this.backRightFin = new ModelRenderer(this, 18, 22);
        this.backRightFin.setRotationPoint(4.0F, 0.0F, -4.0F);
        this.backRightFin.addBox(0.0F, -1.5F, -1.5F, 1, 6, 3, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, -1.5F, -6.0F, 8, 3, 12, 0.0F);
        this.backLeftFin = new ModelRenderer(this, 18, 22);
        this.backLeftFin.setRotationPoint(-4.0F, 0.0F, -4.0F);
        this.backLeftFin.addBox(-1.0F, -1.5F, -1.5F, 1, 6, 3, 0.0F);
        this.shellRing = new ModelRenderer(this, 0, 48);
        this.shellRing.mirror = true;
        this.shellRing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shellRing.addBox(-5.0F, -2.5F, -7.0F, 10, 2, 14, 0.0F);
        this.head = new ModelRenderer(this, 0, 23);
        this.head.mirror = true;
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.0F, -2.5F, 6.0F, 4, 3, 5, 0.0F);
        this.shell = new ModelRenderer(this, 0, 31);
        this.shell.mirror = true;
        this.shell.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shell.addBox(-4.5F, -4.0F, -6.5F, 9, 2, 13, 0.0F);
        this.frontRightFin = new ModelRenderer(this, 18, 22);
        this.frontRightFin.setRotationPoint(4.0F, 0.0F, 4.0F);
        this.frontRightFin.addBox(0.0F, -1.5F, -1.5F, 1, 6, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.frontLeftFin.render(f5);
        this.shellRing.render(f5);
        this.shell.render(f5);
        this.backRightFin.render(f5);
        this.frontRightFin.render(f5);
        this.body.render(f5);
        this.backLeftFin.render(f5);
        this.head.render(f5);
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
