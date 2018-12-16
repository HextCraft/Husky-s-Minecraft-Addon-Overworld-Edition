package team.hdt.neutronia_legacy.groups.client.features;

import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.lib.LibObfuscation;
import team.hdt.neutronia_legacy.groups.client.gui.GuiBetterEditSign;

public class ImprovedSignEdit extends Component {

    public static boolean enableCancel, enableClear, enableShift;

    @Override
    public void setupConfig() {
        enableCancel = loadPropBool("Enable Cancel Button", "", true);
        enableClear = loadPropBool("Enable Clear Button", "", true);
        enableShift = loadPropBool("Enable Shift Button", "", true);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onOpenGUI(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiEditSign) {
            TileEntitySign sign = ReflectionHelper.getPrivateValue(GuiEditSign.class, (GuiEditSign) event.getGui(), LibObfuscation.TILE_SIGN);
            event.setGui(new GuiBetterEditSign(sign));
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return isClient();
    }

}