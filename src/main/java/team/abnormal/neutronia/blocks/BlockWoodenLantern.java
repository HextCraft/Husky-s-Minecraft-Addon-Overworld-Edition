package team.abnormal.neutronia.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.abnormalib.block.BlockMod;
import team.abnormal.neutronia.properties.WoodTypes;

public class BlockWoodenLantern extends BlockMod implements INeutroniaBlock {

    public BlockWoodenLantern(WoodTypes woodType) {
        super(String.format("%s_lantern", woodType.getName()), Material.WOOD);
        setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTickRandomly(true);
        setLightLevel(0.9375F);
    }

    public BlockWoodenLantern(BlockPlanks.EnumType woodType) {
        super(String.format("%s_lantern", woodType.getName()), Material.WOOD);
        setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTickRandomly(true);
        setLightLevel(0.9375F);
    }

}