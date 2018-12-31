package team.hdt.neutronia.world.template;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface TemplateDataProcessor {
    void process(World world, BlockPos pos, String key);
}