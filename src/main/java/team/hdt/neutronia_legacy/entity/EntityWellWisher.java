package team.hdt.neutronia_legacy.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.init.NSounds;

import javax.annotation.Nullable;

public class EntityWellWisher extends EntityMob {

    public EntityWellWisher(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return NSounds.WELL_WISHER_LAUGH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return NSounds.WELL_WISHER_CRY_LAUGH;
    }

}