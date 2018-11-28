package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.handler.server.RecipeProcessor;
import net.hdt.neutronia.groups.building.features.WoodBlocks;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomButton;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomPressurePlate;
import net.hdt.neutronia.groups.world.features.end.PurhoganyWood;
import net.hdt.neutronia.groups.world.features.overworld.BaobabTrees;
import net.hdt.neutronia.groups.world.features.overworld.CherryTrees;
import net.hdt.neutronia.groups.world.features.overworld.PalmTrees;
import net.hdt.neutronia.groups.world.features.overworld.WillowTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

public class VariedButtonsAndPressurePlates extends Component {

    public static Block oak_bark_pressure_plate, stripped_oak_bark_pressure_plate, oak_bark_button, stripped_oak_bark_button;
    public static Block spruce_pressure_plate, spruce_bark_pressure_plate, stripped_spruce_bark_pressure_plate, spruce_button, spruce_bark_button, stripped_spruce_bark_button;
    public static Block birch_pressure_plate, birch_bark_pressure_plate, stripped_birch_bark_pressure_plate, birch_button, birch_bark_button, stripped_birch_bark_button;
    public static Block jungle_pressure_plate, jungle_bark_pressure_plate, stripped_jungle_bark_pressure_plate, jungle_button, jungle_bark_button, stripped_jungle_bark_button;
    public static Block acacia_pressure_plate, acacia_bark_pressure_plate, stripped_acacia_bark_pressure_plate, acacia_button, acacia_bark_button, stripped_acacia_bark_button;
    public static Block dark_oak_pressure_plate, dark_oak_bark_pressure_plate, stripped_dark_oak_bark_pressure_plate, dark_oak_button, dark_oak_bark_button, stripped_dark_oak_bark_button;


    public static Block cherry_pressure_plate, cherry_bark_pressure_plate, stripped_cherry_bark_pressure_plate, cherry_button, cherry_bark_button, stripped_cherry_bark_button;
    public static Block baobab_pressure_plate, baobab_bark_pressure_plate, stripped_baobab_bark_pressure_plate, baobab_button, baobab_bark_button, stripped_baobab_bark_button;
    public static Block willow_pressure_plate, willow_bark_pressure_plate, stripped_willow_bark_pressure_plate, willow_button, willow_bark_button, stripped_willow_bark_button;
    public static Block palm_pressure_plate, palm_bark_pressure_plate, stripped_palm_bark_pressure_plate, palm_button, palm_bark_button, stripped_palm_bark_button;
    public static Block purhogany_pressure_plate, purhogany_bark_pressure_plate, stripped_purhogany_bark_pressure_plate, purhogany_button, purhogany_bark_button, stripped_purhogany_bark_button;

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

            cherry_pressure_plate = new BlockCustomPressurePlate("cherry");
            baobab_pressure_plate = new BlockCustomPressurePlate("baobab");
            willow_pressure_plate = new BlockCustomPressurePlate("willow");
            palm_pressure_plate = new BlockCustomPressurePlate("palm");
            purhogany_pressure_plate = new BlockCustomPressurePlate("purhogany");

            oak_bark_pressure_plate = new BlockCustomPressurePlate("oak_bark");
            spruce_bark_pressure_plate = new BlockCustomPressurePlate("spruce_bark");
            birch_bark_pressure_plate = new BlockCustomPressurePlate("birch_bark");
            jungle_bark_pressure_plate = new BlockCustomPressurePlate("jungle_bark");
            acacia_bark_pressure_plate = new BlockCustomPressurePlate("acacia_bark");
            dark_oak_bark_pressure_plate = new BlockCustomPressurePlate("dark_oak_bark");

            cherry_bark_pressure_plate = new BlockCustomPressurePlate("cherry_bark");
            baobab_bark_pressure_plate = new BlockCustomPressurePlate("baobab_bark");
            willow_bark_pressure_plate = new BlockCustomPressurePlate("willow_bark");
            palm_bark_pressure_plate = new BlockCustomPressurePlate("palm_bark");
            purhogany_bark_pressure_plate = new BlockCustomPressurePlate("purhogany_bark");

            stripped_oak_bark_pressure_plate = new BlockCustomPressurePlate("stripped_oak_bark");
            stripped_spruce_bark_pressure_plate = new BlockCustomPressurePlate("stripped_spruce_bark");
            stripped_birch_bark_pressure_plate = new BlockCustomPressurePlate("stripped_birch_bark");
            stripped_jungle_bark_pressure_plate = new BlockCustomPressurePlate("stripped_jungle_bark");
            stripped_acacia_bark_pressure_plate = new BlockCustomPressurePlate("stripped_acacia_bark");
            stripped_dark_oak_bark_pressure_plate = new BlockCustomPressurePlate("stripped_dark_oak_bark");

            stripped_cherry_bark_pressure_plate = new BlockCustomPressurePlate("stripped_cherry_bark");
            stripped_baobab_bark_pressure_plate = new BlockCustomPressurePlate("stripped_baobab_bark");
            stripped_willow_bark_pressure_plate = new BlockCustomPressurePlate("stripped_willow_bark");
            stripped_palm_bark_pressure_plate = new BlockCustomPressurePlate("stripped_palm_bark");
            stripped_purhogany_bark_pressure_plate = new BlockCustomPressurePlate("stripped_purhogany_bark");

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

            cherry_button = new BlockCustomButton("cherry");
            baobab_button = new BlockCustomButton("baobab");
            willow_button = new BlockCustomButton("willow");
            palm_button = new BlockCustomButton("palm");
            purhogany_button = new BlockCustomButton("purhogany");

            oak_bark_button = new BlockCustomButton("oak_bark");
            spruce_bark_button = new BlockCustomButton("spruce_bark");
            birch_bark_button = new BlockCustomButton("birch_bark");
            jungle_bark_button = new BlockCustomButton("jungle_bark");
            acacia_bark_button = new BlockCustomButton("acacia_bark");
            dark_oak_bark_button = new BlockCustomButton("dark_oak_bark");

            cherry_bark_button = new BlockCustomButton("cherry_bark");
            baobab_bark_button = new BlockCustomButton("baobab_bark");
            willow_bark_button = new BlockCustomButton("willow_bark");
            palm_bark_button = new BlockCustomButton("palm_bark");
            purhogany_bark_button = new BlockCustomButton("purhogany_bark");

            stripped_oak_bark_button = new BlockCustomButton("stripped_oak_bark");
            stripped_spruce_bark_button = new BlockCustomButton("stripped_spruce_bark");
            stripped_birch_bark_button = new BlockCustomButton("stripped_birch_bark");
            stripped_jungle_bark_button = new BlockCustomButton("stripped_jungle_bark");
            stripped_acacia_bark_button = new BlockCustomButton("stripped_acacia_bark");
            stripped_dark_oak_bark_button = new BlockCustomButton("stripped_dark_oak_bark");

            stripped_cherry_bark_button = new BlockCustomButton("stripped_cherry_bark");
            stripped_baobab_bark_button = new BlockCustomButton("stripped_baobab_bark");
            stripped_willow_bark_button = new BlockCustomButton("stripped_willow_bark");
            stripped_palm_bark_button = new BlockCustomButton("stripped_palm_bark");
            stripped_purhogany_bark_button = new BlockCustomButton("stripped_purhogany_bark");

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
            RecipeProcessor.addWoodReplacements(1, Blocks.WOODEN_PRESSURE_PLATE);

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

            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(cherry_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(CherryTrees.cherryPlanks));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(baobab_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(BaobabTrees.baobabPlanks, 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(willow_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WillowTree.willowPlanks, 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(palm_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(PalmTrees.palmPlanks, 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(purhogany_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(PurhoganyWood.purhoganyPlanks, 1));

            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(oak_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.barkBlocks[0], 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(spruce_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.barkBlocks[1], 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(birch_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.barkBlocks[2], 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(jungle_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.barkBlocks[3], 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(acacia_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.barkBlocks[4], 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(dark_oak_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.barkBlocks[5], 1));

            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(stripped_oak_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[0], 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(stripped_spruce_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[1], 1, 1));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(stripped_birch_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[2], 1, 2));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(stripped_jungle_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[3], 1, 3));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(stripped_acacia_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[4], 1, 4));
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(stripped_dark_oak_bark_pressure_plate, 1),
                    "WW", 'W', ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[5], 1, 5));

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
            RecipeProcessor.addWoodReplacements(1, Blocks.WOODEN_BUTTON);

            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(spruce_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(birch_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 2));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(jungle_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 3));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(acacia_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 4));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(dark_oak_button, 1), ProxyRegistry.newStack(Blocks.PLANKS, 1, 5));

            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(oak_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.barkBlocks[0], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(spruce_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.barkBlocks[1], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(birch_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.barkBlocks[2], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(jungle_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.barkBlocks[3], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(acacia_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.barkBlocks[4], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(dark_oak_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.barkBlocks[5], 1));

            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(stripped_oak_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[0], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(stripped_acacia_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[1], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(stripped_birch_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[2], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(stripped_jungle_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[3], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(stripped_acacia_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[4], 1));
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(stripped_dark_oak_bark_button, 1), ProxyRegistry.newStack(WoodBlocks.strippedBarkBlocks[5], 1));

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

            OreDictionary.registerOre("pressurePlateWood", oak_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", spruce_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", birch_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", jungle_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", acacia_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", dark_oak_bark_pressure_plate);

            OreDictionary.registerOre("pressurePlateWood", stripped_oak_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", stripped_spruce_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", stripped_birch_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", stripped_jungle_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", stripped_acacia_bark_pressure_plate);
            OreDictionary.registerOre("pressurePlateWood", stripped_dark_oak_bark_pressure_plate);

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

            OreDictionary.registerOre("buttonWood", oak_bark_button);
            OreDictionary.registerOre("buttonWood", spruce_bark_button);
            OreDictionary.registerOre("buttonWood", birch_bark_button);
            OreDictionary.registerOre("buttonWood", jungle_bark_button);
            OreDictionary.registerOre("buttonWood", acacia_bark_button);
            OreDictionary.registerOre("buttonWood", dark_oak_bark_button);

            OreDictionary.registerOre("buttonWood", stripped_oak_bark_button);
            OreDictionary.registerOre("buttonWood", stripped_spruce_bark_button);
            OreDictionary.registerOre("buttonWood", stripped_birch_bark_button);
            OreDictionary.registerOre("buttonWood", stripped_jungle_bark_button);
            OreDictionary.registerOre("buttonWood", stripped_acacia_bark_button);
            OreDictionary.registerOre("buttonWood", stripped_dark_oak_bark_button);

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
