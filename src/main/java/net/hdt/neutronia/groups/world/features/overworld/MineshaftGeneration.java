package net.hdt.neutronia.groups.world.features.overworld;

import com.google.common.collect.Sets;
import net.hdt.huskylib2.event.StructureSetBlockEvent;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.registry.block.recipe.BlockIngredient;
import net.hdt.neutronia.base.util.SetBlockIngredient;
import net.hdt.neutronia.groups.world.features.overworld.changers.IngredientChanger;
import net.hdt.neutronia.groups.world.features.overworld.changers.StructureChanger;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

public class MineshaftGeneration extends Component {
    public static Set<StructureChanger> MINESHAFT = Sets.newHashSet();
    public static StructureChanger MINESHAFT_CHANGER = StructureChanger.create(MINESHAFT, (w, p) -> true);

    @Override
    public String getFeatureDescription() {
        return "Mineshaft's now generate with logs instead of fences";
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public void init(FMLInitializationEvent event) {
        BlockIngredient fence = new SetBlockIngredient(Blocks.OAK_FENCE, Blocks.DARK_OAK_FENCE);
        MINESHAFT_CHANGER.addChanger(new MineshaftIngredientChanger(fence, Blocks.LOG.getDefaultState(), MapGenMineshaft.Type.NORMAL));
        MINESHAFT_CHANGER.addChanger(new MineshaftIngredientChanger(fence, Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK), MapGenMineshaft.Type.MESA));
    }


    @SubscribeEvent
    public void onStructureSetBlock(StructureSetBlockEvent event) {
        if (event.getComponent() instanceof StructureMineshaftPieces.Peice) {
            System.out.printf("/tp %s ~ %s\n", event.getPos().getX(), event.getPos().getZ());
            StructureChanger.convert(MINESHAFT, event);
        }
    }

    public static class MineshaftIngredientChanger extends IngredientChanger {
        protected MapGenMineshaft.Type type;

        public MineshaftIngredientChanger(BlockIngredient ingredient, IBlockState state, MapGenMineshaft.Type type) {
            super(ingredient, state);
            this.type = type;
        }

        @Override
        public boolean canChange(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
            return (structure instanceof StructureMineshaftPieces.Peice && ((StructureMineshaftPieces.Peice) structure).mineShaftType == this.type) && super.canChange(structure, world, pos, relativePos, original);
        }
    }
}
