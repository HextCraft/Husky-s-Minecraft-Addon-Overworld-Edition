package team.hdt.neutronia_legacy.groups.client.features;

import net.minecraft.block.BlockShulkerBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.tweaks.util.ItemNBTHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShulkerBoxTooltip extends Component {

    public static ResourceLocation WIDGET_RESOURCE = new ResourceLocation("neutronia_legacy", "textures/misc/shulker_widget.png");

    boolean useColors, requireShift;


    @Override
    public void setupConfig() {
        useColors = loadPropBool("Use Colors", "", true);
        requireShift = loadPropBool("Needs Shift to be visible", "", true);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void makeTooltip(ItemTooltipEvent event) {
        if (!event.getItemStack().isEmpty() && event.getItemStack().getItem() instanceof ItemShulkerBox && event.getItemStack().hasTagCompound()) {
            NBTTagCompound cmp = ItemNBTHelper.getCompound(event.getItemStack(), "BlockEntityTag", true);
            if (cmp != null && cmp.hasKey("Items", 9)) {
                List<String> tooltip = event.getToolTip();
                List<String> tooltipCopy = new ArrayList<>(tooltip);

                for (int i = 1; i < tooltipCopy.size(); i++) {
                    String s = tooltipCopy.get(i);
                    if (!s.startsWith("\u00a7") || s.startsWith("\u00a7o"))
                        tooltip.remove(s);
                }

                if (requireShift && !GuiScreen.isShiftKeyDown())
                    tooltip.add(1, I18n.translateToLocal("neutroniaMisc.shulkerBoxShift"));
            }
        }
    }

    @SubscribeEvent
    public void renderTooltip(RenderTooltipEvent.PostText event) {
        if (event.getStack() != null && event.getStack().getItem() instanceof ItemShulkerBox && event.getStack().hasTagCompound() && (!requireShift || GuiScreen.isShiftKeyDown())) {
            NBTTagCompound cmp = ItemNBTHelper.getCompound(event.getStack(), "BlockEntityTag", true);
            if (cmp != null && cmp.hasKey("Items", 9)) {
                ItemStack currentBox = event.getStack();
                int currentX = event.getX() - 5;
                int currentY = event.getY() - 70;

                int texWidth = 172;
                int texHeight = 64;

                if (currentY < 0)
                    currentY = event.getY() + event.getLines().size() * 10 + 5;

                ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
                int right = currentX + texWidth;
                if (right > res.getScaledWidth())
                    currentX -= (right - res.getScaledWidth());

                GlStateManager.pushMatrix();
                RenderHelper.enableStandardItemLighting();
                GlStateManager.enableRescaleNormal();
                GlStateManager.color(1F, 1F, 1F);
                GlStateManager.translate(0, 0, 700);

                Minecraft mc = Minecraft.getMinecraft();
                mc.getTextureManager().bindTexture(WIDGET_RESOURCE);

                RenderHelper.disableStandardItemLighting();

                if (useColors) {
                    EnumDyeColor dye = ((BlockShulkerBox) ((ItemBlock) currentBox.getItem()).getBlock()).getColor();
                    int color = ItemDye.DYE_COLORS[dye.getDyeDamage()];
                    Color colorObj = new Color(color);
                    GlStateManager.color((float) colorObj.getRed() / 255F, (float) colorObj.getGreen() / 255F, (float) colorObj.getBlue() / 255F);
                }
                Gui.drawModalRectWithCustomSizedTexture(currentX, currentY, 0, 0, texWidth, texHeight, 256, 256);

                GlStateManager.color(1F, 1F, 1F);

                NonNullList<ItemStack> itemList = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(cmp, itemList);

                RenderItem render = mc.getRenderItem();

                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.enableDepth();
                int i = 0;
                for (ItemStack itemstack : itemList) {
                    int xp = currentX + 6 + (i % 9) * 18;
                    int yp = currentY + 6 + (i / 9) * 18;

                    if (!itemstack.isEmpty()) {
                        render.renderItemAndEffectIntoGUI(itemstack, xp, yp);
                        render.renderItemOverlays(mc.fontRenderer, itemstack, xp, yp);
                    }

                    i++;
                }

                GlStateManager.disableDepth();
                GlStateManager.disableRescaleNormal();
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return isClient();
    }

}