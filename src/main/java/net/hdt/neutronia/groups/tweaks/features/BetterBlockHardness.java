package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class BetterBlockHardness extends Component {

    private static void rebalancedVanillaHardness() {
        Blocks.STONE.setHardness(2.25F).setResistance(10.0F);
        Blocks.PLANKS.setHardness(1.0F).setResistance(5.0F);
        Blocks.LOG.setHardness(1.25F);
        Blocks.LOG2.setHardness(1.25F);
        Blocks.SANDSTONE.setHardness(1.5F);
        Blocks.CHEST.setHardness(1.5F);
        Blocks.CRAFTING_TABLE.setHardness(1.5F);
        Blocks.FURNACE.setHardness(3.0F);
        Blocks.LIT_FURNACE.setHardness(3.0F);

        Blocks.OAK_DOOR.setHardness(1.5F);
        Blocks.SPRUCE_DOOR.setHardness(1.5F);
        Blocks.BIRCH_DOOR.setHardness(1.5F);
        Blocks.JUNGLE_DOOR.setHardness(1.5F);
        Blocks.ACACIA_DOOR.setHardness(1.5F);
        Blocks.DARK_OAK_DOOR.setHardness(1.5F);

        Blocks.STONE_PRESSURE_PLATE.setHardness(1.5F);
        Blocks.JUKEBOX.setHardness(1.5F).setResistance(10.0F);
        Blocks.OAK_FENCE.setHardness(1.5F);
        Blocks.SPRUCE_FENCE.setHardness(1.5F);
        Blocks.BIRCH_FENCE.setHardness(1.5F);
        Blocks.JUNGLE_FENCE.setHardness(1.5F);
        Blocks.ACACIA_FENCE.setHardness(1.5F);
        Blocks.DARK_OAK_FENCE.setHardness(1.5F);
        Blocks.GLOWSTONE.setHardness(0.6F);
        Blocks.TRAPDOOR.setHardness(1.5F);
        Blocks.MONSTER_EGG.setHardness(1.5F);
        Blocks.STONEBRICK.setHardness(2.25F);

        Blocks.OAK_FENCE_GATE.setHardness(1.5F).setResistance(5.0F);
        Blocks.SPRUCE_FENCE_GATE.setHardness(1.5F).setResistance(5.0F);
        Blocks.BIRCH_FENCE_GATE.setHardness(1.5F).setResistance(5.0F);
        Blocks.JUNGLE_FENCE_GATE.setHardness(1.5F).setResistance(5.0F);
        Blocks.ACACIA_FENCE_GATE.setHardness(1.5F).setResistance(5.0F);
        Blocks.DARK_OAK_FENCE_GATE.setHardness(1.5F).setResistance(5.0F);

        Blocks.WOODEN_SLAB.setHardness(1.0F).setResistance(5.0F);
        Blocks.QUARTZ_ORE.setHardness(1.0F).setResistance(5.0F);
        Blocks.QUARTZ_BLOCK.setHardness(2.0F);
        Blocks.QUARTZ_STAIRS.setHardness(2.0F);
    }

    private static void applyHCHardness() {
        Blocks.FIRE.setFireInfo(Blocks.LEAVES, 60, 100);
        Blocks.FIRE.setFireInfo(Blocks.LEAVES2, 60, 100);
        rebalancedVanillaHardness();
    }

    @Override
    public String getDescription() {
        return "Makes certain block hardness more \"realistic\"";
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public void init(FMLInitializationEvent event) {
        applyHCHardness();
    }

}
