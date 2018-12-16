package team.hdt.neutronia_legacy.groups.earlyGame.features;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.items.ItemNeutroniaBase;
import team.hdt.neutronia_legacy.items.base.tools.*;

public class ClayTools extends Component {

    public static ItemNeutroniaBase UNFIRED_CLAY_AXE, UNFIRED_CLAY_PICKAXE, UNFIRED_CLAY_SWORD, UNFIRED_CLAY_SHOVEL, UNFIRED_CLAY_HOE;
    public static BaseAxe FIRED_CLAY_AXE;
    public static BasePickaxe FIRED_CLAY_PICKAXE;
    public static BaseSword FIRED_CLAY_SWORD;
    public static BaseShovel FIRED_CLAY_SHOVEL;
    public static BaseHoe FIRED_CLAY_HOE;
    private Item.ToolMaterial CLAY = EnumHelper.addToolMaterial("clay", 0, 95, 3.0F, 0.5F, 15);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        UNFIRED_CLAY_AXE = new ItemNeutroniaBase("unfired_clay_axe");
        UNFIRED_CLAY_PICKAXE = new ItemNeutroniaBase("unfired_clay_pickaxe");
        UNFIRED_CLAY_SWORD = new ItemNeutroniaBase("unfired_clay_sword");
        UNFIRED_CLAY_SHOVEL = new ItemNeutroniaBase("unfired_clay_shovel");
        UNFIRED_CLAY_HOE = new ItemNeutroniaBase("unfired_clay_hoe");
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