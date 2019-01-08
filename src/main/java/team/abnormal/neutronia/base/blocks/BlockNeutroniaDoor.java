package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.neutronia.blocks.INeutroniaBlock;

public class BlockNeutroniaDoor extends BlockModDoor implements INeutroniaBlock {

    public BlockNeutroniaDoor(String name) {
        this(Material.WOOD, name);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

    public BlockNeutroniaDoor(Material material, String name) {
        super(material, name);
    }

}