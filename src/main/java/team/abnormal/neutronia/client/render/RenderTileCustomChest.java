package team.abnormal.neutronia.client.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import team.abnormal.neutronia.init.NBlocks;
import team.abnormal.neutronia.tileentities.TileCustomChest;

import java.util.Calendar;

public class RenderTileCustomChest extends TileEntitySpecialRenderer<TileCustomChest> {

    private static final ResourceLocation TEXTURE_CHRISTMAS_DOUBLE = new ResourceLocation("textures/entity/chest/christmas_double.png");
    private static final ResourceLocation TEXTURE_CHRISTMAS = new ResourceLocation("textures/entity/chest/christmas.png");

    private final ModelChest simpleChest = new ModelChest();
    private final ModelChest largeChest = new ModelLargeChest();

    private boolean xmas;

    public RenderTileCustomChest() {
        Calendar calendar = Calendar.getInstance();
        xmas = calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DAY_OF_MONTH) >= 14 && calendar.get(Calendar.DAY_OF_MONTH) <= 16;
    }

    @Override
    public void render(TileCustomChest te, double x, double y, double z, float partialTicks, int destroyStage, float something) {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(GL11.GL_LEQUAL);
        GlStateManager.depthMask(true);
        int meta;

        if (te.hasWorld()) {
            meta = te.getBlockMetadata();
            te.checkForAdjacentChests();
        } else
            meta = 0;

        if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
            ModelChest model;

            if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
                model = simpleChest;

                if (destroyStage >= 0) {
                    bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(GL11.GL_TEXTURE);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(GL11.GL_MODELVIEW);
                } else if (xmas)
                    bindTexture(TEXTURE_CHRISTMAS);
                else bindTexture(te.chestType.nrmTex);
            } else {
                model = largeChest;

                if (destroyStage >= 0) {
                    bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(GL11.GL_TEXTURE);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(GL11.GL_MODELVIEW);
                } else if (xmas)
                    bindTexture(TEXTURE_CHRISTMAS_DOUBLE);
                else bindTexture(te.chestType.dblTex);
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (destroyStage < 0)
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.translate(x, y + 1.0F, z + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            int angle = 0;

            if (meta == 2)
                angle = 180;

            if (meta == 3)
                angle = 0;

            if (meta == 4)
                angle = 90;

            if (meta == 5)
                angle = -90;

            if (meta == 2 && te.adjacentChestXPos != null) {
                GlStateManager.translate(1.0F, 0.0F, 0.0F);
            }

            if (meta == 5 && te.adjacentChestZPos != null) {
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
            }

            GlStateManager.rotate(angle, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float lidAngle = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;

            if (te.adjacentChestZNeg != null) {
                float adjLidAngle = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * partialTicks;

                if (adjLidAngle > lidAngle)
                    lidAngle = adjLidAngle;
            }

            if (te.adjacentChestXNeg != null) {
                float adjLidAngle = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * partialTicks;

                if (adjLidAngle > lidAngle)
                    lidAngle = adjLidAngle;
            }

            lidAngle = 1.0F - lidAngle;
            lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
            model.chestLid.rotateAngleX = -(lidAngle * ((float) Math.PI / 2F));
            model.renderAll();

            if (te.getChestType() == NBlocks.CUSTOM_TYPE_NEUTRONIA_TRAP) {
                if (model == simpleChest)
                    bindTexture(NBlocks.TRAP_RESOURCE);
                else bindTexture(NBlocks.TRAP_DOUBLE_RESOURCE);

                float scale = 1.002F;
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.scale(scale, scale, scale);
                GlStateManager.translate(model == largeChest ? -0.002F : -0.001F, -0.001F, -0.001F);
                model.renderAll();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (destroyStage >= 0) {
                GlStateManager.matrixMode(GL11.GL_TEXTURE);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(GL11.GL_MODELVIEW);
            }
        }
    }
}