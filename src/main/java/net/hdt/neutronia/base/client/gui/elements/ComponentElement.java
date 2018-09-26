package net.hdt.neutronia.base.client.gui.elements;

import net.hdt.neutronia.base.groups.Component;

public class ComponentElement extends GuiElement {

    private Component component;

    public ComponentElement(Component component) {
        super("component");
        this.component = component;
    }

    @Override
    public void addParts() {

    }

}