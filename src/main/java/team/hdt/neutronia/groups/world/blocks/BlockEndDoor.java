package team.hdt.neutronia.groups.world.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.blocks.BlockModDoor;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockEndDoor extends BlockModDoor implements INeutroniaBlock {

    public BlockEndDoor(Material materialIn, String name) {
        super(materialIn, name);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

}
