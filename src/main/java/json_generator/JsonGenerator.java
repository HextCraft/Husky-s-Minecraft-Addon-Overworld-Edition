package json_generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.EnumDyeColor;
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

public class JsonGenerator {

    private static String modid = "neutronia";

    public static void main(String[] args) {
        /*for (EnumCoralColor coralColor : EnumCoralColor.values()) {
            genBlock(new ResourceLocation(modid, String.format("%s_coral_block", coralColor.getName())), new ResourceLocation(modid, String.format("%s_coral_block", coralColor.getName())));
            genBlock(new ResourceLocation(modid, String.format("dead_%s_coral_block", coralColor.getName())), new ResourceLocation(modid, String.format("dead_%s_coral_block", coralColor.getName())));
            genCoralPlant(new ResourceLocation(modid, String.format("%s_coral", coralColor.getName())), new ResourceLocation(modid, String.format("%s_coral", coralColor.getName())));
            genCoralPlant(new ResourceLocation(modid, String.format("dead_%s_coral", coralColor.getName())), new ResourceLocation(modid, String.format("dead_%s_coral", coralColor.getName())));
            genCoralFan(new ResourceLocation(modid, String.format("%s_coral_fan", coralColor.getName())), new ResourceLocation(modid, String.format("%s_coral_fan", coralColor.getName())));
            genCoralFan(new ResourceLocation(modid, String.format("dead_%s_coral_fan", coralColor.getName())), new ResourceLocation(modid, String.format("dead_%s_coral_fan", coralColor.getName())));

            genBlock(new ResourceLocation(modid, String.format("decorative_%s_coral_block", coralColor.getName())), new ResourceLocation(modid, String.format("%s_coral_block", coralColor.getName())));
            genBlock(new ResourceLocation(modid, String.format("decorative_dead_%s_coral_block", coralColor.getName())), new ResourceLocation(modid, String.format("dead_%s_coral_block", coralColor.getName())));
            genCoralPlant(new ResourceLocation(modid, String.format("decorative_%s_coral", coralColor.getName())), new ResourceLocation(modid, String.format("%s_coral", coralColor.getName())));
            genCoralPlant(new ResourceLocation(modid, String.format("decorative_dead_%s_coral", coralColor.getName())), new ResourceLocation(modid, String.format("dead_%s_coral", coralColor.getName())));
            genCoralFan(new ResourceLocation(modid, String.format("decorative_%s_coral_fan", coralColor.getName())), new ResourceLocation(modid, String.format("%s_coral_fan", coralColor.getName())));
            genCoralFan(new ResourceLocation(modid, String.format("decorative_dead_%s_coral_fan", coralColor.getName())), new ResourceLocation(modid, String.format("dead_%s_coral_fan", coralColor.getName())));
        }*/
        for(EnumDyeColor color : EnumDyeColor.values()) {

        }
        for(BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
//            genSlab(new ResourceLocation(modid, String.format("")));
        }
        genSlab(new ResourceLocation("test", "test"), new ResourceLocation("test", "test"), new ResourceLocation("test", "test"), new ResourceLocation("test", "test"));
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
        defaults.addProperty("model", "neutronia:coral_fan");

        JsonObject textures = new JsonObject();
        textures.addProperty("fan", textureName.getNamespace() + ":block/" + textureName.getPath());
        defaults.add("textures", textures);

        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();
        variants.add("normal", new JsonObject());
        variants.add("inventory", new JsonObject());
        root.add("variants", variants);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults2 = new JsonObject();
        defaults2.addProperty("transform", "forge:default-block");
        defaults2.addProperty("model", "neutronia:coral_wall_fan");

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("fan", textureName.getNamespace() + ":block/" + textureName.getPath());
        defaults2.add("textures", textures2);

        root.add("defaults", defaults2);

        JsonObject variants2 = new JsonObject();
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

        variants2.add("facing", facing);

        variants2.add("inventory", new JsonObject());
        root2.add("variants", variants2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
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

    public static void genCoralFanWallModel(ResourceLocation modIdAndName, ResourceLocation textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "neutronia:block/coral_wall_fan");

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
        defaults.addProperty("model", modIdAndName.toString());
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

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("inventory", empty);
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
        root.addProperty("parent", "neutronia:block/cross");

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

    public static void genPillarBlock(ResourceLocation modIdAndName, ResourceLocation endTextureName, ResourceLocation sidesTextureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", "cube_column");

        JsonObject textures = new JsonObject();
        textures.addProperty("end", endTextureName.toString());
        textures.addProperty("side", sidesTextureName.toString());
        defaults.add("textures", textures);

        defaults.addProperty("transform", "forge:default-block");

        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject axis = new JsonObject();

        JsonObject axisX = new JsonObject();
        axisX.addProperty("x", 90);
        axisX.addProperty("y", 90);
        axis.add("x", axisX);

        JsonObject axisY = new JsonObject();
        axis.add("y", axisY);

        JsonObject axisZ = new JsonObject();
        axisZ.addProperty("x", 90);
        axis.add("z", axisZ);

        JsonObject axisNone = new JsonObject();
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
    }

    public static void genStair(ResourceLocation modIdAndName, ResourceLocation topTexture, ResourceLocation sideTexture, ResourceLocation bottomTexture) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        String stairModel = "minecraft:stairs";

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", bottomTexture.toString());
        textures.addProperty("side", sideTexture.toString());
        textures.addProperty("top", topTexture.toString());

        defaults.add("textures", textures);
        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject normal = new JsonObject();
        normal.addProperty("model", stairModel);
        variants.add("normal", normal);

        JsonObject inventory = new JsonObject();
        inventory.addProperty("model", stairModel);
        variants.add("inventory", inventory);

        JsonObject straight1 = new JsonObject();
        straight1.addProperty("model", stairModel);
        variants.add("facing=east,half=bottom,shape=straight", straight1);

        JsonObject straight2 = new JsonObject();
        straight2.addProperty("model", stairModel);
        straight2.addProperty("y", 180);
        straight2.addProperty("uvlock", true);
        variants.add("facing=west,half=bottom,shape=straight", straight2);

        JsonObject straight3 = new JsonObject();
        straight3.addProperty("model", stairModel);
        straight3.addProperty("y", 90);
        straight3.addProperty("uvlock", true);
        variants.add("facing=south,half=bottom,shape=straight", straight3);

        JsonObject straight4 = new JsonObject();
        straight4.addProperty("model", stairModel);
        straight4.addProperty("y", 270);
        straight4.addProperty("uvlock", true);
        variants.add("facing=north,half=bottom,shape=straight", straight4);

        //sss

        JsonObject outerRight1 = new JsonObject();
        outerRight1.addProperty("model", stairModel);
        variants.add("facing=east,half=bottom,shape=outer_right", outerRight1);

        JsonObject outerRight2 = new JsonObject();
        outerRight2.addProperty("model", stairModel);
        outerRight2.addProperty("y", 180);
        outerRight2.addProperty("uvlock", true);
        variants.add("facing=west,half=bottom,shape=outer_right", outerRight2);

        JsonObject outerRight3 = new JsonObject();
        outerRight3.addProperty("model", stairModel);
        outerRight3.addProperty("y", 90);
        outerRight3.addProperty("uvlock", true);
        variants.add("facing=south,half=bottom,shape=outer_right", outerRight3);

        JsonObject outerRight4 = new JsonObject();
        outerRight4.addProperty("model", stairModel);
        outerRight4.addProperty("y", 270);
        outerRight4.addProperty("uvlock", true);
        variants.add("facing=north,half=bottom,shape=outer_right", outerRight4);

        //sss

        JsonObject outerLeft1 = new JsonObject();
        outerLeft1.addProperty("model", stairModel);
        variants.add("facing=east,half=bottom,shape=outer_left", outerLeft1);

        JsonObject outerLeft2 = new JsonObject();
        outerLeft2.addProperty("model", stairModel);
        outerLeft2.addProperty("y", 180);
        outerLeft2.addProperty("uvlock", true);
        variants.add("facing=west,half=bottom,shape=outer_left", outerLeft2);

        JsonObject outerLeft3 = new JsonObject();
        outerLeft3.addProperty("model", stairModel);
        outerLeft3.addProperty("y", 90);
        outerLeft3.addProperty("uvlock", true);
        variants.add("facing=south,half=bottom,shape=outer_left", outerLeft3);

        JsonObject outerLeft4 = new JsonObject();
        outerLeft4.addProperty("model", stairModel);
        outerLeft4.addProperty("y", 270);
        outerLeft4.addProperty("uvlock", true);
        variants.add("facing=north,half=bottom,shape=outer_left", outerLeft4);

        //sss

        JsonObject innerRight1 = new JsonObject();
        innerRight1.addProperty("model", stairModel);
        variants.add("facing=east,half=bottom,shape=inner_right", innerRight1);

        JsonObject innerRight2 = new JsonObject();
        innerRight2.addProperty("model", stairModel);
        innerRight2.addProperty("y", 180);
        innerRight2.addProperty("uvlock", true);
        variants.add("facing=west,half=bottom,shape=inner_right", innerRight2);

        JsonObject innerRight3 = new JsonObject();
        innerRight3.addProperty("model", stairModel);
        innerRight3.addProperty("y", 90);
        innerRight3.addProperty("uvlock", true);
        variants.add("facing=south,half=bottom,shape=inner_right", innerRight3);

        JsonObject innerRight4 = new JsonObject();
        innerRight4.addProperty("model", stairModel);
        innerRight4.addProperty("y", 270);
        innerRight4.addProperty("uvlock", true);
        variants.add("facing=north,half=bottom,shape=inner_right", innerRight4);

        //sss

        JsonObject innerLeft1 = new JsonObject();
        innerLeft1.addProperty("model", stairModel);
        variants.add("facing=east,half=bottom,shape=inner_left", innerLeft1);

        JsonObject innerLeft2 = new JsonObject();
        innerLeft2.addProperty("model", stairModel);
        innerLeft2.addProperty("y", 180);
        innerLeft2.addProperty("uvlock", true);
        variants.add("facing=west,half=bottom,shape=inner_left", innerLeft2);

        JsonObject innerLeft3 = new JsonObject();
        innerLeft3.addProperty("model", stairModel);
        innerLeft3.addProperty("y", 90);
        innerLeft3.addProperty("uvlock", true);
        variants.add("facing=south,half=bottom,shape=inner_left", innerLeft3);

        JsonObject innerLeft4 = new JsonObject();
        innerLeft4.addProperty("model", stairModel);
        innerLeft4.addProperty("y", 270);
        innerLeft4.addProperty("uvlock", true);
        variants.add("facing=north,half=bottom,shape=inner_left", innerLeft4);

        //kkk

        JsonObject straightTop1 = new JsonObject();
        straightTop1.addProperty("model", stairModel);
        variants.add("facing=east,half=top,shape=straight", straightTop1);

        JsonObject straightTop2 = new JsonObject();
        straightTop2.addProperty("model", stairModel);
        straightTop2.addProperty("y", 180);
        straightTop2.addProperty("uvlock", true);
        variants.add("facing=west,half=top,shape=straight", straightTop2);

        JsonObject straightTop3 = new JsonObject();
        straightTop3.addProperty("model", stairModel);
        straightTop3.addProperty("y", 90);
        straightTop3.addProperty("uvlock", true);
        variants.add("facing=south,half=top,shape=straight", straightTop3);

        JsonObject straightTop4 = new JsonObject();
        straightTop4.addProperty("model", stairModel);
        straightTop4.addProperty("y", 270);
        straightTop4.addProperty("uvlock", true);
        variants.add("facing=north,half=top,shape=straight", straightTop4);

        //sss

        JsonObject outerRightTop1 = new JsonObject();
        outerRightTop1.addProperty("model", stairModel);
        variants.add("facing=east,half=top,shape=outer_right", outerRightTop1);

        JsonObject outerRightTop2 = new JsonObject();
        outerRightTop2.addProperty("model", stairModel);
        outerRightTop2.addProperty("y", 180);
        outerRightTop2.addProperty("uvlock", true);
        variants.add("facing=west,half=top,shape=outer_right", outerRightTop2);

        JsonObject outerRightTop3 = new JsonObject();
        outerRightTop3.addProperty("model", stairModel);
        outerRightTop3.addProperty("y", 90);
        outerRightTop3.addProperty("uvlock", true);
        variants.add("facing=south,half=top,shape=outer_right", outerRightTop3);

        JsonObject outerRightTop4 = new JsonObject();
        outerRightTop4.addProperty("model", stairModel);
        outerRightTop4.addProperty("y", 270);
        outerRightTop4.addProperty("uvlock", true);
        variants.add("facing=north,half=top,shape=outer_right", outerRightTop4);

        //sss

        JsonObject outerLeftTop1 = new JsonObject();
        outerLeftTop1.addProperty("model", stairModel);
        variants.add("facing=east,half=top,shape=outer_left", outerLeftTop1);

        JsonObject outerLeftTop2 = new JsonObject();
        outerLeftTop2.addProperty("model", stairModel);
        outerLeftTop2.addProperty("y", 180);
        outerLeftTop2.addProperty("uvlock", true);
        variants.add("facing=west,half=top,shape=outer_left", outerLeftTop2);

        JsonObject outerLeftTop3 = new JsonObject();
        outerLeftTop3.addProperty("model", stairModel);
        outerLeftTop3.addProperty("y", 90);
        outerLeftTop3.addProperty("uvlock", true);
        variants.add("facing=south,half=top,shape=outer_left", outerLeftTop3);

        JsonObject outerLeftTop4 = new JsonObject();
        outerLeftTop4.addProperty("model", stairModel);
        outerLeftTop4.addProperty("y", 270);
        outerLeftTop4.addProperty("uvlock", true);
        variants.add("facing=north,half=top,shape=outer_left", outerLeftTop4);

        //sss

        JsonObject innerRightTop1 = new JsonObject();
        innerRightTop1.addProperty("model", stairModel);
        variants.add("facing=east,half=top,shape=inner_right", innerRightTop1);

        JsonObject innerRightTop2 = new JsonObject();
        innerRightTop2.addProperty("model", stairModel);
        innerRightTop2.addProperty("y", 180);
        innerRightTop2.addProperty("uvlock", true);
        variants.add("facing=west,half=top,shape=inner_right", innerRightTop2);

        JsonObject innerRightTop3 = new JsonObject();
        innerRightTop3.addProperty("model", stairModel);
        innerRightTop3.addProperty("y", 90);
        innerRightTop3.addProperty("uvlock", true);
        variants.add("facing=south,half=top,shape=inner_right", innerRightTop3);

        JsonObject innerRightTop4 = new JsonObject();
        innerRightTop4.addProperty("model", stairModel);
        innerRightTop4.addProperty("y", 270);
        innerRightTop4.addProperty("uvlock", true);
        variants.add("facing=north,half=top,shape=inner_right", innerRightTop4);

        //sss

        JsonObject innerLeftTop1 = new JsonObject();
        innerLeftTop1.addProperty("model", stairModel);
        variants.add("facing=east,half=top,shape=inner_left", innerLeftTop1);

        JsonObject innerLeftTop2 = new JsonObject();
        innerLeftTop2.addProperty("model", stairModel);
        innerLeftTop2.addProperty("y", 180);
        innerLeftTop2.addProperty("uvlock", true);
        variants.add("facing=west,half=top,shape=inner_left", innerLeftTop2);

        JsonObject innerLeftTop3 = new JsonObject();
        innerLeftTop3.addProperty("model", stairModel);
        innerLeftTop3.addProperty("y", 90);
        innerLeftTop3.addProperty("uvlock", true);
        variants.add("facing=south,half=top,shape=inner_left", innerLeftTop3);

        JsonObject innerLeftTop4 = new JsonObject();
        innerLeftTop4.addProperty("model", stairModel);
        innerLeftTop4.addProperty("y", 270);
        innerLeftTop4.addProperty("uvlock", true);
        variants.add("facing=north,half=top,shape=inner_left", innerLeftTop4);

        root.add("variants", variants);

        StringBuilder builder = new StringBuilder();
        builder.append(gson.toJson(root));

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(builder.toString()), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }
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

        JsonObject defaults = new JsonObject();

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", bottomTextureLocation.toString());
        textures.addProperty("top", topTextureLocation.toString());
        textures.addProperty("side", sideTextureLocation.toString());
        defaults.add("textures", textures);
        defaults.addProperty("transform", "forge:default-block");

        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject inventory = new JsonObject();
        inventory.addProperty("model", "minecraft:half_slab");
        variants.add("inventory", inventory);

        JsonObject bottom = new JsonObject();
        bottom.addProperty("model", "minecraft:half_slab");
        variants.add("half=bottom,variant=normal", bottom);

        JsonObject top = new JsonObject();
        top.addProperty("model", "minecraft:upper_slab");
        variants.add("half=top,variant=normal", top);

        root.add("variants", variants);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("forge_marker", 1);

        JsonObject defaults2 = new JsonObject();
        defaults2.addProperty("model", "cube_column");

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("end", bottomTextureLocation.toString());
        textures2.addProperty("side", sideTextureLocation.toString());
        defaults2.add("textures", textures2);
        defaults2.addProperty("transform", "forge:default-block");

        root2.add("defaults", defaults2);

        JsonObject variants2 = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants2.add("variant=normal", empty);

        root2.add("variants", variants2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + "_double.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIDAndName.getPath()));
        }
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
