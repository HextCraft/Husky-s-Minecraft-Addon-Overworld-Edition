//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.recipes;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeSerializers;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class BlastingRecipe extends AbstractSmeltingRecipe {
    public BlastingRecipe(Identifier identifier_1, String string_1, Ingredient ingredient_1, ItemStack itemStack_1, float float_1, int int_1) {
        super(identifier_1, string_1, ingredient_1, itemStack_1, float_1, int_1);
    }

    public boolean matches(Inventory inventory_1, World world_1) {
        return inventory_1 instanceof BlastFurnaceBlockEntity && this.input.matches(inventory_1.getInvStack(0));
    }

    @Environment(EnvType.CLIENT)
    public ItemStack method_17447() {
        return new ItemStack(Blocks.BLAST_FURNACE);
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.field_17084;
    }

    public static class class_3860 implements RecipeSerializer<BlastingRecipe> {
        public class_3860() {
        }

        public BlastingRecipe read(Identifier identifier_1, JsonObject jsonObject_1) {
            String string_1 = JsonHelper.getString(jsonObject_1, "group", "");
            Ingredient ingredient_2;
            if (JsonHelper.hasArray(jsonObject_1, "ingredient")) {
                ingredient_2 = Ingredient.fromJson(JsonHelper.getArray(jsonObject_1, "ingredient"));
            } else {
                ingredient_2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject_1, "ingredient"));
            }

            String string_2 = JsonHelper.getString(jsonObject_1, "result");
            Item item_1 = (Item)Registry.ITEM.get(new Identifier(string_2));
            if (item_1 != null) {
                ItemStack itemStack_2 = new ItemStack(item_1);
                float float_1 = JsonHelper.getFloat(jsonObject_1, "experience", 0.0F);
                int int_1 = JsonHelper.getInt(jsonObject_1, "cookingtime", 100);
                return new BlastingRecipe(identifier_1, string_1, ingredient_2, itemStack_2, float_1, int_1);
            } else {
                throw new IllegalStateException(string_2 + " did not exist");
            }
        }

        public BlastingRecipe read(Identifier identifier_1, PacketByteBuf packetByteBuf_1) {
            String string_1 = packetByteBuf_1.readString(32767);
            Ingredient ingredient_1 = Ingredient.fromPacket(packetByteBuf_1);
            ItemStack itemStack_1 = packetByteBuf_1.readItemStack();
            float float_1 = packetByteBuf_1.readFloat();
            int int_1 = packetByteBuf_1.readVarInt();
            return new BlastingRecipe(identifier_1, string_1, ingredient_1, itemStack_1, float_1, int_1);
        }

        public void write(PacketByteBuf packetByteBuf_1, BlastingRecipe blastingRecipe_1) {
            packetByteBuf_1.writeString(blastingRecipe_1.group);
            blastingRecipe_1.input.write(packetByteBuf_1);
            packetByteBuf_1.writeItemStack(blastingRecipe_1.output);
            packetByteBuf_1.writeFloat(blastingRecipe_1.experience);
            packetByteBuf_1.writeVarInt(blastingRecipe_1.cookTime);
        }

        public String getId() {
            return "blasting";
        }
    }
}
