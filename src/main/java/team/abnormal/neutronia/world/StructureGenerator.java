/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.UnmodifiableIterator
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCactus
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.BlockStaticLiquid
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityLockableLoot
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.Mirror
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraft.world.gen.structure.template.PlacementSettings
 *  net.minecraft.world.gen.structure.template.Template
 *  net.minecraft.world.gen.structure.template.TemplateManager
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package team.abnormal.neutronia.world;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class StructureGenerator extends WorldGenerator {
    private static final int VARIATION = 3;
    private String structureName;

    public StructureGenerator(String structureName) {
        this.structureName = structureName;
    }

    public boolean generate(World world, Random rand, BlockPos position) {
        WorldServer worldServer = (WorldServer)world;
        MinecraftServer minecraftServer = world.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.get(minecraftServer, new ResourceLocation("neutronia", this.structureName));
        if (template == null) {
            System.err.println("The structure template: " + this.structureName + " did not exist!");
            return false;
        }
        if (this.structureName.endsWith("_")) {
            Rotation rotation = Rotation.values()[rand.nextInt(VARIATION)];
            PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(rotation).setIgnoreStructureBlock(false);
            template.addBlocksToWorld(world, position, settings);
            Map dataBlocks = template.getDataBlocks(position, settings);
            for (Object entry : dataBlocks.entrySet()) {
                try {
                    String[] data = ((String)entry).split(" ");
                    if (data.length < 2) continue;
                    Block block = Block.getBlockFromName(data[0]);
                    IBlockState state;
                    state = data.length == 3 ? block.getStateFromMeta(Integer.parseInt(data[2])) : Objects.requireNonNull(block).getDefaultState();
                    for (Map.Entry entry2 : block.getDefaultState().getProperties().entrySet()) {
                        if (!((IProperty)entry2.getKey()).getValueClass().equals(EnumFacing.class) || !((IProperty)entry2.getKey()).getName().equals("facing")) continue;
                        state = state.withRotation(rotation.add(Rotation.CLOCKWISE_180));
                        break;
                    }
                    world.setBlockState((BlockPos)entry, state, 3);
                    TileEntity te = world.getTileEntity((BlockPos)entry);
                    if (!(te instanceof TileEntityLockableLoot)) continue;
                    ((TileEntityLockableLoot)te).setLootTable(new ResourceLocation(data[1]), rand.nextLong());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (AdditionalStructures.activateDebugMode) {
                System.out.println("Special structure (" + this.structureName + ") generated at: x=" + position.getX() + ", y=" + position.getY() + ", z=" + position.getZ());
            }
            return true;
        }
        if (StructureGenerator.canSpawnHere(template, worldServer, position)) {
            Rotation rotation = Rotation.values()[rand.nextInt(VARIATION)];
            PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(rotation).setIgnoreStructureBlock(false);
            template.addBlocksToWorld(world, position, settings);
            Map dataBlocks = template.getDataBlocks(position, settings);
            for (Object entry : dataBlocks.entrySet()) {
                try {
                    String[] data = ((String)entry).split(" ");
                    if (data.length < 2) continue;
                    Block block = Block.getBlockFromName(data[0]);
                    IBlockState state;
                    state = data.length == 3 ? block.getStateFromMeta(Integer.parseInt(data[2])) : block.getDefaultState();
                    for (Map.Entry entry2 : block.getDefaultState().getProperties().entrySet()) {
                        if (!((IProperty)entry2.getKey()).getValueClass().equals(EnumFacing.class) || !((IProperty)entry2.getKey()).getName().equals("facing")) continue;
                        state = state.withRotation(rotation.add(Rotation.CLOCKWISE_180));
                        break;
                    }
                    world.setBlockState((BlockPos)entry, state, 3);
                    TileEntity te = world.getTileEntity((BlockPos)entry);
                    if (!(te instanceof TileEntityLockableLoot)) continue;
                    ((TileEntityLockableLoot)te).setLootTable(new ResourceLocation(data[1]), rand.nextLong());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (AdditionalStructures.activateDebugMode) {
                System.out.println("Structure (" + this.structureName + ") generated at: x=" + position.getX() + ", y=" + position.getY() + ", z=" + position.getZ());
            }
            int posX = position.getX();
            int posY = position.getY() - 1;
            int posZ = position.getZ();
            for (int x = 0; x < template.getSize().getX(); ++x) {
                for (int z = 0; z < template.getSize().getZ(); ++z) {
                    for (int y = 0; y < 10; ++y) {
                        if (world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR) continue;
                        if (BiomeDictionary.hasType(world.getBiome(position), BiomeDictionary.Type.SANDY)) {
                            world.setBlockState(new BlockPos(posX, posY, posZ), Blocks.SAND.getDefaultState());
                        } else if (BiomeDictionary.hasType(world.getBiome(position), BiomeDictionary.Type.NETHER)) {
                            world.setBlockState(new BlockPos(posX, posY, posZ), Blocks.NETHERRACK.getDefaultState());
                        } else if (BiomeDictionary.hasType(world.getBiome(position), BiomeDictionary.Type.END)) {
                            world.setBlockState(new BlockPos(posX, posY, posZ), Blocks.END_STONE.getDefaultState());
                        } else {
                            world.setBlockState(new BlockPos(posX, posY, posZ), Blocks.DIRT.getDefaultState());
                        }
                        if (AdditionalStructures.activateDebugMode && position.getY() > 0) {
                            System.out.println("Foundation for " + this.structureName + " generated at: x=" + posX + ", y=" + posY + ", z=" + posZ + " with rotation: " + settings.getRotation());
                        }
                        --posY;
                    }
                    posY = position.getY() - 1;
                    if (settings.getRotation() == Rotation.NONE) {
                        ++posZ;
                        continue;
                    }
                    if (settings.getRotation() == Rotation.CLOCKWISE_180) {
                        --posZ;
                        continue;
                    }
                    if (settings.getRotation() == Rotation.CLOCKWISE_90) {
                        ++posZ;
                        continue;
                    }
                    if (settings.getRotation() != Rotation.COUNTERCLOCKWISE_90) continue;
                    --posZ;
                }
                posZ = position.getZ();
                if (settings.getRotation() == Rotation.NONE) {
                    ++posX;
                    continue;
                }
                if (settings.getRotation() == Rotation.CLOCKWISE_180) {
                    --posX;
                    continue;
                }
                if (settings.getRotation() == Rotation.CLOCKWISE_90) {
                    ++posX;
                    continue;
                }
                if (settings.getRotation() != Rotation.COUNTERCLOCKWISE_90) continue;
                --posX;
            }
            return true;
        }
        if (AdditionalStructures.activateDebugMode) {
            System.err.println("ERROR! Not accepted position for (" + this.structureName + ") at " + position);
        }
        return false;
    }

    public static boolean canSpawnHere(Template template, World world, BlockPos pos) {
        return StructureGenerator.isCornerValid(world, pos) && StructureGenerator.isCornerValid(world, pos.add(template.getSize().getX(), 0, 0)) && StructureGenerator.isCornerValid(world, pos.add(template.getSize().getX(), 0, template.getSize().getZ())) && StructureGenerator.isCornerValid(world, pos.add(0, 0, template.getSize().getZ()));
    }

    public static boolean isCornerValid(World world, BlockPos pos) {
        int groundY = StructureGenerator.getGroundFromAbove(world, pos.getX(), pos.getZ());
        return groundY > pos.getY() - 3 && groundY < pos.getY() + 3;
    }

    public static int getGroundFromAbove(World world, int x, int z) {
        int y = world.getActualHeight();
        boolean foundGround = false;
        while (!foundGround && y-- > 0) {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = block != Blocks.AIR && block != Blocks.TALLGRASS && block != Blocks.SNOW_LAYER && block != Blocks.LEAVES && block != Blocks.LEAVES2 && block != Blocks.LOG && block != Blocks.LOG2 && block != Blocks.RED_FLOWER && block != Blocks.YELLOW_FLOWER && block != Blocks.CACTUS && block != Blocks.WATERLILY;
        }
        BlockPos pos = new BlockPos(x, y - 1, z);
        if (world.provider.getDimension() == -1) {
            int minY = 100;
            Chunk chunk = world.getChunk(pos);
            IBlockState block1 = chunk.getBlockState(x & 15, y, z & 15);
            IBlockState block2 = chunk.getBlockState(x & 15, y - 1, z & 15);
            while ((!block1.equals(Blocks.AIR.getBlockState()) || !block2.equals(Blocks.NETHERRACK.getBlockState())) && --y >= minY) {
                block1 = block2;
                block2 = chunk.getBlockState(x & 15, y - 1, z & 15);
            }
        }
        if (world.getBlockState(pos).getBlock() == Blocks.WATER && !BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.OCEAN)) {
            y = -99;
        }
        if (world.getBlockState(pos).getBlock() == Blocks.LAVA || world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == Blocks.ICE || world.getBlockState(pos).getBlock() == Blocks.PACKED_ICE) {
            y = -99;
        }
        return y;
    }
}

