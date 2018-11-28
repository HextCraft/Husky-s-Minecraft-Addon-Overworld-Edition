package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.*;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WroughtIron extends Component {

    private static Block wroughtIronBlock;
    private static Block wroughtIronDoor;
    private static Block wroughtIronBars;
    private static Block wroughtIronWall;
    private static Block wroughtIronStairs;
    private static MRSlab.Half wroughtIronSlabHalf;
    private static MRSlab.Double wroughtIronSlabDouble;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        wroughtIronBlock = new BlockNeutroniaBase(Material.IRON, "wrought_iron_block", 5.0F, 10.0F, 0.0F, SoundType.STONE);
        wroughtIronDoor = new BlockNeutroniaDoor(Material.IRON, "wrought_iron_door").setHardness(5.0F).setResistance(10.0F);
        wroughtIronBars = new BlockNeutroniaPane("wrought_iron_bars", Material.IRON).setHardness(5.0F).setResistance(10.0F);
        wroughtIronWall = new BlockNeutroniaWall("wrought_iron_wall", wroughtIronBlock.getDefaultState()).setHardness(5.0F).setResistance(10.0F);
        wroughtIronStairs = new BlockNeutroniaStairs("wrought_iron_stairs", wroughtIronBlock.getDefaultState()).setHardness(5.0F).setResistance(10.0F);
        wroughtIronSlabHalf = new MRSlab.Half("wrought_iron_slab", Material.IRON, wroughtIronBlock.getDefaultState(), 5.0F, 10.0F, SoundType.STONE, WroughtIron.wroughtIronSlabHalf);
        wroughtIronSlabDouble = new MRSlab.Double("wrought_iron_slab_double", Material.IRON, wroughtIronBlock.getDefaultState(), 5.0F, 10.0F, SoundType.STONE, wroughtIronSlabHalf);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}