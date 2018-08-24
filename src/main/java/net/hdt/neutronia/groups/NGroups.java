package net.hdt.neutronia.groups;

import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.groups.building.features.*;
import net.hdt.neutronia.groups.client.features.*;
import net.hdt.neutronia.groups.decoration.features.*;
import net.hdt.neutronia.groups.dimensions.features.*;
import net.hdt.neutronia.groups.experimental.features.BiggerCaves;
import net.hdt.neutronia.groups.misc.feature.ColorRunes;
import net.hdt.neutronia.groups.misc.feature.EnchantedScrolls;
import net.hdt.neutronia.groups.misc.feature.NoteBlocksMobSounds;
import net.hdt.neutronia.groups.misc.feature.UtilityRecipes;
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

public class NGroups {

    public static Group building, client, decoration, dimensions, experimental, misc, tweaks, vanity, world;

    public static void registerGroups() {
        building = Group.builder()
                .withName("Building")
                .withDesc("This group adds new structural building blocks and building utensils.")
                .withIcon(new ItemStack(Blocks.BRICK_BLOCK))
                .withComponent(new BarkBlocks())
                .withComponent(new LogBlocks())
                .withComponent(new MoreStoneBlocks())
                .withComponent(new VanillaStairsAndSlabs())
                .withComponent(new VanillaWalls())
                .withComponent(new WoodBlocks())
                .register();

        client = Group.builder()
                .withName("Client")
                .withDesc("This group adds components that alter the client, not needing Quark to be loaded on the server.")
                .withIcon(new ItemStack(Items.ENDER_EYE))
                .withComponent(new BetterVanillaTextures())
                .withComponent(new FoodTooltip())
                .withComponent(new MapTooltip())
                .withComponent(new NewMenuScreenBackgrounds())
                .withComponent(new NoPotionShift())
                .withComponent(new ShulkerBoxTooltip())
                .withComponent(new VisualStatDisplay())
                .withComponent(new ConsoleHudFeatures())
                .withComponent(new PastelColors())
                .withComponent(new BetterEndTextures())
                .register();

        decoration = Group.builder()
                .withName("Decoration")
                .withDesc("This group adds new decorative building blocks and improves vanilla ones.")
                .withIcon(new ItemStack(Blocks.RED_FLOWER))
                .withComponent(new CharcoalBlock())
                .withComponent(new DecorativeAquamarine())
                .withComponent(new DecorativeCorals())
                .withComponent(new MoreBanners())
                .withComponent(new TerracottaFlowerPots())
                .withComponent(new VariedBookshelves())
                .withComponent(new VariedButtonsAndPressurePlates())
                .withComponent(new VariedTrapdoors())
                .withComponent(new ColoredPlanks())
                .register();

        dimensions = Group.builder()
                .withName("Dimensions")
                .withDesc("This group adds some new dimensions")
                .withIcon(new ItemStack(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE).getBlock()))
                .withComponent(new MarsDimension())
                .withComponent(new MoonBiomes())
                .withComponent(new MoonDimension())
                .withComponent(new SunDimension())
                .withComponent(new RevampedEnd())
                .withComponent(new RevampedNether())
                .register();

        experimental = Group.builder()
                .withName("Experimental")
                .withDesc("Experimental Features. All components in this group are disabled by default. Use at your own risk.")
                .withIcon(new ItemStack(Blocks.TNT))
                .withComponent(new BiggerCaves())
                .register();

        misc = Group.builder()
                .withName("Misc")
                .withDesc("This module adds miscellaneous features that didn't fit in any other modules.")
                .withIcon(new ItemStack(Items.CARROT_ON_A_STICK))
                .withComponent(new ColorRunes())
                .withComponent(new EnchantedScrolls())
                .withComponent(new NoteBlocksMobSounds())
                .withComponent(new UtilityRecipes())
                .register();

        tweaks = Group.builder()
                .withName("Tweaks")
                .withDesc("This module tweaks various gameplay elements.")
                .withIcon(new ItemStack(Items.IRON_PICKAXE))
                .withComponent(new ArmedArmorStands())
                .withComponent(new BabyZombiesBurn())
                .withComponent(new CompassesWorkEverywhere())
                .withComponent(new HoeSickle())
                .withComponent(new ImprovedSleeping())
                .withComponent(new ImprovedStoneToolCrafting())
                .withComponent(new RemoveSnowLayers())
                .withComponent(new SlabsToBlocks())
                .withComponent(new SquidsInkYou())
                .withComponent(new StackableItems())
                .withComponent(new StairsMakeMore())
                .withComponent(new TorchesBurnInFurnaces())
                .withComponent(new BlastProofShulkerBoxes())
                .withComponent(new ChickensShedFeathers())
                .withComponent(new AxesBreakLeaves())
                .withComponent(new ConvertClay())
                .withComponent(new ExtendedToolProgression())
                .withComponent(new AnimalBirth())
                .withComponent(new BabyJumping())
                .withComponent(new BetterBlockHardness())
                .withComponent(new CactusSkeleton())
                .withComponent(new EnchantmentTooltip())
                .withComponent(new EquipmentDrop())
                .withComponent(new GrassPath())
                .withComponent(new HCMovement())
                .withComponent(new HCStructures())
                .withComponent(new HCTools())
                .withComponent(new ImprovedFlee())
                .withComponent(new MobEating())
                .withComponent(new MoreTempting())
                .withComponent(new MossGeneration())
                .withComponent(new Sinkholes())
                .withComponent(new VisibleStorms())
                .withComponent(new ToolEffTweaks())
                .withComponent(new SheepDyeFix())
                .withComponent(new PeacefulSurface())
                .withComponent(new MobDropBuffs())
                .withComponent(new SwingThroughGrass())
                .register();

        vanity = Group.builder()
                .withName("Vanity")
                .withDesc("This module tweaks various gameplay elements.")
                .withIcon(new ItemStack(Items.LEATHER_HELMET))
                .withComponent(new DyableElytra())
                .withComponent(new DyeItemNames())
                .withComponent(new SitInStairs())
                .withComponent(new SimplerHorseModel())
                .register();

        world = Group.builder()
                .withName("World")
                .withDesc("This module adds world generation features to the End, Nether and Overworld.")
                .withIcon(new ItemStack(Blocks.GRASS))
                .withComponent(new Acidian())
                .withComponent(new Basalt())
                .withComponent(new BetterCaves())
                .withComponent(new BetterStoneGeneration())
                .withComponent(new CaveBiomes())
                .withComponent(new Corals())
                .withComponent(new ImprovedWorldGen())
                .withComponent(new NaturalAquamarine())
                .withComponent(new NetherFossils())
                .withComponent(new NetherMushrooms())
                .withComponent(new PurhoganyWood())
                .withComponent(new Stalactite())
                .withComponent(new Stalagmite())
                .withComponent(new VariedDungeons())
                .register();

    }

}
