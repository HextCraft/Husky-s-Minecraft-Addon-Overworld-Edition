/*
package net.hdt.neutronia.entity.render.model;

import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAbstractIllager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.sortme.OptionMainHand;
import net.minecraft.util.math.MathUtils;

public class EntityModelEvilVillager extends ModelPig
{
    protected final ModelRenderer a;
    private final ModelRenderer h;
    protected final ModelRenderer b;
    protected final ModelRenderer c;
    protected final ModelRenderer d;
    protected final ModelRenderer e;
    private final ModelRenderer i;
    protected final ModelRenderer f;
    protected final ModelRenderer g;
    private float j;
    
    public EntityModelEvilVillager(final float aFloat1, final float aFloat2, final int aInteger3, final int aInteger4) {
        (this.a = new ModelRenderer(this).setTextureSize(aInteger3, aInteger4)).setRotationPoint(0.0f, 0.0f + aFloat2, 0.0f);
        this.a.setTextureOffset(0, 0).addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8, aFloat1);
        (this.h = new ModelRenderer(this, 32, 0).setTextureSize(aInteger3, aInteger4)).addBox(-4.0f, -10.0f, -4.0f, 8, 12, 8, aFloat1 + 0.45f);
        this.a.addChild(this.h);
        this.h.isHidden = true;
        (this.i = new ModelRenderer(this).setTextureSize(aInteger3, aInteger4)).setRotationPoint(0.0f, aFloat2 - 2.0f, 0.0f);
        this.i.setTextureOffset(24, 0).addBox(-1.0f, -1.0f, -6.0f, 2, 4, 2, aFloat1);
        this.a.addChild(this.i);
        (this.b = new ModelRenderer(this).setTextureSize(aInteger3, aInteger4)).setRotationPoint(0.0f, 0.0f + aFloat2, 0.0f);
        this.b.setTextureOffset(16, 20).addBox(-4.0f, 0.0f, -3.0f, 8, 12, 6, aFloat1);
        this.b.setTextureOffset(0, 38).addBox(-4.0f, 0.0f, -3.0f, 8, 18, 6, aFloat1 + 0.5f);
        (this.c = new ModelRenderer(this).setTextureSize(aInteger3, aInteger4)).setRotationPoint(0.0f, 0.0f + aFloat2 + 2.0f, 0.0f);
        this.c.setTextureOffset(44, 22).addBox(-8.0f, -2.0f, -2.0f, 4, 8, 4, aFloat1);
        final ModelRenderer vModelRenderer5 = new ModelRenderer(this, 44, 22).setTextureSize(aInteger3, aInteger4);
        vModelRenderer5.mirror = true;
        vModelRenderer5.addBox(4.0f, -2.0f, -2.0f, 4, 8, 4, aFloat1);
        this.c.addChild(vModelRenderer5);
        this.c.a(40, 38).addBox(-4.0f, 2.0f, -2.0f, 8, 4, 4, aFloat1);
        (this.d = new ModelRenderer(this, 0, 22).setTextureSize(aInteger3, aInteger4)).setRotationPoint(-2.0f, 12.0f + aFloat2, 0.0f);
        this.d.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, aFloat1);
        this.e = new ModelRenderer(this, 0, 22).setTextureSize(aInteger3, aInteger4);
        this.e.mirror = true;
        this.e.setRotationPoint(2.0f, 12.0f + aFloat2, 0.0f);
        this.e.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, aFloat1);
        (this.f = new ModelRenderer(this, 40, 46).setTextureSize(aInteger3, aInteger4)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, aFloat1);
        this.f.setRotationPoint(-5.0f, 2.0f + aFloat2, 0.0f);
        this.g = new ModelRenderer(this, 40, 46).setTextureSize(aInteger3, aInteger4);
        this.g.mirror = true;
        this.g.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, aFloat1);
        this.g.setRotationPoint(5.0f, 2.0f + aFloat2, 0.0f);
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        this.a.render(aFloat7);
        this.b.render(aFloat7);
        this.d.render(aFloat7);
        this.e.render(aFloat7);
        final EntityAbstractIllager vEntityAbstractIllager8 = (EntityAbstractIllager)aEntity1;
        if (vEntityAbstractIllager8.l() == EntityAbstractIllager.EnumAttackType.CROSSED) {
            this.c.render(aFloat7);
        }
        else {
            this.f.render(aFloat7);
            this.g.render(aFloat7);
        }
    }
    
    @Override
    public void setRotationAngles(final float aFloat1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final Entity aEntity7) {
        this.a.yaw = aFloat4 * 0.017453292f;
        this.a.pitch = aFloat5 * 0.017453292f;
        this.c.rotationPointY = 3.0f;
        this.c.rotationPointZ = -1.0f;
        this.c.pitch = -0.75f;
        this.d.pitch = MathUtils.cos(aFloat1 * 0.6662f) * 1.4f * aFloat2 * 0.5f;
        this.e.pitch = MathUtils.cos(aFloat1 * 0.6662f + 3.1415927f) * 1.4f * aFloat2 * 0.5f;
        this.d.yaw = 0.0f;
        this.e.yaw = 0.0f;
        final EntityAbstractIllager.EnumAttackType vEntityAbstractIllagerEnumAttackType8 = ((EntityAbstractIllager)aEntity7).l();
        if (vEntityAbstractIllagerEnumAttackType8 == EntityAbstractIllager.EnumAttackType.ATTACKING) {
            final float vFloat9 = MathUtils.sin(this.swingProgress * 3.1415927f);
            final float vFloat10 = MathUtils.sin((1.0f - (1.0f - this.swingProgress) * (1.0f - this.swingProgress)) * 3.1415927f);
            this.f.roll = 0.0f;
            this.g.roll = 0.0f;
            this.f.yaw = 0.15707964f;
            this.g.yaw = -0.15707964f;
            if (((EntityLiving)aEntity7).getMainHand() == OptionMainHand.RIGHT) {
                this.f.pitch = -1.8849558f + MathUtils.cos(aFloat3 * 0.09f) * 0.15f;
                this.g.pitch = -0.0f + MathUtils.cos(aFloat3 * 0.19f) * 0.5f;
                final ModelRenderer f = this.f;
                f.pitch += vFloat9 * 2.2f - vFloat10 * 0.4f;
                final ModelRenderer g = this.g;
                g.pitch += vFloat9 * 1.2f - vFloat10 * 0.4f;
            }
            else {
                this.f.pitch = -0.0f + MathUtils.cos(aFloat3 * 0.19f) * 0.5f;
                this.g.pitch = -1.8849558f + MathUtils.cos(aFloat3 * 0.09f) * 0.15f;
                final ModelRenderer f2 = this.f;
                f2.pitch += vFloat9 * 1.2f - vFloat10 * 0.4f;
                final ModelRenderer g2 = this.g;
                g2.pitch += vFloat9 * 2.2f - vFloat10 * 0.4f;
            }
            final ModelRenderer f3 = this.f;
            f3.roll += MathUtils.cos(aFloat3 * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer g3 = this.g;
            g3.roll -= MathUtils.cos(aFloat3 * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer f4 = this.f;
            f4.pitch += MathUtils.sin(aFloat3 * 0.067f) * 0.05f;
            final ModelRenderer g4 = this.g;
            g4.pitch -= MathUtils.sin(aFloat3 * 0.067f) * 0.05f;
        }
        else if (vEntityAbstractIllagerEnumAttackType8 == EntityAbstractIllager.EnumAttackType.SPELLCASTING) {
            this.f.rotationPointZ = 0.0f;
            this.f.rotationPointX = -5.0f;
            this.g.rotationPointZ = 0.0f;
            this.g.rotationPointX = 5.0f;
            this.f.pitch = MathUtils.cos(aFloat3 * 0.6662f) * 0.25f;
            this.g.pitch = MathUtils.cos(aFloat3 * 0.6662f) * 0.25f;
            this.f.roll = 2.3561945f;
            this.g.roll = -2.3561945f;
            this.f.yaw = 0.0f;
            this.g.yaw = 0.0f;
        }
        else if (vEntityAbstractIllagerEnumAttackType8 == EntityAbstractIllager.EnumAttackType.BOW_AND_ARROW) {
            this.f.yaw = -0.1f + this.a.yaw;
            this.f.pitch = -1.5707964f + this.a.pitch;
            this.g.pitch = -0.9424779f + this.a.pitch;
            this.g.yaw = this.a.yaw - 0.4f;
            this.g.roll = 1.5707964f;
        }
        else if (vEntityAbstractIllagerEnumAttackType8 == EntityAbstractIllager.EnumAttackType.e) {
            this.f.yaw = -0.3f + this.a.yaw;
            this.g.yaw = 0.6f + this.a.yaw;
            this.f.pitch = -1.5707964f + this.a.pitch + 0.1f;
            this.g.pitch = -1.5f + this.a.pitch;
        }
        else if (vEntityAbstractIllagerEnumAttackType8 == EntityAbstractIllager.EnumAttackType.f) {
            this.f.yaw = -0.8f;
            this.f.pitch = -0.97079635f;
            this.g.pitch = -0.97079635f;
            final float vFloat9 = MathUtils.clamp(this.j, 0.0f, 25.0f);
            this.g.yaw = this.a(0.4f, 0.85f, vFloat9 / 25.0f);
            this.g.pitch = this.a(this.g.pitch, -1.5707964f, vFloat9 / 25.0f);
        }
    }
    
    private float a(final float aFloat1, final float aFloat2, final float aFloat3) {
        return aFloat1 + (aFloat2 - aFloat1) * aFloat3;
    }
    
    */
/*@Override
    public void animateModel(final EntityLiving aEntityLiving1, final float aFloat2, final float aFloat3, final float aFloat4) {
        this.j = aEntityLiving1.da();
        super.animateModel(aEntityLiving1, aFloat2, aFloat3, aFloat4);
    }*//*

    
    public ModelRenderer a(final OptionMainHand aOptionMainHand) {
        if (aOptionMainHand == OptionMainHand.LEFT) {
            return this.g;
        }
        return this.f;
    }
    
    public ModelRenderer a() {
        return this.h;
    }
}
*/
