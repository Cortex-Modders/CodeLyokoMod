/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;

import matt.lyoko.entities.vehicles.EntityVehicle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class KeyBindingHandler extends KeyHandler
{
    // static KeyBinding myBinding = new KeyBinding("COMING SOON",
    // Keyboard.KEY_L);

    public KeyBindingHandler()
    {
        // the first value is an array of KeyBindings, the second is whether or
        // not the call
        // keyDown should repeat as long as the key is down
        super(new KeyBinding[] {});// new KeyBinding[]{myBinding}, new
                                   // boolean[]{false});
    }

    @Override
    public String getLabel()
    {
        return "LyokoKeyBindingHandler";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        if (player != null && player.isRiding() && player.ridingEntity instanceof EntityVehicle)
        {
            EntityVehicle vehicle = (EntityVehicle) player.ridingEntity;
            if (kb.keyCode == settings.keyBindJump.keyCode)
                vehicle.motionY = 1.0D;
            else if (kb.keyCode == settings.keyBindSneak.keyCode)
                vehicle.motionY = -1.0D;
            else
                vehicle.motionY = 0.0D;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream data = new DataOutputStream(bos);

            try
            {
                data.writeInt(vehicle.entityId);
                data.writeDouble(vehicle.motionY);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            Packet packet = PacketDispatcher.getPacket("Vehicle", bos.toByteArray());
            PacketDispatcher.sendPacketToServer(packet);
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }
}