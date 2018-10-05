package net.hdt.neutronia.blocks.overworld;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockTest extends BlockMod implements INeutroniaBlock {

    public BlockTest() {
        super(Builder.create(Material.ROCK).slipperiness(0F).hardnessAndResistance(2.0F, 10.0F), "test_block");
    }

}
