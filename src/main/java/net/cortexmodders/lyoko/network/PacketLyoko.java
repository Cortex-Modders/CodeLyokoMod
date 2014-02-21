package net.cortexmodders.lyoko.network;

import io.netty.buffer.ByteBuf;


public abstract class PacketLyoko
{
    
    public PacketLyoko() {}
    
    public abstract void write(ByteBuf data);
    public abstract void read(ByteBuf data);
    
}
