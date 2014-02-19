/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.network;

import ibxm.Player;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.EnumMap;

import net.cortexmodders.lyoko.entities.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityTowerConsole;
import net.cortexmodders.lyoko.entities.vehicles.EntityVehicle;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHandler
{
    private static PacketHandler INSTANCE;
    
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    
    private PacketHandler() 
    {
        this.channels = NetworkRegistry.INSTANCE.newChannel("Console", new ConsolePacketHandler());
        
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            addClientHandler();
        }
    }
    
    public static void init()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new PacketHandler();
        }
    }
    
    public static PacketHandler getInstance()
    {
    	if(INSTANCE == null)
    	{
    		init();
    	}
        return INSTANCE;
    }
    
    @SideOnly(Side.CLIENT)
    private void addClientHandler() {
        FMLEmbeddedChannel clientChannel = this.channels.get(Side.CLIENT);
        // These two lines find the existing codec (Ironchestcodec) and insert our message handler after it
        // in the pipeline
        String codec = clientChannel.findChannelHandlerNameForType(IronChestCodec.class);
        clientChannel.pipeline().addAfter(codec, "ClientHandler", new ConsolePacketHandler());
    }
    
    public static class ConsolePacketHandler extends FMLIndexedMessageToMessageCodec<PacketNBTTag>
    {

        @Override
        public void encodeInto(ChannelHandlerContext ctx, PacketNBTTag msg, ByteBuf target) throws Exception
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, PacketNBTTag msg)
        {
            // TODO Auto-generated method stub
            
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

    private void handlePacketConsole(DataInputStream data, World world)
    {
        String code;
        int x;
        int y;
        int z;

        try
        {
            code = data.readUTF();
            x = data.readInt();
            y = data.readInt();
            z = data.readInt();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

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

    private void handlePacketLP(DataInputStream data, World world, EntityPlayer player)
    {
        int lifepoints;

        try
        {
            lifepoints = data.readInt();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        if (player != null)
        {
            PlayerInformation pi = PlayerInformation.forPlayer(player);
            pi.setLifePoints(lifepoints);
        }
    }

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

    private void handlePacketV(DataInputStream data, World world)
    {
        int entityId;
        double motionY;
        try
        {
            entityId = data.readInt();
            motionY = data.readDouble();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        EntityVehicle vehicle = (EntityVehicle) world.getEntityByID(entityId);
        if (vehicle != null)
            vehicle.motionY = motionY;
    }
    */
}
