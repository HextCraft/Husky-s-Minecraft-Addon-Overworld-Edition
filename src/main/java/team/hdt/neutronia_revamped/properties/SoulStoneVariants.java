package team.hdt.neutronia_revamped.properties;

import net.minecraft.util.IStringSerializable;

public enum SoulStoneVariants implements IStringSerializable {

    NORMAL_SOULSTONE(0, "normal_soulstone"),
    CHISELED_SOULSTONE(1, "chiseled_soulstone"),
    SMOOTH_SOULSTONE(2, "smooth_soulstone"),
    POLISHED_SOULSTONE(3, "polished_soulstone");

    private static final SoulStoneVariants[] META_LOOKUP = new SoulStoneVariants[values().length];

    static {
        for (SoulStoneVariants blockstone$enumtype : values()) {
            META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
        }
    }

    private final int meta;
    private final String name;

    SoulStoneVariants(int p_i46384_3_, String p_i46384_5_) {
        this.meta = p_i46384_3_;
        this.name = p_i46384_5_;
    }

    public static SoulStoneVariants byMetadata(int meta) {
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
