package team.hdt.neutronia_legacy.base.client.gui.listener;

import team.hdt.neutronia_legacy.base.client.gui.components.BaseComponent;

@FunctionalInterface
public interface IKeyTypedListener extends IListenerBase {
    public void componentKeyTyped(BaseComponent component, char character, int keyCode);
}