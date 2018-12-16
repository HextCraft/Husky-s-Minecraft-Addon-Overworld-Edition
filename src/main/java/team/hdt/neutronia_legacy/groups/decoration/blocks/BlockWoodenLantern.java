package team.hdt.neutronia_legacy.groups.decoration.blocks;

import net.minecraft.creativetab.CreativeTabs;
import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockWoodenLantern extends BlockMod implements INeutroniaBlock {

    public BlockWoodenLantern(BlockPlanks.EnumType woodType) {
        super(String.format("%s_lantern", woodType.getName()), Material.WOOD);
        setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTickRandomly(true);
        setLightLevel(0.9375F);
    }

}