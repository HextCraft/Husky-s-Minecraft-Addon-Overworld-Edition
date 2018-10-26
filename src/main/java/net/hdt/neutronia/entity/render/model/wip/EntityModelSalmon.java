package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.Entity;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelSalmon extends EntityModel
{
    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer f;
    private final ModelRenderer g;
    private final ModelRenderer h;
    
    public EntityModelSalmon() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        final int vInteger1 = 20;
        (this.a = new ModelRenderer(this, 0, 0)).a(-1.5f, -2.5f, 0.0f, 3, 5, 8);
        this.a.setRotationPoint(0.0f, 20.0f, 0.0f);
        (this.b = new ModelRenderer(this, 0, 13)).a(-1.5f, -2.5f, 0.0f, 3, 5, 8);
        this.b.setRotationPoint(0.0f, 20.0f, 8.0f);
        (this.c = new ModelRenderer(this, 22, 0)).a(-1.0f, -2.0f, -3.0f, 2, 4, 3);
        this.c.setRotationPoint(0.0f, 20.0f, 0.0f);
        (this.f = new ModelRenderer(this, 20, 10)).a(0.0f, -2.5f, 0.0f, 0, 5, 6);
        this.f.setRotationPoint(0.0f, 0.0f, 8.0f);
        this.b.addChild(this.f);
        (this.d = new ModelRenderer(this, 2, 1)).a(0.0f, 0.0f, 0.0f, 0, 2, 3);
        this.d.setRotationPoint(0.0f, -4.5f, 5.0f);
        this.a.addChild(this.d);
        (this.e = new ModelRenderer(this, 0, 2)).a(0.0f, 0.0f, 0.0f, 0, 2, 4);
        this.e.setRotationPoint(0.0f, -4.5f, -1.0f);
        this.b.addChild(this.e);
        (this.g = new ModelRenderer(this, -4, 0)).a(-2.0f, 0.0f, 0.0f, 2, 0, 2);
        this.g.setRotationPoint(-1.5f, 21.5f, 0.0f);
        this.g.roll = -0.7853982f;
        (this.h = new ModelRenderer(this, 0, 0)).a(0.0f, 0.0f, 0.0f, 2, 0, 2);
        this.h.setRotationPoint(1.5f, 21.5f, 0.0f);
        this.h.roll = 0.7853982f;
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        this.a.render(aFloat7);
        this.b.render(aFloat7);
        this.c.render(aFloat7);
        this.g.render(aFloat7);
        this.h.render(aFloat7);
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        float vFloat8 = 1.0f;
        float vFloat9 = 1.0f;
        if (!aEntity7.isSwimming()) {
            vFloat8 = 1.3f;
            vFloat9 = 1.7f;
        }
        this.b.yaw = -vFloat8 * 0.25f * MathUtils.sin(vFloat9 * 0.6f * aFloat3);
    }
}
