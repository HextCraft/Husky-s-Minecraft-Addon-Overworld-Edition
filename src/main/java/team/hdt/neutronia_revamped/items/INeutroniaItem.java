package team.hdt.neutronia_revamped.items;

import team.hdt.huskylib.interf.IVariantHolder;
import team.hdt.neutronia_revamped.Reference;

public interface INeutroniaItem extends IVariantHolder {

    @Override
    default String getModNamespace() {
        return Reference.MOD_ID;
    }

}