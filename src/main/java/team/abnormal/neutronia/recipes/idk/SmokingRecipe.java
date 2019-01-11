//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.recipes.idk;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.SmokerBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeSerializers;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import team.abnormal.neutronia.init.NBlocks;

import java.util.Map;
import java.util.Set;

public class SmokingRecipe extends AbstractSmeltingRecipe {
    public SmokingRecipe(ResourceLocation identifier_1, String string_1, Ingredient ingredient_1, ItemStack itemStack_1, float float_1, int int_1) {
        super(identifier_1, string_1, ingredient_1, itemStack_1, float_1, int_1);
    }

    public boolean matches(InventoryCrafting inventory_1, World world_1) {
        return inventory_1 instanceof SmokerBlockEntity && this.input.matches(inventory_1.getInvStack(0));
    }

    @Environment(EnvType.CLIENT)
    public ItemStack method_17447() {
        return new ItemStack(NBlocks.SMOKER);
    }

    /**
     * Returns a shaped recipe json object as a Java ShapedRecipe object.
     */
    public static SmokingRecipe deserialize(JsonObject json)
    {
        String s = JsonUtils.getString(json, "group", "");
        Map<String, Ingredient> map = deserializeKey(JsonUtils.getJsonObject(json, "key"));
        String[] astring = shrink(patternFromJson(JsonUtils.getJsonArray(json, "pattern")));
        int i = astring[0].length();
        int j = astring.length;
        NonNullList<Ingredient> nonnulllist = deserializeIngredients(astring, map, i, j);
        ItemStack itemstack = deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
        return new SmokingRecipe(s, i, j, nonnulllist, itemstack);
    }

    private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeight)
    {
        NonNullList<Ingredient> nonnulllist = NonNullList.<Ingredient>withSize(patternWidth * patternHeight, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");

        for (int i = 0; i < pattern.length; ++i)
        {
            for (int j = 0; j < pattern[i].length(); ++j)
            {
                String s = pattern[i].substring(j, j + 1);
                Ingredient ingredient = keys.get(s);

                if (ingredient == null)
                {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(j + patternWidth * i, ingredient);
            }
        }

        if (!set.isEmpty())
        {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        }
        else
        {
            return nonnulllist;
        }
    }

    /**
     * Returns an item json object as a Java ItemStack object.
     */
    public static ItemStack deserializeItem(JsonObject json, boolean useCount)
    {
        String s = JsonUtils.getString(json, "item");
        Item item = Item.REGISTRY.getObject(new ResourceLocation(s));

        if (item == null)
        {
            throw new JsonSyntaxException("Unknown item '" + s + "'");
        }
        else if (item.getHasSubtypes() && !json.has("data"))
        {
            throw new JsonParseException("Missing data for item '" + s + "'");
        }
        else
        {
            int i = JsonUtils.getInt(json, "data", 0);
            int j = useCount ? JsonUtils.getInt(json, "count", 1) : 1;
            return new ItemStack(item, j, i);
        }
    }



    public static class class_3863 implements IRecipeSerializer<SmokingRecipe> {
        public class_3863() {
        }

        public SmokingRecipe read(Identifier identifier_1, JsonObject jsonObject_1) {
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
                return new SmokingRecipe(identifier_1, string_1, ingredient_2, itemStack_2, float_1, int_1);
            } else {
                throw new IllegalStateException(string_2 + " did not exist");
            }
        }

        public SmokingRecipe read(Identifier identifier_1, PacketByteBuf packetByteBuf_1) {
            String string_1 = packetByteBuf_1.readString(32767);
            Ingredient ingredient_1 = Ingredient.fromPacket(packetByteBuf_1);
            ItemStack itemStack_1 = packetByteBuf_1.readItemStack();
            float float_1 = packetByteBuf_1.readFloat();
            int int_1 = packetByteBuf_1.readVarInt();
            return new SmokingRecipe(identifier_1, string_1, ingredient_1, itemStack_1, float_1, int_1);
        }

        public void write(PacketByteBuf packetByteBuf_1, SmokingRecipe smokingRecipe_1) {
            packetByteBuf_1.writeString(smokingRecipe_1.group);
            smokingRecipe_1.input.write(packetByteBuf_1);
            packetByteBuf_1.writeItemStack(smokingRecipe_1.output);
            packetByteBuf_1.writeFloat(smokingRecipe_1.experience);
            packetByteBuf_1.writeVarInt(smokingRecipe_1.cookTime);
        }

        public String getId() {
            return "smoking";
        }
    }
}
