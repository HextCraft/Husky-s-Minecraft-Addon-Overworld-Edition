package net.hdt.neutronia.blocks.nether;

import net.hdt.neutronia.blocks.base.BlockModPillar;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBurnedBones extends BlockModPillar {

    public BlockBurnedBones() {
        super("burned_bones", Material.ROCK);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.STONE);
        setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
    }

}
