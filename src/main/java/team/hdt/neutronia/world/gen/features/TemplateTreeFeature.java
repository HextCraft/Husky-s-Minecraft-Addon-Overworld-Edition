package team.hdt.neutronia.world.gen.features;

import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.util.WorldUtil;
import com.mushroom.midnight.common.world.template.CompiledTemplate;
import com.mushroom.midnight.common.world.template.RotatedSettingConfigurator;
import com.mushroom.midnight.common.world.template.TemplateCompiler;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

import java.util.Collection;
import java.util.Random;

public class TemplateTreeFeature extends MidnightTreeFeature {
    private static final String ANCHOR_KEY = "origin";
    private static final String TRUNK_TOP_KEY = "trunk_top";
    private static final String TRUNK_CORNER_KEY = "trunk_corner";

    private final TemplateCompiler templateCompiler;

    public TemplateTreeFeature(ResourceLocation[] templates, IBlockState log, IBlockState leaves) {
        super(log, leaves);
        this.templateCompiler = this.buildCompiler(templates);
    }

    protected TemplateCompiler buildCompiler(ResourceLocation[] templates) {
        return TemplateCompiler.of(templates)
                .withAnchor(ANCHOR_KEY)
                .withSettingConfigurator(RotatedSettingConfigurator.INSTANCE)
                .withProcessor(this::processState)
                .withDataProcessor(this::processData);
    }

    @Override
    public boolean placeFeature(World world, Random rand, BlockPos origin) {
        if (!this.canGrow(world, origin)) {
            return false;
        }

        CompiledTemplate template = this.templateCompiler.compile(world, rand, origin);

        BlockPos trunkTop = template.lookupAny(TRUNK_TOP_KEY);
        Collection<BlockPos> trunkCorners = template.lookup(TRUNK_CORNER_KEY);
        if (trunkTop == null || trunkCorners.isEmpty()) {
            Midnight.LOGGER.warn("Template '{}' did not have required '{}' and '{}' data blocks", template, TRUNK_TOP_KEY, TRUNK_CORNER_KEY);
            return false;
        }

        BlockPos minCorner = WorldUtil.min(trunkCorners).add(1, 0, 1);
        BlockPos maxCorner = WorldUtil.max(trunkCorners).add(-1, 0, -1);

        if (!this.canGrow(world, minCorner, maxCorner) || !this.canFit(world, trunkTop, minCorner, maxCorner)) {
            return false;
        }

        this.notifyGrowth(world, origin);

        template.addTo(world, rand, 2 | 16);

        return true;
    }

    protected boolean canGrow(World world, BlockPos minCorner, BlockPos maxCorner) {
        for (BlockPos pos : BlockPos.getAllInBoxMutable(minCorner, maxCorner)) {
            if (!this.canGrow(world, pos)) {
                return false;
            }
        }
        return true;
    }

    protected boolean canFit(World world, BlockPos trunkTop, BlockPos minCorner, BlockPos maxCorner) {
        BlockPos maxFit = new BlockPos(maxCorner.getX(), trunkTop.getY(), maxCorner.getZ());

        for (BlockPos pos : BlockPos.getAllInBoxMutable(minCorner, maxFit)) {
            if (!this.canReplace(world, pos)) {
                return false;
            }
        }

        return true;
    }

    protected Template.BlockInfo processState(World world, BlockPos pos, Template.BlockInfo info) {
        IBlockState state = info.blockState;
        if (state.getBlock() instanceof BlockLog) {
            return new Template.BlockInfo(pos, this.log, null);
        } else if (state.getBlock() instanceof BlockLeaves) {
            return new Template.BlockInfo(pos, this.leaves, null);
        } else if (state.getBlock() == Blocks.STRUCTURE_BLOCK || state.getBlock() instanceof BlockAir) {
            return null;
        }
        return info;
    }

    protected void processData(World world, BlockPos pos, String key) {
        if (key.equals(ANCHOR_KEY) || key.equals(TRUNK_TOP_KEY)) {
            world.setBlockState(pos, this.log, 2 | 16);
        }
    }
}