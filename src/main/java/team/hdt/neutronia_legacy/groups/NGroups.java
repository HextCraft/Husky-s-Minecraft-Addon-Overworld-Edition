package team.hdt.neutronia_legacy.groups;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import team.hdt.neutronia_legacy.base.groups.Group;
import team.hdt.neutronia_legacy.groups.building.features.*;
import team.hdt.neutronia_legacy.groups.client.features.*;
import team.hdt.neutronia_legacy.groups.craftingExtension.features.NewCraftingBlocks;
import team.hdt.neutronia_legacy.groups.craftingExtension.features.NewMiscBlocks;
import team.hdt.neutronia_legacy.groups.craftingExtension.features.NewStorageBlocks;
import team.hdt.neutronia_legacy.groups.decoration.features.*;
import team.hdt.neutronia_legacy.groups.dimensions.features.*;
import team.hdt.neutronia_legacy.groups.earlyGame.features.ClayCauldron;
import team.hdt.neutronia_legacy.groups.earlyGame.features.ClayTools;
import team.hdt.neutronia_legacy.groups.experimental.features.BiggerCaves;
import team.hdt.neutronia_legacy.groups.foodExpansion.features.MoreCakes;
import team.hdt.neutronia_legacy.groups.foodExpansion.features.MorePies;
import team.hdt.neutronia_legacy.groups.misc.feature.*;
import team.hdt.neutronia_legacy.groups.musicRevamp.features.Composer;
import team.hdt.neutronia_legacy.groups.tweaks.features.*;
import team.hdt.neutronia_legacy.groups.vanity.feature.DyableElytra;
import team.hdt.neutronia_legacy.groups.vanity.feature.DyeItemNames;
import team.hdt.neutronia_legacy.groups.vanity.feature.SimplerHorseModel;
import team.hdt.neutronia_legacy.groups.vanity.feature.SitOnStairsAndSlabs;
import team.hdt.neutronia_legacy.groups.world.features.end.Acidian;
import team.hdt.neutronia_legacy.groups.world.features.end.PurhoganyWood;
import team.hdt.neutronia_legacy.groups.world.features.nether.NetherFossils;
import team.hdt.neutronia_legacy.groups.world.features.nether.NetherMushrooms;
import team.hdt.neutronia_legacy.groups.world.features.overworld.*;

import java.time.LocalDate;
import java.time.Month;

public class NGroups {

    public static Group building, client, craftingExtension, decoration, dimensions, earlyGame, experimental, foodExpansion, holidays, misc, musicRevamp, seasons, tweaks, vanity, world;

    public static void registerGroups() {
        building = Group.builder()
                .name("Building")
                .description("This group adds new structural building blocks and building utensils.")
                .iconStack(new ItemStack(Blocks.BRICK_BLOCK))
                .addComponent(new CarvedPlanks())
                .addComponent(new FrostedGlass())
                .addComponent(new LogBlocks())
                .addComponent(new LogFenceAndWall())
                .addComponent(new LogPiles())
                .addComponent(new MoreBuildingBlocks())
                .addComponent(new MoreStoneBlocks())
                .addComponent(new SoulStone())
                .addComponent(new VanillaStairsAndSlabs())
                .addComponent(new VanillaWalls())
                .addComponent(new WoodBlocks())
                .addComponent(new WorldStoneBricks())
                .addComponent(new WroughtIron())
                .enabled(true)
                .enabledByDefault(true)
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
//                .addComponent(new ConsoleHudFeatures())
                .enabled(isClient())
                .enabledByDefault(isClient())
                .register();

        craftingExtension = Group.builder()
                .name("Crafting Extension")
                .iconStack(new ItemStack(Blocks.CRAFTING_TABLE))
                .addComponent(new NewCraftingBlocks())
                .addComponent(new NewMiscBlocks())
                .addComponent(new NewStorageBlocks())
                .enabled(true)
                .enabledByDefault(true)
                .register();

        decoration = Group.builder()
                .name("Decoration")
                .description("This group adds new decorative building blocks and improves vanilla ones.")
                .iconStack(new ItemStack(Blocks.BOOKSHELF))
                .addComponent(new CenteredGlazedTerracotta())
                .addComponent(new ChiseledBlocks())

                .addComponent(new ColoredCandles())
//                .addComponent(new CutGlazedTerracotta(), false)

//                .addComponent(new DecorativeAquamarine())

//                .addComponent(new DecorativeCorals(), false)
//                .addComponent(new GlazedTerracottaPillar())
//                .addComponent(new GlazedTerracottaStripes(), false)

                .addComponent(new Lanterns())
                .addComponent(new MetalAndMineralBricks())
                .addComponent(new MetalAndMineralPillars())
                .addComponent(new MoreBanners())
//                .addComponent(new MoreMetals())
                .addComponent(new MorePillars())
                .addComponent(new NetherBlocks())
                .addComponent(new SmoothBlocks())

//                .addComponent(new TerracottaFlowerPots())

                .addComponent(new VariedBookshelves())
                .addComponent(new VariedButtonsAndPressurePlates())
                .addComponent(new VariedChests())
//                .addComponent(new VariedCraftingTable())
                .addComponent(new VariedLadders())
                .addComponent(new VariedShelfs())
                .addComponent(new VariedTrapdoors())
                .addComponent(new VariedWoodenLanterns())
                .addComponent(new WoodPalisades())
                .enabled(true)
                .enabledByDefault(true)
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
                .enabledByDefault(false)
                .register();

        earlyGame = Group.builder()
                .name("Early Game")
                .description("This group aims to extend the early game.")
                .iconStack(new ItemStack(Items.WOODEN_AXE))
                .addComponent(new ClayCauldron())
                .addComponent(new ClayTools())
                .enabled(false)
                .enabledByDefault(false)
                .register();

        experimental = Group.builder()
                .name("Experimental")
                .description("Experimental Features. All components in this group are disabled by default. Use at your own risk.")
                .iconStack(new ItemStack(Blocks.TNT))
                .addComponent(new BiggerCaves())
                .enabled(false)
                .enabledByDefault(false)
                .register();

        foodExpansion = Group.builder()
                .name("Food Expansion")
                .description("This module adds a lot more food to Minecraft")
                .iconStack(new ItemStack(Blocks.CAKE))
                .addComponent(new MoreCakes())
                .addComponent(new MorePies())
                .enabledByDefault(true)
                .enabled(true)
                .register();

        holidays = Group.builder()
                .name("Holidays")
                .iconStack(new ItemStack(Blocks.LIT_PUMPKIN))
                .enabledByDefault(isHalloween() || isChristmas() || isEaster())
                .enabled(isHalloween() || isChristmas() || isEaster())
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
                .enabledByDefault(true)
                .register();

        musicRevamp = Group.builder()
                .name("Music Revamp")
                .description("This revamps how music creation work inside of Minecraft so you can now create custom music")
                .iconStack(new ItemStack(Blocks.NOTEBLOCK))
                .addComponent(new Composer())
                .enabledByDefault(true)
                .enabled(true)
                .register();

        seasons = Group.builder()
                .name("Seasons")
                .description("This module adds different season related things")
                .enabled(false)
                .enabledByDefault(false)
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
                .addComponent(new EnchantmentTooltip())
                .addComponent(new EquipmentDrop())
                .addComponent(new GrassPath())
                .addComponent(new ImprovedFlee())
                .addComponent(new MobEating())
                .addComponent(new MoreTempting())
                .addComponent(new Sinkholes())
                .addComponent(new VisibleStorms())
                .addComponent(new ToolEffTweaks())
                .addComponent(new SheepDyeFix())
                .addComponent(new PeacefulSurface())
                .addComponent(new MobDropBuffs())
                .addComponent(new SwingThroughGrass())
//                .addComponent(new HarderLogs())
                .enabled(true)
                .enabledByDefault(true)
                .register();

        vanity = Group.builder()
                .name("Vanity")
                .description("This module tweaks various gameplay elements.")
                .iconStack(new ItemStack(Items.LEATHER_HELMET))
                .addComponent(new DyableElytra())
                .addComponent(new DyeItemNames())
                .addComponent(new SitOnStairsAndSlabs())
                .addComponent(new SimplerHorseModel())
                .enabledByDefault(true)
                .register();

        world = Group.builder()
                .name("World")
                .description("This module adds world generation features to the End, Nether and Overworld.")
                .iconStack(new ItemStack(Blocks.GRASS))
                .addComponent(new Acidian())
//                .addComponent(new BaobabTrees())
                .addComponent(new BetterCaves())
                .addComponent(new BetterStoneGeneration())
                .addComponent(new CaveBiomes())
                .addComponent(new CherryTrees())
//                .addComponent(new Corals())
                .addComponent(new CrystalsAndWorldGen())
//                .addComponent(new DeadTree())
                .addComponent(new JigsawBlock())
                .addComponent(new MineshaftGeneration())
//                .addComponent(new MoreBiomes())
//                .addComponent(new MoreOceanBiomes())
                .addComponent(new MoreStructures())
                .addComponent(new MudBlocks())
//                .addComponent(new NaturalAquamarine())
                .addComponent(new NetherFossils())
                .addComponent(new NetherMushrooms())
                .addComponent(new PalmTrees())
                .addComponent(new PurhoganyWood())
//                .addComponent(new Stalactite())
//                .addComponent(new Stalagmite())
                .addComponent(new VariedDungeons())
                .addComponent(new WillowTree())
                .addComponent(new WorldDecorationFeatures())
                .enabled(true)
                .enabledByDefault(true)
                .register();
    }

    private static boolean isClient() {
        return FMLCommonHandler.instance().getSide() == Side.CLIENT;
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
