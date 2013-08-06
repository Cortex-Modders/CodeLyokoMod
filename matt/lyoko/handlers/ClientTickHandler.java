/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.handlers;

import java.util.EnumSet;

import matt.lyoko.CodeLyoko;
import matt.lyoko.render.TileAnimator;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler
{
	/**
     * Used to advance all the animator instances once every tick.
     * 
     * @param tickData
     */
    public void advanceAnimatorInstances(Object... tickData) {
        for(TileAnimator inst : CodeLyoko.animatorInstances) {
            inst.animate();
        }
    }
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.RENDER)))
        {
            advanceAnimatorInstances(tickData);
        }
    }
    
    @Override
    public EnumSet<TickType> ticks() 
    {
        return EnumSet.of(TickType.PLAYER, TickType.CLIENT, TickType.RENDER);
    }
    
    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
    	
    }
    
    @Override
    public String getLabel()
    {
        return null;
    }
}