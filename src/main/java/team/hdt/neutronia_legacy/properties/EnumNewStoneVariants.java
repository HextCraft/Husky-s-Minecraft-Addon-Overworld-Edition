package team.hdt.neutronia_legacy.properties;

import net.minecraft.util.IStringSerializable;

public enum EnumNewStoneVariants implements IStringSerializable {

    RAW_BASALT(0, "raw_basalt"),
    SMOOTH_BASALT(1, "smooth_basalt"),
    CHISELED_BASALT(2, "chiseled_basalt"),
    BASALT_BRICKS(3, "basalt_bricks"),
    BASALT_COBBLE(4, "basalt_cobble"),
    RAW_LIMESTONE(5, "raw_limestone"),
    POLISHED_LIMESTONE(6, "polished_limestone"),
    RAW_MARBLE(7, "raw_marble"),
    SMOOTH_MARBLE(8, "smooth_marble"),
    CHISELED_MARBLE(9, "chiseled_marble"),
    MARBLE_BRICKS(10, "marble_bricks"),
    MARBLE_COBBLE(11, "marble_cobble"),
    RAW_METEORITE(12, "raw_meteorite"),
    METEORITE_BRICKS(13, "meteorite_bricks"),
    SMOOTH_METEORITE(14, "smooth_meteorite"),
    GRANITE_COBBLE(15, "granite_cobble"),
    ANDESITE_COBBLE(16, "andesite_cobble"),
    BLUE_ARDUIN(17, "blue_arduin"),
    SMOOTH_BLUE_ARDUIN(18, "smooth_blue_arduin"),
    RUST_ARDUIN(19, "rust_arduin"),
    SMOOTH_RUST_ARDUIN(20, "smooth_rust_arduin"),
    TINTED_ARDUIN(21, "tinted_arduin"),
    SMOOTH_TINTED_ARDUIN(22, "smooth_tinted_arduin"),
    RED_COBBLE(23, "red_cobble"),
    MIXED_COBBLE(24, "mixed_cobble"),
    DARK_ANDESITE(25, "dark_andesite"),
    POLISHED_DARK_ANDESITE(26, "polished_dark_andesite"),
    DARK_ANDESITE_BRICKS(27, "dark_andesite_bricks");

    private static final EnumNewStoneVariants[] META_LOOKUP = new EnumNewStoneVariants[values().length];

    static {
        for (EnumNewStoneVariants blockstone$enumtype : values()) {
            META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
        }
    }

    private final int meta;
    private final String name;

    EnumNewStoneVariants(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public static EnumNewStoneVariants byMetadata(int meta) {
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
