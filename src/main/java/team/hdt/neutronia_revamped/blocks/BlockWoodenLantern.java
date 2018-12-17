package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.huskylib.block.BlockMod;

public class BlockWoodenLantern extends BlockMod implements INeutroniaBlock {

    public BlockWoodenLantern(BlockPlanks.EnumType woodType) {
        super(String.format("%s_lantern", woodType.getName()), Material.WOOD);
        setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTickRandomly(true);
        setLightLevel(0.9375F);
    }

}