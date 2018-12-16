package team.hdt.neutronia_revamped.base.blocks;

import net.minecraft.block.material.Material;
import team.hdt.neutronia_revamped.blocks.INeutroniaBlock;

public class BlockNeutroniaDoor extends BlockModDoor implements INeutroniaBlock {

    public BlockNeutroniaDoor(String name) {
        this(Material.WOOD, name);
    }

    public BlockNeutroniaDoor(Material material, String name) {
        super(material, name);
    }

}