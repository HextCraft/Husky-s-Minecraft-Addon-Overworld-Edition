package team.hdt.neutronia_legacy.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaHotGlowing;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.blocks.base.BlockGlassBase;
import team.hdt.neutronia_legacy.blocks.base.BlockRodBase;
import team.hdt.neutronia_legacy.groups.decoration.blocks.BlockAsh;
import team.hdt.neutronia_legacy.groups.decoration.blocks.BlockBurnedBones;
import team.hdt.neutronia_legacy.groups.decoration.blocks.BlockNetherSponge;
import team.hdt.neutronia_legacy.properties.EnumGlowingNetherBlocks;

public class NetherBlocks extends Component {

    public static final Block[] glowingNetherBlocks = new Block[24];
    public static Block netherGlass, netherRod, netherSponge, ash, burnedBones;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // Nether Blocks
        netherGlass = new BlockGlassBase("nether_glass").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        netherRod = new BlockRodBase("nether_rod", true);
        netherSponge = new BlockNetherSponge();
        burnedBones = new BlockBurnedBones();
        ash = new BlockAsh();

        for (EnumGlowingNetherBlocks enumGlowingNetherBlocks : EnumGlowingNetherBlocks.values()) {
            glowingNetherBlocks[enumGlowingNetherBlocks.getMetadata()] = new BlockNeutroniaHotGlowing(Material.GLASS, enumGlowingNetherBlocks.getName());
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}