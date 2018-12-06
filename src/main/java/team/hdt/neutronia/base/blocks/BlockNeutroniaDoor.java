package team.hdt.neutronia.base.blocks;

import net.minecraft.block.material.Material;

public class BlockNeutroniaDoor extends BlockModDoor implements INeutroniaBlock {

    public BlockNeutroniaDoor(String name) {
        this(Material.WOOD, name);
    }

    public BlockNeutroniaDoor(Material material, String name) {
        super(material, name);
    }

}