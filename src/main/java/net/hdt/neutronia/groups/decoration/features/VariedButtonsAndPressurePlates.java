package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.handler.server.WoodVariantReplacer;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomButton;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomPressurePlate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

public class VariedButtonsAndPressurePlates extends Component {

    public static Block spruce_pressure_plate, spruce_button;
    public static Block birch_pressure_plate, birch_button;
    public static Block jungle_pressure_plate, jungle_button;
    public static Block acacia_pressure_plate, acacia_button;
    public static Block dark_oak_pressure_plate, dark_oak_button;
    public static Block andesite_pressure_plate, andesite_button;
    public static Block granite_pressure_plate, granite_button;
    public static Block diorite_pressure_plate, diorite_button;
    public static Block polished_andesite_pressure_plate, polished_andesite_button;
    public static Block polished_granite_pressure_plate, polished_granite_button;
    public static Block polished_diorite_pressure_plate, polished_diorite_button;

    boolean renameVanillaBlocks;
    boolean enablePressurePlates, enableButtons;

    @Override
    public void setupConfig() {
        renameVanillaBlocks = loadPropBool("Prefix vanilla blocks with Oak", "", true);
        enablePressurePlates = loadPropBool("Enable Pressure Plates", "", true);
        enableButtons = loadPropBool("Enable Buttons", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (enablePressurePlates) {
            spruce_pressure_plate = new BlockCustomPressurePlate("spruce");
            birch_pressure_plate = new BlockCustomPressurePlate("birch");
            jungle_pressure_plate = new BlockCustomPressurePlate("jungle");
            acacia_pressure_plate = new BlockCustomPressurePlate("acacia");
            dark_oak_pressure_plate = new BlockCustomPressurePlate("dark_oak");

            andesite_pressure_plate = new BlockCustomPressurePlate("andesite", Material.ROCK, SoundType.STONE, BlockPressurePlate.Sensitivity.MOBS);
            granite_pressure_plate = new BlockCustomPressurePlate("granite", Material.ROCK, SoundType.STONE, BlockPressurePlate.Sensitivity.MOBS);
            diorite_pressure_plate = new BlockCustomPressurePlate("diorite", Material.ROCK, SoundType.STONE, BlockPressurePlate.Sensitivity.MOBS);
            polished_andesite_pressure_plate = new BlockCustomPressurePlate("polished_andesite", Material.ROCK, SoundType.STONE, BlockPressurePlate.Sensitivity.MOBS);
            polished_granite_pressure_plate = new BlockCustomPressurePlate("polished_granite", Material.ROCK, SoundType.STONE, BlockPressurePlate.Sensitivity.MOBS);
            polished_diorite_pressure_plate = new BlockCustomPressurePlate("polished_diorite", Material.ROCK, SoundType.STONE, BlockPressurePlate.Sensitivity.MOBS);
        }

        if (enableButtons) {
            spruce_button = new BlockCustomButton("spruce");
            birch_button = new BlockCustomButton("birch");
            jungle_button = new BlockCustomButton("jungle");
            acacia_button = new BlockCustomButton("acacia");
            dark_oak_button = new BlockCustomButton("dark_oak");

            andesite_button = new BlockCustomButton("andesite", false);
            granite_button = new BlockCustomButton("granite", false);
            diorite_button = new BlockCustomButton("diorite", false);
            polished_andesite_button = new BlockCustomButton("polished_andesite", false);
            polished_granite_button = new BlockCustomButton("polished_granite", false);
            polished_diorite_button = new BlockCustomButton("polished_diorite", false);
        }
    }

    @Override
    public void postPreInit(FMLPreInitializationEvent event) {
        if (enablePressurePlates) {
            WoodVariantReplacer.addReplacements(1, Blocks.WOODEN_PRESSURE_PLATE);

            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(spruce_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(birch_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 2));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(jungle_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 3));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(acacia_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 4));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(dark_oak_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 5));

            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(andesite_pressure_plate, 1),
                    "SS", 'S', ProxyRegistry.newStack(Blocks.STONE, 1, 0));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(diorite_pressure_plate, 1),
                    "SS", 'S', ProxyRegistry.newStack(Blocks.STONE, 1, 3));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(granite_pressure_plate, 1),
                    "SS", 'S', ProxyRegistry.newStack(Blocks.STONE, 1, 5));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(polished_andesite_pressure_plate, 1),
                    "SS", 'S', ProxyRegistry.newStack(Blocks.STONE, 1, 6));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(polished_diorite_pressure_plate, 1),
                    "SS", 'S', ProxyRegistry.newStack(Blocks.STONE, 1, 4));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(polished_granite_pressure_plate, 1),
                    "SS", 'S', ProxyRegistry.newStack(Blocks.STONE, 1, 2));

            if (renameVanillaBlocks)
                Blocks.WOODEN_PRESSURE_PLATE.setTranslationKey("oak_pressure_plate");
        }

        if (enableButtons) {
            WoodVariantReplacer.addReplacements(1, Blocks.WOODEN_BUTTON);

            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(spruce_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(birch_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 2));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(jungle_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 3));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(acacia_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 4));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(dark_oak_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 5));

            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(andesite_button, 1),
                    ProxyRegistry.newStack(Blocks.STONE, 1, 0));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(diorite_button, 1),
                    ProxyRegistry.newStack(Blocks.STONE, 1, 3));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(granite_button, 1),
                    ProxyRegistry.newStack(Blocks.STONE, 1, 5));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(polished_andesite_button, 1),
                    ProxyRegistry.newStack(Blocks.STONE, 1, 6));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(polished_diorite_button, 1),
                    ProxyRegistry.newStack(Blocks.STONE, 1, 4));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(polished_granite_button, 1),
                    ProxyRegistry.newStack(Blocks.STONE, 1, 2));

            if (renameVanillaBlocks)
                Blocks.WOODEN_BUTTON.setTranslationKey("oak_button");
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (enablePressurePlates) {
            OreDictionary.registerOre("pressurePlateWood", Blocks.WOODEN_PRESSURE_PLATE);
            OreDictionary.registerOre("pressurePlateWood", spruce_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", birch_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", jungle_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", acacia_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", dark_oak_pressure_plate);

            OreDictionary.registerOre("pressurePlateStone", andesite_pressure_plate);
            OreDictionary.registerOre("pressurePlateStone", diorite_pressure_plate);
            OreDictionary.registerOre("pressurePlateStone", granite_pressure_plate);
            OreDictionary.registerOre("pressurePlateStone", polished_andesite_pressure_plate);
            OreDictionary.registerOre("pressurePlateStone", polished_diorite_pressure_plate);
            OreDictionary.registerOre("pressurePlateStone", polished_granite_pressure_plate);
        }

        if (enableButtons) {
            OreDictionary.registerOre("buttonWood", Blocks.WOODEN_BUTTON);
            OreDictionary.registerOre("buttonWood", spruce_button);
            OreDictionary.registerOre("buttonWood", birch_button);
            OreDictionary.registerOre("buttonWood", jungle_button);
            OreDictionary.registerOre("buttonWood", acacia_button);
            OreDictionary.registerOre("buttonWood", dark_oak_button);

            OreDictionary.registerOre("buttonStone", andesite_button);
            OreDictionary.registerOre("buttonStone", diorite_button);
            OreDictionary.registerOre("buttonStone", granite_button);
            OreDictionary.registerOre("buttonStone", polished_andesite_button);
            OreDictionary.registerOre("buttonStone", polished_diorite_button);
            OreDictionary.registerOre("buttonStone", polished_granite_button);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public String getComponentInGameConfigName() {
        return "Varied Buttons/P.Plates";
    }

}
