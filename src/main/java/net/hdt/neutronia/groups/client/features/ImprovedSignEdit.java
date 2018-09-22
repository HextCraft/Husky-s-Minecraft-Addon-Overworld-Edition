package net.hdt.neutronia.groups.client.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.lib.LibObfuscation;
import net.hdt.neutronia.groups.client.gui.GuiBetterEditSign;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Side.CLIENT)
public class ImprovedSignEdit extends Component {

    public static boolean enableCancel, enableClear, enableShift;

    @Override
    public void setupConfig() {
        enableCancel = loadProperty("Enable Cancel Button", true).get();
        enableClear = loadProperty("Enable Clear Button", true).get();
        enableShift = loadProperty("Enable Shift Button", true).get();
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
    public String getDescription() {
        return "This makes so we can edit signs that are already placed";
    }
}