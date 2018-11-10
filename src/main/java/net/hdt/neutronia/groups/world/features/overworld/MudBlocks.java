package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockMud;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MudBlocks extends Component {

    public static Block MUD, MUD_BRICKS, DRIED_MUD_BRICKS;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MUD = new BlockMud();
        MUD_BRICKS = new BlockNeutroniaBase(Material.GROUND, "mud_bricks");
        DRIED_MUD_BRICKS = new BlockNeutroniaBase(Material.ROCK, "dried_mud_bricks");
        FurnaceRecipes.instance().addSmelting(new ItemStack(MUD_BRICKS).getItem(), new ItemStack(DRIED_MUD_BRICKS, 4), 0.2f);
    }
}
