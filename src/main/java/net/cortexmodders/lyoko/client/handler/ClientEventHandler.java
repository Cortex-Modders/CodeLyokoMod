package net.cortexmodders.lyoko.client.handler;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.mob.EntityXanafiedMob;
import net.cortexmodders.lyoko.lib.ModLogger;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler
{
    
    private Minecraft mc;
    private static final ResourceLocation inventoryTexture = new ResourceLocation("/gui/inventory.png");
    
    public ClientEventHandler()
    {
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void renderGameOverlayEvent(RenderGameOverlayEvent event)
    {
        // We draw after the ExperienceBar has drawn. The event raised by
        // GuiIngameForge.pre()
        // will return true from isCancelable. If you call
        // event.setCanceled(true) in
        // that case, the portion of rendering which this event represents will
        // be canceled.
        // We want to draw *after* the experience bar is drawn, so we make sure
        // isCancelable() returns
        // false and that the eventType represents the ExperienceBar event.
        if (event.isCancelable() || event.type != ElementType.EXPERIENCE || !CodeLyoko.entityInLyoko(this.mc.thePlayer))
            return;
        
        // Starting position for the buff bar - 2 pixels from the top left
        // corner.
        int xPos = 2;
        int yPos = 2;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.mc.renderEngine.bindTexture(inventoryTexture);
        
        PlayerInformation pi = PlayerInformation.forPlayer(this.mc.thePlayer);
        if (pi == null) {
            CodeLyoko.instance.getLogger().warn(String.format("Player info for %s could not be retrieved!", this.mc.thePlayer.getCommandSenderName()));
        } else if (!Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            this.mc.fontRenderer.drawStringWithShadow("Life Points: " + pi.getLifePoints(), xPos, yPos, 16777215);
        }
    }
    
    @SubscribeEvent
    public void onLivingRender(RenderLivingEvent.Specials.Pre event)
    {
        if (event.entity instanceof EntityXanafiedMob)
        {
            EntityXanafiedMob xana = (EntityXanafiedMob) event.entity;
            if (xana.infectedMob != null)
            {
                // event.setCanceled(true);
            }
        }
    }
}
