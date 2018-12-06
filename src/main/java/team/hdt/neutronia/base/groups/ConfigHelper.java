package team.hdt.neutronia.base.groups;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigHelper {

    static boolean needsRestart;
    static boolean allNeedRestart = false;
    static Property lastProp;

    public static int loadPropInt(String propName, String category, String desc, String comment, int default_, int min, int max) {
        Property prop = GroupLoader.config.get(category, propName, default_, comment, min, max);
        prop.setComment(desc);
        setNeedsRestart(prop);

        lastProp = prop;
        return prop.getInt(default_);
    }

    public static int loadPropInt(String propName, String category, String desc, int default_) {
        Property prop = GroupLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        lastProp = prop;
        return prop.getInt(default_);
    }

    public static double loadPropDouble(String propName, String category, String desc, double default_) {
        Property prop = GroupLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        lastProp = prop;
        return prop.getDouble(default_);
    }

    public static boolean loadPropBool(String propName, String category, String desc, boolean default_) {
        Property prop = GroupLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        lastProp = prop;
        return prop.getBoolean(default_);
    }

    public static String loadPropString(String propName, String category, String desc, String default_) {
        Property prop = GroupLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        lastProp = prop;
        return prop.getString();
    }

    public static String[] loadPropStringList(String propName, String category, String desc, String[] default_) {
        Property prop = GroupLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        lastProp = prop;
        return prop.getStringList();
    }

    private static ItemStack stackFromString(String name) {
        String[] split = name.split(":");
        if (split.length > 1) {
            int meta = 0;
            if (split.length > 2) {
                if (split[2].equalsIgnoreCase("*"))
                    meta = OreDictionary.WILDCARD_VALUE;
                else
                    meta = Integer.parseInt(split[2]);
            }
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(split[0], split[1]));
            if (item != null) {
                return new ItemStack(item, 1, meta);
            }
        }
        return ItemStack.EMPTY;
    }

    public static List<ItemStack> loadItemStackList(String propName, String category, String desc, String[] default_) {
        return Arrays.stream(loadPropStringList(propName, category, desc, default_)).map(ConfigHelper::stackFromString).collect(Collectors.toList());
    }

    private static void setNeedsRestart(Property prop) {
        if (needsRestart)
            prop.setRequiresMcRestart(true);
        needsRestart = allNeedRestart;
    }

}
