/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client;

import matt.lyoko.container.ContainerSuperCalc;
import matt.lyoko.entities.tileentity.TileEntitySuperCalc;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiSuperCalc extends GuiContainer
{
    public GuiSuperCalc(InventoryPlayer inventoryPlayer, TileEntitySuperCalc tileEntity)
    {
        // the container is instanciated and passed to the superclass for
        // handling
        super(new ContainerSuperCalc(inventoryPlayer, tileEntity));
        this.ySize = 153;
        this.xSize = 176;
        this.tsc = tileEntity;
    }

    public TileEntitySuperCalc tsc;

    @Override
    protected void func_146979_b(int param1, int param2)
    {
        // draw text and stuff here
        // the parameters for drawString are: string, x, y, color
        this.fontRenderer.drawString("Super Calculator", 8, 6, 4210752);
        this.fontRenderer.drawString("Fuel Cell: ", 8, 25, 4210752);
        this.fontRenderer.drawString("Time Remaining: " + this.tsc.timeLeft, 8, 42, 4210752);
        this.fontRenderer.drawString("Temperature: " + this.tsc.getTemperature(), 8, 52, 4210752);
        // draws "Inventory" or your regional equivalent
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3)
    {
        // draw your Gui here, only thing you need to change is the path
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation("lyoko:textures/gui/supercalculator.png"));
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}