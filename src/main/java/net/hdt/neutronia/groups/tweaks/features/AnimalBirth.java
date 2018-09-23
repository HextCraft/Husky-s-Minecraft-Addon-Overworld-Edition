package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.util.EntityUtils;
import net.hdt.neutronia.entity.ai.EntityAIMate;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AnimalBirth extends Component {

    @SubscribeEvent
    public void addEntityAI(EntityJoinWorldEvent evt) {
        if (evt.getEntity() instanceof EntityLiving) {
            EntityLiving entity = (EntityLiving) evt.getEntity();
            if (entity instanceof EntityAnimal && EntityUtils.hasAI(entity, net.minecraft.entity.ai.EntityAIMate.class)) {
                EntityUtils.removeAI(entity, net.minecraft.entity.ai.EntityAIMate.class);
                entity.tasks.addTask(0, new EntityAIMate((EntityAnimal) entity, 1.0D, 25d));
            }
        }
    }

    @Override
    public String getFeatureDescription() {
        return "Make born animals spawn between their parents for easier automation.";
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}