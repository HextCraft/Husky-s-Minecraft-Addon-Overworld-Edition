package team.hdt.neutronia.base.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import team.hdt.neutronia.base.groups.GroupLoader;
import team.hdt.neutronia.base.lib.LibMisc;

import java.util.ArrayList;
import java.util.List;

public class ConfigCategory extends GuiConfig {

    public ConfigCategory(GuiScreen parentScreen, String baseCategory) {
        super(parentScreen, getAllElements(baseCategory), LibMisc.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(GroupLoader.config.toString()));
    }

    private static List<IConfigElement> getAllElements(String baseCategory) {
        return new ArrayList<>(new ConfigElement(GroupLoader.config.getCategory(baseCategory)).getChildElements());
    }

}