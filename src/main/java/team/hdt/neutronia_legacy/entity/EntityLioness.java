package team.hdt.neutronia_legacy.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityLioness extends EntityLiving {

    public EntityLioness(World worldIn) {
        super(worldIn);
        setSize(0.5F, 0.5F);
    }

    protected void initEntityAI() {
        super.initEntityAI();
    }

}