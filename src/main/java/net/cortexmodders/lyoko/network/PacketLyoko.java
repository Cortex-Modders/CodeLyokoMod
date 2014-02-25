/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import io.netty.buffer.ByteBuf;

public abstract class PacketLyoko
{
    
    public PacketLyoko()
    {
    }
    
    public abstract void write(ByteBuf data);
    
    public abstract void read(ByteBuf data);
    
    public void writeString(String string, ByteBuf data)
    {
        byte[] stringBytes = string.getBytes();
        data.writeInt(stringBytes.length);
        data.writeBytes(stringBytes);
    }
    
    public String readString(ByteBuf data)
    {
        int length = data.readInt();
        byte[] stringBytes = new byte[length];
        data.readBytes(stringBytes);
        
        return new String(stringBytes);
    }
}
