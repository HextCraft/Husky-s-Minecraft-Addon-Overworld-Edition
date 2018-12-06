package team.hdt.neutronia.groups.building.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumWorldStoneVariants implements IStringSerializable {

    GRANITE_BRICKS(0, "granite_bricks"),
    DIORITE_BRICKS(1, "diorite_bricks"),
    ANDESITE_BRICKS(2, "andesite_bricks"),
    CHISELED_GRANITE_BRICKS(3, "chiseled_granite_bricks"),
    CHISELED_DIORITE_BRICKS(4, "chiseled_diorite_bricks"),
    CHISELED_ANDESITE_BRICKS(5, "chiseled_andesite_bricks"),
    CRACKED_GRANITE_BRICKS(6, "cracked_granite_bricks"),
    CRACKED_DIORITE_BRICKS(7, "cracked_diorite_bricks"),
    CRACKED_ANDESITE_BRICKS(8, "cracked_andesite_bricks"),
    MOSSY_GRANITE_BRICKS(9, "mossy_granite_bricks"),
    MOSSY_DIORITE_BRICKS(10, "mossy_diorite_bricks"),
    MOSSY_ANDESITE_BRICKS(11, "mossy_andesite_bricks");

    private int ID;
    private String name;

    EnumWorldStoneVariants(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }
}
