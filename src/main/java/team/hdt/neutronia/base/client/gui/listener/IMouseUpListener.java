package team.hdt.neutronia.base.client.gui.listener;

import team.hdt.neutronia.base.client.gui.components.BaseComponent;

@FunctionalInterface
public interface IMouseUpListener extends IListenerBase {
    public void componentMouseUp(BaseComponent component, int x, int y, int button);
}