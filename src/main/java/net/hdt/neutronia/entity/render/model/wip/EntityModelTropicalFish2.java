package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.Entity;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelTropicalFish2 extends EntityModel
{
    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    private final ModelRenderer e;
    private final ModelRenderer f;
    
    public EntityModelTropicalFish2() {
        this(0.0f);
    }
    
    public EntityModelTropicalFish2(final float aFloat) {
        this.textureWidth = 32;
        this.textureHeight = 32;
        final int vInteger2 = 19;
        (this.a = new ModelRenderer(this, 0, 20)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6, aFloat);
        this.a.setRotationPoint(0.0f, 19.0f, 0.0f);
        (this.b = new ModelRenderer(this, 21, 16)).addBox(0.0f, -3.0f, 0.0f, 0, 6, 5, aFloat);
        this.b.setRotationPoint(0.0f, 19.0f, 3.0f);
        (this.c = new ModelRenderer(this, 2, 16)).addBox(-2.0f, 0.0f, 0.0f, 2, 2, 0, aFloat);
        this.c.setRotationPoint(-1.0f, 20.0f, 0.0f);
        this.c.yaw = 0.7853982f;
        (this.d = new ModelRenderer(this, 2, 12)).addBox(0.0f, 0.0f, 0.0f, 2, 2, 0, aFloat);
        this.d.setRotationPoint(1.0f, 20.0f, 0.0f);
        this.d.yaw = -0.7853982f;
        (this.e = new ModelRenderer(this, 20, 11)).addBox(0.0f, -4.0f, 0.0f, 0, 4, 6, aFloat);
        this.e.setRotationPoint(0.0f, 16.0f, -3.0f);
        (this.f = new ModelRenderer(this, 20, 21)).addBox(0.0f, 0.0f, 0.0f, 0, 4, 6, aFloat);
        this.f.setRotationPoint(0.0f, 22.0f, -3.0f);
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
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        float vFloat8 = 1.0f;
        if (!aEntity7.isSwimming()) {
            vFloat8 = 1.5f;
        }
        this.b.yaw = -vFloat8 * 0.45f * MathUtils.sin(0.6f * aFloat3);
    }
}
