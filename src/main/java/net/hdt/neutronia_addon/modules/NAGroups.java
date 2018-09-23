package net.hdt.neutronia_addon.modules;

import net.hdt.neutronia.base.groups.Group;

public class NAGroups {

    public static Group test;

    static {
        test = new Group.Builder()
                .name("Test")
                .enabled(true)
                .register();
    }

}
