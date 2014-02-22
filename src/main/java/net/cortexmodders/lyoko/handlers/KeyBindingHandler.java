/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.cortexmodders.lyoko.entities.vehicles.EntityVehicle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyBindingHandler
{
    
    @SubscribeEvent
    public void keyDown(KeyInputEvent event)
    {
        if(Keyboard.getEventKeyState())
        {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            GameSettings settings = Minecraft.getMinecraft().gameSettings;
            if (player != null && player.isRiding() && player.ridingEntity instanceof EntityVehicle)
            {
                EntityVehicle vehicle = (EntityVehicle) player.ridingEntity;
                if (Keyboard.getEventKey() == settings.keyBindJump.getKeyCode())
                    vehicle.motionY = 1.0D;
                else if (Keyboard.getEventKey() == settings.keyBindSneak.getKeyCode())
                    vehicle.motionY = -1.0D;
                else
                    vehicle.motionY = 0.0D;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream data = new DataOutputStream(bos);
                
                try
                {
                    data.writeInt(vehicle.getEntityId());
                    data.writeDouble(vehicle.motionY);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}