/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import matt.lyoko.CodeLyoko;
import matt.lyoko.container.ContainerTowerConsole;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiTowerConsole extends GuiContainer
{
    private GuiTextField textBoxCode;

    private String code;

    private EntityPlayer player;
    public TileEntityTowerConsole ttc;

    public GuiTowerConsole(InventoryPlayer inv, TileEntityTowerConsole tileEntity)
    {
        // the container is instanciated and passed to the superclass for
        // handling
        super(new ContainerTowerConsole(inv, tileEntity));
        this.ySize = 119;
        this.xSize = 176;
        this.ttc = tileEntity;
        this.player = inv.player;
        this.code = "";
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.textBoxCode = new GuiTextField(this.field_146289_q, (this.width - this.xSize) / 2 + this.xSize / 2 - 40, (this.height - this.ySize) / 2 + this.ySize / 2, 80, 10);
        this.textBoxCode.func_146180_a(this.code);
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
            if (this.code.equals("developer"))
            {
                boolean valid = false;
                for (int dev = 0; dev < CodeLyoko.getDevelopers().length; dev++)
                    if (this.player.username.equals(CodeLyoko.getDevelopers()[dev]))
                        valid = true;
                // player.sendChatToPlayer("A tower has been activated at: " +
                // ttc.xCoord + ", " + ttc.yCoord + ", " + ttc.zCoord +
                // ", in dimension: " + ttc.worldObj.provider.dimensionId);
                // player.sendChatToPlayer("Automatic deactivation will occur in 10 minutes");
                if (!valid)
                    this.code = "lol";
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream(this.code.length() * 2 + 12);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try
            {
                outputStream.writeUTF(this.code);
                outputStream.writeInt(this.ttc.xCoord);
                outputStream.writeInt(this.ttc.yCoord);
                outputStream.writeInt(this.ttc.zCoord);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "Console";
            packet.data = bos.toByteArray();
            packet.length = bos.size();

            ((EntityClientPlayerMP) this.player).sendQueue.addToSendQueue(packet);

            this.textBoxCode.func_146191_b("");
        }

        if (par2 == 1 || par2 == this.field_146297_k.gameSettings.keyBindInventory.keyCode && !this.textBoxCode.isFocused())
            this.field_146297_k.thePlayer.closeScreen();

    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);

        this.textBoxCode.func_146192_a(par1, par2, par3);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        this.textBoxCode.drawTextBox();
    }

    @Override
    protected void func_146979_b(int param1, int param2)
    {
        // draw text and stuff here
        // the parameters for drawString are: string, x, y, color
        this.field_146289_q.drawString("Code", 75, 36, 4210752);
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3)
    {
        // draw your Gui here, only thing you need to change is the path
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.renderEngine.bindTexture(new ResourceLocation("lyoko:textures/gui/towerconsole.png"));
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}