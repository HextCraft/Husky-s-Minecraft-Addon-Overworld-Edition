package net.hdt.neutronia.base.client;

import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.hdt.neutronia.base.lib.LibObfuscation;
import net.hdt.neutronia.entity.render.RenderPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class ContributorRewardHandler {

	private static final ImmutableSet<String> UUIDS = ImmutableSet.of("b344687b-ec74-479a-9540-1aa8ccb13e92");

	private static final Set<EntityPlayer> done = Collections.newSetFromMap(new WeakHashMap<>());
	
	public static int localPatronTier = 0;
	public static String featuredPatron = "";
	
	public static void init() {
		MinecraftForge.EVENT_BUS.register(ContributorRewardHandler.class);
		new ThreadContributorListLoader();
	}
	
	@SubscribeEvent
	public static void onRenderPlayer(RenderPlayerEvent.Post event) {
		EntityPlayer player = event.getEntityPlayer();
		String uuid = EntityPlayer.getUUID(player.getGameProfile()).toString();
		if(player instanceof AbstractClientPlayer && UUIDS.contains(uuid) && !done.contains(player)) {
			AbstractClientPlayer clplayer = (AbstractClientPlayer) player;
			if(clplayer.hasPlayerInfo()) {
				NetworkPlayerInfo info = ReflectionHelper.getPrivateValue(AbstractClientPlayer.class, clplayer, LibObfuscation.PLAYER_INFO);
				Map<Type, ResourceLocation> textures = ReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, LibObfuscation.PLAYER_TEXTURES);
				ResourceLocation loc = new ResourceLocation("neutronia", "textures/misc/mod_cape.png");
				textures.put(Type.CAPE, loc);
				textures.put(Type.ELYTRA, loc);
				done.add(player);
			}
		}
		if(player instanceof AbstractClientPlayer && /*uuid.equals("75c298f9-27c8-415b-9a16-329e3884054b")*/UUIDS.contains(uuid) && !done.contains(player)) {
			AbstractClientPlayer clplayer = (AbstractClientPlayer) player;
			RenderingRegistry.registerEntityRenderingHandler(AbstractClientPlayer.class, RenderPlayer::new);
			done.add(player);
		}
	}
	
	private static void load(Properties props) {
		List<String> allPatrons = new ArrayList<>(props.size());
		
		String name = Minecraft.getMinecraft().getSession().getUsername().toLowerCase();
		props.forEach((k, v) -> {
			String key = (String) k;
			String value = (String) v;
			
			int tier = Integer.parseInt(value);
			if(tier < 10)
				allPatrons.add(key);
			
			if(key.toLowerCase().equals(name))
				localPatronTier = tier;
		});
		
		if(!allPatrons.isEmpty())
			featuredPatron = allPatrons.get((int) (Math.random() * allPatrons.size()));
	}
	
	private static class ThreadContributorListLoader extends Thread {

		public ThreadContributorListLoader() {
			setName("Neutronia Contributor Loading Thread");
			setDaemon(true);
			start();
		}

		@Override
		public void run() {
			try {
				URL url = new URL("https://raw.githubusercontent.com/HuskysDevelopmentTeam/Neutronia/tree/module-system/contributors.properties");
				Properties props = new Properties();
				try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
					props.load(reader);
					load(props);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
