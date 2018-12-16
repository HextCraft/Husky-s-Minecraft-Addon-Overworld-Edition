package team.hdt.neutronia_legacy.base.client.gui.listener;

import team.hdt.neutronia_legacy.base.client.gui.components.BaseComponent;

@FunctionalInterface
public interface IMouseDownListener extends IListenerBase {
    public void componentMouseDown(BaseComponent component, int x, int y, int button);
}