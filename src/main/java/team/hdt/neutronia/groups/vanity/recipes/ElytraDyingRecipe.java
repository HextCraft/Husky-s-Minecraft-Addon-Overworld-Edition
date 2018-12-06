package team.hdt.neutronia.groups.vanity.recipes;

import team.hdt.huskylib.recipe.ModRecipe;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import team.hdt.neutronia.groups.tweaks.util.ItemNBTHelper;
import team.hdt.neutronia.groups.vanity.feature.DyableElytra;

public class ElytraDyingRecipe extends ModRecipe {

    public ElytraDyingRecipe() {
        super(new ResourceLocation("neutronia", "elytra_dying"));
    }

    @Override
    public boolean matches(InventoryCrafting var1, World var2) {
        boolean foundSource = false;
        boolean foundTarget = false;

        for (int i = 0; i < var1.getSizeInventory(); i++) {
            ItemStack stack = var1.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ItemElytra) {
                    if (foundTarget)
                        return false;
                    foundTarget = true;
                } else if (stack.getItem() instanceof ItemDye) {
                    if (foundSource)
                        return false;
                    foundSource = true;
                } else return false;
            }
        }

        return foundSource && foundTarget;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1) {
        int source = -1;
        ItemStack target = ItemStack.EMPTY;

        for (int i = 0; i < var1.getSizeInventory(); i++) {
            ItemStack stack = var1.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ItemDye)
                    source = stack.getItemDamage();
                else target = stack;
            }
        }

        if (!target.isEmpty()) {
            ItemStack copy = target.copy();
            ItemNBTHelper.setInt(copy, DyableElytra.TAG_ELYTRA_DYE, source);
            return copy;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(Items.ELYTRA);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    @Override
    public boolean canFit(int x, int y) {
        return true;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.withSize(2, Ingredient.EMPTY);
        list.set(0, Ingredient.fromStacks(new ItemStack(Items.ELYTRA)));

        ItemStack[] stacks = new ItemStack[16];
        for (int i = 0; i < 16; i++)
            stacks[i] = new ItemStack(Items.DYE, 1, i);
        list.set(1, Ingredient.fromStacks(stacks));
        return list;
    }

}