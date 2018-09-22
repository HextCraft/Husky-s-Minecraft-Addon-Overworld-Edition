package net.hdt.neutronia.api.modules.impl;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public interface BlockAdder  {

    void registerBlocks(RegistryEvent.Register<Block> event);

}
