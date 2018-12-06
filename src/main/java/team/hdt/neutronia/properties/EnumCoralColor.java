package team.hdt.neutronia.properties;

import net.minecraft.util.IStringSerializable;

public enum EnumCoralColor implements IStringSerializable {

    BLUE(0, "tube"),
    PINK(1, "brain"),
    PURPLE(2, "bubble"),
    RED(3, "fire"),
    YELLOW(4, "horn"),
    CYAN(5, "prismarine"),
    BROWN(6, "earth"),
    LIGHT_BLUE(7, "diamond"),
    LIME(8, "emerald"),
    ORANGE(9, "lava"),
    GREEN(10, "moss");

    private static final EnumCoralColor[] META_LOOKUP = new EnumCoralColor[values().length];

    static {
        for (EnumCoralColor enumdyecolor : values()) {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }

    private final int meta;
    private final String name;

    EnumCoralColor(int metaIn, String newName) {
        this.meta = metaIn;
        this.name = newName;
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

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

}