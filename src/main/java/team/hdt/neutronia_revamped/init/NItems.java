package team.hdt.neutronia_revamped.init;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import team.hdt.neutronia_revamped.items.ItemSeedFood;

public class NItems {

    public static Item SWEET_BERRIES;
    public static Item GOOSEBERRIES;
    public static Item BLUEBERRIES;
    public static Item RED_GRAPES;
    public static Item GREEN_GRAPES;
    public static Item BLACKBERRIES;
    public static Item POISON_BERRIES;
    public static Item RASPBERRIES;
    public static Item SOUR_BERRIES;
    public static Item CURRANTS;

    public static void init() {
        SWEET_BERRIES = new ItemSeedFood("sweet_berries", 2, 0.1F, NBlocks.SWEET_BERRY_BUSH);
        GOOSEBERRIES = new ItemSeedFood("gooseberries", 2, 0.1F, NBlocks.GOOSEBERRY_BUSH);
        BLUEBERRIES = new ItemSeedFood("blueberries", 2, 0.1F, NBlocks.BLUEBERRY_BUSH);
        RED_GRAPES = new ItemSeedFood("red_grapes", 2, 0.1F, NBlocks.RED_GRAPE_BUSH);
        GREEN_GRAPES = new ItemSeedFood("green_grapes", 2, 0.1F, NBlocks.GREEN_GRAPE_BUSH);
        BLACKBERRIES = new ItemSeedFood("blackberries", 2, 0.1F, NBlocks.BLACKBERRY_BUSH);
        POISON_BERRIES = new ItemSeedFood("poison_berries", 2, 0.1F, NBlocks.POISON_BERRY_BUSH).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.6F);
        RASPBERRIES = new ItemSeedFood("raspberries", 2, 0.1F, NBlocks.RASPBERRY_BUSH);
        SOUR_BERRIES = new ItemSeedFood("sour_berries", 2, 0.1F, NBlocks.SOUR_BERRY_BUSH);
        CURRANTS = new ItemSeedFood("currants", 2, 0.1F, NBlocks.CURRANT_BUSH);
    }

}