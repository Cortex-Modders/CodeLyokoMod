/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;

public class PacketConsoleCommand extends PacketTileUpdate
{
    
    public String command;
    
    public PacketConsoleCommand(String command, int xCoord, int yCoord, int zCoord, int dimensionId)
    {
        super(xCoord, yCoord, zCoord, dimensionId);
        
        this.command = command;
    }
    
    public PacketConsoleCommand(String command, int xCoord, int yCoord, int zCoord, World world)
    {
        this(command, xCoord, yCoord, zCoord, world.provider.dimensionId);
    }
    
    @Override
    public void write(ByteBuf data)
    {
        // call super first!
        super.write(data);
        
        this.writeString(this.command, data);
    }
    
    @Override
    public void read(ByteBuf data)
    {
        // call super first!
        super.read(data);
        
        this.command = this.readString(data);
    }
}
