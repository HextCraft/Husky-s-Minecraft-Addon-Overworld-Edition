/*
package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelIllagerBeast extends ModelBase {

    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer f;
    private final ModelRenderer g;
    private final ModelRenderer h;

    public ModelIllagerBeast() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        (this.h = new ModelRenderer(this)).setRotationPoint(0.0f, -7.0f, -1.5f);
        this.h.setTextureOffset(68, 73).addBox(-5.0f, -1.0f, -18.0f, 10, 10, 18, 0.0f);
        (this.a = new ModelRenderer(this)).setRotationPoint(0.0f, 16.0f, -17.0f);
        this.a.setTextureOffset(0, 0).addBox(-8.0f, -20.0f, -14.0f, 16, 20, 16, 0.0f);
        this.a.setTextureOffset(0, 0).addBox(-2.0f, -6.0f, -18.0f, 4, 8, 4, 0.0f);
        final ModelRenderer vModelRenderer3 = new ModelRenderer(this);
        vModelRenderer3.setRotationPoint(-10.0f, -14.0f, -8.0f);
        vModelRenderer3.setTextureOffset(74, 55).addBox(0.0f, -14.0f, -2.0f, 2, 14, 4, 0.0f);
        vModelRenderer3.pitch = 1.0995574f;
        this.a.addChild(vModelRenderer3);
        final ModelRenderer vModelRenderer4 = new ModelRenderer(this);
        vModelRenderer4.mirror = true;
        vModelRenderer4.setRotationPoint(8.0f, -14.0f, -8.0f);
        vModelRenderer4.setTextureOffset(74, 55).addBox(0.0f, -14.0f, -2.0f, 2, 14, 4, 0.0f);
        vModelRenderer4.pitch = 1.0995574f;
        this.a.addChild(vModelRenderer4);
        (this.b = new ModelRenderer(this)).setRotationPoint(0.0f, -2.0f, 2.0f);
        this.b.setTextureOffset(0, 36).addBox(-8.0f, 0.0f, -16.0f, 16, 3, 16, 0.0f);
        this.a.addChild(this.b);
        this.h.addChild(this.a);
        this.c = new ModelRenderer(this);
        this.c.setTextureOffset(0, 55).addBox(-7.0f, -10.0f, -7.0f, 14, 16, 20, 0.0f);
        this.c.setTextureOffset(0, 91).addBox(-6.0f, 6.0f, -7.0f, 12, 13, 18, 0.0f);
        this.c.setRotationPoint(0.0f, 1.0f, 2.0f);
        (this.d = new ModelRenderer(this, 96, 0)).addBox(-4.0f, 0.0f, -4.0f, 8, 37, 8, 0.0f);
        this.d.setRotationPoint(-8.0f, -13.0f, 18.0f);
        this.e = new ModelRenderer(this, 96, 0);
        this.e.mirror = true;
        this.e.addBox(-4.0f, 0.0f, -4.0f, 8, 37, 8, 0.0f);
        this.e.setRotationPoint(8.0f, -13.0f, 18.0f);
        (this.f = new ModelRenderer(this, 64, 0)).addBox(-4.0f, 0.0f, -4.0f, 8, 37, 8, 0.0f);
        this.f.setRotationPoint(-8.0f, -13.0f, -5.0f);
        this.g = new ModelRenderer(this, 64, 0);
        this.g.mirror = true;
        this.g.addBox(-4.0f, 0.0f, -4.0f, 8, 37, 8, 0.0f);
        this.g.setRotationPoint(8.0f, -13.0f, -5.0f);
    }

    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        this.h.render(aFloat7);
        this.c.render(aFloat7);
        this.d.render(aFloat7);
        this.e.render(aFloat7);
        this.f.render(aFloat7);
        this.g.render(aFloat7);
    }

    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        this.a.pitch = aFloat5 * 0.017453292f;
        this.a.yaw = aFloat4 * 0.017453292f;
        this.c.pitch = 1.5707964f;
        final float vFloat8 = 0.4f * aFloat2;
        this.d.pitch = MathUtils.cos(aFloat1 * 0.6662f) * vFloat8;
        this.e.pitch = MathUtils.cos(aFloat1 * 0.6662f + 3.1415927f) * vFloat8;
        this.f.pitch = MathUtils.cos(aFloat1 * 0.6662f + 3.1415927f) * vFloat8;
        this.g.pitch = MathUtils.cos(aFloat1 * 0.6662f) * vFloat8;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(aEntityLiving1, aFloat2, aFloat3, aFloat4);
        final EntityIllagerBeast vEntityIllagerBeast5 = (EntityIllagerBeast)aEntityLiving1;
        final int vInteger6 = vEntityIllagerBeast5.dA();
        final int vInteger7 = vEntityIllagerBeast5.dB();
        final int vInteger8 = 20;
        final int vInteger9 = vEntityIllagerBeast5.l();
        final int vInteger10 = 10;
        if (vInteger9 > 0) {
            final float vFloat11 = this.a(vInteger9 - aFloat4, 10.0f);
            final float vFloat12 = (1.0f + vFloat11) * 0.5f;
            final float vFloat13 = vFloat12 * vFloat12 * vFloat12 * 12.0f;
            final float vFloat14 = vFloat13 * MathUtils.sin(this.h.pitch);
            this.h.rotationPointZ = -6.5f + vFloat13;
            this.h.rotationPointY = -7.0f - vFloat14;
            final float vFloat15 = MathUtils.sin((vInteger9 - aFloat4) / 10.0f * 3.1415927f * 0.25f);
            this.b.pitch = 1.5707964f * vFloat15;
            if (vInteger9 > 5) {
                this.b.pitch = MathUtils.sin((-4 + vInteger9 - aFloat4) / 4.0f) * 3.1415927f * 0.4f;
            }
            else {
                this.b.pitch = 0.15707964f * MathUtils.sin(3.1415927f * (vInteger9 - aFloat4) / 10.0f);
            }
        }
        else {
            final float vFloat11 = -1.0f;
            final float vFloat12 = -1.0f * MathUtils.sin(this.h.pitch);
            this.h.rotationPointX = 0.0f;
            this.h.rotationPointY = -7.0f - vFloat12;
            this.h.rotationPointZ = 5.5f;
            final boolean vBoolean13 = vInteger6 > 0;
            this.h.pitch = (vBoolean13 ? 0.21991149f : 0.0f);
            this.b.pitch = 3.1415927f * (vBoolean13 ? 0.05f : 0.01f);
            if (vBoolean13) {
                final double vDouble14 = vInteger6 / 40.0;
                this.h.rotationPointX = (float)Math.sin(vDouble14 * 10.0) * 3.0f;
            }
            else if (vInteger7 > 0) {
                final float vFloat14 = MathUtils.sin((20 - vInteger7 - aFloat4) / 20.0f * 3.1415927f * 0.25f);
                this.b.pitch = 1.5707964f * vFloat14;
            }
        }
    }

    private float a(final float aFloat1, final float aFloat2) {
        return (Math.abs(aFloat1 % aFloat2 - aFloat2 * 0.5f) - aFloat2 * 0.25f) / (aFloat2 * 0.25f);
    }

}
*/
