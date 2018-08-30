package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelSkytrap - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelSkytrap extends ModelBase {

    public ModelRenderer mouthBase;
    public ModelRenderer tongue01;
    public ModelRenderer petalN01;
    public ModelRenderer petalS01;
    public ModelRenderer petalE01;
    public ModelRenderer petalW01;
    public ModelRenderer petalN02;
    public ModelRenderer petalNFangs03;
    public ModelRenderer petalNFangs04;
    public ModelRenderer petalNFangs01;
    public ModelRenderer petalNFangs02;
    public ModelRenderer petalS02;
    public ModelRenderer petalSFangs03;
    public ModelRenderer petalSFangs04;
    public ModelRenderer petalSFangs01;
    public ModelRenderer petalSFangs02;
    public ModelRenderer petalE02;
    public ModelRenderer petalEFangs03;
    public ModelRenderer petalEFangs04;
    public ModelRenderer petalEFangs01;
    public ModelRenderer petalEFangs02;
    public ModelRenderer petalW02;
    public ModelRenderer petalWFangs03;
    public ModelRenderer petalWFangs04;
    public ModelRenderer petalWFangs01;
    public ModelRenderer petalWFangs02;
    public ModelRenderer tongue02;
    public ModelRenderer tongue03;

    public ModelSkytrap() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.petalWFangs02 = new ModelRenderer(this, 0, 50);
        this.petalWFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalWFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalE02 = new ModelRenderer(this, 0, 38);
        this.petalE02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalE02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalE02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalW02 = new ModelRenderer(this, 0, 38);
        this.petalW02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalW02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalW02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalW01 = new ModelRenderer(this, 0, 23);
        this.petalW01.setRotationPoint(6.5F, -2.5F, 0.0F);
        this.petalW01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalW01, -0.13962634015954636F, -1.5707963267948966F, 0.0F);
        this.petalNFangs01 = new ModelRenderer(this, 0, 50);
        this.petalNFangs01.mirror = true;
        this.petalNFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalNFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalE01 = new ModelRenderer(this, 0, 23);
        this.petalE01.setRotationPoint(-6.5F, -2.5F, 0.0F);
        this.petalE01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalE01, -0.13962634015954636F, 1.5707963267948966F, 0.0F);
        this.petalSFangs02 = new ModelRenderer(this, 0, 50);
        this.petalSFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalSFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.tongue02 = new ModelRenderer(this, 46, 46);
        this.tongue02.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tongue02.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.petalEFangs01 = new ModelRenderer(this, 0, 50);
        this.petalEFangs01.mirror = true;
        this.petalEFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalEFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalN02 = new ModelRenderer(this, 0, 38);
        this.petalN02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalN02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalN02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalNFangs04 = new ModelRenderer(this, 9, 41);
        this.petalNFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalNFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalWFangs04 = new ModelRenderer(this, 9, 41);
        this.petalWFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalWFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalNFangs03 = new ModelRenderer(this, 9, 41);
        this.petalNFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalNFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalWFangs03 = new ModelRenderer(this, 9, 41);
        this.petalWFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalWFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.tongue01 = new ModelRenderer(this, 44, 36);
        this.tongue01.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.tongue01.addBox(-1.5F, -6.0F, -1.5F, 3, 6, 3, 0.0F);
        this.petalWFangs01 = new ModelRenderer(this, 0, 50);
        this.petalWFangs01.mirror = true;
        this.petalWFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalWFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.tongue03 = new ModelRenderer(this, 39, 47);
        this.tongue03.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.tongue03.addBox(-0.5F, -10.0F, -0.5F, 1, 10, 1, 0.0F);
        this.petalSFangs03 = new ModelRenderer(this, 9, 41);
        this.petalSFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalSFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalNFangs02 = new ModelRenderer(this, 0, 50);
        this.petalNFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalNFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalSFangs04 = new ModelRenderer(this, 9, 41);
        this.petalSFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalSFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalEFangs02 = new ModelRenderer(this, 0, 50);
        this.petalEFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalEFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalS01 = new ModelRenderer(this, 0, 23);
        this.petalS01.setRotationPoint(0.0F, -2.5F, 6.5F);
        this.petalS01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalS01, -0.13962634015954636F, 3.141592653589793F, 0.0F);
        this.petalN01 = new ModelRenderer(this, 0, 23);
        this.petalN01.setRotationPoint(0.0F, -2.5F, -6.5F);
        this.petalN01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalN01, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalEFangs03 = new ModelRenderer(this, 9, 41);
        this.petalEFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalEFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalS02 = new ModelRenderer(this, 0, 38);
        this.petalS02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalS02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalS02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalSFangs01 = new ModelRenderer(this, 0, 50);
        this.petalSFangs01.mirror = true;
        this.petalSFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalSFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalEFangs04 = new ModelRenderer(this, 9, 41);
        this.petalEFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalEFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.mouthBase = new ModelRenderer(this, 0, 0);
        this.mouthBase.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.mouthBase.addBox(-7.0F, -4.0F, -7.0F, 14, 4, 14, 0.0F);
        this.petalW02.addChild(this.petalWFangs02);
        this.petalE01.addChild(this.petalE02);
        this.petalW01.addChild(this.petalW02);
        this.mouthBase.addChild(this.petalW01);
        this.petalN02.addChild(this.petalNFangs01);
        this.mouthBase.addChild(this.petalE01);
        this.petalS02.addChild(this.petalSFangs02);
        this.tongue01.addChild(this.tongue02);
        this.petalE02.addChild(this.petalEFangs01);
        this.petalN01.addChild(this.petalN02);
        this.petalN01.addChild(this.petalNFangs04);
        this.petalW01.addChild(this.petalWFangs04);
        this.petalN01.addChild(this.petalNFangs03);
        this.petalW01.addChild(this.petalWFangs03);
        this.petalW02.addChild(this.petalWFangs01);
        this.tongue02.addChild(this.tongue03);
        this.petalS01.addChild(this.petalSFangs03);
        this.petalN02.addChild(this.petalNFangs02);
        this.petalS01.addChild(this.petalSFangs04);
        this.petalE02.addChild(this.petalEFangs02);
        this.mouthBase.addChild(this.petalS01);
        this.mouthBase.addChild(this.petalN01);
        this.petalE01.addChild(this.petalEFangs03);
        this.petalS01.addChild(this.petalS02);
        this.petalS02.addChild(this.petalSFangs01);
        this.petalE01.addChild(this.petalEFangs04);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.tongue01.render(f5);
        this.mouthBase.render(f5);
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