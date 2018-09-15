package net.hdt.neutronia.properties;

import net.minecraft.util.IStringSerializable;

public enum EnumCoralColor implements IStringSerializable {

    BLUE(0, "blue", "tube"),
    PINK(1, "pink", "brain"),
    PURPLE(2, "purple", "bubble"),
    RED(3, "red", "fire"),
    YELLOW(4, "yellow", "horn"),
    CYAN(5, "cyan", "prismarine"),
    BROWN(6, "brown", "earth"),
    LIGHT_BLUE(7, "light_blue", "diamond"),
    LIME(8, "lime", "emerald"),
    ORANGE(9, "orange", "lava");

    private static final EnumCoralColor[] META_LOOKUP = new EnumCoralColor[values().length];

    static {
        for (EnumCoralColor enumdyecolor : values()) {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }

    private final int meta;
    private final String oldName, newName;

    EnumCoralColor(int metaIn, String oldName) {
        this.meta = metaIn;
        this.oldName = oldName;
        this.newName = oldName;
    }

    EnumCoralColor(int metaIn, String oldName, String newName) {
        this.meta = metaIn;
        this.oldName = oldName;
        this.newName = newName;
    }

    public static EnumCoralColor byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMetadata() {
        return this.meta;
    }

    public String getNewName() {
        return newName;
    }

    public String toString() {
        return this.oldName;
    }

    public String getName() {
        return this.oldName;
    }

}