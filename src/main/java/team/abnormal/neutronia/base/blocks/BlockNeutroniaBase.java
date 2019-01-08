package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.abnormalib.block.BlockMod;
import team.abnormal.neutronia.blocks.INeutroniaBlock;

public class BlockNeutroniaBase extends BlockMod implements INeutroniaBlock {

    public BlockNeutroniaBase(String name, Material materialIn) {
        super(name, materialIn);
    }

    public BlockNeutroniaBase(Material material, String name) {
        super(name, material);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    public BlockNeutroniaBase(Material material, String name, CreativeTabs creativetab, float hardness, float resistance, SoundType soundType) {
        super(name, material);
        setCreativeTab(creativetab);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

}
