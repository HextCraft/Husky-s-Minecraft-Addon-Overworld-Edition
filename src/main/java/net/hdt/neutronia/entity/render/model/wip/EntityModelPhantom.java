package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.Entity;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelPhantom extends EntityModel
{
    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer f;
    private final ModelRenderer g;
    private final ModelRenderer h;
    
    public EntityModelPhantom() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.a = new ModelRenderer(this, 0, 8)).a(-3.0f, -2.0f, -8.0f, 5, 3, 9);
        (this.g = new ModelRenderer(this, 3, 20)).a(-2.0f, 0.0f, 0.0f, 3, 2, 6);
        this.g.setRotationPoint(0.0f, -2.0f, 1.0f);
        this.a.addChild(this.g);
        (this.h = new ModelRenderer(this, 4, 29)).a(-1.0f, 0.0f, 0.0f, 1, 1, 6);
        this.h.setRotationPoint(0.0f, 0.5f, 6.0f);
        this.g.addChild(this.h);
        (this.b = new ModelRenderer(this, 23, 12)).a(0.0f, 0.0f, 0.0f, 6, 2, 9);
        this.b.setRotationPoint(2.0f, -2.0f, -8.0f);
        (this.c = new ModelRenderer(this, 16, 24)).a(0.0f, 0.0f, 0.0f, 13, 1, 9);
        this.c.setRotationPoint(6.0f, 0.0f, 0.0f);
        this.b.addChild(this.c);
        this.d = new ModelRenderer(this, 23, 12);
        this.d.mirror = true;
        this.d.a(-6.0f, 0.0f, 0.0f, 6, 2, 9);
        this.d.setRotationPoint(-3.0f, -2.0f, -8.0f);
        this.e = new ModelRenderer(this, 16, 24);
        this.e.mirror = true;
        this.e.a(-13.0f, 0.0f, 0.0f, 13, 1, 9);
        this.e.setRotationPoint(-6.0f, 0.0f, 0.0f);
        this.d.addChild(this.e);
        this.b.roll = 0.1f;
        this.c.roll = 0.1f;
        this.d.roll = -0.1f;
        this.e.roll = -0.1f;
        this.a.pitch = -0.1f;
        (this.f = new ModelRenderer(this, 0, 0)).a(-4.0f, -2.0f, -5.0f, 7, 3, 5);
        this.f.setRotationPoint(0.0f, 1.0f, -7.0f);
        this.f.pitch = 0.2f;
        this.a.addChild(this.f);
        this.a.addChild(this.b);
        this.a.addChild(this.d);
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.a.render(aFloat7);
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        final float vFloat8 = (aEntity7.getEntityId() * 3 + aFloat3) * 0.13f;
        final float vFloat9 = 16.0f;
        this.b.roll = (0.0f + MathUtils.cos(vFloat8) * 16.0f) * 0.017453292f;
        this.c.roll = (0.0f + MathUtils.cos(vFloat8) * 16.0f) * 0.017453292f;
        this.d.roll = -this.b.roll;
        this.e.roll = -this.c.roll;
        this.g.pitch = -(5.0f + MathUtils.cos(vFloat8 * 2.0f) * 5.0f) * 0.017453292f;
        this.h.pitch = -(5.0f + MathUtils.cos(vFloat8 * 2.0f) * 5.0f) * 0.017453292f;
    }
}
