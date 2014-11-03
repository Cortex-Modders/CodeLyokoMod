/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.relauncher.Side;

public enum PacketType
{

    PLAYER_INFORMATION(PacketPlayerInformation.class, Side.SERVER),
    CONSOLE_COMMAND(PacketConsoleCommand.class, Side.SERVER);

    public final Class<? extends IMessage> packetClass;
    public final Class<? extends IMessageHandler/*<? extends IMessage, ? extends IMessage>*/> messageHandlerClass;
    public final Side recieveSide;

    //    @SuppressWarnings("unchecked")
    private PacketType(Class<? extends PacketLyoko> packetClass, Side recieveSide)
    {
        this(packetClass, packetClass, recieveSide);
    }

    private PacketType(Class<? extends PacketLyoko> packetClass,
                       Class<? extends IMessageHandler/*<? extends PacketLyoko, ? extends PacketLyoko>*/> messageHandlerClass,
                       Side recieveSide)
    {
        this.packetClass = packetClass;
        this.messageHandlerClass = messageHandlerClass;
        this.recieveSide = recieveSide;
    }
}
