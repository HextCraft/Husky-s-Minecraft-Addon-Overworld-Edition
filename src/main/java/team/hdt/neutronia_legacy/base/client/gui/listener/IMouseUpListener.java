package team.hdt.neutronia_legacy.base.client.gui.listener;

import team.hdt.neutronia_legacy.base.client.gui.components.BaseComponent;

@FunctionalInterface
public interface IMouseUpListener extends IListenerBase {
    public void componentMouseUp(BaseComponent component, int x, int y, int button);
}