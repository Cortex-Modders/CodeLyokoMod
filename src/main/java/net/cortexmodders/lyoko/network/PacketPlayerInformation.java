package net.cortexmodders.lyoko.network;

import io.netty.buffer.ByteBuf;


public class PacketPlayerInformation extends PacketLyoko
{
    public int lifePoints = 0;
    
    public PacketPlayerInformation(int lifePoints)
    {
        this.lifePoints = lifePoints;
    }
    
    @Override
    public void write(ByteBuf data)
    {
        data.writeInt(this.lifePoints);
    }
    
    @Override
    public void read(ByteBuf data)
    {
        this.lifePoints = data.readInt();
    }
}
