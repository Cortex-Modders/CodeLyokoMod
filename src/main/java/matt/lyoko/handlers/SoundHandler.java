/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.handlers;

import java.util.logging.Level;

import matt.lyoko.ModLogger;
import matt.lyoko.lib.ModProperties;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler
{
	/**
	 * When Minecraft loads sounds, load our sounds too.
	 * 
	 * @param event
	 */
	@ForgeSubscribe
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
			manager.addSound(ModProperties.SOUND_PREFIX + file);
		}
		catch(Exception e)
		{
			ModLogger.log(Level.WARNING, "Error cannot load sound " + file);
		}
	}
}