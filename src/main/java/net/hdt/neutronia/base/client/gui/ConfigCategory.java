package net.hdt.neutronia.base.client.gui;

import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

class ConfigCategory extends GuiConfig {

    ConfigCategory(GuiScreen parentScreen, String baseCategory) {
        super(parentScreen, getAllElements(baseCategory), LibMisc.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(GroupLoader.config.toString()));
    }

    private static List<IConfigElement> getAllElements(String baseCategory) {
        return new ArrayList<>(new ConfigElement(GroupLoader.config.getCategory(baseCategory)).getChildElements());
    }

}