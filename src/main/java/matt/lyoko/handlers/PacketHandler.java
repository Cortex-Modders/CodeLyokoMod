/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import matt.lyoko.entities.vehicles.EntityVehicle;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
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
}
