package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;

public class BlockWoodenLantern extends BlockMod implements INeutroniaBlock {

    public BlockWoodenLantern(BlockPlanks.EnumType woodType) {
        super(String.format("%s_lantern", woodType.getName()), Material.WOOD);
        this.setTickRandomly(true);
        setLightLevel(0.9375F);
    }

}