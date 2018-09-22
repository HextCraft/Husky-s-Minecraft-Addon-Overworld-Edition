package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArmedArmorStands extends Component {

    @SubscribeEvent
    public void entityConstruct(EntityEvent.EntityConstructing event) {
        if (event.getEntity() instanceof EntityArmorStand) {
            EntityArmorStand stand = (EntityArmorStand) event.getEntity();
            if (!stand.getShowArms())
                setShowArms(stand, true);
        }
    }

    private void setShowArms(EntityArmorStand e, boolean showArms) {
        e.getDataManager().set(EntityArmorStand.STATUS, func_184797_a(e.getDataManager().get(EntityArmorStand.STATUS), showArms));
    }

    // idk, copypasta from EntityArmorStand
    private byte func_184797_a(byte p_184797_1_, boolean p_184797_3_) {
        if (p_184797_3_)
            p_184797_1_ = (byte) (p_184797_1_ | 4);
        else
            p_184797_1_ = (byte) (p_184797_1_ & ~4);

        return p_184797_1_;
    }

    @Override
    public String getDescription() {
        return "Adds armed armor stands";
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}
