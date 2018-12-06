package team.hdt.neutronia.properties;

import net.minecraft.util.IStringSerializable;

public enum EnumNaturalAquamarineVariants implements IStringSerializable {

    CRACKED_BRICKS(0, "natural_cracked_aquamarine_bricks"),
    MOSSY_BRICKS(1, "natural_mossy_aquamarine_bricks"),
    CRACKED_CHISELED(2, "natural_cracked_chiseled_aquamarine"),
    MOSSY_CHISELED(3, "natural_mossy_chiseled_aquamarine"),
    CRACKED_PLANKS(4, "natural_cracked_aquamarine_planks"),
    MOSSY_PLANKS(5, "natural_mossy_aquamarine_planks"),
    CRACKED_ROUGH_PLANKS(6, "natural_cracked_aquamarine_rough_planks"),
    MOSSY_ROUGH_PLANKS(7, "natural_mossy_aquamarine_rough_planks"),
    RAW(8, "raw_natural_aquamarine"),
    CRACKED_SMALL_BRICKS(9, "natural_cracked_small_aquamarine_bricks"),
    MOSSY_SMALL_BRICKS(10, "natural_mossy_small_aquamarine_bricks"),
    CRACKED_SMOOTH(11, "natural_cracked_smooth_aquamarine"),
    MOSSY_SMOOTH(12, "natural_mossy_smooth_aquamarine");

    private String name;
    private int ID;

    EnumNaturalAquamarineVariants(int ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

}