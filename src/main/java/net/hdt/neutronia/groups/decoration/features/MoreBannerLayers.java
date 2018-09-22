package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;

public class MoreBannerLayers extends Component {

    public static int layers;

    public static int getLayerCount() {
        return GroupLoader.isComponentEnabled(MoreBannerLayers.class) ? layers : 6;
    }

    @Override
    public void setupConfig() {
        layers = loadProperty("Survival Layer Count", 16).get();
    }

    @Override
    public String getDescription() {
        return "Let's banners have more layers in survival";
    }


}
