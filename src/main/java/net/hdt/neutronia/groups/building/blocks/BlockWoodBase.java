package net.hdt.neutronia.groups.building.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockWoodBase extends BlockMod implements INeutroniaBlock {

    public BlockWoodBase(Material material, String name, boolean flammable) {
        super(name, material);
        setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
        if (flammable) {
            addFlammable(this);
        }
        setSoundType(SoundType.WOOD);
    }

    public static void addFlammable(Block block) {
        Blocks.FIRE.setFireInfo(block, 5, 20);
    }

}
