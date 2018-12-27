package team.hdt.neutronia_legacy.base.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import team.hdt.neutronia_legacy.base.util.Config;
import team.hdt.neutronia_legacy.base.util.StructureHelper;

import java.util.Map;
import java.util.Random;

public class WorldGenFeatureStructure extends WorldGenFeature {
    private ResourceLocation structure;
    private Type type;
    private Mirror mirror;
    private Rotation rotation;
    private Block ignoredBlock;
    private float clearancePercentage;

    public WorldGenFeatureStructure(Config config) {
        super(config);
        structure = config.getResource("structure");
        type = config.getEnum("type", Type.class, Type.GROUNDED);
        mirror = config.getEnum("mirror", Mirror.class, Mirror.NONE);
        rotation = config.getEnum("rotation", Rotation.class, Rotation.NONE);
        ignoredBlock = config.getBlock("ignoredBlock", Blocks.STRUCTURE_VOID.getDefaultState()).getBlock();
        clearancePercentage = config.getFloat("clearancePercentage", 0.875F);
    }

    public WorldGenFeatureStructure(int genAttempts, float genProbability, boolean randomizeGenAttempts, int minGenHeight, int maxGenHeight, ResourceLocation structure, Type type, Mirror mirror, Rotation rotation, Block ignoredBlock, float clearancePercentage) {
        super(genAttempts, genProbability, randomizeGenAttempts, minGenHeight, maxGenHeight);
        this.structure = structure;
        this.type = type;
        this.mirror = mirror;
        this.rotation = rotation;
        this.ignoredBlock = ignoredBlock;
        this.clearancePercentage = clearancePercentage;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        MinecraftServer server = world.getMinecraftServer();
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        Template template = manager.getTemplate(server, structure);
        PlacementSettings placementSettings = new PlacementSettings().setMirror(mirror).setRotation(rotation).setReplacedBlock(ignoredBlock).setRandom(rand);
        BlockPos structureSize = template.transformedSize(rotation);
        BlockPos spawnPos = null;

        if (type == Type.GROUNDED) {
            spawnPos = StructureHelper.getGroundedPos(world, pos, structureSize, clearancePercentage);
        } else if (type == Type.FLOATING) {
            spawnPos = StructureHelper.getFloatingPos(world, pos, structureSize, clearancePercentage);
        } else if (type == Type.HANGING) {
            spawnPos = StructureHelper.getHangingPos(world, pos, structureSize, clearancePercentage);
        } else if (type == Type.BURIED) {
            spawnPos = StructureHelper.getBuriedPos(world, pos, structureSize, clearancePercentage);
        }

        if (spawnPos != null && spawnPos.getY() >= minGenHeight && spawnPos.getY() <= maxGenHeight) {
            template.addBlocksToWorld(world, spawnPos, placementSettings);
            handleDataBlocks(world, spawnPos, template, placementSettings, rand);
            return true;
        }

        return false;
    }

    private void handleDataBlocks(World world, BlockPos pos, Template template, PlacementSettings placementSettings, Random rand) {
        Map<BlockPos, String> map = template.getDataBlocks(pos, placementSettings);

        for (Map.Entry<BlockPos, String> entry : map.entrySet()) {
            BlockPos dataPos = entry.getKey();
            String[] data = entry.getValue().split("\\s+");

            if (data[0].equals("chest") && data.length == 2) {
                world.setBlockState(dataPos, Blocks.CHEST.correctFacing(world, dataPos, Blocks.CHEST.getDefaultState()));
                TileEntityChest chest = (TileEntityChest) world.getTileEntity(dataPos);

                if (chest != null) {
                    chest.setLootTable(new ResourceLocation(data[1]), rand.nextLong());
                }
            } else if (data[0].equals("spawner") && data.length == 2) {
                world.setBlockState(dataPos, Blocks.MOB_SPAWNER.getDefaultState());
                TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(dataPos);

                if (spawner != null) {
                    MobSpawnerBaseLogic logic = spawner.getSpawnerBaseLogic();
                    NBTTagCompound compound = new NBTTagCompound();
                    logic.writeToNBT(compound);
                    compound.removeTag("SpawnPotentials");
                    logic.readFromNBT(compound);
                    logic.setEntityId(new ResourceLocation(data[1]));
                    spawner.markDirty();
                    IBlockState state = world.getBlockState(dataPos);
                    world.notifyBlockUpdate(pos, state, state, 3);
                }
            } else if (data[0].equals("entity") && data.length == 2) {
                Entity entity = EntityList.newEntity(EntityList.getClass(new ResourceLocation(data[1])), world);

                if (entity != null) {
                    entity.setPosition(dataPos.getX() + 0.5F, dataPos.getY(), dataPos.getZ() + 0.5F);
                    world.spawnEntity(entity);
                }
            } else {
                world.setBlockToAir(dataPos);
            }
        }
    }

    public enum Type {
        GROUNDED,
        FLOATING,
        HANGING,
        BURIED
    }
}