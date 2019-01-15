package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.abnormalib.block.BlockMod;

public class BlockMinecraftBase extends BlockMod implements IMinecraftBlock {

    public BlockMinecraftBase(String name, Material materialIn) {
        super(name, materialIn);
    }

    public BlockMinecraftBase(Material material, String name) {
        super(name, material);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    public BlockMinecraftBase(Material material, String name, CreativeTabs creativetab, float hardness, float resistance, SoundType soundType) {
        super(name, material);
        setCreativeTab(creativetab);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

}
