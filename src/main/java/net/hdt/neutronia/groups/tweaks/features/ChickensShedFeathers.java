package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChickensShedFeathers extends Component {

    boolean chicksDropFeathers;
    boolean dropAtLeastOne;
    int dropFreq;

    @Override
    public void setupConfig() {
        chicksDropFeathers = loadProperty("Chicks drop feathers", true).get();
        dropAtLeastOne = loadProperty("Force at least one feather on kill", true).get();
        dropFreq = loadProperty("Drop frequency (lower means more)", 28000).get();
    }

    @Override
    public String getDescription() {
        return "Makes chickens shed feathers";
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