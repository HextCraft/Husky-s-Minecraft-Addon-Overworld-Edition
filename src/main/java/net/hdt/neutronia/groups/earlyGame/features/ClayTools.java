package net.hdt.neutronia.groups.earlyGame.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.init.NCreativeTabs;
import net.hdt.neutronia.items.ItemBase;
import net.hdt.neutronia.items.base.tools.*;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClayTools extends Component {

    public static ItemBase UNFIRED_CLAY_AXE, UNFIRED_CLAY_PICKAXE, UNFIRED_CLAY_SWORD, UNFIRED_CLAY_SHOVEL, UNFIRED_CLAY_HOE;
    public static BaseAxe FIRED_CLAY_AXE;
    public static BasePickaxe FIRED_CLAY_PICKAXE;
    public static BaseSword FIRED_CLAY_SWORD;
    public static BaseShovel FIRED_CLAY_SHOVEL;
    public static BaseHoe FIRED_CLAY_HOE;
    private Item.ToolMaterial CLAY = EnumHelper.addToolMaterial("clay", 0, 95, 3.0F, 0.5F, 15);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        UNFIRED_CLAY_AXE = new ItemBase("unfired_clay_axe", NCreativeTabs.ITEM_EXPANSION_TAB);
        UNFIRED_CLAY_PICKAXE = new ItemBase("unfired_clay_pickaxe", NCreativeTabs.ITEM_EXPANSION_TAB);
        UNFIRED_CLAY_SWORD = new ItemBase("unfired_clay_sword", NCreativeTabs.ITEM_EXPANSION_TAB);
        UNFIRED_CLAY_SHOVEL = new ItemBase("unfired_clay_shovel", NCreativeTabs.ITEM_EXPANSION_TAB);
        UNFIRED_CLAY_HOE = new ItemBase("unfired_clay_hoe", NCreativeTabs.ITEM_EXPANSION_TAB);
        FIRED_CLAY_AXE = new BaseAxe("fired_clay_axe", CLAY);
        FIRED_CLAY_PICKAXE = new BasePickaxe("fired_clay_pickaxe", CLAY);
        FIRED_CLAY_SWORD = new BaseSword("fired_clay_sword", CLAY);
        FIRED_CLAY_SHOVEL = new BaseShovel("fired_clay_shovel", CLAY);
        FIRED_CLAY_HOE = new BaseHoe("fired_clay_hoe", CLAY);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}