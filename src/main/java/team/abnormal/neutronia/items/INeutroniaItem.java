package team.abnormal.neutronia.items;

import team.abnormal.abnormalib.interf.IVariantHolder;
import team.abnormal.neutronia.base.Reference;

public interface INeutroniaItem extends IVariantHolder {

    @Override
    default String getModNamespace() {
        return Reference.MOD_ID;
    }

}