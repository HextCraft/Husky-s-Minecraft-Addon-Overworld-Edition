package team.hdt.neutronia_legacy.entity.render.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class EntityModelPanda extends ModelQuadruped {

    private float i, j, k;

    public EntityModelPanda(final int aInteger1, final float aFloat2) {
        super(aInteger1, aFloat2);
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.head = new ModelRenderer(this, 0, 6)).addBox(-6.5f, -5.0f, -4.0f, 13, 10, 9);
        this.head.setRotationPoint(0.0f, 11.5f, -17.0f);
        this.head.setTextureOffset(45, 16).addBox(-3.5f, 0.0f, -6.0f, 7, 5, 2);
        this.head.setTextureOffset(52, 25).addBox(-8.5f, -8.0f, -1.0f, 5, 4, 1);
        this.head.setTextureOffset(52, 25).addBox(3.5f, -8.0f, -1.0f, 5, 4, 1);
        (this.body = new ModelRenderer(this, 0, 25)).addBox(-9.5f, -13.0f, -6.5f, 19, 26, 13);
        this.body.setRotationPoint(0.0f, 10.0f, 0.0f);
        (this.leg1 = new ModelRenderer(this, 40, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
        this.leg1.setRotationPoint(-5.5f, 15.0f, 9.0f);
        (this.leg2 = new ModelRenderer(this, 40, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
        this.leg2.setRotationPoint(5.5f, 15.0f, 9.0f);
        (this.leg3 = new ModelRenderer(this, 40, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
        this.leg3.setRotationPoint(-5.5f, 15.0f, -9.0f);
        (this.leg4 = new ModelRenderer(this, 40, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
        this.leg4.setRotationPoint(5.5f, 15.0f, -9.0f);
    }
    
    /*@Override
    public void setLivingAnimations(final EntityLivingBase aEntityLiving1, final float aFloat2, final float aFloat3, final float aFloat4) {
        super.setLivingAnimations(aEntityLiving1, aFloat2, aFloat3, aFloat4);
        final EntityPanda vEntityPanda5 = (EntityPanda)aEntityLiving1;
        this.i = vEntityPanda5.v(aFloat4);
        this.j = vEntityPanda5.w(aFloat4);
        this.k = (vEntityPanda5.isChild() ? 0.0f : vEntityPanda5.x(aFloat4));
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        super.setRotationAngles(aFloat1, aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aEntity7);
        final EntityPanda vEntityPanda8 = (EntityPanda)aEntity7;
        final boolean vBoolean9 = vEntityPanda8.dz() > 0;
        final boolean vBoolean10 = vEntityPanda8.dA();
        final int vInteger11 = vEntityPanda8.dI();
        final boolean vBoolean11 = vEntityPanda8.dD();
        final boolean vBoolean12 = vEntityPanda8.dT();
        if (vBoolean9) {
            this.a.yaw = 0.35f * MathUtils.sin(0.6f * aFloat3);
            this.a.roll = 0.35f * MathUtils.sin(0.6f * aFloat3);
            this.e.pitch = -0.75f * MathUtils.sin(0.3f * aFloat3);
            this.f.pitch = 0.75f * MathUtils.sin(0.3f * aFloat3);
        }
        else {
            this.a.roll = 0.0f;
        }
        if (vBoolean10) {
            if (vInteger11 < 15) {
                this.head.pitch = -0.7853982f * vInteger11 / 14.0f;
            }
            else if (vInteger11 < 20) {
                final float vFloat14 = (vInteger11 - 15) / 5;
                this.head.pitch = -0.7853982f + 0.7853982f * vFloat14;
            }
        }
        if (this.i > 0.0f) {
            this.body.pitch = this.a(this.b.pitch, 1.7407963f, this.i);
            this.head.pitch = this.a(this.a.pitch, 1.5707964f, this.i);
            this.leg3.roll = -0.27079642f;
            this.leg4.roll = 0.27079642f;
            this.leg1.roll = 0.5707964f;
            this.leg2.roll = -0.5707964f;
            if (vBoolean11) {
                this.head.pitch = 1.5707964f + 0.2f * MathUtils.sin(aFloat3 * 0.6f);
                this.leg3.pitch = -0.4f - 0.2f * MathUtils.sin(aFloat3 * 0.6f);
                this.leg4.pitch = -0.4f - 0.2f * MathUtils.sin(aFloat3 * 0.6f);
            }
            if (vBoolean12) {
                this.head.pitch = 2.1707964f;
                this.leg3.pitch = -0.9f;
                this.leg4.pitch = -0.9f;
            }
        }
        else {
            this.c.roll = 0.0f;
            this.d.roll = 0.0f;
            this.e.roll = 0.0f;
            this.f.roll = 0.0f;
        }
        if (this.j > 0.0f) {
            this.c.pitch = -0.6f * MathUtils.sin(aFloat3 * 0.15f);
            this.d.pitch = 0.6f * MathUtils.sin(aFloat3 * 0.15f);
            this.e.pitch = 0.3f * MathUtils.sin(aFloat3 * 0.25f);
            this.f.pitch = -0.3f * MathUtils.sin(aFloat3 * 0.25f);
            this.a.pitch = this.a(this.a.pitch, 1.5707964f, this.j);
        }
        if (this.k > 0.0f) {
            this.a.pitch = this.a(this.a.pitch, 2.0561945f, this.k);
            this.c.pitch = -0.5f * MathUtils.sin(aFloat3 * 0.5f);
            this.d.pitch = 0.5f * MathUtils.sin(aFloat3 * 0.5f);
            this.e.pitch = 0.5f * MathUtils.sin(aFloat3 * 0.5f);
            this.f.pitch = -0.5f * MathUtils.sin(aFloat3 * 0.5f);
        }
    }*/

    protected float a(final float aFloat1, final float aFloat2, final float aFloat3) {
        float vFloat4;
        for (vFloat4 = aFloat2 - aFloat1; vFloat4 < -3.1415927f; vFloat4 += 6.2831855f) {
        }
        while (vFloat4 >= 3.1415927f) {
            vFloat4 -= 6.2831855f;
        }
        return aFloat1 + aFloat3 * vFloat4;
    }

    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        if (this.isChild) {
            GlStateManager.pushMatrix();
//            GlStateManager.translate(0.0f, this.g * aFloat7, this.h * aFloat7);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5555555f, 0.5555555f, 0.5555555f);
            GlStateManager.translate(0.0f, 23.0f * aFloat7, 0.3f);
            this.head.render(aFloat7);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.33333334f, 0.33333334f, 0.33333334f);
            GlStateManager.translate(0.0f, 49.0f * aFloat7, 0.0f);
            this.body.render(aFloat7);
            this.leg1.render(aFloat7);
            this.leg2.render(aFloat7);
            this.leg3.render(aFloat7);
            this.leg4.render(aFloat7);
            GlStateManager.popMatrix();
        } else {
            this.head.render(aFloat7);
            this.body.render(aFloat7);
            this.leg1.render(aFloat7);
            this.leg2.render(aFloat7);
            this.leg3.render(aFloat7);
            this.leg4.render(aFloat7);
        }
    }
}
