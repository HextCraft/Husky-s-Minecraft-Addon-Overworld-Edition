package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockSpecialBrick;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MetalAndMineralBricks extends Component {

    public static BlockSpecialBrick diamondBricks;
    public static BlockSpecialBrick emeraldBricks;
    public static BlockSpecialBrick ironBricks;
    public static BlockSpecialBrick goldBricks;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        diamondBricks = new BlockSpecialBrick(Material.IRON, "diamond_bricks", Neutronia.NEUTRONIA_MAIN, 5.0F, 10.0F);
        emeraldBricks = new BlockSpecialBrick(Material.IRON, "emerald_bricks", Neutronia.NEUTRONIA_MAIN, 5.0F, 10.0F);
        ironBricks = new BlockSpecialBrick(Material.IRON, "iron_bricks", Neutronia.NEUTRONIA_MAIN, 5.0F, 10.0F);
        goldBricks = new BlockSpecialBrick(Material.IRON, "gold_bricks", Neutronia.NEUTRONIA_MAIN, 3.0F, 10.0F);
    }

}