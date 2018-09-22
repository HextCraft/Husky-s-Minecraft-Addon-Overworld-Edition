package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EquipmentDrop extends Component {

    @SubscribeEvent
    public void setDropChance(EntityJoinWorldEvent e) {
        if (e.getEntity() instanceof EntityLiving) {
            EntityLiving entity = (EntityLiving) e.getEntity();
            if (entity instanceof EntityZombie) {
                for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
                    entity.setDropChance(slot, 1);
                }
            }
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Zombies have a 100% chance to drop any equipment";
    }
}
