/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.handlers;

import net.cortexmodders.lyoko.lib.ModLogger;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SoundHandler
{
	/**
	 * When Minecraft loads sounds, load our sounds too.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public void onSoundLoad(SoundLoadEvent event)
	{
		this.loadSound(event.manager, "scannerClose.ogg");
		this.loadSound(event.manager, "scannerOpen.ogg");
	}
	
	/**
	 * Tries to load sounds to game.
	 * 
	 * @param manager
	 * @param file
	 */
	public void loadSound(SoundManager manager, String file)
	{
		try
		{
		    //TODO: figure out new sound system
			//manager. //(ModProperties.SOUND_PREFIX + file);
		}
		catch(Exception e)
		{
			ModLogger.log(Level.WARN, "Error cannot load sound " + file);
		}
	}
}
