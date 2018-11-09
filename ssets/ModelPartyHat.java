package sssss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPartyHat - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelPartyHat extends ModelBase {
    public ModelRenderer PartyHat1;
    public ModelRenderer PartyHat2;
    public ModelRenderer PartyHat3;
    public ModelRenderer Poof1;
    public ModelRenderer Poof2;
    public ModelRenderer Poof3;
    public ModelRenderer Poof4;
    public ModelRenderer Poof5;
    public ModelRenderer Poof6;
    public ModelRenderer Poof7;
    public ModelRenderer Poof8;

    public ModelPartyHat() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Poof4 = new ModelRenderer(this, 0, 19);
        this.Poof4.setRotationPoint(-1.0F, -6.5F, 0.0F);
        this.Poof4.addBox(-1.5F, -2.0F, 0.0F, 3, 3, 0, 0.0F);
        this.setRotateAngle(Poof4, 0.0F, -1.5707963267948966F, -0.17453292519943295F);
        this.Poof5 = new ModelRenderer(this, -3, 19);
        this.Poof5.setRotationPoint(0.0F, -5.75F, -1.0F);
        this.Poof5.addBox(-1.5F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
        this.setRotateAngle(Poof5, 0.7853981633974483F, -0.7853981633974483F, -0.17453292519943295F);
        this.Poof7 = new ModelRenderer(this, -3, 19);
        this.Poof7.setRotationPoint(-1.5F, -5.75F, 1.0F);
        this.Poof7.addBox(-1.5F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
        this.setRotateAngle(Poof7, 0.7853981633974483F, 2.356194490192345F, -0.17453292519943295F);
        this.PartyHat2 = new ModelRenderer(this, 0, 8);
        this.PartyHat2.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.PartyHat2.addBox(-2.0F, -5.0F, -2.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(PartyHat2, 0.0F, 0.0F, -0.08726646259971647F);
        this.Poof8 = new ModelRenderer(this, -3, 19);
        this.Poof8.setRotationPoint(-1.5F, -5.75F, -1.0F);
        this.Poof8.addBox(-1.5F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
        this.setRotateAngle(Poof8, 0.7853981633974483F, 0.7853981633974483F, -0.17453292519943295F);
        this.PartyHat1 = new ModelRenderer(this, 0, 0);
        this.PartyHat1.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.PartyHat1.addBox(-3.0F, -2.0F, -3.0F, 6, 2, 6, 0.0F);
        this.PartyHat3 = new ModelRenderer(this, 0, 15);
        this.PartyHat3.setRotationPoint(0.0F, 0.6F, 0.0F);
        this.PartyHat3.addBox(-1.0F, -7.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(PartyHat3, 0.0F, 0.0F, -0.1308996938995747F);
        this.Poof6 = new ModelRenderer(this, -3, 19);
        this.Poof6.setRotationPoint(0.0F, -5.75F, 1.0F);
        this.Poof6.addBox(-1.5F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
        this.setRotateAngle(Poof6, 0.7853981633974483F, -2.356194490192345F, -0.17453292519943295F);
        this.Poof2 = new ModelRenderer(this, 0, 19);
        this.Poof2.setRotationPoint(-1.0F, -6.5F, 0.0F);
        this.Poof2.addBox(-1.5F, -2.0F, 0.0F, 3, 3, 0, 0.0F);
        this.setRotateAngle(Poof2, 0.0F, 0.0F, -0.17453292519943295F);
        this.Poof3 = new ModelRenderer(this, 0, 19);
        this.Poof3.setRotationPoint(-1.0F, -6.5F, 0.0F);
        this.Poof3.addBox(-1.5F, -2.0F, 0.0F, 3, 3, 0, 0.0F);
        this.setRotateAngle(Poof3, 0.0F, -0.7853981633974483F, -0.17453292519943295F);
        this.Poof1 = new ModelRenderer(this, 0, 19);
        this.Poof1.setRotationPoint(-1.0F, -6.5F, 0.0F);
        this.Poof1.addBox(-1.5F, -2.0F, 0.0F, 3, 3, 0, 0.0F);
        this.setRotateAngle(Poof1, 0.0F, 0.7853981633974483F, -0.17453292519943295F);
        this.PartyHat1.addChild(this.Poof4);
        this.PartyHat1.addChild(this.Poof5);
        this.PartyHat1.addChild(this.Poof7);
        this.PartyHat1.addChild(this.PartyHat2);
        this.PartyHat1.addChild(this.Poof8);
        this.PartyHat1.addChild(this.PartyHat3);
        this.PartyHat1.addChild(this.Poof6);
        this.PartyHat1.addChild(this.Poof2);
        this.PartyHat1.addChild(this.Poof3);
        this.PartyHat1.addChild(this.Poof1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.PartyHat1.render(f5);
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
