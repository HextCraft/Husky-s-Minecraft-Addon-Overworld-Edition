package team.hdt.neutronia.base.config;

import net.minecraftforge.fml.client.DefaultGuiFactory;
import team.hdt.neutronia.base.Reference;

public class NGuiFactory extends DefaultGuiFactory {

    protected NGuiFactory() {
        super(Reference.MOD_ID, "Neutronia Config");
    }

}