/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.EnumMap;

import net.cortexmodders.lyoko.lib.ModProperties;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalcConsole;
import net.cortexmodders.lyoko.tileentity.TileEntityTowerConsole;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHandler 
{
    private static PacketHandler INSTANCE = new PacketHandler();
    
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    
    private PacketHandler() 
    {
        this.channels = NetworkRegistry.INSTANCE.newChannel(ModProperties.MOD_ID, new ChannelCodec());
        
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            addClientHandler();
        }
    }
    
    public static PacketHandler getInstance()
    {
        return INSTANCE;
        
    }
    
    @SideOnly(Side.CLIENT)
    private void addClientHandler() {
        FMLEmbeddedChannel clientChannel = this.channels.get(Side.CLIENT);
        
        String codec = clientChannel.findChannelHandlerNameForType(ChannelCodec.class);
        clientChannel.pipeline().addAfter(codec, "PlayerInformation", new ChannelHandler());
    }
    
    /**
     * Wrapper method for {@link FMLEmbeddedChannel#generatePacketFrom(Object)}.
     * Must have a codec in place to transform it for it to return anything.
     * 
     * @param msg object to generate from
     * @param side channel to side being sent to
     * @return created packet
     */
    public Packet generatePacketFrom(PacketLyoko msg, Side side)
    {
        return this.channels.get(side).generatePacketFrom(msg);
    }
    
    @SideOnly(Side.CLIENT)
    public void sendPacketToServer(Packet packet)
    {
        sendPacketToTarget(packet, FMLOutboundHandler.OutboundTarget.TOSERVER);
    }
    
    public void sendPacketToPlayer(Packet packet, EntityPlayer player)
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        sendPacketToTarget(packet, FMLOutboundHandler.OutboundTarget.PLAYER);
    }
    
    public void sendPacketToAllPlayers(Packet packet, EntityPlayer player)
    {
        sendPacketToTarget(packet, FMLOutboundHandler.OutboundTarget.ALL);
    }
    
    protected void sendPacketToTarget(Packet packet, OutboundTarget target)
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(target);
        channels.get(Side.SERVER).writeOutbound(packet);
    }
    
    private static class ChannelCodec extends FMLIndexedMessageToMessageCodec<PacketLyoko>
    {

        public ChannelCodec()
        {
            for(PacketType type : PacketType.values())
            {
                addDiscriminator(type.ordinal(), type.packetClass);
            }
        }
        
        @Override
        public void encodeInto(ChannelHandlerContext ctx, PacketLyoko msg, ByteBuf target) throws Exception
        {
            msg.write(target);
        }

        @Override
        public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, PacketLyoko msg)
        {
            msg.read(source);
            
            
        }
    }
    
    @SideOnly(Side.CLIENT)
    private static class ChannelHandler extends SimpleChannelInboundHandler<PacketLyoko>
    {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, PacketLyoko packet) throws Exception
        {
            if(packet instanceof PacketConsoleCommand)
            {
                PacketHandler.getInstance().handlePacketConsole((PacketConsoleCommand) packet);
            }
            else if(packet instanceof PacketPlayerInformation)
            {
                PacketHandler.getInstance().handlePacketPlayerInformation((PacketPlayerInformation) packet, Minecraft.getMinecraft().thePlayer);
            }
        }
    }
    
    /*
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
        EntityPlayer sender = (EntityPlayer) player;

        if (packet.channel.equals("Console"))
            this.handlePacketConsole(data, sender.worldObj);
        if (packet.channel.equals("LifePoints"))
            this.handlePacketLP(data, sender.worldObj, sender);
        if (packet.channel.equals("Devirt"))
            this.handlePacketD(data, sender.worldObj, sender);
        if (packet.channel.equals("ScannerDoors"))
            this.handlePacketSD(data, sender.worldObj);
        if (packet.channel.equals("Vehicle"))
            this.handlePacketV(data, sender.worldObj);
    }
*/
    private void handlePacketConsole(PacketConsoleCommand packet)
    {
        String code = packet.command;
        int x       = packet.xCoord;
        int y       = packet.yCoord;
        int z       = packet.zCoord;
        World world = packet.getWorld(); 
        
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityTowerConsole)
        {
            TileEntityTowerConsole tetc = (TileEntityTowerConsole) world.getTileEntity(x, y, z);
            tetc.owner = code;
            world.markBlockForUpdate(x, y, z);
        }
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntitySuperCalcConsole)
        {
            TileEntitySuperCalcConsole tescc = (TileEntitySuperCalcConsole) world.getTileEntity(x, y, z);
            tescc.sector = code;
            world.markBlockForUpdate(x, y, z);
        }
    }

    private void handlePacketPlayerInformation(PacketPlayerInformation packet, EntityPlayer player)
    {
        //TODO: fix this so it doesn't need a player instance.
        int lifepoints = packet.lifePoints;

        if (player != null)
        {
            PlayerInformation pi = PlayerInformation.forPlayer(player);
            pi.setLifePoints(lifepoints);
        }
    }

    /*
    private void handlePacketD(DataInputStream data, World world, EntityPlayer player)
    {
        // int dim;
        double posX;
        double posY;
        double posZ;
        float yaw;

        try
        {
            // dim = data.readInt();
            posX = data.readDouble();
            posY = data.readDouble();
            posZ = data.readDouble();
            yaw = data.readFloat();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        if (player != null)
            // player.dimension = dim;
            player.setLocationAndAngles(posX, posY, posZ, yaw, 0);
    }

    private void handlePacketSD(DataInputStream data, World world)
    {
        boolean open;
        int x;
        int y;
        int z;

        try
        {
            open = data.readBoolean();
            x = data.readInt();
            y = data.readInt();
            z = data.readInt();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityScanner)
        {
            TileEntityScanner tes = (TileEntityScanner) world.getTileEntity(x, y, z);
            tes.doorsOpen = open;
            world.markBlockForUpdate(x, y, z);
        }
    }
    */
}
