package team.hdt.neutronia.base.client.gui.utils;

import net.minecraft.inventory.Slot;
import team.hdt.neutronia.base.client.gui.components.BaseComponent;

public interface ISlotBackgroundRenderer {
    public void render(BaseComponent gui, Slot slot);
}