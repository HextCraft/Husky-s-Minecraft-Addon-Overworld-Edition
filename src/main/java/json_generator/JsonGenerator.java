package json_generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class JsonGenerator {

    private static String modid = "neutronia";

    public static void main(String[] args) {
        /*for (BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
            genSlab(new ResourceLocation(modid, String.format("%s_wood_slab", woodType.getName())), new ResourceLocation("minecraft", "blocks/" + String.format("log_%s", woodType.getName())), new ResourceLocation("minecraft", "blocks/" + String.format("log_%s", woodType.getName())), new ResourceLocation("minecraft", "blocks/" + String.format("log_%s", woodType.getName())));
            genSlab(new ResourceLocation(modid, String.format("%s_log_slab", woodType.getName())), new ResourceLocation("minecraft", "blocks/" + String.format("log_%s_top", woodType.getName())), new ResourceLocation("minecraft", "blocks/" + String.format("log_%s", woodType.getName())), new ResourceLocation("minecraft", "blocks/" + String.format("log_%s_top", woodType.getName())));
            genSlab(new ResourceLocation(modid, String.format("stripped_%s_wood_slab", woodType.getName())), new ResourceLocation(modid, "block/" + String.format("stripped_log_%s_side", woodType.getName())), new ResourceLocation(modid, "block/" + String.format("stripped_log_%s_side", woodType.getName())), new ResourceLocation(modid, "block/" + String.format("stripped_log_%s_side", woodType.getName())));
            genSlab(new ResourceLocation(modid, String.format("stripped_%s_log_slab", woodType.getName())), new ResourceLocation(modid, "block/" + String.format("stripped_log_%s_top", woodType.getName())), new ResourceLocation(modid, "block/" + String.format("stripped_log_%s_side", woodType.getName())), new ResourceLocation(modid, "block/" + String.format("stripped_log_%s_top", woodType.getName())));
        }
        for (EnumDyeColor color : EnumDyeColor.values()) {
            genSlabColored(new ResourceLocation(modid, String.format("%s_colored_planks_slab", color.getName())), new ResourceLocation(modid, "block/colored_planks"), new ResourceLocation(modid, "block/colored_planks"), new ResourceLocation(modid, "block/colored_planks"));
//            genStair(modid, String.format("%s_colored_planks_slab", color.getName()));
        }*/
//        genSlab(new ResourceLocation(modid, "acidian_bricks_slab"), new ResourceLocation(modid, "block/acidian_bricks"), new ResourceLocation(modid, "block/acidian_bricks"), new ResourceLocation(modid, "block/acidian_bricks"));
//        genSlab(new ResourceLocation(modid, "chiseled_acidian_slab"), new ResourceLocation(modid, "block/chiseled_acidian"), new ResourceLocation(modid, "block/chiseled_acidian"), new ResourceLocation(modid, "block/chiseled_acidian"));
//        genSlab(new ResourceLocation(modid, "natural_acidian_slab"), new ResourceLocation(modid, "block/natural_acidian"), new ResourceLocation(modid, "block/natural_acidian"), new ResourceLocation(modid, "block/natural_acidian"));
//        genSlab(new ResourceLocation(modid, "purhogany_planks_slab"), new ResourceLocation(modid, "block/purhogany_planks"), new ResourceLocation(modid, "block/purhogany_planks"), new ResourceLocation(modid, "block/purhogany_planks"));
//        genSlab(new ResourceLocation(modid, "purhogany_log_slab"), new ResourceLocation(modid, "block/purhogany_log_top"), new ResourceLocation(modid, "block/purhogany_log"), new ResourceLocation(modid, "block/purhogany_log_top"));
    }

    public static void genBlock(ResourceLocation modIdAndName, ResourceLocation textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", "cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", textureName.getNamespace() + ":block/" + textureName.getPath());
        defaults.add("textures", textures);

        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("normal", empty);
        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genCustomBlock(ResourceLocation modIdAndName, ResourceLocation modelPath) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", modelPath.getNamespace() + ":" + modelPath.getPath());

        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("normal", empty);
        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }
    }

    public static void genCustomBlockWithTexture(ResourceLocation modIdAndName, ResourceLocation modelPath, ResourceLocation textureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", modelPath.getNamespace() + ":" + modelPath.getPath());

        JsonObject textures = new JsonObject();
        textures.addProperty("all", textureLocation.getNamespace() + ":block/" + textureLocation.getPath());
        defaults.add("textures", textures);

        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("normal", empty);
        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }
    }

    private static void genCoralFan(ResourceLocation modIdAndName, ResourceLocation textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("transform", "forge:default-block");
        defaults.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath());
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();
        JsonObject facing = new JsonObject();
        facing.add("north", new JsonObject());

        JsonObject south = new JsonObject();
        south.addProperty("y", 90);
        facing.add("south", south);

        JsonObject east = new JsonObject();
        east.addProperty("y", 180);
        facing.add("east", east);

        JsonObject west = new JsonObject();
        west.addProperty("y", 270);
        facing.add("west", west);

        variants.add("facing", facing);
        variants.add("inventory", new JsonObject());
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            System.out.print(String.format("Created file with the name %s" + "\n", modIdAndName.getPath()));
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }
        genCoralFanModel(modIdAndName, textureName);
        genCoralFanItemModel(modIdAndName, textureName);
    }

    public static void genCoralFanModel(ResourceLocation modIdAndName, ResourceLocation textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "neutronia:block/coral_fan");

        JsonObject textures = new JsonObject();
        textures.addProperty("fan", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genCoralFanItemModel(ResourceLocation modIdAndName, ResourceLocation textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    private static void genCoralPlant(ResourceLocation modIdAndName, ResourceLocation textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("transform", "forge:default-block");
        defaults.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath());
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();
        JsonObject facing = new JsonObject();
        facing.add("north", new JsonObject());

        JsonObject south = new JsonObject();
        south.addProperty("y", 90);
        facing.add("south", south);

        JsonObject east = new JsonObject();
        east.addProperty("y", 180);
        facing.add("east", east);

        JsonObject west = new JsonObject();
        west.addProperty("y", 270);
        facing.add("west", west);

        variants.add("facing", facing);
        variants.add("inventory", new JsonObject());
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            System.out.print(String.format("Created file with the name %s" + "\n", modIdAndName.getPath()));
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }
        genCoralPlantModel(modIdAndName, textureName);
        genCoralPlantItemModel(modIdAndName, textureName);
    }

    public static void genCoralPlantModel(ResourceLocation modIdAndName, ResourceLocation textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "block/cross");

        JsonObject textures = new JsonObject();
        textures.addProperty("cross", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genCoralPlantItemModel(ResourceLocation modIdAndName, ResourceLocation textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genPlant(String modId, String blockName, String textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", modId + ":" + blockName);
        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("normal", empty);
        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

        genPlantBlockModel(modId, blockName, textureName);
        genPlantItemModel(modId, blockName, textureName);
    }

    public static void genPlantBlockModel(String modId, String blockName, String textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "block/cross");

        JsonObject textures = new JsonObject();
        textures.addProperty("cross", modId + ":block/" + textureName);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genPlantItemModel(String modId, String blockName, String textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modId + ":block/" + textureName);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genOrientedBlock(ResourceLocation modIdAndName, ResourceLocation topTextureName, ResourceLocation frontTextureName, ResourceLocation sidesTextureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", "block/orientable");

        JsonObject textures = new JsonObject();
        textures.addProperty("top", topTextureName.getNamespace() + ":block/" + topTextureName.getPath());
        textures.addProperty("front", frontTextureName.getNamespace() + ":block/" + frontTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());
        defaults.add("textures", textures);

        defaults.addProperty("transform", "forge:default-block");

        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject facing = new JsonObject();
        facing.add("north", new JsonObject());

        JsonObject south = new JsonObject();
        south.addProperty("y", "90");
        facing.add("south", south);

        JsonObject east = new JsonObject();
        east.addProperty("y", "180");
        facing.add("east", east);

        JsonObject west = new JsonObject();
        west.addProperty("y", "270");
        facing.add("west", west);

        variants.add("facing", facing);

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

        genBlockOrientedItemModel(modIdAndName, topTextureName, frontTextureName, sidesTextureName);
    }

    public static void genBlockOrientedItemModel(ResourceLocation modIdAndName, ResourceLocation topTextureName, ResourceLocation frontTextureName, ResourceLocation sidesTextureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "block/orientable");
        JsonObject textures = new JsonObject();
        textures.addProperty("top", topTextureName.getNamespace() + ":block/" + topTextureName.getPath());
        textures.addProperty("front", frontTextureName.getNamespace() + ":block/" + frontTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());
        root.add("textures", textures);
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }
    }

    public static void genPillarBlock(ResourceLocation modIdAndName, ResourceLocation modIdAndBarkName, ResourceLocation endTextureName, ResourceLocation sidesTextureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", "block/cube_column");

        JsonObject textures = new JsonObject();
        textures.addProperty("end", endTextureName.getNamespace() + ":block/" + endTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());
        defaults.add("textures", textures);

        defaults.addProperty("transform", "forge:default-block");

        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject axis = new JsonObject();

        JsonObject axisX = new JsonObject();
        axisX.addProperty("x", 90);
        axisX.addProperty("y", 90);
        axis.add("x", axisX);

        axis.add("y", new JsonObject());

        JsonObject axisZ = new JsonObject();
        axisZ.addProperty("x", 90);
        axis.add("z", axisZ);

        JsonObject axisNone = new JsonObject();
        axisNone.addProperty("model", modIdAndBarkName.getNamespace() + ":" + modIdAndBarkName.getPath());
        axis.add("none", axisNone);

        variants.add("axis", axis);

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

        genBlockPillarItemModel(modIdAndName, endTextureName, sidesTextureName);
    }

    public static void genBlockPillarItemModel(ResourceLocation modIdAndName, ResourceLocation endTextureName, ResourceLocation sidesTextureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "block/cube_column");
        JsonObject textures = new JsonObject();
        textures.addProperty("end", endTextureName.getNamespace() + ":block/" + endTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());
        root.add("textures", textures);
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genStair(String modId, String blockName, String topTexture, String sideTexture, String bottomTexture) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modId + ":block/" + bottomTexture);
        textures.addProperty("side", modId + ":block/" + sideTexture);
        textures.addProperty("top", modId + ":block/" + topTexture);

        defaults.add("textures", textures);
        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject straight = new JsonObject();
        straight.addProperty("model", "minecraft:stairs");
        straight.addProperty("y", 270);
        straight.addProperty("uvlock", true);

        JsonObject innerLeft = new JsonObject();
        innerLeft.addProperty("model", "minecraft:inner_stairs");
        innerLeft.addProperty("y", 270);
        innerLeft.addProperty("uvlock", true);

        JsonObject innerRight = new JsonObject();
        innerRight.addProperty("model", "minecraft:inner_stairs");
        innerRight.addProperty("y", 270);
        innerRight.addProperty("uvlock", true);

        JsonObject outerLeft = new JsonObject();
        innerLeft.addProperty("model", "minecraft:outer_stairs");
        innerLeft.addProperty("y", 270);
        innerLeft.addProperty("uvlock", true);

        JsonObject outerRight = new JsonObject();
        innerRight.addProperty("model", "minecraft:outer_stairs");
        innerRight.addProperty("y", 270);
        innerRight.addProperty("uvlock", true);

        for (EnumFacing facing : EnumFacing.values()) {
            for (BlockStairs.EnumHalf enumHalf : BlockStairs.EnumHalf.values()) {
                for (BlockStairs.EnumShape enumShape : BlockStairs.EnumShape.values()) {
                    if (Objects.equals(enumShape.getName(), "straight")) {
                        variants.add(String.format("facing=%s,half=%s,shape=%s", facing.getName(), enumHalf.getName(), enumShape.getName()), straight);
                    }
                    if (Objects.equals(enumShape.getName(), "inner_left")) {
                        variants.add(String.format("facing=%s,half=%s,shape=%s", facing.getName(), enumHalf.getName(), enumShape.getName()), innerLeft);
                    }
                    if (Objects.equals(enumShape.getName(), "inner_right")) {
                        variants.add(String.format("facing=%s,half=%s,shape=%s", facing.getName(), enumHalf.getName(), enumShape.getName()), innerRight);
                    }
                    if (Objects.equals(enumShape.getName(), "outer_left")) {
                        variants.add(String.format("facing=%s,half=%s,shape=%s", facing.getName(), enumHalf.getName(), enumShape.getName()), outerLeft);
                    }
                    if (Objects.equals(enumShape.getName(), "outer_right")) {
                        variants.add(String.format("facing=%s,half=%s,shape=%s", facing.getName(), enumHalf.getName(), enumShape.getName()), outerRight);
                    }
                }
            }
        }

        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }
        genStairItemModel(modId, blockName, topTexture, sideTexture, bottomTexture);
    }

    public static void genStairItemModel(String modId, String blockName, String topTexture, String sideTexture, String bottomTexture) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "block/stairs");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modId + ":block/" + bottomTexture);
        textures.addProperty("side", modId + ":block/" + sideTexture);
        textures.addProperty("top", modId + ":block/" + topTexture);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }


    public static void genTest(String modId, String blockName, String topTexture, String sideTexture, String bottomTexture) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modId + ":block/" + bottomTexture);
        textures.addProperty("side", modId + ":block/" + sideTexture);
        textures.addProperty("top", modId + ":block/" + topTexture);

        defaults.add("textures", textures);
        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject facing = new JsonObject();
        facing.add("north", new JsonObject());

        JsonObject south = new JsonObject();
        south.addProperty("y", "90");
        facing.add("south", south);

        JsonObject east = new JsonObject();
        east.addProperty("y", "180");
        facing.add("east", east);

        JsonObject west = new JsonObject();
        west.addProperty("y", "270");
        facing.add("west", west);

        variants.add("facing", facing);

        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }
        genStairItemModel(modId, blockName, topTexture, sideTexture, bottomTexture);
    }

    public static void genTestItemModel(String modId, String blockName, String topTexture, String sideTexture, String bottomTexture) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "block/stairs");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modId + ":block/" + bottomTexture);
        textures.addProperty("side", modId + ":block/" + sideTexture);
        textures.addProperty("top", modId + ":block/" + topTexture);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genPressurePlateBlock(String modId, String blockName, String textureName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modId, "blockstates").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + ".json"), "UTF-8");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();
            jw.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw.name("variants");
            jw.beginObject();

            jw.name("powered=false");
            jw.beginObject();
            jw.name("model").value(modId + ":" + blockName + "_up");
            jw.endObject();

            jw.name("powered=true");
            jw.beginObject();
            jw.name("model").value(modId + ":" + blockName + "_down");
            jw.endObject();

            jw.endObject();
            jw.endObject();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        genBlockPressurePlateModel(modId, blockName, textureName);
        genBlockPressurePlateItemModel(modId, blockName);

    }

    public static void genBlockPressurePlateModel(String modId, String blockName, String textureName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modId, "models", "block").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + "_up" + ".json"), "UTF-8");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();
            jw.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw.name("parent").value("block/pressure_plate_up");
            jw.name("textures");
            jw.beginObject();
            jw.name("texture").value(modId + ":block/" + textureName);
            jw.endObject();
            jw.endObject();

            writer.close();

            Writer writer2 = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + "_down" + ".json"), StandardCharsets.UTF_8);
            JsonWriter jw2 = gson.newJsonWriter(writer2);

            jw2.beginObject();
            jw2.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw2.name("parent").value("block/pressure_plate_down");
            jw2.name("textures");
            jw2.beginObject();
            jw2.name("texture").value(modId + ":block/" + textureName);
            jw2.endObject();
            jw2.endObject();

            writer2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void genBlockPressurePlateItemModel(String modId, String blockName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modId, "models", "item").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + ".json"), "UTF-8");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();

            jw.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw.name("parent").value(modId + ":block/" + blockName + "_up");

            jw.endObject();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void genSlab(ResourceLocation modIDAndName, ResourceLocation topTextureLocation, ResourceLocation sideTextureLocation, ResourceLocation bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject variants = new JsonObject();

        JsonObject half = new JsonObject();

        JsonObject upper = new JsonObject();
        upper.addProperty("model", modIDAndName.getNamespace() + ":upper_" + modIDAndName.getPath());
        half.add("top", upper);

        JsonObject lower = new JsonObject();
        lower.addProperty("model", modIDAndName.getNamespace() + ":half_" + modIDAndName.getPath());
        half.add("bottom", lower);

        variants.add("half", half);

        root.add("variants", variants);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("forge_marker", 1);

        JsonObject variants2 = new JsonObject();

        JsonObject prop = new JsonObject();

        JsonObject blarg = new JsonObject();
        blarg.addProperty("model", "cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", sideTextureLocation.toString());

        blarg.add("textures", textures);

        prop.add("blarg", blarg);

        variants2.add("prop", prop);

        root2.add("variants", variants2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + "_double.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIDAndName.getPath()));
        }

        genSlabBlockModel(modIDAndName, topTextureLocation, sideTextureLocation, bottomTextureLocation);
        genSlabItemModel(modIDAndName.getNamespace(), modIDAndName.getPath());

    }

    public static void genSlabColored(ResourceLocation modIDAndName, ResourceLocation topTextureLocation, ResourceLocation sideTextureLocation, ResourceLocation bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject variants = new JsonObject();

        JsonObject half = new JsonObject();

        JsonObject upper = new JsonObject();
        upper.addProperty("model", modIDAndName.getNamespace() + ":upper_" + modIDAndName.getPath());
        half.add("top", upper);

        JsonObject lower = new JsonObject();
        lower.addProperty("model", modIDAndName.getNamespace() + ":half_" + modIDAndName.getPath());
        half.add("bottom", lower);

        variants.add("half", half);

        root.add("variants", variants);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("forge_marker", 1);

        JsonObject variants2 = new JsonObject();

        JsonObject prop = new JsonObject();

        JsonObject blarg = new JsonObject();
        blarg.addProperty("model", "neutronia:cube_all_colored");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", sideTextureLocation.toString());

        blarg.add("textures", textures);

        prop.add("blarg", blarg);

        variants2.add("prop", prop);

        root2.add("variants", variants2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + "_double.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIDAndName.getPath()));
        }

        genSlabColoredBlockModel(modIDAndName, topTextureLocation, sideTextureLocation, bottomTextureLocation);
        genSlabItemModel(modIDAndName.getNamespace(), modIDAndName.getPath());

    }

    public static void genSlabBlockModel(ResourceLocation modIDAndName, ResourceLocation topTextureLocation, ResourceLocation sideTextureLocation, ResourceLocation bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "minecraft:block/half_slab");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", bottomTextureLocation.toString());
        textures.addProperty("side", sideTextureLocation.toString());
        textures.addProperty("top", topTextureLocation.toString());
        root.add("textures", textures);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("parent", "minecraft:block/upper_slab");

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("bottom", bottomTextureLocation.toString());
        textures2.addProperty("side", sideTextureLocation.toString());
        textures2.addProperty("top", topTextureLocation.toString());
        root2.add("textures", textures2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve("half_" + modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve("upper_" + modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIDAndName.getPath()));
        }

    }

    public static void genSlabColoredBlockModel(ResourceLocation modIDAndName, ResourceLocation topTextureLocation, ResourceLocation sideTextureLocation, ResourceLocation bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "neutronia:block/slab");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", bottomTextureLocation.toString());
        textures.addProperty("side", sideTextureLocation.toString());
        textures.addProperty("top", topTextureLocation.toString());
        root.add("textures", textures);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("parent", "neutronia:block/slab_top");

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("bottom", bottomTextureLocation.toString());
        textures2.addProperty("side", sideTextureLocation.toString());
        textures2.addProperty("top", topTextureLocation.toString());
        root2.add("textures", textures2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve("half_" + modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve("upper_" + modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIDAndName.getPath()));
        }

    }

    public static void genSlabItemModel(String modId, String blockName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", modId + ":block/" + "half_" + blockName);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genLayeredSlab(String modId, String blockName, ResourceLocation mainTexture, ResourceLocation overlayTexture) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject variants = new JsonObject();

        JsonObject half = new JsonObject();

        JsonObject upper = new JsonObject();
        upper.addProperty("model", modId + ":upper_" + blockName);
        half.add("top", upper);

        JsonObject lower = new JsonObject();
        lower.addProperty("model", modId + ":half_" + blockName);
        half.add("bottom", lower);

        variants.add("half", half);

        root.add("variants", variants);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("forge_marker", 1);

        JsonObject variants2 = new JsonObject();

        JsonObject prop = new JsonObject();

        JsonObject blarg = new JsonObject();
        blarg.addProperty("model", modId + ":upper_" + blockName);

        prop.add("blarg", blarg);

        variants2.add("prop", prop);

        root2.add("variants", variants2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(blockName + "_double.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

        genLayeredSlabModel(modId, blockName, mainTexture, overlayTexture);
        genLayeredSlabItemModel(modId, blockName);

    }

    public static void genLayeredSlabModel(String modId, String blockName, ResourceLocation mainTexture, ResourceLocation overlayTexture) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", new ResourceLocation("neutronia", "block/cube_bottom_half_overlay_all").toString());

        JsonObject textures = new JsonObject();
        textures.addProperty("all", mainTexture.toString());
        textures.addProperty("overlay", overlayTexture.toString());
        root.add("textures", textures);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("parent", new ResourceLocation("neutronia", "block/cube_top_half_overlay_all").toString());

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("all", mainTexture.toString());
        textures2.addProperty("overlay", overlayTexture.toString());
        root2.add("textures", textures2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve("half_" + blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve("upper_" + blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genLayeredSlabItemModel(String modId, String blockName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", modId + ":block/" + "half_" + blockName);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genFenceBlock(ResourceLocation modIdAndName, ResourceLocation textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");

        JsonArray multipart = new JsonArray();

        JsonObject pole = new JsonObject();

        JsonObject applyPost = new JsonObject();
        applyPost.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_post");
        pole.add("apply", applyPost);

        multipart.add(pole);

        JsonObject sideNorth = new JsonObject();

        JsonObject whenNorth = new JsonObject();
        whenNorth.addProperty("north", true);
        sideNorth.add("when", whenNorth);

        JsonObject applyNorth = new JsonObject();
        applyNorth.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applyNorth.addProperty("uvlock", true);
        sideNorth.add("apply", applyNorth);

        multipart.add(sideNorth);

        JsonObject sideEast = new JsonObject();

        JsonObject whenEast = new JsonObject();
        whenEast.addProperty("north", true);
        sideEast.add("when", whenEast);

        JsonObject applyEast = new JsonObject();
        applyEast.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applyEast.addProperty("uvlock", true);
        applyEast.addProperty("y", 90);
        sideEast.add("apply", applyEast);

        multipart.add(sideEast);

        JsonObject sideSouth = new JsonObject();

        JsonObject whenSouth = new JsonObject();
        whenSouth.addProperty("north", true);
        sideSouth.add("when", whenSouth);

        JsonObject applySouth = new JsonObject();
        applySouth.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applySouth.addProperty("uvlock", true);
        applySouth.addProperty("y", 180);
        sideSouth.add("apply", applySouth);

        multipart.add(sideSouth);

        JsonObject sideWest = new JsonObject();

        JsonObject whenWest = new JsonObject();
        whenWest.addProperty("west", true);
        sideWest.add("when", whenWest);

        JsonObject applyWest = new JsonObject();
        applyWest.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applyWest.addProperty("uvlock", true);
        applyWest.addProperty("y", 270);
        sideWest.add("apply", applyWest);

        multipart.add(sideWest);

        root.add("multipart", multipart);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

        genBlockFenceModel(modIdAndName, textureName);
        genBlockFenceItemModel(modIdAndName);

    }

    public static void genBlockFenceModel(ResourceLocation modIdAndName, ResourceLocation textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        {
            JsonObject root = new JsonObject();
            root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
            root.addProperty("parent", "block/fence_post");

            JsonObject textures = new JsonObject();
            textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());
            root.add("textures", textures);

            String json = gson.toJson(root);

            try {
                FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_post.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath() + "_post"));
            }
        }
        {
            JsonObject root = new JsonObject();
            root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
            root.addProperty("parent", "block/fence_side");

            JsonObject textures = new JsonObject();
            textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());
            root.add("textures", textures);

            String json = gson.toJson(root);

            try {
                FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_side.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath() + "_side"));
            }
        }
        {
            JsonObject root = new JsonObject();
            root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
            root.addProperty("parent", "block/fence_inventory");

            JsonObject textures = new JsonObject();
            textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());
            root.add("textures", textures);

            String json = gson.toJson(root);

            try {
                FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_inventory.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath() + "_inventory"));
            }
        }

    }

    public static void genBlockFenceItemModel(ResourceLocation modIdAndName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + modIdAndName.getPath() + ".json"), "UTF-8");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();

            jw.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw.name("parent").value(modIdAndName.getNamespace() + ":block/" + modIdAndName.getPath() + "_inventory");

            jw.endObject();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void genModInfo(String modId, String modName, String version, String gameVersion, String[] authors, String[] screenshots, String url, String description, String credits, String parentMod, String logoFile) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonArray root = new JsonArray();

        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        if (!modId.equalsIgnoreCase(" ") || modId != null)
            rootObject.addProperty("mod_id", modId);
        if (!modName.equalsIgnoreCase(" ") || modName != null)
            rootObject.addProperty("name", modName);
        if (!description.equalsIgnoreCase(" ") || description != null)
            rootObject.addProperty("description", description);
        if (!version.equalsIgnoreCase(" ") || version != null)
            rootObject.addProperty("version", version);
        if (!credits.equalsIgnoreCase(" ") || credits != null)
            rootObject.addProperty("credits", credits);
        if (!gameVersion.equalsIgnoreCase(" ") || gameVersion != null)
            rootObject.addProperty("mcversion", gameVersion);
        if (!url.equalsIgnoreCase(" ") || url != null)
            rootObject.addProperty("url", url);

        JsonArray authorList = new JsonArray();
        for (int i = 0; i <= authors.length - 1; i++) {
            authorList.add(authors[i]);
        }
        rootObject.add("authorList", authorList);
        if (!parentMod.equalsIgnoreCase(" ") || parentMod != null)
            rootObject.addProperty("parent", parentMod);
        if (!logoFile.equalsIgnoreCase(" ") || logoFile != null)
            rootObject.addProperty("logoFile", logoFile);

        JsonArray screenshotList = new JsonArray();
        for (int i = 0; i <= screenshots.length - 1; i++) {
            screenshotList.add(screenshots[i]);
        }
        rootObject.add("screenshots", screenshotList);
        root.add(rootObject);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve("moon.info").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print("Error creating file mcmod file" + "\n");
        }
    }

    public static void genRecipe(String modId, String name, boolean isShaped, String row1, String row2, String row3, String[] keys, String[] values, int[] data, String result, String craftingGroup, int resultCount) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modId, "recipes");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        if (isShaped) {
            root.addProperty("type", "forge:ore_shaped");
            if (!craftingGroup.equalsIgnoreCase("")) root.addProperty("group", craftingGroup);
            JsonArray pattern = new JsonArray();
            if (!row1.equalsIgnoreCase(" ")) pattern.add(row1);
            if (!row2.equalsIgnoreCase(" ")) pattern.add(row2);
            if (!row3.equalsIgnoreCase(" ")) pattern.add(row3);
            root.add("pattern", pattern);
            JsonObject key = new JsonObject();
            for (int i = 0; i <= keys.length - 1; i++) {
                if (!values[i].equalsIgnoreCase("")) {
                    JsonObject item = new JsonObject();
                    item.addProperty("item", values[i]);
                    key.add(keys[i], item);
                    if (data != null)
                        if (data[i] != 0) item.addProperty("data", data[i]);
                }
            }
            root.add("key", key);
            JsonObject resultName = new JsonObject();
            resultName.addProperty("item", result);
            if (resultCount > 1) resultName.addProperty("count", resultCount);
            root.add("result", resultName);
            String json = gson.toJson(root);
            try {
                FileUtils.writeStringToFile(base.resolve(name + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.print(String.format("Error creating recipe file %s.json" + "\n", name));
            }
        } else {
            root.addProperty("type", "forge:ore_shapeless");
            if (!craftingGroup.equalsIgnoreCase("")) root.addProperty("group", craftingGroup);
            JsonArray ingredients = new JsonArray();
            for (int i2 = 0; i2 <= values.length - 1; i2++) {
                if (!values[i2].equalsIgnoreCase("")) {
                    JsonObject item = new JsonObject();
                    item.addProperty("item", values[i2]);
                    ingredients.add(item);
                    if (data != null) item.addProperty("data", data[i2]);
                }
            }
            root.add("ingredients", ingredients);
            JsonObject resultName = new JsonObject();
            resultName.addProperty("item", result);
            if (resultCount > 1) resultName.addProperty("count", resultCount);
            root.add("result", resultName);
            String json = gson.toJson(root);
            try {
                FileUtils.writeStringToFile(base.resolve(name + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.print(String.format("Error creating recipe file %s.json" + "\n", name));
            }
        }
    }

    public static void genItemModel(String modId, String itemName, String textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modId + ":items/" + textureName);
        root.add("textures", textures);
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(itemName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", itemName));
        }
    }

    public static void genToolModel(String modId, String itemName, String textureName, String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/handheld");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modId + ":items/" + textureName);
        root.add("textures", textures);
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(itemName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", itemName));
        }
    }

    private static void genLangFile(String modid, String block_name, String unlocalized_name, String lang_file_name) {
        Path base = Paths.get("src", "main", "resources", "assets", modid, "lang");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        try (BufferedWriter w = Files.newBufferedWriter(base.resolve(String.format("%s.lang", lang_file_name)), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            String name = unlocalized_name.replace("_", " ");
            unlocalized_name = WordUtils.capitalizeFully(name);
            w.write("tile." + modid + ":" + block_name + ".name=" + unlocalized_name + "\n");
        } catch (IOException ignored) {
            System.out.print(String.format("Error creating file %s.json" + "\n", lang_file_name));
        }
    }

    public static void genAdvancementRootJson(String modId, String advancement_name, String item_name, String title, String desc, String background_texture_name, boolean show_toast, boolean announce_to_chat, boolean hidden) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "advancements");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");

        JsonObject display = new JsonObject();

        JsonObject icon = new JsonObject();
        icon.addProperty("item", modId + ":" + item_name);
        display.add("icon", icon);

        JsonObject titleObject = new JsonObject();
        titleObject.addProperty("translate", modId + ".advancements." + title);
        display.add("title", titleObject);

        JsonObject descObject = new JsonObject();
        descObject.addProperty("translate", modId + ".advancements." + desc + ".desc");
        display.add("description", descObject);

        display.addProperty("background", modId + ":textures/advancements/" + background_texture_name + ".png");
        display.addProperty("show_toast", show_toast);
        display.addProperty("announce_to_chat", announce_to_chat);
        display.addProperty("hidden", hidden);

        JsonObject criteria = new JsonObject();

        root.add("display", display);
        root.add("criteria", criteria);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve("root.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", advancement_name));
        }

    }

    public static void genAdvancementJson(String modId, String advancement_name, String item_name, String title, String desc, String background_texture_name, boolean show_toast, boolean announce_to_chat, boolean hidden) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "advancements");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");

        JsonObject display = new JsonObject();

        JsonObject icon = new JsonObject();
        icon.addProperty("item", modId + ":" + item_name);
        display.add("icon", icon);

        JsonObject titleObject = new JsonObject();
        titleObject.addProperty("translate", modId + ".advancements." + title);
        display.add("title", titleObject);

        JsonObject descObject = new JsonObject();
        descObject.addProperty("translate", modId + ".advancements." + desc + ".desc");
        display.add("description", descObject);

        display.addProperty("background", modId + ":textures/advancements/" + background_texture_name + ".png");
        display.addProperty("show_toast", show_toast);
        display.addProperty("announce_to_chat", announce_to_chat);
        display.addProperty("hidden", hidden);

        JsonObject criteria = new JsonObject();

        root.add("display", display);
        root.addProperty("parent", modId + ":" + "root");
        root.add("criteria", criteria);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve("root.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", advancement_name));
        }

    }

    private static void stringToFile(String text, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName + ".txt", true);
            writer.write(text + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String text, String targetFilePath) throws IOException {
        Path targetPath = Paths.get(targetFilePath);
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        Files.write(targetPath, bytes, StandardOpenOption.CREATE);
    }

}
