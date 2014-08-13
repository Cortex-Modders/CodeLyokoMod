/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.minecraft.entity.player.EntityPlayer;

public class PacketPlayerInformation extends PacketLyoko<PacketPlayerInformation, PacketLyoko>
{
    public int lifePoints = 0;

    public PacketPlayerInformation() {}
    public PacketPlayerInformation(int lifePoints)
    {
        this.lifePoints = lifePoints;
    }
    
    @Override
    public void toBytes(ByteBuf data)
    {
        data.writeInt(this.lifePoints);
    }
    
    @Override
    public void fromBytes(ByteBuf data)
    {
        this.lifePoints = data.readInt();
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
    public PacketLyoko onMessage(PacketPlayerInformation packet, MessageContext ctx) {
        int lifepoints = packet.lifePoints;

        EntityPlayer player = ctx.getServerHandler().playerEntity;

        if (player != null)
        {
            PlayerInformation pi = PlayerInformation.forPlayer(player);
            pi.setLifePoints(lifepoints);
        }

        return null;
    }
}
