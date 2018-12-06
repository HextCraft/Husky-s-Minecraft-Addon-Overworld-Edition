package team.hdt.neutronia.groups.decoration.blocks.corals;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockDecorativeCoralBlockBase extends BlockMod implements INeutroniaBlock {

    public BlockDecorativeCoralBlockBase(String name) {
        super(name, Material.CORAL);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

}
