package net.hdt.neutronia.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityHost extends EntityLiving {

    public EntityHost(World worldIn) {
        super(worldIn);
        setSize(0.5F, 0.5F);
    }

    protected void initEntityAI() {
        super.initEntityAI();
    }

}