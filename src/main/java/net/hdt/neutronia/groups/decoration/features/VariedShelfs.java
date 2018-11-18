package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockShelf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VariedShelfs extends Component {

    private static Block[] shelf = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            shelf[type.getMetadata()] = new BlockShelf(type);
        }
    }

}
