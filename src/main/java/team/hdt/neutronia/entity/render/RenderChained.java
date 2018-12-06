package team.hdt.neutronia.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.base.lib.LibMisc;
import team.hdt.neutronia.entity.EntityChained;
import team.hdt.neutronia.entity.render.model.ModelChained;

@SideOnly(Side.CLIENT)
public class RenderChained extends RenderLiving<EntityChained> {

    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/chained.png");

    public RenderChained(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelChained(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityChained entity) {
        return SKELETON_TEXTURES;
    }

}