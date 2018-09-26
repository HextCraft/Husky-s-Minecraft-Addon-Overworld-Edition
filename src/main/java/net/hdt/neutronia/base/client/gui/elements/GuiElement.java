package net.hdt.neutronia.base.client.gui.elements;

public abstract class GuiElement {

    public int posX, posY, width, height;

    public GuiElement(String elementName) {
        System.out.println(elementName);
        posX = 10;
        posY = 10;
        width = 16;
        height = 16;
    }

    public abstract void addParts();

}
