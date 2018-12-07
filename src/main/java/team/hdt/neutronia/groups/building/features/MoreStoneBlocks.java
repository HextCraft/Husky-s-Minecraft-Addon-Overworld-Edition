package team.hdt.neutronia.groups.building.features;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.properties.EnumNewStoneVariants;

@team.hdt.neutronia.base.new_groups.Component(name = "More Stone Blocks", icon = "minecraft:bricks")
public class MoreStoneBlocks extends team.hdt.neutronia.base.groups.Component {

    //Stone Blocks
    public static final Block[] newStoneVariants = new Block[28];
    public static int limestoneVeinFrequency = 50, marbleVeinFrequency = 50;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // New Stone Blocks
        for (EnumNewStoneVariants newStoneVariant : EnumNewStoneVariants.values()) {
            newStoneVariants[newStoneVariant.getMetadata()] = new BlockNeutroniaBase(BlockMod.Builder.create(Material.ROCK).hardnessAndResistance(3.0F, 5.0F), newStoneVariant.getName());
        }
        /*for (EnumNewStoneVariantsSlabsAndStairs newStoneVariantsSlabsAndStairs : EnumNewStoneVariantsSlabsAndStairs.values()) {
            VanillaStairsAndSlabs.add(newStoneVariantsSlabsAndStairs.getName(), newStoneVariants[newStoneVariantsSlabsAndStairs.getMetadata()], 0, true, false, true);
        }
        VanillaStairsAndSlabs.add("andesite_cobble", newStoneVariants[EnumNewStoneVariants.ANDESITE_COBBLE.getMetadata()], 0, false, true, true);
        VanillaStairsAndSlabs.add("granite_cobble", newStoneVariants[EnumNewStoneVariants.GRANITE_COBBLE.getMetadata()], 0, false, true, true);*/
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}