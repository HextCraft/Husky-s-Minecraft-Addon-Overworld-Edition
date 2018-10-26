package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.Entity;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelPufferfishState0 extends EntityModel
{
    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer f;
    
    public EntityModelPufferfishState0() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        final int vInteger1 = 23;
        (this.a = new ModelRenderer(this, 0, 27)).a(-1.5f, -2.0f, -1.5f, 3, 2, 3);
        this.a.setRotationPoint(0.0f, 23.0f, 0.0f);
        (this.b = new ModelRenderer(this, 24, 6)).a(-1.5f, 0.0f, -1.5f, 1, 1, 1);
        this.b.setRotationPoint(0.0f, 20.0f, 0.0f);
        (this.c = new ModelRenderer(this, 28, 6)).a(0.5f, 0.0f, -1.5f, 1, 1, 1);
        this.c.setRotationPoint(0.0f, 20.0f, 0.0f);
        (this.f = new ModelRenderer(this, -3, 0)).a(-1.5f, 0.0f, 0.0f, 3, 0, 3);
        this.f.setRotationPoint(0.0f, 22.0f, 1.5f);
        (this.d = new ModelRenderer(this, 25, 0)).a(-1.0f, 0.0f, 0.0f, 1, 0, 2);
        this.d.setRotationPoint(-1.5f, 22.0f, -1.5f);
        (this.e = new ModelRenderer(this, 25, 0)).a(0.0f, 0.0f, 0.0f, 1, 0, 2);
        this.e.setRotationPoint(1.5f, 22.0f, -1.5f);
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        this.a.render(aFloat7);
        this.b.render(aFloat7);
        this.c.render(aFloat7);
        this.f.render(aFloat7);
        this.d.render(aFloat7);
        this.e.render(aFloat7);
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        this.d.roll = -0.2f + 0.4f * MathUtils.sin(aFloat3 * 0.2f);
        this.e.roll = 0.2f - 0.4f * MathUtils.sin(aFloat3 * 0.2f);
    }
}
