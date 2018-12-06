package team.hdt.neutronia.entity;

import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.World;

public class EntityWhale extends EntityWaterMob {

    public EntityWhale(World worldIn) {
        super(worldIn);
        setSize(0.5F, 0.5F);
    }

    protected void initEntityAI() {
        super.initEntityAI();
    }

}