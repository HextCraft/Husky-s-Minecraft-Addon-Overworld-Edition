package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.huskylib.block.BlockMod;

public class BlockNeutroniaBase extends BlockMod implements INeutroniaBlock {

    public BlockNeutroniaBase(String name, Material materialIn) {
        super(name, materialIn);
    }

    public BlockNeutroniaBase(Material material, String name, CreativeTabs creativetab, float hardness, float resistance, SoundType soundType) {
        super(name, material);
        setCreativeTab(creativetab);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

}
