package net.hdt.neutronia.groups.dimensions.properties;

import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public enum EnumMoonBlockVariants implements IStringSerializable {

    CARVED_MOON_STONE(0, "carved_moon_stone", Material.ROCK),
    CARVED_MOON_STONE_CRACKED(1, "carved_moon_stone_cracked", Material.ROCK),
    CHISELED_MOON_STONE(2, "chiseled_moon_stone", Material.ROCK),
    CHISELED_MOON_STONE_CRACKED(3, "chiseled_moon_stone_cracked", Material.ROCK),
    MOON_DUST(4, "moon_dust", Material.SAND),
    MOON_STONE(5, "moon_stone", Material.ROCK),
    SOLID_MOON_STONE(6, "solid_moon_stone", Material.ROCK);

    private String name;
    private int ID;
    private Material material;

    EnumMoonBlockVariants(int ID, String name, Material material) {
        this.name = name;
        this.ID = ID;
        this.material = material;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getID() {
        return ID;
    }

    public Material getMaterial() {
        return material;
    }

}