package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.material.Material;

public enum PlantBehaviorType {
    BUSH(Material.VINE, true, true),
    FLOWER(Material.PLANTS, false, false);

    private final Material material;
    private final boolean shearable;
    private final boolean replacable;

    PlantBehaviorType(Material material, boolean shearable, boolean replacable) {
        this.material = material;
        this.shearable = shearable;
        this.replacable = replacable;
    }

    public Material getMaterial() {
        return this.material;
    }

    public boolean isShearable() {
        return this.shearable;
    }

    public boolean isReplacable() {
        return this.replacable;
    }
}