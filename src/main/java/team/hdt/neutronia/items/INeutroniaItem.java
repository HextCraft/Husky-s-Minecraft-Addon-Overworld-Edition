package team.hdt.neutronia.items;

import team.hdt.huskylib.interf.IVariantHolder;
import team.hdt.neutronia.base.Reference;

public interface INeutroniaItem extends IVariantHolder {

    @Override
    default String getModNamespace() {
        return Reference.MOD_ID;
    }

}