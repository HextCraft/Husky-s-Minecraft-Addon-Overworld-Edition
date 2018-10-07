package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBurnedBones extends BlockNeutroniaPillar {

    public BlockBurnedBones() {
        super(Material.ROCK, "burned_bones");
        this.setHardness(2.0F);
        this.setSoundType(SoundType.STONE);
    }

}
