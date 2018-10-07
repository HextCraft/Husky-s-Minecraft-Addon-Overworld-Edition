package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.hdt.neutronia.groups.building.features.CarvedPlanks;
import net.hdt.neutronia.groups.building.features.LogBlocks;
import net.hdt.neutronia.groups.building.features.LogFenceAndWall;
import net.hdt.neutronia.groups.building.features.WoodBlocks;
import net.hdt.neutronia.groups.decoration.features.VariedBookshelves;
import net.hdt.neutronia.groups.decoration.features.VariedButtonsAndPressurePlates;
import net.hdt.neutronia.groups.decoration.features.VariedTrapdoors;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Wood extends AbstractCreativeCategory {

    public Wood() {
        super("neutronia.category.wood", new ItemStack(Blocks.LOG));
    }

    @Override
    public void init() {
        add(LogBlocks.strippedLogPoles);
        add(LogBlocks.logPoles);
        add(LogBlocks.strippedLogs);
        add(LogBlocks.plankPoles);
//        add(LogBlocks.logDowels);
//        add(LogBlocks.strippedLogDowels);
        add(WoodBlocks.barkBlocks);
        add(WoodBlocks.chiseledBarkBlocks);
        add(WoodBlocks.strippedBarkBlocks);
        add(WoodBlocks.unnamedChiseledBarkBlock);
        add(CarvedPlanks.carvedPlanks);
        add(LogFenceAndWall.strippedOakLogWall);
        add(LogFenceAndWall.strippedAcaciaLogWall);
        add(LogFenceAndWall.strippedBirchLogWall);
        add(LogFenceAndWall.strippedDarkOakLogWall);
        add(LogFenceAndWall.strippedJungleLogWall);
        add(LogFenceAndWall.strippedSpruceLogWall);
        add(LogFenceAndWall.oakLogWall);
        add(LogFenceAndWall.acaciaLogWall);
        add(LogFenceAndWall.birchLogWall);
        add(LogFenceAndWall.darkOakLogWall);
        add(LogFenceAndWall.jungleLogWall);
        add(LogFenceAndWall.spruceLogWall);
        add(LogFenceAndWall.strippedOakLogFence);
        add(LogFenceAndWall.strippedAcaciaLogFence);
        add(LogFenceAndWall.strippedBirchLogFence);
        add(LogFenceAndWall.strippedDarkOakLogFence);
        add(LogFenceAndWall.strippedJungleLogFence);
        add(LogFenceAndWall.strippedSpruceLogFence);
        add(LogFenceAndWall.oakLogFence);
        add(LogFenceAndWall.acaciaLogFence);
        add(LogFenceAndWall.birchLogFence);
        add(LogFenceAndWall.darkOakLogFence);
        add(LogFenceAndWall.jungleLogFence);
        add(LogFenceAndWall.spruceLogFence);
        add(VariedBookshelves.acacia_bookshelf);
        add(VariedBookshelves.birch_bookshelf);
        add(VariedBookshelves.dark_oak_bookshelf);
        add(VariedBookshelves.jungle_bookshelf);
        add(VariedBookshelves.spruce_bookshelf);
        add(VariedButtonsAndPressurePlates.acacia_button);
        add(VariedButtonsAndPressurePlates.acacia_pressure_plate);
        add(VariedButtonsAndPressurePlates.birch_button);
        add(VariedButtonsAndPressurePlates.birch_pressure_plate);
        add(VariedButtonsAndPressurePlates.dark_oak_button);
        add(VariedButtonsAndPressurePlates.dark_oak_pressure_plate);
        add(VariedButtonsAndPressurePlates.spruce_button);
        add(VariedButtonsAndPressurePlates.spruce_pressure_plate);
        add(VariedButtonsAndPressurePlates.jungle_button);
        add(VariedButtonsAndPressurePlates.jungle_pressure_plate);
        add(VariedTrapdoors.acacia_trapdoor);
        add(VariedTrapdoors.birch_trapdoor);
        add(VariedTrapdoors.dark_oak_trapdoor);
        add(VariedTrapdoors.jungle_trapdoor);
        add(VariedTrapdoors.spruce_trapdoor);
    }

}