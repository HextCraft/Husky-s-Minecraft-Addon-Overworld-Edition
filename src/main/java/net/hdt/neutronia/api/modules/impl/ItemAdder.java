package net.hdt.neutronia.api.modules.impl;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public interface ItemAdder  {

    void registerItems(RegistryEvent.Register<Item> event);

}
