package net.hdt.neutronia.groups.world.blocks;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.BlockModDoor;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockEndDoor extends BlockModDoor implements INeutroniaBlock {

    public BlockEndDoor(Material materialIn, String name) {
        super(materialIn, name);
        setCreativeTab(Neutronia.CREATIVE_TAB);
    }

}
