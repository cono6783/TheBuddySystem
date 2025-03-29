package cono6783.thebuddysystem.menus;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class BuddyScreen extends AbstractContainerScreen<BuddyMenu> {
    public BuddyScreen(BuddyMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);

    }

    @Override
    protected void init() {
        super.init();
        //Add widgets here
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        guiGraphics.blit(INVENTORY_LOCATION, this.leftPos, this.topPos, 0, 0,this.imageWidth, this.imageHeight);
    }
}
