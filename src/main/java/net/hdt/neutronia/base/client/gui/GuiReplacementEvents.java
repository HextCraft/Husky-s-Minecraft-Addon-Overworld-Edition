package net.hdt.neutronia.base.client.gui;

import net.hdt.neutronia.base.client.gui.screens.MainMenu;
import net.hdt.neutronia.base.client.gui.screens.ModList;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Side.CLIENT)
public class GuiReplacementEvents {

    private static GuiScreen mainMenu;

    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu) {
            event.setGui(new MainMenu());
        }
        if(event.getGui() instanceof GuiModList) {
            if(event.getGui() instanceof GuiMainMenu) {
                mainMenu = new GuiMainMenu();
            } else if(event.getGui() instanceof MainMenu) {
                mainMenu = new MainMenu();
            }
            event.setGui(new ModList(mainMenu));
        }
    }

}
