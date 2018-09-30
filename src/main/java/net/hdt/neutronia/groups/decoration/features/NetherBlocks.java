package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockGlassBase;
import net.hdt.neutronia.blocks.base.BlockRodBase;
import net.hdt.neutronia.blocks.nether.BlockAsh;
import net.hdt.neutronia.blocks.nether.BlockBurnedBones;
import net.hdt.neutronia.blocks.nether.BlockNetherGlowingBase;
import net.hdt.neutronia.blocks.nether.BlockNetherSponge;
import net.hdt.neutronia.properties.EnumGlowingNetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.hdt.neutronia.init.NCreativeTabs.NETHER_EXPANSION_TAB;

public class NetherBlocks extends Component {

    private static Block netherGlass, netherRod, netherSponge, ash, burnedBones;
    private static Block netherbrickPillar;
    public static final Block[] glowingNetherBlocks = new Block[24];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // Nether Blocks
        netherGlass = new BlockGlassBase("nether_glass").setCreativeTab(NETHER_EXPANSION_TAB);
        netherRod = new BlockRodBase("nether_rod", NETHER_EXPANSION_TAB, true);
        netherSponge = new BlockNetherSponge();
        burnedBones = new BlockBurnedBones();
        ash = new BlockAsh();

        for (EnumGlowingNetherBlocks enumGlowingNetherBlocks : EnumGlowingNetherBlocks.values()) {
            glowingNetherBlocks[enumGlowingNetherBlocks.getMetadata()] = new BlockNetherGlowingBase(Material.GLASS, enumGlowingNetherBlocks.getName());
        }

        netherbrickPillar = new BlockNeutroniaPillar(Material.ROCK, "netherbrick_pillar").setCreativeTab(NETHER_EXPANSION_TAB);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}