/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.handler;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.mob.EntityLyoko;
import net.cortexmodders.lyoko.entities.mob.EntitySpecter;
import net.cortexmodders.lyoko.entities.vehicle.EntityVehicle;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.cortexmodders.lyoko.lib.UniqueArmorGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class EventHandler
{

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
