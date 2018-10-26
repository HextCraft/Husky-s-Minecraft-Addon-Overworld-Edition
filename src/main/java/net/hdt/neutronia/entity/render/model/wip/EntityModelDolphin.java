package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.passive.EntityDolphin;
import net.minecraft.entity.Entity;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelDolphin extends EntityModel
{
    private final ModelRenderer a;
    private final ModelRenderer b;
    private final ModelRenderer c;
    private final ModelRenderer d;
    
    public EntityModelDolphin() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        final float vFloat1 = 18.0f;
        final float vFloat2 = -8.0f;
        (this.b = new ModelRenderer(this, 22, 0)).a(-4.0f, -7.0f, 0.0f, 8, 7, 13);
        this.b.setRotationPoint(0.0f, 22.0f, -5.0f);
        final ModelRenderer vModelRenderer3 = new ModelRenderer(this, 51, 0);
        vModelRenderer3.a(-0.5f, 0.0f, 8.0f, 1, 4, 5);
        vModelRenderer3.pitch = 1.0471976f;
        this.b.addChild(vModelRenderer3);
        final ModelRenderer vModelRenderer4 = new ModelRenderer(this, 48, 20);
        vModelRenderer4.mirror = true;
        vModelRenderer4.a(-0.5f, -4.0f, 0.0f, 1, 4, 7);
        vModelRenderer4.setRotationPoint(2.0f, -2.0f, 4.0f);
        vModelRenderer4.pitch = 1.0471976f;
        vModelRenderer4.roll = 2.0943952f;
        this.b.addChild(vModelRenderer4);
        final ModelRenderer vModelRenderer5 = new ModelRenderer(this, 48, 20);
        vModelRenderer5.a(-0.5f, -4.0f, 0.0f, 1, 4, 7);
        vModelRenderer5.setRotationPoint(-2.0f, -2.0f, 4.0f);
        vModelRenderer5.pitch = 1.0471976f;
        vModelRenderer5.roll = -2.0943952f;
        this.b.addChild(vModelRenderer5);
        (this.c = new ModelRenderer(this, 0, 19)).a(-2.0f, -2.5f, 0.0f, 4, 5, 11);
        this.c.setRotationPoint(0.0f, -2.5f, 11.0f);
        this.c.pitch = -0.10471976f;
        this.b.addChild(this.c);
        (this.d = new ModelRenderer(this, 19, 20)).a(-5.0f, -0.5f, 0.0f, 10, 1, 6);
        this.d.setRotationPoint(0.0f, 0.0f, 9.0f);
        this.d.pitch = 0.0f;
        this.c.addChild(this.d);
        (this.a = new ModelRenderer(this, 0, 0)).a(-4.0f, -3.0f, -3.0f, 8, 7, 6);
        this.a.setRotationPoint(0.0f, -4.0f, -3.0f);
        final ModelRenderer vModelRenderer6 = new ModelRenderer(this, 0, 13);
        vModelRenderer6.a(-1.0f, 2.0f, -7.0f, 2, 2, 4);
        this.a.addChild(vModelRenderer6);
        this.b.addChild(this.a);
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.b.render(aFloat7);
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        this.b.pitch = aFloat5 * 0.017453292f;
        this.b.yaw = aFloat4 * 0.017453292f;
        if (aEntity7 instanceof EntityDolphin) {
            final EntityDolphin vEntityDolphin8 = (EntityDolphin)aEntity7;
            if (vEntityDolphin8.r != 0.0 || vEntityDolphin8.t != 0.0) {
                final ModelRenderer b = this.b;
                b.pitch += -0.05f + -0.05f * MathUtils.cos(aFloat3 * 0.3f);
                this.c.pitch = -0.1f * MathUtils.cos(aFloat3 * 0.3f);
                this.d.pitch = -0.2f * MathUtils.cos(aFloat3 * 0.3f);
            }
        }
    }
}
