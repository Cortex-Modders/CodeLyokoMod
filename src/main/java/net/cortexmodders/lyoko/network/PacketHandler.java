/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
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
import net.minecraft.entity.player.EntityPlayerMP;
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
import net.minecraftforge.common.MinecraftForge;

public enum PacketHandler
{
    INSTANCE;

    private SimpleNetworkWrapper networkHandler;

//    private EnumMap<Side, FMLEmbeddedChannel> channels;
    
    private PacketHandler()
    {
        this.networkHandler = NetworkRegistry.INSTANCE.newSimpleChannel(ModProperties.MOD_ID);
    }

//    @SuppressWarnings("unchecked")
    public void initPackets() {
        for (PacketType type : PacketType.values()) {

            this.networkHandler.registerMessage((Class<IMessageHandler<IMessage, IMessage>>)type.messageHandlerClass,
                                                (Class<IMessage>)type.packetClass, type.ordinal(),
                                                type.recieveSide);
        }
//        this.networkHandler.registerMessage(PacketPlayerInformation.class, PacketPlayerInformation.class, 0, Side.SERVER);
//        this.networkHandler.registerMessage(PacketConsoleCommand.class,    PacketConsoleCommand.class,    1, Side.SERVER);
    }

    @SideOnly(Side.CLIENT)
    public void sendPacketToServer(PacketLyoko packet) {
        this.networkHandler.sendToServer(packet);
    }
    
    public void sendPacketToPlayer(PacketLyoko packet, EntityPlayerMP player) {
        this.networkHandler.sendTo(packet, player);
    }
    
    public void sendPacketToAllPlayers(PacketLyoko packet, EntityPlayerMP player) {
        this.networkHandler.sendToAll(packet);
    }

    public void sendPacketToDimension(PacketLyoko packet, int dimensionId) {
        this.networkHandler.sendToDimension(packet, dimensionId);
    }

    public void sendPackeAllAround(PacketLyoko packet, NetworkRegistry.TargetPoint point) {
        this.networkHandler.sendToAllAround(packet, point);
    }

    /*
     * @Override
     * public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player
     * player)
     * {
     * DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
     * EntityPlayer sender = (EntityPlayer) player;
     * if (packet.channel.equals("Devirt"))
     * this.handlePacketD(data, sender.worldObj, sender);
     * }
     */
    private void handlePacketConsole(PacketConsoleCommand packet)
    {
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
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntitySuperCalcConsole)
        {
            TileEntitySuperCalcConsole tescc = (TileEntitySuperCalcConsole) world.getTileEntity(x, y, z);
            tescc.sector = code;
            world.markBlockForUpdate(x, y, z);
        }
    }
    
    private void handlePacketPlayerInformation(PacketPlayerInformation packet, EntityPlayer player) {
        int lifepoints = packet.lifePoints;
        
        if (player != null)
        {
            PlayerInformation pi = PlayerInformation.forPlayer(player);
            pi.setLifePoints(lifepoints);
        }
    }
    
    /*
     * private void handlePacketD(DataInputStream data, World world, EntityPlayer player)
     * {
     * // int dim;
     * double posX;
     * double posY;
     * double posZ;
     * float yaw;
     * try
     * {
     * // dim = data.readInt();
     * posX = data.readDouble();
     * posY = data.readDouble();
     * posZ = data.readDouble();
     * yaw = data.readFloat();
     * } catch (IOException e)
     * {
     * e.printStackTrace();
     * return;
     * }
     * if (player != null)
     * // player.dimension = dim;
     * player.setLocationAndAngles(posX, posY, posZ, yaw, 0);
     * }
     * private void handlePacketSD(DataInputStream data, World world)
     * {
     * boolean open;
     * int x;
     * int y;
     * int z;
     * try
     * {
     * open = data.readBoolean();
     * x = data.readInt();
     * y = data.readInt();
     * z = data.readInt();
     * } catch (IOException e)
     * {
     * e.printStackTrace();
     * return;
     * }
     * if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof
     * TileEntityScanner)
     * {
     * TileEntityScanner tes = (TileEntityScanner) world.getTileEntity(x, y, z);
     * tes.doorsOpen = open;
     * world.markBlockForUpdate(x, y, z);
     * }
     * }
     */

//    public static Side getSideForTarget(OutboundTarget target) {
//        boolean isServer = FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
//
//        if (isServer && OutboundTarget == OutboundTarget.REPLY) {
//            return Side.SERVER;
//        }
//    }
}
