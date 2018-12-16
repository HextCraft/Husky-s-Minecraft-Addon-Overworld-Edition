package team.hdt.neutronia_legacy.base.items;

import team.hdt.huskylib.interf.IVariantHolder;
import team.hdt.neutronia_legacy.base.lib.LibMisc;

public interface INeutroniaItem extends IVariantHolder {

    @Override
    default String getModNamespace() {
        return LibMisc.MOD_ID;
    }

}