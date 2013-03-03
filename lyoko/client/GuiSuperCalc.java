package matt.lyoko.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.*;
import matt.lyoko.CodeLyoko;
import matt.lyoko.container.ContainerSuperCalc;
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
                
                fontRenderer.drawString("Selected Sector: " + tsc.selectedSector, 8, 42, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
                
                fontRenderer.drawString("Time Remaining: " + tsc.getRemainingTime(), 80, 25, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
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
                controlList.add(new GuiButton(6, 361, 154, 34, 20, "none"));
        }
        
        protected void actionPerformed(GuiButton guibutton) {
            //id is the id you give your button
        	String sectorSelection = "";
        	switch(guibutton.id) {
        	case 1:
        		if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
        		{
        			break;
        		}
        		sectorSelection = "ice";
        		break;
        	case 2:
        		if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
        		{
        			break;
        		}
        		sectorSelection = "forest";
        		break;
        	case 3:
        		if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
        		{
        			break;
        		}
        		sectorSelection = "mountain";
        		break;
        	case 4:
        		if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
        		{
        			break;
        		}
        		sectorSelection = "desert";
        		break;
        	case 5:
        		if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
        		{
        			break;
        		}
        		sectorSelection = "carthage";
        		break;
        	case 6:
        		if(tsc.getStackInSlot(0) == null || !(tsc.getStackInSlot(0).getItem() instanceof ItemLyokoFuel))
        		{
        			break;
        		}
        		sectorSelection = "";
        		break;
        	default:
        		sectorSelection = "";
        	}
        	
        	ByteArrayOutputStream bos = new ByteArrayOutputStream(22);
        	DataOutputStream outputStream = new DataOutputStream(bos);
        	try
        	{
        		outputStream.writeUTF(sectorSelection);
        		outputStream.writeInt(tsc.xCoord);
        		outputStream.writeInt(tsc.yCoord);
        		outputStream.writeInt(tsc.zCoord);
        	}
        	catch (Exception ex)
        	{
        		ex.printStackTrace();
        	}
        	
        	Packet250CustomPayload packet = new Packet250CustomPayload();
        	packet.channel = "Code_Lyoko";
        	packet.data = bos.toByteArray();
        	packet.length = bos.size();

        	Side side = FMLCommonHandler.instance().getEffectiveSide();
        	if (side == Side.SERVER)
        	{
        		EntityPlayerMP playerMP = (EntityPlayerMP) player;
        	}
        	else if (side == Side.CLIENT)
        	{
        		EntityClientPlayerMP playerMP = (EntityClientPlayerMP) player;
        		playerMP.sendQueue.addToSendQueue(packet);
        	}
        	else
        	{
        		
        	}
        }
}