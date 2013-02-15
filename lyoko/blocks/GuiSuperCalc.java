package matt.lyoko.blocks;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import matt.lyoko.entities.*;
import matt.lyoko.items.*;

public class GuiSuperCalc extends GuiContainer {

        public GuiSuperCalc (InventoryPlayer inventoryPlayer,
                        TileEntitySuperCalc tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerSuperCalc(inventoryPlayer, tileEntity));
                this.ySize = 200;
                this.xSize = 234;
                tsc = tileEntity;
        }
        
        public TileEntitySuperCalc tsc;
        
        public String getRemainingTime(float par1)
        {
        	if(tsc.getStackInSlot(0) != null && tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel)
        	{
        		if(par1 <= 0.0F)
        		{
        			tsc.timeLeft = 0.0F;
        			if(tsc.selectedSector != "")
        			{
        				tsc.selectedSector = "";
        				tsc.timeLeft = 10.0F;
        			}
        			return Float.toString(0.0F);
        		}
        		if(tsc.selectedSector == "")
        		{
        			tsc.timeLeft = 10.0F;
        			return Float.toString(10.0F);
        		}
        		par1 = par1 - 0.05F;
        		tsc.timeLeft = par1;
        	}
        	
        	return Float.toString(par1);
        }
        
        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
                fontRenderer.drawString("Super Calculator", 8, 6, 4210752);
                //draws "Inventory" or your regional equivalent
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
                
                fontRenderer.drawString("Fuel Cell: ", 8, 25, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
                
                fontRenderer.drawString("Selected Sector: " + tsc.selectedSector, 8, 42, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
                
                fontRenderer.drawString("Time Remaining: " + getRemainingTime(tsc.timeLeft), 100, 6, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
                
                //fontRenderer.drawString("Fuel Cell: ", 8, 25, 4210752);
                //fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
                //draw your Gui here, only thing you need to change is the path
                int texture = mc.renderEngine.getTexture("/matt/lyoko/gui/supercalculator.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.renderEngine.bindTexture(texture);
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        }
        
        @Override
        public void initGui() {
                super.initGui();
                //make buttons
                                        //id, x, y, width, height, text
                controlList.add(new GuiButton(1, 240, 130, 26, 20, "Ice"));
                controlList.add(new GuiButton(2, 270, 130, 44, 20, "Forest"));
                controlList.add(new GuiButton(3, 318, 130, 53, 20, "Mountain"));
                controlList.add(new GuiButton(4, 255, 154, 44, 20, "Desert"));
                controlList.add(new GuiButton(5, 303, 154, 54, 20, "Carthage"));
                controlList.add(new GuiButton(6, 303, 180, 34, 20, "none"));
        }
        
        protected void actionPerformed(GuiButton guibutton) {
                //id is the id you give your button
                switch(guibutton.id) {
                case 1:
                	if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
                	{
                		break;
                	}
                	tsc.selectedSector = "ice";
                    break;
                case 2:
                	if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
                	{
                		break;
                	}
                	tsc.selectedSector = "forest";
                	break;
                case 3:
                	if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
                	{
                		break;
                	}
                	tsc.selectedSector = "mountain";
                	break;
                case 4:
                	if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
                	{
                		break;
                	}
                	tsc.selectedSector = "desert";
                	break;
                case 5:
                	if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
                	{
                		break;
                	}
                	tsc.selectedSector = "carthage";
                	break;
                case 6:
                	if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
                	{
                		break;
                	}
                	tsc.selectedSector = "";
                	break;
                default:
                	tsc.selectedSector = "";
                }
                //Packet code here
                //PacketDispatcher.sendPacketToServer(packet); //send packet
        }

}