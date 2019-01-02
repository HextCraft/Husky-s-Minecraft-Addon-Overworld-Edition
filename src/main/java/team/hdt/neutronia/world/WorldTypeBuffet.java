package team.hdt.neutronia.world;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.WorldType;

public class WorldTypeBuffet extends WorldType {

    /**
     * Creates a new world type, the ID is hidden and should not be referenced by modders.
     * It will automatically expand the underlying workdType array if there are no IDs left.
     *
     * @param name
     */
    public WorldTypeBuffet(String name) {
        super("buffet");
    }

    @Override
    public void onCustomizeButton(Minecraft mc, GuiCreateWorld guiCreateWorld) {

    }
}
