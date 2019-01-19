package team.abnormal.neutronia.recipes.idk;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.recipes.SmokingRecipes;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.Iterator;

public class BlastingManager {



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

    private static boolean parseJsonRecipes()
    {
        FileSystem filesystem = null;
        Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
        boolean flag1;

        try
        {
            URL url = CraftingManager.class.getResource("/assets/.mcassetsroot");

            if (url != null)
            {
                URI uri = url.toURI();
                Path path;

                if ("file".equals(uri.getScheme()))
                {
                    path = Paths.get(CraftingManager.class.getResource("/assets/minecraft/recipes").toURI());
                }
                else
                {
                    if (!"jar".equals(uri.getScheme()))
                    {
                        Reference.LOGGER.error("Unsupported scheme " + uri + " trying to list all recipes");
                        boolean flag2 = false;
                        return flag2;
                    }

                    filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                    path = filesystem.getPath("/assets/minecraft/recipes");
                }

                Iterator<Path> iterator = Files.walk(path).iterator();

                while (iterator.hasNext())
                {
                    Path path1 = iterator.next();

                    if ("json".equals(FilenameUtils.getExtension(path1.toString())))
                    {
                        Path path2 = path.relativize(path1);
                        String s = FilenameUtils.removeExtension(path2.toString()).replaceAll("\\\\", "/");
                        ResourceLocation resourcelocation = new ResourceLocation(s);
                        BufferedReader bufferedreader = null;

                        try
                        {
                            boolean flag;

                            try
                            {
                                bufferedreader = Files.newBufferedReader(path1);
                                register(s, parseRecipeJson( JsonUtils.fromJson(gson, bufferedreader, JsonObject.class)));
                            }
                            catch (JsonParseException jsonparseexception)
                            {
                                Reference.LOGGER.error("Parsing error loading recipe " + resourcelocation, jsonparseexception);
                                flag = false;
                                return flag;
                            }
                            catch (IOException ioexception)
                            {
                                Reference.LOGGER.error("Couldn't read recipe " + resourcelocation + " from " + path1, (Throwable)ioexception);
                                flag = false;
                                return flag;
                            }
                        }
                        finally
                        {
                            IOUtils.closeQuietly((Reader)bufferedreader);
                        }
                    }
                }

                return true;
            }

            Reference.LOGGER.error("Couldn't find .mcassetsroot");
            flag1 = false;
        }
        catch (IOException | URISyntaxException urisyntaxexception)
        {
            LOGGER.error("Couldn't get a list of all recipe files", (Throwable)urisyntaxexception);
            flag1 = false;
            return flag1;
        }
        finally
        {
            IOUtils.closeQuietly((Closeable)filesystem);
        }

        return flag1;
    }

    private static IRecipe parseRecipeJson(JsonObject json)
    {
        String s = JsonUtils.getString(json, "type");

        if ("smoking".equals(s))
        {
            return SmokingRecipes.deserialize(json);
        }
        else
        {
            throw new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'");
        }
    }

}
