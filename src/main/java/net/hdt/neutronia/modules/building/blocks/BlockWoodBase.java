package net.hdt.neutronia.modules.building.blocks;

import net.hdt.huskylib2.blocks.BlockMod;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockWoodBase extends BlockMod {

    public BlockWoodBase(Material material, String name, boolean flammable) {
        super(material, Reference.MOD_ID, name);
        setCreativeTab(NCreativeTabs.OVERWORLD_EXPANSION_TAB);
        if (flammable) {
            addFlammable(this);
        }
        setSoundType(SoundType.WOOD);
    }

    public static void addFlammable(Block block) {
        Blocks.FIRE.setFireInfo(block, 5, 20);
    }

}
