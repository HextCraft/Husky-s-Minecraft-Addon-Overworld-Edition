package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.world.gen.features.WorldGenBeaverDam;
import net.hdt.neutronia.groups.world.world.gen.features.WorldGenMuddyLake;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class WorldDecorationFeatures extends Component {

    private static final IBlockState WATER = Blocks.WATER.getDefaultState();
    private static final int DAM_FREQUENCY = 3; //lower numbers = more dams. set this to 0 to crash
    public static Block stickBlock, mossyStone;
    private static WorldGenSand MUD_FEATURE;
    private static WorldGenBlockBlob MOSSY_FEATURE;
    private static WorldGenBeaverDam DAM_FEATURE;
    private static int riverMudFrequency, mossyStoneFrequency, swampMudFrequency;

    @Override
    public void setupConfig() {
        riverMudFrequency = loadPropInt("River Mud Frequency", "How frequent mud will spawn in rivers", 4);
        mossyStoneFrequency = loadPropInt("Mossy Stone Frequency", "How frequent mossy stone would spawn in the world", 4);
        swampMudFrequency = loadPropInt("Swamp Mud Frequency", "How frequent mud will spawn in swamps", 4);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        stickBlock = new BlockNeutroniaBase(Material.WOOD, "stick_block");
        mossyStone = new BlockNeutroniaBase(Material.ROCK, "mossy_stone");

        MUD_FEATURE = new WorldGenSand(MudBlocks.MUD, 4);
        MOSSY_FEATURE = new WorldGenBlockBlob(mossyStone, 2);
        DAM_FEATURE = new WorldGenBeaverDam(stickBlock.getDefaultState(), MudBlocks.MUD.getDefaultState(), true);
    }

    //Replace vanilla lakes with our lakes that contains mud blocks around the edges
    @SubscribeEvent
    public void onLakeEvent(PopulateChunkEvent.Populate event) {
        if (event.getType() == PopulateChunkEvent.Populate.EventType.LAKE) {
            event.setResult(Event.Result.DENY);
            Random rand = event.getRand();
            int x = rand.nextInt(16) + 8;
            int y = rand.nextInt(256);
            int z = rand.nextInt(16) + 8;
            BlockPos pos = new BlockPos(event.getChunkX() * 16 + x, y, event.getChunkZ() * 16 + z);
            (new WorldGenMuddyLake(Blocks.WATER)).generate(event.getWorld(), rand, pos);

            if (event.getWorld().getBiome(pos) == Biomes.DESERT) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public void onBiomeDecoratePre(DecorateBiomeEvent.Pre event) {
        Random rand = event.getRand();
        World world = event.getWorld();

        // Add muddy blobs to rivers
        for (int n = 0; n < riverMudFrequency; n++) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            BlockPos pos = new BlockPos(event.getChunkPos().getXStart() + x, 0, event.getChunkPos().getZStart() + z);
            if (world.getBiome(pos) == Biomes.RIVER) {
                MUD_FEATURE.generate(event.getWorld(), event.getRand(), event.getWorld().getTopSolidOrLiquidBlock(pos));
            }
        }

        // add dams to rivers
        if (rand.nextInt(DAM_FREQUENCY) == 0) {
            // smaller rng range for location to prevent cascading... hopefully
            int x = rand.nextInt(8) + 12;
            int z = rand.nextInt(8) + 12;
            BlockPos pos = new BlockPos(event.getChunkPos().getXStart() + x, world.getSeaLevel() - 1, event.getChunkPos().getZStart() + z);
            if (world.getBiome(pos) == Biomes.RIVER) {
                IBlockState state = world.getBlockState(pos);
                if (state == WATER) {
                    Direction firstBankDir = null;
                    BlockPos firstBankPos = null;

                    // check east, north east, south east (in that order) from starting location to
                    // find first river bank
                    for (int riverSearch = 1; riverSearch < 10; riverSearch++) {
                        // check east block
                        state = world.getBlockState(getDirectionalPos(pos, Direction.E, riverSearch));
                        if (state != WATER) {
                            firstBankDir = Direction.E;
                            firstBankPos = getDirectionalPos(pos, Direction.E, riverSearch);
                            break;
                        }

                        // check north east block
                        state = world.getBlockState(getDirectionalPos(pos, Direction.NE, riverSearch));
                        if (state != WATER) {
                            firstBankDir = Direction.NE;
                            firstBankPos = getDirectionalPos(pos, Direction.NE, riverSearch);
                            break;
                        }

                        // check south east block
                        state = world.getBlockState(getDirectionalPos(pos, Direction.SE, riverSearch));
                        if (state != WATER) {
                            firstBankDir = Direction.SE;
                            firstBankPos = getDirectionalPos(pos, Direction.SE, riverSearch);
                            break;
                        }
                    }

                    // make sure we actually found a bank before continuing
                    if (firstBankDir != null) {
                        Direction secondBankDir = null;
                        BlockPos secondBankPos = null;
                        Direction dirToTest = null;

                        switch (firstBankDir) {
                            case NE:
                                dirToTest = Direction.SW;
                                break;
                            case E:
                                dirToTest = Direction.W;
                                break;
                            case SE:
                                dirToTest = Direction.NW;
                                break;
                            default:
                                // how did you get here
                                break;
                        }

                        for (int riverSearch = 1; riverSearch < 7; riverSearch++) {
                            state = world.getBlockState(getDirectionalPos(pos, dirToTest, riverSearch));
                            if (state != WATER) {
                                secondBankDir = dirToTest;
                                secondBankPos = getDirectionalPos(pos, dirToTest, riverSearch);
                                break;
                            }
                        }

                        if (secondBankDir != null) {

                            // check that at least one edge of the dam is touching a forest type biome
                            Biome firstBankBiome = world.getBiome(getDirectionalPos(firstBankPos, firstBankDir, 4));
                            Biome secondBankBiome = world.getBiome(getDirectionalPos(secondBankPos, secondBankDir, 4));
                            if (BiomeDictionary.hasType(firstBankBiome, BiomeDictionary.Type.FOREST)
                                    || BiomeDictionary.hasType(secondBankBiome, BiomeDictionary.Type.FOREST)) {

                                DAM_FEATURE.generate(world, rand, firstBankPos, secondBankPos, firstBankDir);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBiomeDecoratePost(DecorateBiomeEvent.Post event) {
        Random rand = event.getRand();
        World world = event.getWorld();

        // Add mossy stone blobs to jungle biome
        for (int n = 0; n < mossyStoneFrequency; n++) {
            int x = rand.nextInt(8) + 12;
            int z = rand.nextInt(8) + 12;
            BlockPos pos = new BlockPos(event.getChunkPos().getXStart() + x, 0, event.getChunkPos().getZStart() + z);
            if (world.getBiome(pos) == Biomes.JUNGLE) {
                pos = pos.add(0, rand.nextInt(event.getWorld().getTopSolidOrLiquidBlock(pos).getY() - 10) + 5, 0);
                MOSSY_FEATURE.generate(event.getWorld(), event.getRand(), pos);
            }
        }

        for (int i = 0; i < swampMudFrequency; i++) {
            int x = rand.nextInt(8) + 12;
            int z = rand.nextInt(8) + 12;
            BlockPos pos = new BlockPos(event.getChunkPos().getXStart() + x, 0, event.getChunkPos().getZStart() + z);
            if (world.getBiome(pos) == Biomes.SWAMPLAND) {
                pos = pos.add(0, rand.nextInt(event.getWorld().getTopSolidOrLiquidBlock(pos).getY() - 10) + 5, 0);
                if (world.getBlockState(pos).getBlock() == Blocks.WATER) {
                    world.setBlockState(pos, MudBlocks.MUD.getDefaultState());
                }
            }
        }

    }

    private BlockPos getDirectionalPos(BlockPos pos, Direction dir, int numBlocksAway) {
        switch (dir) {
            case NE:
                return pos.add(numBlocksAway, 0, -numBlocksAway);
            case E:
                return pos.add(numBlocksAway, 0, 0);
            case SE:
                return pos.add(numBlocksAway, 0, numBlocksAway);
            case NW:
                return pos.add(-numBlocksAway, 0, -numBlocksAway);
            case W:
                return pos.add(-numBlocksAway, 0, 0);
            case SW:
                return pos.add(-numBlocksAway, 0, numBlocksAway);
            default:
                return pos;
        }

    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    public enum Direction {NE, E, SE, NW, W, SW}
}