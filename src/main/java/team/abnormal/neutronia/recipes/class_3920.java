//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.recipes;

import com.google.gson.JsonObject;
import net.minecraft.class_3924.class_3925;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeSerializers;
import net.minecraft.recipe.smelting.AbstractSmeltingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class class_3920 extends AbstractSmeltingRecipe {
    public class_3920(Identifier identifier_1, String string_1, Ingredient ingredient_1, ItemStack itemStack_1, float float_1, int int_1) {
        super(identifier_1, string_1, ingredient_1, itemStack_1, float_1, int_1);
    }

    public boolean matches(Inventory inventory_1, World world_1) {
        return inventory_1 instanceof class_3925 && this.input.matches(inventory_1.getInvStack(0));
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.field_17347;
    }

    public static class class_3921 implements RecipeSerializer<class_3920> {
        public class_3921() {
        }

        public class_3920 read(Identifier identifier_1, JsonObject jsonObject_1) {
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
                return new class_3920(identifier_1, string_1, ingredient_2, itemStack_2, float_1, int_1);
            } else {
                throw new IllegalStateException(string_2 + " did not exist");
            }
        }

        public class_3920 read(Identifier identifier_1, PacketByteBuf packetByteBuf_1) {
            String string_1 = packetByteBuf_1.readString(32767);
            Ingredient ingredient_1 = Ingredient.fromPacket(packetByteBuf_1);
            ItemStack itemStack_1 = packetByteBuf_1.readItemStack();
            float float_1 = packetByteBuf_1.readFloat();
            int int_1 = packetByteBuf_1.readVarInt();
            return new class_3920(identifier_1, string_1, ingredient_1, itemStack_1, float_1, int_1);
        }

        public void write(PacketByteBuf packetByteBuf_1, class_3920 class_3920_1) {
            packetByteBuf_1.writeString(class_3920_1.group);
            class_3920_1.input.write(packetByteBuf_1);
            packetByteBuf_1.writeItemStack(class_3920_1.output);
            packetByteBuf_1.writeFloat(class_3920_1.experience);
            packetByteBuf_1.writeVarInt(class_3920_1.cookTime);
        }

        public String getId() {
            return "campfire";
        }
    }
}
