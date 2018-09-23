package net.hdt.neutronia.blocks.overworld;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.util.Reference;
import net.minecraft.block.material.Material;

public class BlockColoredWool extends BlockMod {

    public BlockColoredWool(String name, Material materialIn, String[] variants) {
        super(name, materialIn, variants);
    }


    @Override
    public String getModNamespace() {
        return Reference.MOD_ID;
    }

}
