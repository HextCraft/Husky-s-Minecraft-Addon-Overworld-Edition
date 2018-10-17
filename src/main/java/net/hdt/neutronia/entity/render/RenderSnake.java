package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.entity.EntitySnake;
import net.hdt.neutronia.entity.render.model.ModelSmolSnake;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Joseph on 10/9/2018.
 */
public class RenderSnake extends RenderLiving<EntitySnake> {

	private static final ResourceLocation[] smallSnakeTextures = new ResourceLocation[3];
	private static final ResourceLocation[] mediumSnakeTextures = new ResourceLocation[2];

	static {
		smallSnakeTextures[0] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/snakes/small_snake_1.png");
		smallSnakeTextures[1] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/snakes/small_snake_2.png");
		smallSnakeTextures[2] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/snakes/small_snake_3.png");
		mediumSnakeTextures[0] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/snakes/medium_snake_4.png");
		mediumSnakeTextures[1] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/snakes/medium_snake_5.png");
	}

	public RenderSnake(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSmolSnake(), 0.3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySnake entity) {
		return smallSnakeTextures[entity.getFamiliarSkin()] ;
	}

	@Override
	protected void preRenderCallback(EntitySnake entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
		if (entitylivingbaseIn.isChild()) {
			GlStateManager.scale(0.4d, 0.4d, 0.4d);
		} else {
			GlStateManager.scale(0.6d, 0.6d, 0.6d);
		}
	}

}