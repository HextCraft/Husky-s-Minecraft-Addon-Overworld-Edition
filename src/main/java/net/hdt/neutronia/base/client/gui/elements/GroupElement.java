package net.hdt.neutronia.base.client.gui.elements;

import net.hdt.neutronia.base.client.gui.ColoredButton;
import net.hdt.neutronia.base.groups.Group;
import net.minecraft.util.text.translation.I18n;

public class GroupElement extends GuiElement {

    private Group group;

    public GroupElement(Group group) {
        super("group");
        this.group = group;
    }

    @Override
    public void addParts() {
        new Icon(group.getIconStack());
        new ColoredButton(3, posX - 100, posY + 44, 54, I18n.translateToLocal("neutronia.config.opensite"), 0x4078c0);
    }

}