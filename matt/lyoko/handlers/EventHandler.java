package matt.lyoko.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import matt.lyoko.CodeLyoko;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

public class EventHandler extends Gui
{
	private Minecraft mc;
	
	public EventHandler(Minecraft mc)
	{
		super();
		this.mc = mc;
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
		
		if(this.mc.thePlayer.getEntityData().hasKey("lifePoints"))
		{
			this.drawString(mc.fontRenderer, "Life Points: " + this.mc.thePlayer.getEntityData().getByte("lifePoints"), xPos, yPos, 16777215);
		}
	}
}