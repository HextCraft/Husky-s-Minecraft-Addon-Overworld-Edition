package net.hdt.neutronia.base.client;

import com.google.common.collect.Lists;
import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.hdt.neutronia.base.client.category.creativeTabCategories.CreativeTabCategories;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

public class GuiDrawHandler {

    private static final ResourceLocation BEACON = new ResourceLocation("textures/gui/container/beacon.png");

    private AbstractCreativeCategory[] categories;
    private List<GuiCategoryButton> categoryButtons;
    private GuiButton categoryUp;
    private GuiButton categoryDown;
    private GuiButton categoryEnableAll;
    private GuiButton categoryDisableAll;
    private static int startIndex;
    private List<GuiButton> buttonList;

    private boolean viewingFurnitureTab;

    private int guiCenterX = 0;
    private int guiCenterY = 0;

    @SubscribeEvent
    public void onDrawGui(InitGuiEvent.Post event)
    {
        viewingFurnitureTab = false;
        if(event.getGui() instanceof GuiContainerCreative)
        {
            this.guiCenterX = ((GuiContainerCreative) event.getGui()).getGuiLeft();
            this.guiCenterY = ((GuiContainerCreative) event.getGui()).getGuiTop();
            this.categories = new AbstractCreativeCategory[] { CreativeTabCategories.END, CreativeTabCategories.GENERAL, CreativeTabCategories.ITEMS, CreativeTabCategories.NETHER, CreativeTabCategories.OCEAN, CreativeTabCategories.OVERWORLD, CreativeTabCategories.WOOD };
            this.categoryButtons = Lists.newArrayList();
            this.buttonList = event.getButtonList();

            event.getButtonList().add(categoryUp = new GuiArrowButton(11, guiCenterX - 22, guiCenterY - 12, 20, 20, true));
            event.getButtonList().add(categoryDown = new GuiArrowButton(11, guiCenterX - 22, guiCenterY + 127, 20, 20, false));
            event.getButtonList().add(categoryEnableAll = new GuiImageButton(guiCenterX - 50, guiCenterY + 88, 91, 223, 14, 14, BEACON));
            event.getButtonList().add(categoryDisableAll = new GuiImageButton(guiCenterX - 50, guiCenterY + 110, 114, 223, 14, 14, BEACON));
            updateCategories();

            GuiContainerCreative creative = (GuiContainerCreative) event.getGui();
            if(creative.getSelectedTabIndex() == NCreativeTabs.NEUTRONIA_MAIN.getIndex())
            {
                viewingFurnitureTab = true;
                categoryButtons.forEach(guiCategoryButton -> guiCategoryButton.visible = true);
                updateItems(creative);
            }
        }
    }

    private void updateCategories()
    {
        if(!categoryButtons.isEmpty())
        {
            buttonList.removeAll(categoryButtons);
            categoryButtons.clear();
        }
        for(int i = startIndex; i < startIndex + 4 && i < categories.length; i++)
        {
            GuiCategoryButton button = new GuiCategoryButton(guiCenterX - 28, guiCenterY + 29 * (i - startIndex) + 10, categories[i]);
            categoryButtons.add(button);
            buttonList.add(button);
        }
        updateCategoryButtons();
    }

    @SubscribeEvent
    public void onDrawGui(DrawScreenEvent.Pre event)
    {
        if(event.getGui() instanceof GuiContainerCreative)
        {
            GuiContainerCreative creative = (GuiContainerCreative) event.getGui();
            if(creative.getSelectedTabIndex() == NCreativeTabs.NEUTRONIA_MAIN.getIndex())
            {
                if(!viewingFurnitureTab)
                {
                    updateItems(creative);
                    viewingFurnitureTab = true;
                }
            }
            else
            {
                viewingFurnitureTab = false;
            }
        }
    }

    @SubscribeEvent
    public void onDrawGui(DrawScreenEvent.Post event)
    {
        if(event.getGui() instanceof GuiContainerCreative)
        {
            GuiContainerCreative creative = (GuiContainerCreative) event.getGui();
            this.guiCenterX = creative.getGuiLeft();
            this.guiCenterY = creative.getGuiTop();
            if(creative.getSelectedTabIndex() == NCreativeTabs.NEUTRONIA_MAIN.getIndex())
            {
                categoryUp.visible = true;
                categoryDown.visible = true;
                categoryEnableAll.visible = true;
                categoryDisableAll.visible = true;
                categoryButtons.forEach(guiButton -> guiButton.visible = true);
                categoryButtons.forEach(guiButton ->
                {
                    if(guiButton.isMouseOver())
                    {
                        guiButton.drawHoveringText(event.getGui(), event.getMouseX(), event.getMouseY());
                    }
                });
                if(categoryEnableAll.isMouseOver())
                {
                    event.getGui().drawHoveringText("Enable All Filters", event.getMouseX(), event.getMouseY());
                }
                else if(categoryDisableAll.isMouseOver())
                {
                    event.getGui().drawHoveringText("Disable All Filters", event.getMouseX(), event.getMouseY());
                }
            }
            else
            {
                categoryUp.visible = false;
                categoryDown.visible = false;
                categoryEnableAll.visible = false;
                categoryDisableAll.visible = false;
                categoryButtons.forEach(guiButton -> guiButton.visible = false);
            }
        }
    }

    @SubscribeEvent
    public void onButtonClick(ActionPerformedEvent.Post event)
    {
        if(event.getButton() instanceof GuiCategoryButton)
        {
            ((GuiCategoryButton) event.getButton()).onClick();
            if(event.getGui() instanceof GuiContainerCreative)
            {
                GuiContainerCreative creative = (GuiContainerCreative) event.getGui();
                updateItems(creative);
            }
        }
        else if(categories != null && event.getGui() instanceof GuiContainerCreative)
        {
            if(event.getButton() == categoryUp)
            {
                if(startIndex > 0)
                {
                    startIndex--;
                }
            }
            else if(event.getButton() == categoryDown)
            {
                if(startIndex <= categories.length - 4 - 1)
                {
                    startIndex++;
                }
            }
            else if(event.getButton() == categoryEnableAll)
            {
                for(AbstractCreativeCategory category : categories)
                {
                    category.setEnabled(true);
                }
                updateItems((GuiContainerCreative) event.getGui());
            }
            else if(event.getButton() == categoryDisableAll)
            {
                for(AbstractCreativeCategory category : categories)
                {
                    category.setEnabled(false);
                }
                updateItems((GuiContainerCreative) event.getGui());
            }
            updateCategories();
            GuiContainerCreative creative = (GuiContainerCreative) event.getGui();
            if(creative.getSelectedTabIndex() == NCreativeTabs.NEUTRONIA_MAIN.getIndex())
            {
                categoryButtons.forEach(guiCategoryButton -> guiCategoryButton.visible = true);
            }
        }
    }

    private void updateItems(GuiContainerCreative creative)
    {
        GuiContainerCreative.ContainerCreative container = (GuiContainerCreative.ContainerCreative) creative.inventorySlots;
        Set<Item> categorisedItems = new LinkedHashSet<>();
        for(AbstractCreativeCategory category : categories)
        {
            if(category.isEnabled())
            {
                categorisedItems.addAll(category.getItems());
            }
        }
        container.itemList.clear();
        categorisedItems.forEach(item -> item.getSubItems(CreativeTabs.SEARCH, container.itemList));
        container.itemList.sort(Comparator.comparingInt(o -> Item.getIdFromItem(o.getItem())));
        container.scrollTo(0);
    }

    private void updateCategoryButtons()
    {
        categoryUp.enabled = startIndex > 0;
        categoryDown.enabled = startIndex <= categories.length - 4 - 1;
    }

    private static class GuiCategoryButton extends GuiButton
    {
        private static final ResourceLocation TABS = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");

        private int x, y;
        private boolean toggled;
        private ItemStack stack;
        private AbstractCreativeCategory category;

        public GuiCategoryButton(int x, int y, AbstractCreativeCategory category)
        {
            super(0, x, y, 32, 28, "");
            this.x = x;
            this.y = y;
            this.stack = category.getIcon();
            this.toggled = category.isEnabled();
            this.category = category;
            this.visible = false;
        }

        public void onClick()
        {
            if(!this.visible || !this.enabled)
                return;

            toggled = !toggled;
            category.setEnabled(toggled);
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
        {
            if(!this.visible || !this.enabled)
                return;

            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            mc.getTextureManager().bindTexture(TABS);
            GlStateManager.disableLighting();
            GlStateManager.color(1F, 1F, 1F);
            GlStateManager.enableBlend();

            int width = toggled ? 32 : 28;
            int textureX = 28;
            int textureY = toggled ? 32 : 0;

            this.zLevel = 100.0F;
            GlStateManager.enableDepth();
            this.drawRotatedTexture(x, y, textureX, textureY, width, 28, 28, width);
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            renderItem.zLevel = 100.0F;
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableRescaleNormal();
            renderItem.renderItemAndEffectIntoGUI(stack, x + 8, y + 6);
            renderItem.renderItemOverlays(mc.fontRenderer, stack, x + 8, y + 6);
            renderItem.zLevel = 0.0F;
            this.zLevel = 0.0F;
        }

        public void drawHoveringText(GuiScreen screen, int mouseX, int mouseY)
        {
            if(!this.visible || !this.enabled)
                return;

            if(this.hovered)
            {
                String title = I18n.format(category.getTitleKey());
                screen.drawHoveringText(Arrays.asList(TextFormatting.BOLD + title, category.isEnabled() ? TextFormatting.BLUE + "Enabled" : TextFormatting.DARK_GRAY + "Disabled"), mouseX, mouseY);
            }
        }

        private void drawRotatedTexture(int x, int y, int textureX, int textureY, int width, int height, int textureWidth, int textureHeight)
        {
            float scaleX = 0.00390625F;
            float scaleY = 0.00390625F;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + textureWidth) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
            bufferbuilder.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + textureWidth) * 0.00390625F), (double)((float)(textureY + textureHeight) * 0.00390625F)).endVertex();
            bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + textureHeight) * 0.00390625F)).endVertex();
            bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
            tessellator.draw();
        }

        @Override
        public boolean equals(Object obj)
        {
            return obj instanceof GuiCategoryButton && ((GuiCategoryButton) obj).category == category;
        }
    }

    private static class GuiArrowButton extends GuiButton
    {
        private static final ResourceLocation ARROWS = new ResourceLocation("textures/gui/resource_packs.png");

        private boolean up = false;

        public GuiArrowButton(int buttonId, int x, int y, int widthIn, int heightIn, boolean up)
        {
            super(buttonId, x, y, widthIn, heightIn, "");
            this.up = up;
            this.visible = false;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
        {
            if(!visible)
                return;

            this.zLevel = 100.0F;
            super.drawButton(mc, mouseX, mouseY, partialTicks);
            mc.getTextureManager().bindTexture(ARROWS);
            if(up)
            {
                this.drawTexturedModalRect(x + 4, y + 7, 114, 37, 11, 7);
            }
            else
            {
                this.drawTexturedModalRect(x + 4, y + 7, 82, 52, 11, 7);
            }
            this.zLevel = 0.0F;
        }
    }

    private static class GuiImageButton extends GuiButton
    {
        private ResourceLocation resource;
        private int textureU, textureV;
        private int textureWidth, textureHeight;

        public GuiImageButton(int x, int y, int textureU, int textureV, int textureWidth, int textureHeight, ResourceLocation resource)
        {
            super(-1, x, y, textureWidth + 6, textureHeight + 6, "");
            this.resource = resource;
            this.textureU = textureU;
            this.textureV = textureV;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.visible = false;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
        {
            if(!this.visible)
                return;
            this.zLevel = 100.0F;
            super.drawButton(mc, mouseX, mouseY, partialTicks);
            mc.getTextureManager().bindTexture(resource);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(x + 3, y + 3, textureU, textureV, textureWidth, textureHeight);
            this.zLevel = 0.0F;
        }
    }
}