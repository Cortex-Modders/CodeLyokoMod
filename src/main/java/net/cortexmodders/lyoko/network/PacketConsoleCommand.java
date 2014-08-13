/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalcConsole;
import net.cortexmodders.lyoko.tileentity.TileEntityTowerConsole;
import net.minecraft.world.World;

public class PacketConsoleCommand extends PacketTileUpdate<PacketConsoleCommand, PacketLyoko>
{
    
    public String command;

    // instantion
    public PacketConsoleCommand() {}

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
    public void toBytes(ByteBuf data)
    {
        super.toBytes(data);
        
        this.writeString(this.command, data);
    }
    
    @Override
    public void fromBytes(ByteBuf data)
    {
        super.fromBytes(data);

        this.command = this.readString(data);
    }

    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @param ctx
     * @return an optional return message
     */
    @Override
    public PacketLyoko onMessage(PacketConsoleCommand packet, MessageContext ctx) {

        String code = packet.command;
        int x = packet.xCoord;
        int y = packet.yCoord;
        int z = packet.zCoord;
        World world = packet.getWorld();

        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityTowerConsole)
        {
            TileEntityTowerConsole tetc = (TileEntityTowerConsole) world.getTileEntity(x, y, z);
            tetc.owner = code;
            world.markBlockForUpdate(x, y, z);
        }
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntitySuperCalcConsole) {
            TileEntitySuperCalcConsole tescc = (TileEntitySuperCalcConsole) world.getTileEntity(x, y, z);
            tescc.sector = code;
            world.markBlockForUpdate(x, y, z);
        }

        return null;
    }
}
