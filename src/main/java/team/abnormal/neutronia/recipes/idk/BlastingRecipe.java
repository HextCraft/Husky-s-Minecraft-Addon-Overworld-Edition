//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.recipes.idk;

import com.google.gson.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
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
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import team.abnormal.neutronia.base.Reference;

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

public class BlastingRecipe extends IForgeRegistryEntry.Impl<IRecipe> {

    /** How many horizontal slots this recipe is wide. */
    public final int recipeWidth;
    /** How many vertical slots this recipe uses. */
    public final int recipeHeight;
    /** Is a array of ItemStack that composes the recipe. */
    public final NonNullList<Ingredient> recipeItems;
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;
    private final String group;

    public BlastingRecipe(String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
        this.group = group;
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = ingredients;
        this.recipeOutput = result;
    }

    public boolean matches(InventoryCrafting inventory_1, World world_1) {
        return inventory_1 instanceof BlastFurnaceBlockEntity && this.input.test(inventory_1.getStackInSlot(0));
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
                            IOUtils.closeQuietly(bufferedreader);
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
            Reference.LOGGER.error("Couldn't get a list of all recipe files", urisyntaxexception);
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
            return ShapedRecipes.deserialize(json);
        }
        else
        {
            throw new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'");
        }
    }

}
