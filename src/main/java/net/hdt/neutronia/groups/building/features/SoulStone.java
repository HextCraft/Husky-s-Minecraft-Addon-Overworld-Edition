package net.hdt.neutronia.groups.building.features;

import net.hdt.huskylib2.block.BlockModSlab;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.blocks.slab.BlockVanillaSlab;
import net.hdt.neutronia.properties.EnumSoulStoneTypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SoulStone extends Component {

    public static final Block[] soulStone = new Block[3];
    public static final Block[] soulStoneSlabSingle = new Block[3];
    private static final Block[] soulStoneSlabDouble = new Block[3];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumSoulStoneTypes soulStoneTypes : EnumSoulStoneTypes.values()) {
            soulStone[soulStoneTypes.getMetadata()] = new BlockNeutroniaBase(Material.ROCK, soulStoneTypes.getName());
            soulStoneSlabSingle[soulStoneTypes.getMetadata()] = new BlockVanillaSlab(soulStoneTypes.getName(), soulStone[soulStoneTypes.getMetadata()].getDefaultState(), false);
            soulStoneSlabDouble[soulStoneTypes.getMetadata()] = new BlockVanillaSlab(soulStoneTypes.getName(), soulStone[soulStoneTypes.getMetadata()].getDefaultState(), true);
            BlockModSlab.initSlab(soulStone[soulStoneTypes.getMetadata()], 0, (BlockModSlab) soulStoneSlabSingle[soulStoneTypes.getMetadata()], (BlockModSlab) soulStoneSlabDouble[soulStoneTypes.getMetadata()]);
        }

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[0]),
                "SS", "SS",
                'S', ProxyRegistry.newStack(Blocks.SOUL_SAND));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[2], 4),
                "SS", "SS",
                'S', ProxyRegistry.newStack(soulStone[0], 1));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[1], 1),
                "S", "S",
                'S', ProxyRegistry.newStack(soulStoneSlabSingle[0], 1));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
