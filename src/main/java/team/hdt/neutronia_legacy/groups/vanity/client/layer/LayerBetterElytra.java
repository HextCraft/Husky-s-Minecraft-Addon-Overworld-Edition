package team.hdt.neutronia_legacy.groups.vanity.client.layer;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.groups.tweaks.util.ItemNBTHelper;
import team.hdt.neutronia_legacy.groups.vanity.feature.DyableElytra;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class LayerBetterElytra implements LayerRenderer<AbstractClientPlayer> {

    private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");
    private final RenderPlayer renderPlayer;
    private final ModelElytra modelElytra = new ModelElytra();

    public LayerBetterElytra(RenderPlayer renderPlayerIn) {
        renderPlayer = renderPlayerIn;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

        if (!itemstack.isEmpty() && itemstack.getItem() == Items.ELYTRA) {
            int colorIndex = ItemNBTHelper.getInt(itemstack, DyableElytra.TAG_ELYTRA_DYE, -1);

            if (colorIndex == -1 || colorIndex == 15)
                GlStateManager.color(1F, 1F, 1F);
            else {
                Color color = new Color(ItemDye.DYE_COLORS[colorIndex]);
                float r = color.getRed() / 255F;
                float g = color.getGreen() / 255F;
                float b = color.getBlue() / 255F;
                GlStateManager.color(r, g, b);
            }

            if (entitylivingbaseIn.isPlayerInfoSet() && entitylivingbaseIn.getLocationElytra() != null)
                renderPlayer.bindTexture(entitylivingbaseIn.getLocationElytra());
            else if (entitylivingbaseIn.hasPlayerInfo() && entitylivingbaseIn.getLocationCape() != null && entitylivingbaseIn.isWearing(EnumPlayerModelParts.CAPE))
                renderPlayer.bindTexture(entitylivingbaseIn.getLocationCape());
            else renderPlayer.bindTexture(TEXTURE_ELYTRA);

            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, 0.125F);
            modelElytra.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
            modelElytra.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

            if (itemstack.isItemEnchanted()) {
//				ColorRunes.setTargetStack(itemstack);
                LayerArmorBase.renderEnchantedGlint(renderPlayer, entitylivingbaseIn, modelElytra, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

            GlStateManager.color(1F, 1F, 1F);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}