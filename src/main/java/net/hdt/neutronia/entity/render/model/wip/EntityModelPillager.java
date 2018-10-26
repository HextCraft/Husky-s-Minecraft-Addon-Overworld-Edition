package net.hdt.neutronia.entity.render.model;

import net.minecraft.entity.Entity;

public class EntityModelPillager extends EntityModelEvilVillager
{
    public EntityModelPillager(final float aFloat1, final float aFloat2, final int aInteger3, final int aInteger4) {
        super(aFloat1, aFloat2, aInteger3, aInteger4);
    }
    
    @Override
    public void render(final Entity aEntity1, final float aFloat2, final float aFloat3, final float aFloat4, final float aFloat5, final float aFloat6, final float aFloat7) {
        this.setRotationAngles(aFloat2, aFloat3, aFloat4, aFloat5, aFloat6, aFloat7, aEntity1);
        this.a.render(aFloat7);
        this.b.render(aFloat7);
        this.d.render(aFloat7);
        this.e.render(aFloat7);
        this.f.render(aFloat7);
        this.g.render(aFloat7);
    }
}
