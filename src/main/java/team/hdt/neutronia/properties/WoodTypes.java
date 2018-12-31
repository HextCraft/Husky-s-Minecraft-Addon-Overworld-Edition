package team.hdt.neutronia.properties;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum WoodTypes implements IStringSerializable {

    OAK(0, "oak", MapColor.ADOBE),
    SPRUCE(1, "spruce", MapColor.OBSIDIAN),
    BIRCH(2, "birch", MapColor.SAND),
    JUNGLE(3, "jungle", MapColor.DIRT),
    ACACIA(4, "acacia", MapColor.ADOBE),
    DARK_OAK(5, "dark_oak", MapColor.BROWN),
    BAMBOO(6, "bamboo", MapColor.DIRT),
    PALM(7, "palm", MapColor.ADOBE),
    WILLOW(8, "willow", MapColor.GRASS),
    CHERRY(9, "cherry", MapColor.PINK);

    private static final WoodTypes[] META_LOOKUP = new WoodTypes[values().length];

    static {
        for (WoodTypes blockplanks$enumtype : values()) {
            META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
        }
    }

    private final int meta;
    private final String name;
    private final String unlocalizedName;
    /**
     * The color that represents this entry on a map.
     */
    private final MapColor mapColor;

    WoodTypes(int metaIn, String nameIn, MapColor mapColorIn) {
        this(metaIn, nameIn, nameIn, mapColorIn);
    }

    WoodTypes(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn) {
        this.meta = metaIn;
        this.name = nameIn;
        this.unlocalizedName = unlocalizedNameIn;
        this.mapColor = mapColorIn;
    }

    public static WoodTypes byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMetadata() {
        return this.meta;
    }

    /**
     * The color which represents this entry on a map.
     */
    public MapColor getMapColor() {
        return this.mapColor;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public String getTranslationKey() {
        return this.unlocalizedName;
    }
}