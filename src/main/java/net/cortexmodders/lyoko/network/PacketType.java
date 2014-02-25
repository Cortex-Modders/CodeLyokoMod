/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

public enum PacketType
{
    PLAYER_INFORMATION(PacketPlayerInformation.class), CONSOLE_COMMAND(PacketConsoleCommand.class);
    
    public final Class<? extends PacketLyoko> packetClass;
    
    private PacketType(Class<? extends PacketLyoko> packetClass)
    {
        this.packetClass = packetClass;
    }
}
