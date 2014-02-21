package net.cortexmodders.lyoko.network;

import io.netty.buffer.ByteBuf;


public class PacketConsoleCommand extends PacketTileUpdate
{

    public String command;
    
    public PacketConsoleCommand(String command, int xCoord, int yCoord, int zCoord)
    {
        super(xCoord, yCoord, zCoord);

        this.command = command;
    }

    @Override
    public void write(ByteBuf data)
    {
        // call super first!
        super.write(data);
        
        writeString(command, data);
    }

    @Override
    public void read(ByteBuf data)
    {
        // call super first!
        super.read(data);
        
        this.command = readString(data);
    }
    
    
    
    
}
