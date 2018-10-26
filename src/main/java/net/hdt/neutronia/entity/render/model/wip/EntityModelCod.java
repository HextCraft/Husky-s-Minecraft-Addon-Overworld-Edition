package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathUtils;

public class EntityModelCod extends ModelBase
{
    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer f;
    private final ModelRenderer g;
    
    public EntityModelCod() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        final int vInteger1 = 22;
        (this.a = new ModelRenderer(this, 0, 0)).a(-1.0f, -2.0f, 0.0f, 2, 4, 7);
        this.a.setRotationPoint(0.0f, 22.0f, 0.0f);
        (this.c = new ModelRenderer(this, 11, 0)).a(-1.0f, -2.0f, -3.0f, 2, 4, 3);
        this.c.setRotationPoint(0.0f, 22.0f, 0.0f);
        (this.d = new ModelRenderer(this, 0, 0)).a(-1.0f, -2.0f, -1.0f, 2, 3, 1);
        this.d.setRotationPoint(0.0f, 22.0f, -3.0f);
        (this.e = new ModelRenderer(this, 22, 1)).a(-2.0f, 0.0f, -1.0f, 2, 0, 2);
        this.e.setRotationPoint(-1.0f, 23.0f, 0.0f);
        this.e.roll = -0.7853982f;
        (this.f = new ModelRenderer(this, 22, 4)).a(0.0f, 0.0f, -1.0f, 2, 0, 2);
        this.f.setRotationPoint(1.0f, 23.0f, 0.0f);
        this.f.roll = 0.7853982f;
        (this.g = new ModelRenderer(this, 22, 3)).a(0.0f, -2.0f, 0.0f, 0, 4, 4);
        this.g.setRotationPoint(0.0f, 22.0f, 7.0f);
        (this.b = new ModelRenderer(this, 20, -6)).a(0.0f, -1.0f, -1.0f, 0, 1, 6);
        this.b.setRotationPoint(0.0f, 20.0f, 0.0f);
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        this.a.render(aFloat7);
        this.c.render(aFloat7);
        this.d.render(aFloat7);
        this.e.render(aFloat7);
        this.f.render(aFloat7);
        this.g.render(aFloat7);
        this.b.render(aFloat7);
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        float vFloat8 = 1.0f;
        if (!aEntity7.isSwimming()) {
            vFloat8 = 1.5f;
        }
        this.g.yaw = -vFloat8 * 0.45f * MathUtils.sin(0.6f * aFloat3);
    }
}
