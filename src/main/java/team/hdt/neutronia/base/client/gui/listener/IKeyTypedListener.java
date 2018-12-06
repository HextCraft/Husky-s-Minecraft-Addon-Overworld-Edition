package team.hdt.neutronia.base.client.gui.listener;

import team.hdt.neutronia.base.client.gui.components.BaseComponent;

@FunctionalInterface
public interface IKeyTypedListener extends IListenerBase {
    public void componentKeyTyped(BaseComponent component, char character, int keyCode);
}