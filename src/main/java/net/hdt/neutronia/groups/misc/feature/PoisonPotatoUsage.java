package net.hdt.neutronia.groups.misc.feature;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PoisonPotatoUsage extends Component {

    private static final String TAG_POISONED = "neutronia:poison_potato_applied";

    double chance;

    @Override
    public void setupConfig() {
        chance = loadProperty("Chance to Poison", 0.1).get();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @SubscribeEvent
    public void onInteract(EntityInteract event) {
        if (event.getTarget() instanceof EntityAnimal && event.getItemStack().getItem() == Items.POISONOUS_POTATO) {
            EntityAnimal animal = (EntityAnimal) event.getTarget();
            if (animal.isChild() && !isEntityPoisoned(animal)) {
                if (!event.getWorld().isRemote) {
                    if (animal.world.rand.nextDouble() < chance) {
                        poisonEntity(animal);
                        animal.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0));
                    }
                } else event.getEntityPlayer().swingArm(event.getHand());

                event.getItemStack().shrink(1);
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityAnimal) {
            EntityAnimal animal = (EntityAnimal) event.getEntity();
            if (animal.isChild() && isEntityPoisoned(animal))
                animal.setGrowingAge(-24000);
        }
    }

    private boolean isEntityPoisoned(Entity e) {
        return e.getEntityData().getBoolean(TAG_POISONED);
    }

    private void poisonEntity(Entity e) {
        e.getEntityData().setBoolean(TAG_POISONED, true);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}
