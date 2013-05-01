package matt.lyoko.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import matt.lyoko.entities.*;

public class PacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
        EntityPlayer sender = (EntityPlayer) player;
        
        handlePacket(packet, sender.worldObj);
    }
    
    private void handlePacket(Packet250CustomPayload packet, World world)
    {
    	DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        String code;
        int x;
        int y;
        int z;
        
        try
        {
        	code = inputStream.readUTF();
        	x = inputStream.readInt();
        	y = inputStream.readInt();
        	z = inputStream.readInt();
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        	return;
        }
        
        if(world.getBlockTileEntity(x, y, z) != null && world.getBlockTileEntity(x, y, z) instanceof TileEntityTowerConsole)
        {
        	TileEntityTowerConsole tetc = (TileEntityTowerConsole) world.getBlockTileEntity(x, y, z);
        	tetc.owner = code;
        }
        
        System.out.println(code + " " + x + " " + y + " " + z);
    }
}