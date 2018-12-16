package team.hdt.neutronia_legacy.entity.ai.eat;

import net.minecraft.entity.EntityCreature;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class EntityAIMonsterEat extends EntityAIEatFood<EntityCreature> {

    private int cooldown;

    public EntityAIMonsterEat(EntityCreature entity, Ingredient validItem, double squareDistance) {
        super(entity, validItem, squareDistance);
    }

    @Override
    public boolean isReady() {
        return cooldown > 0;
    }

    @Override
    public void onEaten(ItemStack food) {
        entity.playSound(SoundEvents.ENTITY_PLAYER_BURP, 1.0F, (entity.world.rand.nextFloat() - entity.world.rand.nextFloat()) * 0.2F + 1.0F);
        cooldown = 200;
        food.shrink(1);
    }

}
