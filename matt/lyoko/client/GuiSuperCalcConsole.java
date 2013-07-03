package matt.lyoko.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import matt.lyoko.CodeLyoko;
import matt.lyoko.container.ContainerSuperCalcConsole;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.player.*;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringTranslate;

public class GuiSuperCalcConsole extends GuiContainer
{
    private GuiTextField textBoxCode;
    
    private String code;
    
	private EntityPlayer player;
	public TileEntitySuperCalcConsole tscc;
    
	public GuiSuperCalcConsole (InventoryPlayer inv, TileEntitySuperCalcConsole tileEntity)
	{
		//the container is instanciated and passed to the superclass for handling
		super(new ContainerSuperCalcConsole(inv, tileEntity));
		this.ySize = 119;
		this.xSize = 176;
		tscc = tileEntity;
		player = inv.player;
		code = "";
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		textBoxCode = new GuiTextField(fontRenderer, ((width - xSize) / 2) + (xSize/2) - 40, ((height - ySize) / 2) + (ySize/2), 80, 10);
		textBoxCode.setText(code);
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
        
        if(par2 == 28)
        {
        	ByteArrayOutputStream bos = new ByteArrayOutputStream(code.length() + 12);
        	DataOutputStream outputStream = new DataOutputStream(bos);
        	try
        	{
        		outputStream.writeUTF(code);
        		outputStream.writeInt(tscc.xCoord);
        		outputStream.writeInt(tscc.yCoord);
        		outputStream.writeInt(tscc.zCoord);
        	}
        	catch (Exception ex)
        	{
        		ex.printStackTrace();
        	}
        	
        	Packet250CustomPayload packet = new Packet250CustomPayload();
        	packet.channel = "SuperCalcConsole";
        	packet.data = bos.toByteArray();
        	packet.length = bos.size();
        	
        	((EntityClientPlayerMP)player).sendQueue.addToSendQueue(packet);
        	
        	this.textBoxCode.setText("");
        }

        if (par2 == 1 || (par2 == mc.gameSettings.keyBindInventory.keyCode && !this.textBoxCode.isFocused()))
        {
            this.mc.thePlayer.closeScreen();
        }
        
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
		//draw text and stuff here
		//the parameters for drawString are: string, x, y, color
		//fontRenderer.drawString("Code", 75, 36, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		//draw your Gui here, only thing you need to change is the path
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_110577_a(new ResourceLocation("/mods/lyoko/textures/gui/towerconsole.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}