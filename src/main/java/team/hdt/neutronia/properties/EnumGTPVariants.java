package team.hdt.neutronia.properties;

import net.minecraft.util.IStringSerializable;

public enum EnumGTPVariants implements IStringSerializable {

    BLACK("black", 0),
    BLUE("blue", 1),
    BROWN("brown", 2),
    CYAN("cyan", 3),
    GRAY("gray", 4),
    GREEN("green", 5),
    LIGHT_BLUE("light_blue", 6),
    LIME("lime", 7),
    ORANGE("orange", 8),
    PURPLE("purple", 9),
    RED("red", 10),
    SILVER("silver", 11),
    YELLOW("yellow", 12);

    private String name;
    private int id;

    EnumGTPVariants(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

}