package team.hdt.neutronia_legacy.groups.tweaks.features;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia_legacy.base.groups.Component;

public class ChickensShedFeathers extends Component {

    boolean chicksDropFeathers;
    boolean dropAtLeastOne;
    int dropFreq;

    @Override
    public void setupConfig() {
        chicksDropFeathers = loadPropBool("Chicks drop feathers", "", true);
        dropAtLeastOne = loadPropBool("Force at least one feather on kill", "", true);
        dropFreq = loadPropInt("Drop frequency (lower means more)", "", 28000);
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event) {
        if (event.getEntity().getEntityWorld().isRemote || !(event.getEntity() instanceof EntityChicken))
            return;

        EntityChicken chicken = (EntityChicken) event.getEntity();
        if ((chicksDropFeathers || !chicken.isChild()) && chicken.getEntityWorld().rand.nextInt(dropFreq) == 0)
            chicken.dropItem(Items.FEATHER, 1);
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if (!dropAtLeastOne || event.getEntity().getEntityWorld().isRemote || !(event.getEntity() instanceof EntityChicken) || !((EntityChicken) event.getEntity()).isChild() && !chicksDropFeathers)
            return;

        EntityChicken chicken = (EntityChicken) event.getEntity();
        boolean hasFeather = false;

        for (EntityItem item : event.getDrops())
            if (!item.getItem().isEmpty() && item.getItem().getItem().equals(Items.FEATHER)) {
                hasFeather = true;
                break;
            }

        if (!hasFeather)
            event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), chicken.posX, chicken.posY, chicken.posZ, new ItemStack(Items.FEATHER, 1)));
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}