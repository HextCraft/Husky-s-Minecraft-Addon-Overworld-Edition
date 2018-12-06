package team.hdt.neutronia.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.world.blocks.BlockMud;

public class MudBlocks extends Component {

    public static Block MUD, DRIED_MUD, MUD_BRICKS, DRIED_MUD_BRICKS;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MUD = new BlockMud();
        DRIED_MUD = new BlockNeutroniaBase(Material.ROCK, "dried_mud");
        MUD_BRICKS = new BlockNeutroniaBase(Material.GROUND, "mud_bricks");
        DRIED_MUD_BRICKS = new BlockNeutroniaBase(Material.ROCK, "dried_mud_bricks");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        GameRegistry.addSmelting(Item.getItemFromBlock(MUD_BRICKS), new ItemStack(DRIED_MUD_BRICKS, 4), 0.1f);
        GameRegistry.addSmelting(Item.getItemFromBlock(MUD), new ItemStack(DRIED_MUD, 4), 0.1f);
    }

}
