package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.math.MathUtils;
import net.minecraft.client.gl.GlHandler;
import net.minecraft.entity.passive.EntityTurtle;
import net.minecraft.entity.Entity;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelTurtle extends EntityModelQuadruped
{
    private final ModelRenderer i;
    
    public EntityModelTurtle(final float aFloat) {
        super(12, aFloat);
        this.textureWidth = 128;
        this.textureHeight = 64;
        (this.a = new ModelRenderer(this, 3, 0)).addBox(-3.0f, -1.0f, -3.0f, 6, 5, 6, 0.0f);
        this.a.setRotationPoint(0.0f, 19.0f, -10.0f);
        this.b = new ModelRenderer(this);
        this.b.a(7, 37).addBox(-9.5f, 3.0f, -10.0f, 19, 20, 6, 0.0f);
        this.b.a(31, 1).addBox(-5.5f, 3.0f, -13.0f, 11, 18, 3, 0.0f);
        this.b.setRotationPoint(0.0f, 11.0f, -10.0f);
        this.i = new ModelRenderer(this);
        this.i.a(70, 33).addBox(-4.5f, 3.0f, -14.0f, 9, 18, 1, 0.0f);
        this.i.setRotationPoint(0.0f, 11.0f, -10.0f);
        final int vInteger2 = 1;
        (this.c = new ModelRenderer(this, 1, 23)).addBox(-2.0f, 0.0f, 0.0f, 4, 1, 10, 0.0f);
        this.c.setRotationPoint(-3.5f, 22.0f, 11.0f);
        (this.d = new ModelRenderer(this, 1, 12)).addBox(-2.0f, 0.0f, 0.0f, 4, 1, 10, 0.0f);
        this.d.setRotationPoint(3.5f, 22.0f, 11.0f);
        (this.e = new ModelRenderer(this, 27, 30)).addBox(-13.0f, 0.0f, -2.0f, 13, 1, 5, 0.0f);
        this.e.setRotationPoint(-5.0f, 21.0f, -4.0f);
        (this.f = new ModelRenderer(this, 27, 24)).addBox(0.0f, 0.0f, -2.0f, 13, 1, 5, 0.0f);
        this.f.setRotationPoint(5.0f, 21.0f, -4.0f);
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        final EntityTurtle vEntityTurtle8 = (EntityTurtle)aEntity1;
        if (this.isChild) {
            final float vFloat9 = 6.0f;
            GlHandler.pushMatrix();
            GlHandler.scale(0.16666667f, 0.16666667f, 0.16666667f);
            GlHandler.translate(0.0f, 120.0f * aFloat7, 0.0f);
            this.a.render(aFloat7);
            this.b.render(aFloat7);
            this.c.render(aFloat7);
            this.d.render(aFloat7);
            this.e.render(aFloat7);
            this.f.render(aFloat7);
            GlHandler.popMatrix();
        }
        else {
            GlHandler.pushMatrix();
            if (vEntityTurtle8.dz()) {
                GlHandler.translate(0.0f, -0.08f, 0.0f);
            }
            this.a.render(aFloat7);
            this.b.render(aFloat7);
            GlHandler.pushMatrix();
            this.c.render(aFloat7);
            this.d.render(aFloat7);
            GlHandler.popMatrix();
            this.e.render(aFloat7);
            this.f.render(aFloat7);
            if (vEntityTurtle8.dz()) {
                this.i.render(aFloat7);
            }
            GlHandler.popMatrix();
        }
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        super.setRotationAngles(aFloat1, aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aEntity7);
        final EntityTurtle vEntityTurtle8 = (EntityTurtle)aEntity7;
        this.c.pitch = MathUtils.cos(aFloat1 * 0.6662f * 0.6f) * 0.5f * aFloat2;
        this.d.pitch = MathUtils.cos(aFloat1 * 0.6662f * 0.6f + 3.1415927f) * 0.5f * aFloat2;
        this.e.roll = MathUtils.cos(aFloat1 * 0.6662f * 0.6f + 3.1415927f) * 0.5f * aFloat2;
        this.f.roll = MathUtils.cos(aFloat1 * 0.6662f * 0.6f) * 0.5f * aFloat2;
        this.e.pitch = 0.0f;
        this.f.pitch = 0.0f;
        this.e.yaw = 0.0f;
        this.f.yaw = 0.0f;
        this.c.yaw = 0.0f;
        this.d.yaw = 0.0f;
        this.i.pitch = 1.5707964f;
        if (!vEntityTurtle8.isSwimming() && vEntityTurtle8.onGround) {
            final float vFloat9 = vEntityTurtle8.dA() ? 4.0f : 1.0f;
            final float vFloat10 = vEntityTurtle8.dA() ? 2.0f : 1.0f;
            final float vFloat11 = 5.0f;
            this.e.yaw = MathUtils.cos(vFloat9 * aFloat1 * 5.0f + 3.1415927f) * 8.0f * aFloat2 * vFloat10;
            this.e.roll = 0.0f;
            this.f.yaw = MathUtils.cos(vFloat9 * aFloat1 * 5.0f) * 8.0f * aFloat2 * vFloat10;
            this.f.roll = 0.0f;
            this.c.yaw = MathUtils.cos(aFloat1 * 5.0f + 3.1415927f) * 3.0f * aFloat2;
            this.c.pitch = 0.0f;
            this.d.yaw = MathUtils.cos(aFloat1 * 5.0f) * 3.0f * aFloat2;
            this.d.pitch = 0.0f;
        }
    }
}
