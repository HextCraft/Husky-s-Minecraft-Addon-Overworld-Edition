package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.Entity;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelPufferfishState1 extends EntityModel
{
    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer f;
    private final ModelRenderer g;
    private final ModelRenderer h;
    private final ModelRenderer i;
    private final ModelRenderer j;
    private final ModelRenderer k;
    
    public EntityModelPufferfishState1() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        final int vInteger1 = 22;
        (this.a = new ModelRenderer(this, 12, 22)).a(-2.5f, -5.0f, -2.5f, 5, 5, 5);
        this.a.setRotationPoint(0.0f, 22.0f, 0.0f);
        (this.b = new ModelRenderer(this, 24, 0)).a(-2.0f, 0.0f, 0.0f, 2, 0, 2);
        this.b.setRotationPoint(-2.5f, 17.0f, -1.5f);
        (this.c = new ModelRenderer(this, 24, 3)).a(0.0f, 0.0f, 0.0f, 2, 0, 2);
        this.c.setRotationPoint(2.5f, 17.0f, -1.5f);
        (this.d = new ModelRenderer(this, 15, 16)).a(-2.5f, -1.0f, 0.0f, 5, 1, 1);
        this.d.setRotationPoint(0.0f, 17.0f, -2.5f);
        this.d.pitch = 0.7853982f;
        (this.e = new ModelRenderer(this, 10, 16)).a(-2.5f, -1.0f, -1.0f, 5, 1, 1);
        this.e.setRotationPoint(0.0f, 17.0f, 2.5f);
        this.e.pitch = -0.7853982f;
        (this.f = new ModelRenderer(this, 8, 16)).a(-1.0f, -5.0f, 0.0f, 1, 5, 1);
        this.f.setRotationPoint(-2.5f, 22.0f, -2.5f);
        this.f.yaw = -0.7853982f;
        (this.g = new ModelRenderer(this, 8, 16)).a(-1.0f, -5.0f, 0.0f, 1, 5, 1);
        this.g.setRotationPoint(-2.5f, 22.0f, 2.5f);
        this.g.yaw = 0.7853982f;
        (this.h = new ModelRenderer(this, 4, 16)).a(0.0f, -5.0f, 0.0f, 1, 5, 1);
        this.h.setRotationPoint(2.5f, 22.0f, 2.5f);
        this.h.yaw = -0.7853982f;
        (this.i = new ModelRenderer(this, 0, 16)).a(0.0f, -5.0f, 0.0f, 1, 5, 1);
        this.i.setRotationPoint(2.5f, 22.0f, -2.5f);
        this.i.yaw = 0.7853982f;
        (this.j = new ModelRenderer(this, 8, 22)).a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.j.setRotationPoint(0.5f, 22.0f, 2.5f);
        this.j.pitch = 0.7853982f;
        (this.k = new ModelRenderer(this, 17, 21)).a(-2.5f, 0.0f, 0.0f, 5, 1, 1);
        this.k.setRotationPoint(0.0f, 22.0f, -2.5f);
        this.k.pitch = -0.7853982f;
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        this.a.render(aFloat7);
        this.b.render(aFloat7);
        this.c.render(aFloat7);
        this.d.render(aFloat7);
        this.e.render(aFloat7);
        this.f.render(aFloat7);
        this.g.render(aFloat7);
        this.h.render(aFloat7);
        this.i.render(aFloat7);
        this.j.render(aFloat7);
        this.k.render(aFloat7);
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        this.b.roll = -0.2f + 0.4f * MathUtils.sin(aFloat3 * 0.2f);
        this.c.roll = 0.2f - 0.4f * MathUtils.sin(aFloat3 * 0.2f);
    }
}
