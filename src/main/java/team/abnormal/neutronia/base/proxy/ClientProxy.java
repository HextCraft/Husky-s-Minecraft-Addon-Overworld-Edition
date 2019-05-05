package team.abnormal.neutronia.base.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.base.client.ContributorRewardHandler;
import team.abnormal.neutronia.base.modules.ModuleLoader;
import team.abnormal.neutronia.client.NEntityRender;
import team.abnormal.neutronia.client.render.RenderTileCustomChest;
import team.abnormal.neutronia.tileentities.TileCustomChest;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        Display.setTitle(String.format("%s %s | Minecraft 1.12.2", Reference.MOD_NAME, Reference.VERSION));
        ModuleLoader.preInitClient(event);
        ClientRegistry.bindTileEntitySpecialRenderer(TileCustomChest.class, new RenderTileCustomChest());
        NEntityRender.registerRenderers();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ModuleLoader.initClient(event);
        ContributorRewardHandler.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ModuleLoader.postInitClient(event);
    }

}
