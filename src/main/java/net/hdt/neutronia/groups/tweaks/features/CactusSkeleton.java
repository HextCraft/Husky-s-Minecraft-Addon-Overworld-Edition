package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CactusSkeleton extends Component {

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof AbstractSkeleton && event.getSource().damageType.equals(DamageSource.CACTUS.damageType)) {
            event.setCanceled(true);
        }
    }

    @Override
    public String getFeatureDescription() {
        return "Skeletons are no longer damaged by Cacti. Intended to make killing mobs in mob-traps harder.";
    }
}
