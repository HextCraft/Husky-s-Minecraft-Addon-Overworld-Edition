package team.hdt.neutronia.world.gen.features;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import team.hdt.neutronia.base.Reference;
import team.hdt.neutronia.base.utils.IStructure;
import team.hdt.neutronia.init.NBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructureFeature extends WorldGenerator implements IStructure {

    private static List<String> structureNames = new ArrayList<>();

    public StructureFeature(List<String> name) {
        structureNames = name;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        MinecraftServer mcServer = worldIn.getMinecraftServer();
        TemplateManager manager = worldServer.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, structureNames.get(rand.nextInt(structureNames.size())));
        Template template = manager.get(mcServer, location);

        if (template != null) {
            IBlockState state = worldIn.getBlockState(position);
            worldIn.notifyBlockUpdate(position, state, state, 3);
            template.addBlocksToWorldChunk(worldIn, position, settings);
        }

        if (template == null) {
            System.out.println("NO STRUCTURE");
            System.out.println(location.toString());
        }
        return true;
    }

}