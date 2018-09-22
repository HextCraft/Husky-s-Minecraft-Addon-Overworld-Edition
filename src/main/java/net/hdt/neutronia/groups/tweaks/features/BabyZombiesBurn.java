package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BabyZombiesBurn extends Component {

    @SubscribeEvent
    public void entityUpdate(LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityZombie && !(event.getEntity() instanceof EntityHusk)) {
            EntityZombie zombie = (EntityZombie) event.getEntity();

            if (zombie.getEntityWorld().isDaytime() && !zombie.getEntityWorld().isRemote && zombie.isChild()) {
                float f = zombie.getBrightness();
                BlockPos blockpos = zombie.getRidingEntity() instanceof EntityBoat ? new BlockPos(zombie.posX, Math.round(zombie.posY), zombie.posZ).up() : new BlockPos(zombie.posX, Math.round(zombie.posY), zombie.posZ);

                if (f > 0.5F && zombie.getEntityWorld().rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && zombie.getEntityWorld().canSeeSky(blockpos)) {
                    boolean flag = true;
                    ItemStack itemstack = zombie.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

                    if (!itemstack.isEmpty()) {
                        if (itemstack.isItemStackDamageable()) {
                            itemstack.setItemDamage(itemstack.getItemDamage() + zombie.getEntityWorld().rand.nextInt(2));

                            if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                                zombie.renderBrokenItemStack(itemstack);
                                zombie.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                            }
                        }

                        flag = false;
                    }

                    if (flag)
                        zombie.setFire(8);
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "Makes baby zombies burn in daylight";
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}
