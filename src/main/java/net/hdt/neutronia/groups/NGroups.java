package net.hdt.neutronia.groups;

import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.groups.building.features.*;
import net.hdt.neutronia.groups.client.features.*;
import net.hdt.neutronia.groups.decoration.features.*;
import net.hdt.neutronia.groups.dimensions.features.*;
import net.hdt.neutronia.groups.earlyGame.features.ClayCauldron;
import net.hdt.neutronia.groups.earlyGame.features.ClayTools;
import net.hdt.neutronia.groups.experimental.features.BiggerCaves;
import net.hdt.neutronia.groups.misc.feature.*;
import net.hdt.neutronia.groups.tweaks.features.*;
import net.hdt.neutronia.groups.vanity.feature.DyableElytra;
import net.hdt.neutronia.groups.vanity.feature.DyeItemNames;
import net.hdt.neutronia.groups.vanity.feature.SimplerHorseModel;
import net.hdt.neutronia.groups.vanity.feature.SitInStairs;
import net.hdt.neutronia.groups.world.features.end.Acidian;
import net.hdt.neutronia.groups.world.features.end.PurhoganyWood;
import net.hdt.neutronia.groups.world.features.nether.NetherFossils;
import net.hdt.neutronia.groups.world.features.nether.NetherMushrooms;
import net.hdt.neutronia.groups.world.features.overworld.*;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

import java.time.LocalDate;
import java.time.Month;

public class NGroups {

    public static Group building, christmas, client, decoration, dimensions, earlyGame, easter, experimental, halloween, misc, summer, tweaks, vanity, winter, world;

    public static void registerGroups() {
        building = Group.builder()
                .name("Building")
                .description("This group adds new structural building blocks and building utensils.")
                .iconStack(new ItemStack(Blocks.BRICK_BLOCK))
                .addComponent(new LogBlocks())
                .addComponent(new MoreStoneBlocks())
                .addComponent(new VanillaStairsAndSlabs())
                .addComponent(new VanillaWalls())
                .addComponent(new WoodBlocks())
                .addComponent(new WorldStoneBricks())
                .addComponent(new SoulStone())
                .enabled(true)
                .register();

        christmas = Group.builder()
                .name("Christmas")
                .enabled(isChristmas())
                .register();

        client = Group.builder()
                .name("Client")
                .description("This group adds components that alter the client, not needing Neutronia to be loaded on the server.")
                .iconStack(new ItemStack(Items.ENDER_EYE))
                .addComponent(new BetterVanillaTextures())
                .addComponent(new FoodTooltip())
                .addComponent(new MapTooltip())
                .addComponent(new NoPotionShift())
                .addComponent(new ShulkerBoxTooltip())
                .addComponent(new VisualStatDisplay())
                .addComponent(new ConsoleHudFeatures())
                .addComponent(new PastelColors())
                .addComponent(new BetterEndTextures())
//                .addComponent(new MoreDetailedModels())
                .enabled(true)
                .register();

        decoration = Group.builder()
                .name("Decoration")
                .description("This group adds new decorative building blocks and improves vanilla ones.")
                .iconStack(new ItemStack(Blocks.RED_FLOWER))
                .addComponent(new CarvedPlanks())
                .addComponent(new CenteredGlazedTerracotta())
                .addComponent(new CharcoalBlock())
                .addComponent(new ChiseledBlocks())
//                .addComponent(new ColoredPlanks())
//                .addComponent(new ColoredCandles())
//                .addComponent(new ColoredRedstoneLamps())
//                .addComponent(new ColoredSlimeBlocks())
//                .addComponent(new CutGlazedTerracotta())
                .addComponent(new DecorativeAquamarine())
//                .addComponent(new DecorativeCorals())
//                .addComponent(new FrostedBlocks())
//                .addComponent(new GlazedTerracottaPillar())
//                .addComponent(new GlazedTerracottaStripes())
                .addComponent(new LogFenceAndWall())
                .addComponent(new MetalAndMineralBricks())
                .addComponent(new MetalAndMineralPillars())
                .addComponent(new MoreBanners())
                .addComponent(new MoreMetals())
                .addComponent(new NetherBlocks())
                .addComponent(new SmoothBlocks())
                .addComponent(new MorePillars())
//                .addComponent(new TerracottaFlowerPots())
                .addComponent(new VariedBookshelves())
                .addComponent(new VariedButtonsAndPressurePlates())
                .addComponent(new VariedTrapdoors())
                .enabled(true)
                .register();

        dimensions = Group.builder()
                .name("Dimensions")
                .description("This is a WIP group which adds new dimensions to Minecraft")
                .iconStack(new ItemStack(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE).getBlock()))
                .addComponent(new MoonBlocks())
                .addComponent(new MoonBiomes())
                .addComponent(new MoonDimension())
                .addComponent(new MarsBiomes())
                .addComponent(new MarsDimension())
                .addComponent(new SunBiomes())
                .addComponent(new SunDimension())
                .addComponent(new RevampedEnd())
                .addComponent(new RevampedNether())
                .enabled(false)
                .register();

        earlyGame = Group.builder()
                .name("Early Game")
                .description("This group aims to extend the early game.")
                .iconStack(new ItemStack(Items.WOODEN_AXE))
                .addComponent(new ClayCauldron())
                .addComponent(new ClayTools())
                .enabled(false)
                .register();

        easter = Group.builder()
                .name("Easter")
                .enabled(isEaster())
                .register();

        experimental = Group.builder()
                .name("Experimental")
                .description("Experimental Features. All components in this group are disabled by default. Use at your own risk.")
                .iconStack(new ItemStack(Blocks.TNT))
                .addComponent(new BiggerCaves())
                .enabled(true)
                .register();

        halloween = Group.builder()
                .name("Halloween")
                .iconStack(new ItemStack(Blocks.LIT_PUMPKIN))
                .enabled(isHalloween())
                .register();

        misc = Group.builder()
                .name("Misc")
                .description("This module adds miscellaneous features that didn't fit in any other modules.")
                .iconStack(new ItemStack(Items.CARROT_ON_A_STICK))
//                .addComponent(new ColorRunes())
                .addComponent(new EnchantedScrolls())
                .addComponent(new EndermitesIntoShulkers())
                .addComponent(new MapMarkers())
                .addComponent(new MiscRecipes())
                .addComponent(new NoteBlocksMobSounds())
                .addComponent(new PoisonPotatoUsage())
                .addComponent(new UtilityRecipes())
                .enabled(true)
                .register();

        tweaks = Group.builder()
                .name("Tweaks")
                .description("This module tweaks various gameplay elements.")
                .iconStack(new ItemStack(Items.IRON_PICKAXE))
                .addComponent(new ArmedArmorStands())
                .addComponent(new BabyZombiesBurn())
                .addComponent(new CompassesWorkEverywhere())
                .addComponent(new HoeSickle())
                .addComponent(new ImprovedStoneToolCrafting())
                .addComponent(new RemoveSnowLayers())
                .addComponent(new SlabsToBlocks())
                .addComponent(new SquidsInkYou())
                .addComponent(new StackableItems())
                .addComponent(new StairsMakeMore())
                .addComponent(new TorchesBurnInFurnaces())
                .addComponent(new BlastProofShulkerBoxes())
                .addComponent(new ChickensShedFeathers())
                .addComponent(new AxesBreakLeaves())
                .addComponent(new ConvertClay())
                .addComponent(new AnimalBirth())
                .addComponent(new BabyJumping())
                .addComponent(new BetterBlockHardness())
                .addComponent(new CactusSkeleton())
                .addComponent(new EnchantmentTooltip())
                .addComponent(new EquipmentDrop())
                .addComponent(new GrassPath())
                .addComponent(new HCMovement())
//                .addComponent(new HCTools())
                .addComponent(new ImprovedFlee())
                .addComponent(new MobEating())
                .addComponent(new MoreTempting())
                .addComponent(new MossGeneration())
                .addComponent(new Sinkholes())
                .addComponent(new VisibleStorms())
                .addComponent(new ToolEffTweaks())
                .addComponent(new SheepDyeFix())
                .addComponent(new PeacefulSurface())
                .addComponent(new MobDropBuffs())
                .addComponent(new SwingThroughGrass())
//                .addComponent(new HarderLogs())
                .enabled(true)
                .register();

        vanity = Group.builder()
                .name("Vanity")
                .description("This module tweaks various gameplay elements.")
                .iconStack(new ItemStack(Items.LEATHER_HELMET))
                .addComponent(new DyableElytra())
                .addComponent(new DyeItemNames())
                .addComponent(new SitInStairs())
                .addComponent(new SimplerHorseModel())
                .enabled(true)
                .register();

        winter = Group.builder()
                .name("Winter")
                .iconStack(new ItemStack(Blocks.SNOW))
                .enabled(false)
                .register();

        world = Group.builder()
                .name("World")
                .description("This module adds world generation features to the End, Nether and Overworld.")
                .iconStack(new ItemStack(Blocks.GRASS))
                .addComponent(new Acidian())
                .addComponent(new BetterCaves())
                .addComponent(new BetterStoneGeneration())
                .addComponent(new BetterVillages())
                .addComponent(new CaveBiomes())
//                .addComponent(new Corals())
                .addComponent(new HCStructures())
                .addComponent(new HCVillages())
//                .addComponent(new ImprovedWorldGen())
                .addComponent(new MineshaftGeneration())
                .addComponent(new MoreBiomes())
                .addComponent(new MoreOceanBiomes())
                .addComponent(new MoreStructures())
                .addComponent(new NaturalAquamarine())
                .addComponent(new NetherFossils())
                .addComponent(new NetherMushrooms())
                .addComponent(new PurhoganyWood())
                .addComponent(new Stalactite())
                .addComponent(new Stalagmite())
                .addComponent(new VariedDungeons())
                .enabled(true)
                .register();
    }

    private static boolean isHalloween() {
        return LocalDate.now().getMonth() == Month.OCTOBER && LocalDate.now().getDayOfMonth() == 31;
    }

    private static boolean isChristmas() {
        return LocalDate.now().getMonth() == Month.DECEMBER && LocalDate.now().getDayOfMonth() == 24;
    }

    private static boolean isEaster() {
        return LocalDate.now().getMonth() == Month.APRIL && LocalDate.now().getDayOfMonth() == 21;
    }

}
