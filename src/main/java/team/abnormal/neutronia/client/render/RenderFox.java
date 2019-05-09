package team.abnormal.neutronia.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.client.model.ModelFox;
import team.abnormal.neutronia.entity.passive.EntityFox;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderFox extends RenderLiving<EntityFox> {
    private static final ResourceLocation FOX_LOCATION = new ResourceLocation(Reference.MOD_ID,"textures/entity/fox.png");

    public RenderFox(RenderManager p_i48864_1_) {
        super(p_i48864_1_, new ModelFox(), 0.25F);
    }

    @Nullable
    protected ResourceLocation getEntityTexture(EntityFox entity) {
        return FOX_LOCATION;
    }

}