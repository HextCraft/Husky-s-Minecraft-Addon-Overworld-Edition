package team.hdt.neutronia_legacy.groups.world.properties;

import net.minecraft.util.IStringSerializable;

public enum CrystalVariant implements IStringSerializable {

    PRASIONLITE(0, "prasionlite"),
    AJOITE(1, "ajoite"),
    CITRINE(2, "citrine"),
    BIXBITE(3, "bixbite"),
    CALCITE(4, "calcite");

    CrystalVariant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

}