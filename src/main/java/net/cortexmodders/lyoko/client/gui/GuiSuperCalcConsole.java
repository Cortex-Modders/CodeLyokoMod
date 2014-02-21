/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.gui;

import net.cortexmodders.lyoko.container.ContainerSuperCalcConsole;
import net.cortexmodders.lyoko.network.PacketConsoleCommand;
import net.cortexmodders.lyoko.network.PacketHandler;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalcConsole;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.Packet;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;

public class GuiSuperCalcConsole extends GuiContainer
{
    private GuiTextField textBoxCode;

    private String code;

    private EntityPlayer player;
    public TileEntitySuperCalcConsole tscc;

    public GuiSuperCalcConsole(InventoryPlayer inv, TileEntitySuperCalcConsole tileEntity)
    {
        // the container is instanciated and passed to the superclass for
        // handling
        super(new ContainerSuperCalcConsole(inv, tileEntity));
        this.ySize = 119;
        this.xSize = 176;
        this.tscc = tileEntity;
        this.player = inv.player;
        this.code = "";
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.textBoxCode = new GuiTextField(this.fontRendererObj, (this.width - this.xSize) / 2 + this.xSize / 2 - 40, (this.height - this.ySize) / 2 + this.ySize / 2, 80, 10);
        this.textBoxCode.setText(this.code);
    }

    @Override
    public void updateScreen()
    {
        this.textBoxCode.updateCursorCounter();
    }

    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void keyTyped(char par1, int par2)
    {
        if (this.textBoxCode.isFocused())
        {
            this.textBoxCode.textboxKeyTyped(par1, par2);
            this.code = this.textBoxCode.getText();
        }

        if (par2 == 28)
        {
            PacketHandler packetHandler = PacketHandler.getInstance();
            
            PacketConsoleCommand message = new PacketConsoleCommand(this.code, this.tscc.xCoord, this.tscc.yCoord, this.tscc.zCoord);
            Packet packet = packetHandler.generatePacketFrom(message, Side.CLIENT);
            packetHandler.sendPacketToPlayer(packet, this.player);
            
            this.textBoxCode.setText("");
        }

        if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode() && !this.textBoxCode.isFocused())
            this.mc.thePlayer.closeScreen();

    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);

        this.textBoxCode.mouseClicked(par1, par2, par3);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        this.textBoxCode.drawTextBox();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2)
    {
        // draw text and stuff here
        // the parameters for drawString are: string, x, y, color
        // fontRenderer.drawString("Code", 75, 36, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        // draw your Gui here, only thing you need to change is the path
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation("lyoko:textures/gui/towerconsole.png"));
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
