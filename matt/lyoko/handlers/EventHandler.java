package matt.lyoko.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.IPlayerTracker;

public class EventHandler extends Gui implements IPlayerTracker
{
	private Minecraft mc;
	
	public EventHandler()
	{
		super();
		this.mc = Minecraft.getMinecraft();
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void renderGameOverlayEvent(RenderGameOverlayEvent event)
	{
		// We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
		// will return true from isCancelable.  If you call event.setCanceled(true) in
		// that case, the portion of rendering which this event represents will be canceled.
		// We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
		// false and that the eventType represents the ExperienceBar event.
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE)// || !CodeLyoko.playerInLyoko(this.mc.thePlayer))
		{      
			return;
		}
		
		// Starting position for the buff bar - 2 pixels from the top left corner.
		int xPos = 2;
		int yPos = 2;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.mc.renderEngine.bindTexture("/gui/inventory.png");
		
		PlayerInformation pi = PlayerInformation.forPlayer(this.mc.thePlayer);
		
		int lifePoints = pi.getLifePoints();
		
		this.drawString(mc.fontRenderer, "Life Points: " + lifePoints, xPos, yPos, 16777215);
	}
	
	@ForgeSubscribe
    public void onEntityConstruct(EntityEvent.EntityConstructing event)
	{
        if(event.entity instanceof EntityPlayer)
        {
            event.entity.registerExtendedProperties(PlayerInformation.IDENTIFIER, new PlayerInformation((EntityPlayer)event.entity));
        }
    }
	
	@Override
    public void onPlayerChangedDimension(EntityPlayer player)
	{
        PlayerInformation.forPlayer(player).setDirty();
    }
	
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
    	
    }
    
    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
    	
    }
    
    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        PlayerInformation.forPlayer(player).setDirty();
    }
}