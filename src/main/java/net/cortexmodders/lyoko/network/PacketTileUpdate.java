/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

/**
 * Base for tile updates. Basically just contains a x, y, and z coord.
 * 
 * @author Jadar
 */
public abstract class PacketTileUpdate<T extends PacketTileUpdate, E extends PacketLyoko> extends PacketLyoko<T, E>
{
    public int xCoord;
    public int yCoord;
    public int zCoord;
    public int dimensionId;

    public PacketTileUpdate() {}
    public PacketTileUpdate(int xCoord, int yCoord, int zCoord, World world)
    {
        this(xCoord, yCoord, zCoord, world.provider.dimensionId);
    }
    
    public PacketTileUpdate(int xCoord, int yCoord, int zCoord, int dimensionId)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.dimensionId = dimensionId;
    }
    
    @Override
    public void toBytes(ByteBuf data)
    {
        data.writeInt(this.xCoord);
        data.writeInt(this.yCoord);
        data.writeInt(this.zCoord);
    }
    
    @Override
    public void fromBytes(ByteBuf data)
    {
        this.xCoord = data.readInt();
        this.yCoord = data.readInt();
        this.zCoord = data.readInt();
    }



    public World getWorld()
    {
        return DimensionManager.getWorld(this.dimensionId);
    }
}
