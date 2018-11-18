package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaHotGlowing;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockGlassBase;
import net.hdt.neutronia.blocks.base.BlockRodBase;
import net.hdt.neutronia.groups.decoration.blocks.BlockAsh;
import net.hdt.neutronia.groups.decoration.blocks.BlockBurnedBones;
import net.hdt.neutronia.groups.decoration.blocks.BlockNetherSponge;
import net.hdt.neutronia.properties.EnumGlowingNetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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