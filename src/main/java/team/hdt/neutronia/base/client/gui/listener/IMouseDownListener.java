package team.hdt.neutronia.base.client.gui.listener;

import team.hdt.neutronia.base.client.gui.components.BaseComponent;

@FunctionalInterface
public interface IMouseDownListener extends IListenerBase {
    public void componentMouseDown(BaseComponent component, int x, int y, int button);
}