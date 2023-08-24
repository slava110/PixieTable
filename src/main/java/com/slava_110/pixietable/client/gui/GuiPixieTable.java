package com.slava_110.pixietable.client.gui;

import com.slava_110.pixietable.inventory.ContainerPixieTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiPixieTable extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("pixietable", "textures/gui/container/pixietable.png");

    public GuiPixieTable(ContainerPixieTable inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("tile.pixietable.name");
        fontRenderer.drawString(s, xSize / 2 - fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        fontRenderer.drawString(
                ((ContainerPixieTable) inventorySlots).playerInv.getDisplayName().getUnformattedText(),
                8,
                ySize - 96 + 2,
                4210752
        );
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(GUI_TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }


}
