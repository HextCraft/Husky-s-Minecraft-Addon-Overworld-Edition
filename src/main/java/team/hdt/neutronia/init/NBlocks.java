package team.hdt.neutronia.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.blocks.overworld.BlockFireflyBulb;

public class NBlocks {

    public static final Block fireflyBulbOff, fireflyBulbOn;
    // Sea Blocks
    private static final Block wrautnaut, wrautnautOld, wrautnautPorthole;

    static {
        wrautnaut = new BlockNeutroniaBase(Material.IRON, "wrautnaut", false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        wrautnautOld = new BlockNeutroniaBase(Material.IRON, "old_wrautnaut", false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        wrautnautPorthole = new BlockNeutroniaBase(Material.IRON, "wrautnaut_porthole", false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        //Misc
        fireflyBulbOff = new BlockFireflyBulb(false);
        fireflyBulbOn = new BlockFireflyBulb(true);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

}