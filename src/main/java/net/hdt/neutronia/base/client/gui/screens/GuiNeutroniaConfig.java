package net.hdt.neutronia.base.client.gui.screens;

import net.hdt.neutronia.base.client.gui.components.ModuleFold;
import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: scrollbar (only mousewheel supported), actually load modules, bottom buttons, etc...
public class GuiNeutroniaConfig extends GuiScreen {

	private GuiScreen parent;
	private int scroll;
	private int maxScroll = 0;
	private ArrayList<ModuleFold> modules = new ArrayList<>();

	public GuiNeutroniaConfig(GuiScreen parent) {
		this.parent = parent;
		List<Group> groups = new ArrayList<>(GroupLoader.enabledGroups);
		Collections.sort(groups);
		for(Group group : groups) {
			ModuleFold fold = new ModuleFold(group.name, group.desc);
			this.modules.add(fold);
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		for(ModuleFold fold : this.modules) {
			this.buttonList.addAll(fold.getButtonList());
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof ModuleFold.GuiUnfoldButton) {
			ModuleFold fold = ((ModuleFold.GuiUnfoldButton) button).fold;
			int oldHeight = fold.getHeight();
			boolean unfolded = !fold.isUnfolded();
			fold.setUnfolded(unfolded);
			int newHeight = fold.getHeight();
			button.displayString = unfolded ? "v" : "<";
			this.repositionFolds();
		}
		super.actionPerformed(button);
	}

	@Override
	public void handleMouseInput() throws IOException {
		int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
		if(mouseY >= 23 && mouseY <= this.height - 32) {
			int scroll = MathHelper.clamp(Mouse.getEventDWheel(), -1, 1)*10;
			if(scroll != 0) {
				this.scroll = MathHelper.clamp(this.scroll - scroll, 0, this.maxScroll);
				for(ModuleFold fold : this.modules) {
					fold.setScroll(this.scroll);
				}
			}
		}
		super.handleMouseInput();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == Keyboard.KEY_ESCAPE) {
			this.onGuiClosed();
			this.mc.displayGuiScreen(this.parent);
			return;
		}
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground(0);
		FontRenderer fr = this.mc.fontRenderer;
		this.drawCenteredString(fr, "Neutronia Module Configuration", this.width / 2, 8, 16777215);
		this.drawContainerBackground();

		// Scissor to keep list within darker bg part
		int scale = new ScaledResolution(this.mc).getScaleFactor();
		GL11.glScissor(0, 32*scale, this.width*scale, (this.height - 55)*scale);
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		for(ModuleFold fold : this.modules) {
			fold.drawScreen(mouseX, mouseY, partialTicks);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

	private void repositionFolds() {
		int y = 0;
		int dark = this.height - 55;
		for(ModuleFold fold : this.modules) {
			fold.setWorldAndResolution(mc, width, height);
			fold.setY(23 + y);
			fold.setScroll(fold.getScroll());
			y += fold.getHeight();
		}
		this.maxScroll = Math.max(y - dark, 0);
	}

	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height) {
		super.setWorldAndResolution(mc, width, height);
		this.repositionFolds();
	}

	protected void drawContainerBackground() {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		this.mc.getTextureManager().bindTexture(Gui.OPTIONS_BACKGROUND);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		float f = 32.0F;

		double left = 0;
		double top = 23;
		double right = this.width;
		double bottom = this.height - 32;

		buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		buffer.pos(left,  bottom, 0.0D).tex((left  / f), ((bottom + this.scroll) / f)).color(32, 32, 32, 255).endVertex();
		buffer.pos(right, bottom, 0.0D).tex((right / f), ((bottom + this.scroll) / f)).color(32, 32, 32, 255).endVertex();
		buffer.pos(right, top,    0.0D).tex((right / f), ((top    + this.scroll) / f)).color(32, 32, 32, 255).endVertex();
		buffer.pos(left,  top,    0.0D).tex((left  / f), ((top    + this.scroll) / f)).color(32, 32, 32, 255).endVertex();
		tessellator.draw();
	}
}