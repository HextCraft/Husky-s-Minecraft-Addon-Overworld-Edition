package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.util.EntityUtils;
import net.hdt.neutronia.entity.ai.EntityAIFlee;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ImprovedFlee extends Component {

    private static boolean blockPlace, blockBreak, groupFlee;

    public static boolean canSeeBlock(BlockPos pos, EntityLiving entity) {
        RayTraceResult result = entity.world.rayTraceBlocks(entity.getPositionVector(), new Vec3d(pos).add(0.5, 0.5, 0.5));
        return result != null && pos.equals(result.getBlockPos());
    }

    @Override
    public void setupConfig() {
        blockPlace = loadPropBool("Animals Flee from Block Place", "When a block is placed in the vicinity of an animal it will get scared and run.", true);
        blockBreak = loadPropBool("Animals Flee from Block Break", "When a non-replaceable block is broken in the vicinity of an animal it will get scared and run.", true);
        groupFlee = loadPropBool("Animals Group Flee", "When an animal is attacked in the vicinity of another animal it will get scared and run.", true);
    }

    @SubscribeEvent
    public void addEntityAI(EntityJoinWorldEvent evt) {
        if (evt.getEntity() instanceof EntityLiving) {
            EntityLiving entity = (EntityLiving) evt.getEntity();
            if (entity instanceof EntityAnimal && EntityUtils.hasAI(entity, EntityAIPanic.class)) {
                float speed = (float) entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 7;
                EntityUtils.removeAI(entity, EntityAIPanic.class);
                entity.tasks.addTask(0, new EntityAIFlee((EntityCreature) entity, speed));
            }
        }
    }


    @SubscribeEvent
    public void onPlaceBlock(BlockEvent.PlaceEvent event) {
        if (!blockPlace)
            return;
        if (event.getPlayer() != null) {
            AxisAlignedBB box = event.getPlacedBlock().getBoundingBox(event.getWorld(), event.getPos()).offset(event.getPos()).grow(10);
            for (EntityAnimal animal : event.getWorld().getEntitiesWithinAABB(EntityAnimal.class, box)) {
                if (cantBeScared(animal)) continue;
                if (canSeeBlock(event.getPos(), animal))
                    animal.setRevengeTarget(event.getPlayer());
            }
        }
    }

    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        if (!blockBreak)
            return;
        if (event.getPlayer() != null && !event.getState().getMaterial().isReplaceable()) {
            AxisAlignedBB box = event.getState().getBoundingBox(event.getWorld(), event.getPos()).offset(event.getPos()).grow(10);
            for (EntityAnimal animal : event.getWorld().getEntitiesWithinAABB(EntityAnimal.class, box)) {
                if (cantBeScared(animal)) continue;
                if (canSeeBlock(event.getPos(), animal))
                    animal.setRevengeTarget(event.getPlayer());
            }
        }
    }

    @SubscribeEvent
    public void onGroupFlee(LivingSetAttackTargetEvent event) {
        if (!groupFlee)
            return;
        if (event.getTarget() != null && event.getEntityLiving() instanceof EntityAnimal) {
            EntityAnimal a = (EntityAnimal) event.getEntityLiving();
            AxisAlignedBB box = new AxisAlignedBB(a.posX, a.posY, a.posZ, a.posX + 1, a.posY + 1, a.posZ + 1).grow(10);
            for (EntityAnimal animal : a.getEntityWorld().getEntitiesWithinAABB(EntityAnimal.class, box, entity -> entity != null && entity != a && entity.getRevengeTarget() == null)) {
                if (cantBeScared(animal)) continue;
                if (animal.canEntityBeSeen(a)) {
                    animal.setRevengeTarget(event.getTarget());
                }
            }
        }
    }

    private boolean cantBeScared(EntityAnimal animal) {
        if (animal instanceof EntityTameable && ((EntityTameable) animal).isTamed())
            return true;
        if (animal instanceof AbstractHorse && ((AbstractHorse) animal).isTame())
            return true;
        if (!EntityUtils.hasAI(animal, EntityAIFlee.class))
            return true;
        return false;
    }

    @Override
    public String getFeatureDescription() {
        return "Improve fleeing AI for attacked animals";
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }
}
