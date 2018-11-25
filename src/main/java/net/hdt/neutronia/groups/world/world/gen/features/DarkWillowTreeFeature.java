package net.hdt.neutronia.groups.world.world.gen.features;

import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.block.BlockMidnightLeaves;
import com.mushroom.midnight.common.block.BlockMidnightLog;
import com.mushroom.midnight.common.registry.ModBlocks;
import com.mushroom.midnight.common.util.WorldUtil;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DarkWillowTreeFeature extends NeutroniaAbstractFeature {
    private static final ResourceLocation[] TEMPLATES = new ResourceLocation[] {
            new ResourceLocation(LibMisc.MOD_ID, "trees/dark_willow_1"),
            new ResourceLocation(LibMisc.MOD_ID, "trees/dark_willow_2"),
            new ResourceLocation(LibMisc.MOD_ID, "trees/dark_willow_3")
    };

    public DarkWillowTreeFeature(IBlockState log, IBlockState leaves) {
        super(log, leaves);
    }

    public DarkWillowTreeFeature() {
        this(ModBlocks.DARK_WILLOW_LOG.getDefaultState(), ModBlocks.DARK_WILLOW_LEAVES.getDefaultState());
    }

    @Override
    public boolean placeFeature(World world, Random rand, BlockPos origin) {
        if (!this.canGrow(world, origin)) {
            return false;
        }

        ResourceLocation templateId = TEMPLATES[rand.nextInt(TEMPLATES.length)];

        MinecraftServer server = world.getMinecraftServer();
        TemplateManager templateManager = world.getSaveHandler().getStructureTemplateManager();

        PlacementSettings placementSettings = this.buildPlacementSettings(rand);

        Template template = templateManager.getTemplate(server, templateId);

        BlockPos correctedOrigin = this.computeCorrectedOrigin(origin, template, placementSettings);
        if (correctedOrigin == null) {
            Midnight.LOGGER.warn("Template '{}' did not have required 'origin' data block", templateId);
            return false;
        }

        Map<BlockPos, String> data = template.getDataBlocks(correctedOrigin, placementSettings);

        BlockPos trunkTop = this.reverseLookupFirst(data, "trunk_top");
        Collection<BlockPos> trunkCorners = this.reverseLookup(data, "trunk_corner");
        if (trunkTop == null || trunkCorners.isEmpty()) {
            LibMisc.LOGGER.warn("Template '{}' did not have required 'trunk_top' and 'trunk_corner' data blocks", templateId);
            return false;
        }

        BlockPos minCorner = WorldUtil.min(trunkCorners).add(1, 0, 1);
        BlockPos maxCorner = WorldUtil.max(trunkCorners).add(-1, 0, -1);

        for (BlockPos pos : BlockPos.getAllInBoxMutable(minCorner, maxCorner)) {
            if (!this.canGrow(world, pos)) {
                return false;
            }
        }

        if (!this.canFit(world, trunkTop, minCorner, maxCorner)) {
            return false;
        }

        template.addBlocksToWorld(world, correctedOrigin, this::processState, placementSettings, 2 | 16);

        this.processDataBlocks(world, data);

        return true;
    }

    private boolean canFit(World world, BlockPos trunkTop, BlockPos minCorner, BlockPos maxCorner) {
        BlockPos maxFit = new BlockPos(maxCorner.getX(), trunkTop.getY(), maxCorner.getZ());

        for (BlockPos pos : BlockPos.getAllInBoxMutable(minCorner, maxFit)) {
            if (!this.canReplace(world, pos)) {
                return false;
            }
        }

        return true;
    }

    private Template.BlockInfo processState(World world, BlockPos pos, Template.BlockInfo info) {
        IBlockState state = info.blockState;
        if (state.getBlock() instanceof BlockMidnightLog) {
            return new Template.BlockInfo(pos, this.log, null);
        } else if (state.getBlock() instanceof BlockMidnightLeaves) {
            return new Template.BlockInfo(pos, this.leaves, null);
        } else if (state.getBlock() == Blocks.STRUCTURE_BLOCK || state.getBlock() instanceof BlockAir) {
            return null;
        }
        return info;
    }

    private void processDataBlocks(World world, Map<BlockPos, String> data) {
        for (Map.Entry<BlockPos, String> entry : data.entrySet()) {
            BlockPos pos = entry.getKey();
            String tag = entry.getValue();
            if (tag.equals("origin") || tag.equals("trunk_top")) {
                world.setBlockState(pos, this.log, 2 | 16);
            }
        }
    }

    @Nullable
    private BlockPos computeCorrectedOrigin(BlockPos origin, Template template, PlacementSettings placementSettings) {
        Map<BlockPos, String> dataBlocks = template.getDataBlocks(BlockPos.ORIGIN, placementSettings);
        BlockPos localOrigin = this.reverseLookupFirst(dataBlocks, "origin");
        if (localOrigin == null) {
            return null;
        }
        return origin.subtract(localOrigin);
    }

    private PlacementSettings buildPlacementSettings(Random rand) {
        Rotation[] rotations = Rotation.values();
        Mirror[] mirrors = Mirror.values();

        return new PlacementSettings()
                .setRotation(rotations[rand.nextInt(rotations.length)])
                .setMirror(mirrors[rand.nextInt(mirrors.length)]);
    }

    @Nullable
    private BlockPos reverseLookupFirst(Map<BlockPos, String> data, String value) {
        List<BlockPos> result = this.reverseLookup(data, value);
        return !result.isEmpty() ? result.get(0) : null;
    }

    private List<BlockPos> reverseLookup(Map<BlockPos, String> data, String value) {
        List<BlockPos> result = new ArrayList<>();
        for (Map.Entry<BlockPos, String> entry : data.entrySet()) {
            if (entry.getValue().equals(value)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}