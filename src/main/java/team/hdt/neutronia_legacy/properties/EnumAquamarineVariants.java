package team.hdt.neutronia_legacy.properties;

import net.minecraft.util.IStringSerializable;

public enum EnumAquamarineVariants implements IStringSerializable {

    BRICKS(0, "aquamarine_bricks"),
    CHISELED(0, "chiseled_aquamarine"),
    RAW(0, "raw_aquamarine"),
    SMALL_BRICKS(0, "small_aquamarine_bricks"),
    SMOOTH(0, "smooth_aquamarine");

    private String name;
    private int ID;

    EnumAquamarineVariants(int ID, String name) {
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