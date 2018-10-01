package net.hdt.neutronia.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MRBlock extends BlockMod implements INeutroniaBlock {

    protected String name;

    public MRBlock(Material material, String name, CreativeTabs creativetab, float hardness, float resistance, float slipperiness, float lightValue, SoundType soundType) {
        super(name, material);
        setCreativeTab(creativetab);
        setHardness(hardness);
        setResistance(resistance);
        setDefaultSlipperiness(slipperiness);
        setLightLevel(this.lightValue);
        setSoundType(soundType);
    }

}
