package net.cortexmodders.lyoko.network;

import io.netty.buffer.ByteBuf;

/**
 * Base for tile updates. Basically just contains a x, y, and z coord.
 * 
 * @author Jadar
 */
public abstract class PacketTileUpdate extends PacketLyoko
{
    public int xCoord;
    public int yCoord;
    public int zCoord;
    
    public PacketTileUpdate(int xCoord, int yCoord, int zCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }
    
    @Override
    public void write(ByteBuf data)
    {
        data.writeInt(this.xCoord);
        data.writeInt(this.yCoord);
        data.writeInt(this.zCoord);
    }

    @Override
    public void read(ByteBuf data)
    {
        this.xCoord = data.readInt();
        this.yCoord = data.readInt();
        this.zCoord = data.readInt();
    }
}
