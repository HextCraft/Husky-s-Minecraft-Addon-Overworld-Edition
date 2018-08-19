package net.hdt.neutronia.groups.client.features;

import com.google.common.base.Predicate;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.EntityEquipmentSlot.Type;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class UsageTicker extends Component {

    List<TickerElement> elements;
    boolean invert;
    int shiftLeft, shiftRight;

    @Override
    public void setupConfig() {
        elements = new ArrayList<>();

        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            String config = "Enable " + WordUtils.capitalize(slot.getName());
            if (loadPropBool(config, "", true))
                elements.add(new TickerElement(slot));
        }

        invert = loadPropBool("Invert Displays", "Switch the armor display to the off hand side and the hand display to the main hand side", false);
        shiftLeft = loadPropInt("Left Side Offeset", "", 0);
        shiftRight = loadPropInt("Right Side Offset", "", 0);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void clientTick(ClientTickEvent event) {
        if (event.phase == Phase.START) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.player != null)
                elements.forEach((ticker) -> ticker.tick(event, mc.player));
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderHUD(RenderGameOverlayEvent.Post event) {
        if (event.getType() == ElementType.HOTBAR) {
            ScaledResolution res = event.getResolution();
            EntityPlayer player = Minecraft.getMinecraft().player;
            float pticks = event.getPartialTicks();
            elements.forEach((ticker) -> ticker.render(res, player, invert, pticks));
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return isClient();
    }

    class TickerElement {

        private static final int MAX_TIME = 60;
        private static final int ANIM_TIME = 5;

        int liveTicks;
        EntityEquipmentSlot slot;
        ItemStack currStack = ItemStack.EMPTY;
        int currCount;

        public TickerElement(EntityEquipmentSlot slot) {
            this.slot = slot;
        }

        @SideOnly(Side.CLIENT)
        public void tick(ClientTickEvent event, EntityPlayer player) {
            ItemStack heldStack = getStack(player).copy();

            int count = 0;

            count = getStackCount(player, heldStack);
            heldStack = getDisplayedStack(player, heldStack, count);

            if (heldStack.isEmpty())
                liveTicks = 0;
            else if (shouldChange(heldStack, currStack, count, currCount)) {
                boolean done = liveTicks == 0;
                boolean animatingIn = liveTicks > MAX_TIME - ANIM_TIME;
                boolean animatingOut = liveTicks < ANIM_TIME && !done;
                if (animatingOut)
                    liveTicks = MAX_TIME - liveTicks;
                else if (!animatingIn) {
                    if (!done)
                        liveTicks = MAX_TIME - ANIM_TIME;
                    else liveTicks = MAX_TIME;
                }
            } else if (liveTicks > 0)
                liveTicks--;

            currCount = count;
            currStack = heldStack;
        }

        @SideOnly(Side.CLIENT)
        public void render(ScaledResolution res, EntityPlayer player, boolean invert, float partialTicks) {
            if (liveTicks > 0) {
                float animProgress;

                if (liveTicks < ANIM_TIME)
                    animProgress = Math.max(0, liveTicks - partialTicks) / ANIM_TIME;
                else animProgress = Math.min(ANIM_TIME, (MAX_TIME - liveTicks) + partialTicks) / ANIM_TIME;

                float anim = -animProgress * (animProgress - 2) * 20F;

                float x = res.getScaledWidth() / 2;
                float y = res.getScaledHeight() - anim;

                int armorWidth = 80;
                int heldWidth = 40;
                int barWidth = 190;
                boolean armor = slot.getSlotType() == Type.ARMOR;

                EnumHandSide primary = player.getPrimaryHand();
                EnumHandSide ourSide = (armor != invert) ? primary : primary.opposite();

                int slots = armor ? 4 : 2;
                int index = slots - slot.getIndex() - 1;
                float mul = ourSide == EnumHandSide.LEFT ? -1 : 1;

                if (ourSide != primary && !player.getHeldItem(EnumHand.OFF_HAND).isEmpty())
                    barWidth += 58;

                Minecraft mc = Minecraft.getMinecraft();
                x += (barWidth / 2) * mul + index * 20;
                if (ourSide == EnumHandSide.LEFT) {
                    x -= slots * 20;
                    x += shiftLeft;
                } else x += shiftRight;

                ItemStack stack = getRenderedStack(player);

                GlStateManager.pushMatrix();
                GlStateManager.translate(x, y, 0);
                RenderHelper.enableGUIStandardItemLighting();
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, 0, 0);
                mc.getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer, stack, 0, 0);
                RenderHelper.disableStandardItemLighting();
                GlStateManager.popMatrix();
            }
        }

        @SideOnly(Side.CLIENT)
        public boolean shouldChange(ItemStack currStack, ItemStack prevStack, int currentTotal, int pastTotal) {
            return !prevStack.isItemEqual(currStack) || currentTotal != pastTotal;
        }

        @SideOnly(Side.CLIENT)
        public ItemStack getStack(EntityPlayer player) {
            return player.getItemStackFromSlot(slot);
        }

        @SideOnly(Side.CLIENT)
        public ItemStack getDisplayedStack(EntityPlayer player, ItemStack stack, int count) {
            boolean verifySize = true;
            if (stack.getItem() instanceof ItemBow && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0) {
                stack = new ItemStack(Items.ARROW);
                verifySize = false;
            }

            if (!stack.isStackable() && slot.getSlotType() == Type.HAND)
                return ItemStack.EMPTY;

            if (verifySize && stack.isStackable() && count == stack.getCount())
                return ItemStack.EMPTY;

            return stack;
        }

        @SideOnly(Side.CLIENT)
        public ItemStack getRenderedStack(EntityPlayer player) {
            ItemStack stack = getStack(player);
            int count = getStackCount(player, stack);
            ItemStack displayStack = getDisplayedStack(player, stack, count).copy();
            if (displayStack != stack)
                count = getStackCount(player, displayStack);
            displayStack.setCount(count);

            return displayStack;
        }

        @SideOnly(Side.CLIENT)
        public int getStackCount(EntityPlayer player, ItemStack stack) {
            if (!stack.isStackable())
                return 1;

            Predicate<ItemStack> pred = (stackAt) -> stack.isItemEqual(stackAt);

            if (stack.getItem() == Items.ARROW)
                pred = (stackAt) -> stackAt.getItem() instanceof ItemArrow;

            int total = 0;
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack stackAt = player.inventory.getStackInSlot(i);
                if (pred.apply(stackAt))
                    total += stackAt.getCount();
            }

            return total;
        }

    }

}