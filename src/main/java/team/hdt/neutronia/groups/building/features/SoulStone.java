package team.hdt.neutronia.groups.building.features;

import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.properties.EnumSoulStoneVariants;

public class SoulStone extends Component {

    public static final Block[] soulStone = new Block[4];

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        for (EnumSoulStoneVariants soulStoneTypes : EnumSoulStoneVariants.values()) {
            soulStone[soulStoneTypes.getMetadata()] = new BlockNeutroniaBase(Material.ROCK, soulStoneTypes.getName());
            VanillaStairsAndSlabs.add(soulStoneTypes.getName(), soulStone[soulStoneTypes.getMetadata()], 0, true);
        }

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[0]),
                "SS", "SS",
                'S', ProxyRegistry.newStack(Blocks.SOUL_SAND));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[2], 4),
                "SS", "SS",
                'S', ProxyRegistry.newStack(soulStone[0], 1));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[1], 1),
                "S", "S",
                'S', ProxyRegistry.newStack(Block.getBlockFromName("neutronia:normal_soulstone_slab"), 1));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
