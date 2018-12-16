package team.hdt.neutronia_legacy.base.client.gui.elements;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import team.hdt.neutronia_legacy.base.groups.Group;
import team.hdt.neutronia_legacy.base.lib.LibMisc;

public class CustomGroupButton extends CustomIconButton {

    public final Group group;

    public CustomGroupButton(int x, int y, Group group) {
        super(0, x, y, 150, 34, new ResourceLocation(LibMisc.MOD_ID, "textures/misc/config_screen_misc.png"), I18n.translateToLocal("neutronia_legacy.config.group." + group.getName().toLowerCase().replace(" ", "_")));
        this.group = group;
    }

    @Override
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        super.drawCenteredString(fontRendererIn, text, x, y - 6, color);
    }

}