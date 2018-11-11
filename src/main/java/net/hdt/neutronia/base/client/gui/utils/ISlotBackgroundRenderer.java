package net.hdt.neutronia.base.client.gui.utils;

import net.hdt.neutronia.base.client.gui.components.BaseComponent;
import net.minecraft.inventory.Slot;

public interface ISlotBackgroundRenderer {
    public void render(BaseComponent gui, Slot slot);
}