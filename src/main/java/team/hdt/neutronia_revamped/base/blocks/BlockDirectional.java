package team.hdt.neutronia_revamped.base.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;

public abstract class BlockDirectional extends BlockMod {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    protected BlockDirectional(Material materialIn, String name) {
        super(name, materialIn);
    }

}