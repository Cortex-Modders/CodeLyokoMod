/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.handlers;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.mobs.EntityLyoko;
import net.cortexmodders.lyoko.entities.mobs.EntitySpecter;
import net.cortexmodders.lyoko.entities.mobs.EntityXanafiedMob;
import net.cortexmodders.lyoko.entities.vehicles.EntityVehicle;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.cortexmodders.lyoko.lib.UniqueArmorGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class EventHandler extends Gui
{
    private Minecraft mc;
    private static final ResourceLocation inventoryTexture = new ResourceLocation("/gui/inventory.png");

    public EventHandler()
    {
        super();
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

        this.drawString(this.mc.fontRenderer, "Life Points: " + pi.getLifePoints(), xPos, yPos, 16777215);
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
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
    	Entity ent = event.entity;
    	if(!(ent instanceof EntityPlayer || ent instanceof EntityLyoko || ent instanceof EntitySpecter || ent instanceof IProjectile || ent instanceof EntityVehicle))
    	{
    		if(CodeLyoko.entityInLyoko(ent))
    		{
    			event.setCanceled(true);
    		}
    	}
    }

    @SubscribeEvent
    public void onEntityConstruct(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer)
            event.entity.registerExtendedProperties(PlayerInformation.IDENTIFIER, new PlayerInformation((EntityPlayer) event.entity));
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerChangedDimensionEvent event)
    {
        PlayerInformation.forPlayer(event.player).setDirty();
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        System.out.println(UniqueArmorGenerator.getUniquePlayerIdentifier(event.player));
        PlayerInformation.forPlayer(event.player).setDirty();
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event)
    {

    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        PlayerInformation.forPlayer(event.player).setDirty();
    }
}
