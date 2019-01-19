package team.abnormal.neutronia.blocks.materials;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialPart extends Material {

    public MaterialPart() {
        super(MapColor.AIR);
    }

    /**
     * Returns true if the block is a considered solid. This is true by default.
     */
    @Override
    public boolean isSolid()
    {
        return false;
    }

    /**
     * Returns if this material is considered solid or not
     */
    @Override
    public boolean blocksMovement() {
        return false;
    }

}