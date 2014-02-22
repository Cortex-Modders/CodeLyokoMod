/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

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
