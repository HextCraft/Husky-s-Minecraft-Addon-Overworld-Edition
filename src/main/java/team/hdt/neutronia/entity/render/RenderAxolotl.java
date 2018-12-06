package team.hdt.neutronia.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia.base.util.Reference;
import team.hdt.neutronia.entity.EntityAxolotl;
import team.hdt.neutronia.entity.render.model.ModelAxolotl;

public class RenderAxolotl extends RenderLiving<EntityAxolotl> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/axolotl.png");

    public RenderAxolotl(RenderManager manager) {
        super(manager, new ModelAxolotl(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAxolotl entity) {
        return SCORP_TEXTURE;
    }

}
