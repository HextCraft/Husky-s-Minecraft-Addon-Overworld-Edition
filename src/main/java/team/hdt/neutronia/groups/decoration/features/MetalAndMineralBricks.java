package team.hdt.neutronia.groups.decoration.features;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.decoration.blocks.BlockSpecialBrick;

public class MetalAndMineralBricks extends Component {

    public static BlockSpecialBrick diamondBricks;
    public static BlockSpecialBrick emeraldBricks;
    public static BlockSpecialBrick ironBricks;
    public static BlockSpecialBrick goldBricks;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        diamondBricks = new BlockSpecialBrick(Material.IRON, "diamond_bricks", CreativeTabs.DECORATIONS, 5.0F, 10.0F);
        emeraldBricks = new BlockSpecialBrick(Material.IRON, "emerald_bricks", CreativeTabs.DECORATIONS, 5.0F, 10.0F);
        ironBricks = new BlockSpecialBrick(Material.IRON, "iron_bricks", CreativeTabs.DECORATIONS, 5.0F, 10.0F);
        goldBricks = new BlockSpecialBrick(Material.IRON, "gold_bricks", CreativeTabs.DECORATIONS, 3.0F, 10.0F);
    }

}