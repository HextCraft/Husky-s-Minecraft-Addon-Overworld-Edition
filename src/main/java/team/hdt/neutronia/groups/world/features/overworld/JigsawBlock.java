package team.hdt.neutronia.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.world.blocks.BlockJigsaw;
import team.hdt.neutronia.groups.world.blocks.tileEntity.TileEntityJigsaw;

public class JigsawBlock extends Component {

    private Block jigsawBlock;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        jigsawBlock = new BlockJigsaw();
        GameRegistry.registerTileEntity(TileEntityJigsaw.class, "neutronia:jigsaw");
    }
}
