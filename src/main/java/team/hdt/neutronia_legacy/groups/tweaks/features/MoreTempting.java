package team.hdt.neutronia_legacy.groups.tweaks.features;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.entity.ai.eat.EntityAITempt;

public class MoreTempting extends Component {

    private static Ingredient CHICKEN;
    private static Ingredient PIG;
    private static Ingredient HERD_ANIMAL;

    private static Ingredient temptingIngredients(EntityAnimal entity) {
        if (entity instanceof EntityPig)
            return PIG;
        if (entity instanceof EntitySheep || entity instanceof EntityCow)
            return HERD_ANIMAL;
        if (entity instanceof EntityChicken)
            return CHICKEN;
        return null;
    }

    private static void removeTask(EntityLiving entity, Class<? extends EntityAIBase> clazz) {
        entity.tasks.taskEntries.removeIf(task -> clazz.isAssignableFrom(task.action.getClass()));
    }

    @Override
    public String getFeatureDescription() {
        return "Add more valid items for tempting animals to follow. Sheep and cows follow Tall Grass or Wheat." +
                " Chickens follow most seeds." +
                " Pigs will follow Wheat, Potatoes, Beets, Chocolate";
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        CHICKEN = Ingredient.fromStacks(new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.MELON_SEEDS), new ItemStack(Items.PUMPKIN_SEEDS), new ItemStack(Items.BEETROOT_SEEDS));
        PIG = Ingredient.fromStacks(new ItemStack(Items.WHEAT), new ItemStack(Items.CARROT));
        HERD_ANIMAL = Ingredient.fromStacks(new ItemStack(Items.WHEAT), new ItemStack(Blocks.TALLGRASS));
    }

    @SubscribeEvent
    public void addEntityAI(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) event.getEntity();
            if (entity instanceof EntityAnimal) {
                EntityAnimal animal = ((EntityAnimal) entity);
                Ingredient ingredient = temptingIngredients(animal);
                if (ingredient != null) {
                    removeTask(animal, net.minecraft.entity.ai.EntityAITempt.class);
                    animal.tasks.addTask(3, new EntityAITempt(animal, 1.5, false, ingredient));
                }
            }
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public String[] getIncompatibleMods() {
        return new String[]{"easybreeding"};
    }

}
