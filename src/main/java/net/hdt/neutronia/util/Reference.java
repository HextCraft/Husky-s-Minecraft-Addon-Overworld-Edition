package net.hdt.neutronia.util;

import net.hdt.neutronia.client.gui.GuiNeutroniaConfig;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class Reference {

    public static final String MOD_ID = "neutronia";
    public static final String NAME = "Neutronia";
    public static final String VERSION = "0.2.1";
    public static final String DEPENDENCIES = "required-before:hl2;";
    public static final String UPDATE_JSON = "https://gist.githubusercontent.com/sindrefag/be19331edf8ae73628450304918911cc/raw/76bfdb4aa14b5ac631cfa1861548351247a2350e/update_json_neutronia.json";
    public static final String CLIENT_PROXY = "net.hdt.neutronia.proxy.ClientProxy";
    public static final String SERVER_PROXY = "net.hdt.neutronia.proxy.CommonProxy";
    public static final String GUI_FACTORY = "net.hdt.neutronia.client.gui.GuiFactory";

    public static final String DESC = "This mod aims for extending biomes, blocks, items, structures and entities for all of the Minecraft dimensions. So go and create a new world and explore!";
    public static final List<String> AUTHORS = new ArrayList<>();
    public static final String CREDITS = "Thanks to Nox, Goopy25, AguilaPapi, AbsolemJackdaw, SnakeBlock, Dion6103, YoshiSalad, _Zontic_, Igrek and King of Creepers for their awesome art. " +
            "Also Mistral__ for the amazing logo and banner he made :) And Axoladdy and KingVampyre for their awesome ideas. Thanks to MCvinnyq, Dion6103, cybercat5555 and AguilaPapi for their amazing entity models";
    public static final String URL = "https://github.com/HuskysDevelopmentTeam/Neutronia";
    public static final String LOGO = "/assets/neutronia/textures/logo.png";

    static {
        AUTHORS.add(TextFormatting.RED + "HuskyTheArtist");
        AUTHORS.add(TextFormatting.GREEN + "Blommm");
        AUTHORS.add(TextFormatting.BLUE + "Polar");
    }

}
