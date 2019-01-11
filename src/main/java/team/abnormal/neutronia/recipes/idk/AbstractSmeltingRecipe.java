//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.recipes.idk;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractSmeltingRecipe implements IRecipe {
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient input;
    protected final ItemStack output;
    protected final float experience;
    protected final int cookTime;

    public AbstractSmeltingRecipe(ResourceLocation identifier_1, String string_1, Ingredient ingredient_1, ItemStack itemStack_1, float float_1, int int_1) {
        this.id = identifier_1;
        this.group = string_1;
        this.input = ingredient_1;
        this.output = itemStack_1;
        this.experience = float_1;
        this.cookTime = int_1;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return this.output.copy();
    }

    @SideOnly(Side.CLIENT)
    public boolean canFit(int int_1, int int_2) {
        return true;
    }

    public NonNullList<Ingredient> getPreviewInputs() {
        NonNullList<Ingredient> defaultedList_1 = NonNullList.create();
        defaultedList_1.add(this.input);
        return defaultedList_1;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return this.id;
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return (Class<IRecipe>) this.getClass();
    }

    public float getExperience() {
        return this.experience;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @SideOnly(Side.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name) {
        return this;
    }

}
