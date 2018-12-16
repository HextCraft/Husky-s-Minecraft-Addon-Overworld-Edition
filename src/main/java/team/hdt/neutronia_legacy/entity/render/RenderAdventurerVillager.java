package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.entity.EntityAdventurerVillager;
import team.hdt.neutronia_legacy.entity.render.model.ModelAdventurerVillager;

import static team.hdt.neutronia_legacy.base.util.Reference.MOD_ID;

public class RenderAdventurerVillager extends RenderLiving<EntityAdventurerVillager> {

    private static final ResourceLocation ADVENTURER_VILLAGER_TEXTURES = new ResourceLocation(MOD_ID, "textures/entity/villagers/adventurer/adventurer_villager.png");

    public RenderAdventurerVillager(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelAdventurerVillager(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAdventurerVillager entity) {
        return ADVENTURER_VILLAGER_TEXTURES;
    }

    @Override
    protected void applyRotations(EntityAdventurerVillager entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
