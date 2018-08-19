package net.hdt.neutronia.groups.management.capability;

import net.hdt.huskylib2.interf.IDropInItem;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class BaseDropIn implements ICapabilityProvider, IDropInItem {

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == DROP_IN_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == DROP_IN_CAPABILITY ? (T) this : null;
    }

}
