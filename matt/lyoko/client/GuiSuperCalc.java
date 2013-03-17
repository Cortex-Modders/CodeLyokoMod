package matt.lyoko.client;

import org.lwjgl.opengl.GL11;
import matt.lyoko.container.ContainerSuperCalc;
import matt.lyoko.entities.TileEntitySuperCalc;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.*;
import net.minecraft.util.StatCollector;

public class GuiSuperCalc extends GuiContainer {

        public GuiSuperCalc (InventoryPlayer inventoryPlayer,
                        TileEntitySuperCalc tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerSuperCalc(inventoryPlayer, tileEntity));
                this.ySize = 153;
                this.xSize = 176;
                tsc = tileEntity;
                player = inventoryPlayer.player;
        }
        
        private EntityPlayer player;
        
        public TileEntitySuperCalc tsc;
        
        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
                fontRenderer.drawString("Super Calculator", 8, 6, 4210752);
                //draws "Inventory" or your regional equivalent
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
                
                fontRenderer.drawString("Fuel Cell: ", 8, 25, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
                
                fontRenderer.drawString("Time Remaining: " + tsc.timeLeft, 8, 42, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
                //draw your Gui here, only thing you need to change is the path
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.renderEngine.func_98187_b("/mods/lyoko/textures/gui/supercalculator.png");
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        }
}