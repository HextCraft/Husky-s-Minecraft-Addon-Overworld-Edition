package team.hdt.neutronia_revamped.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nullable;

public class IngredientNBT extends Ingredient
{
    private final ItemStack stack;
    protected IngredientNBT(ItemStack stack)
    {
        super(stack);
        this.stack = stack;
    }

    @Override
    public boolean apply(@Nullable ItemStack input)
    {
        if (input == null)
            return false;
        //Can't use areItemStacksEqualUsingNBTShareTag because it compares stack size as well
        return this.stack.getItem() == input.getItem() && this.stack.getItemDamage() == input.getItemDamage() && ItemStack.areItemStackShareTagsEqual(this.stack, input);
    }

    @Override
    public boolean isSimple()
    {
        return false;
    }
}