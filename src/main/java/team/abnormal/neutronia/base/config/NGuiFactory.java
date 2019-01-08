package team.abnormal.neutronia.base.config;

import net.minecraftforge.fml.client.DefaultGuiFactory;
import team.abnormal.neutronia.base.Reference;

public class NGuiFactory extends DefaultGuiFactory {

    protected NGuiFactory() {
        super(Reference.MOD_ID, "Neutronia Config");
    }

}