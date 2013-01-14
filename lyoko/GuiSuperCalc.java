package matt.lyoko;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiSuperCalc extends GuiContainer {

        public GuiSuperCalc (InventoryPlayer inventoryPlayer,
                        TileEntitySuperCalc tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerSuperCalc(inventoryPlayer, tileEntity));
                this.ySize = 200;
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
                
                //fontRenderer.drawString("Fuel Cell: ", 8, 25, 4210752);
                //fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
                //draw your Gui here, only thing you need to change is the path
                int texture = mc.renderEngine.getTexture("/matt/lyoko/gui/supercalculator_test.png");
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
                controlList.add(new GuiButton(1, 275, 130, 26, 20, "Ice"));
                controlList.add(new GuiButton(2, 305, 130, 44, 20, "Forest"));
                controlList.add(new GuiButton(3, 353, 130, 53, 20, "Mountain"));
                controlList.add(new GuiButton(4, 290, 154, 44, 20, "Desert"));
                controlList.add(new GuiButton(5, 338, 154, 54, 20, "Carthage"));
        }
        
        protected void actionPerformed(GuiButton guibutton) {
                //id is the id you give your button
                switch(guibutton.id) {
                case 1:
                	//Dimension stuff here
                    //TODO
                    break;
                case 2:
                	//Dimension stuff here
                    //TODO
                	break;
                case 3:
                	//Dimension stuff here
                    //TODO
                	break;
                case 4:
                	//Dimension stuff here
                    //TODO
                	break;
                case 5:
                	//Dimension stuff here
                    //TODO
                }
                //Packet code here
                //PacketDispatcher.sendPacketToServer(packet); //send packet
        }

}