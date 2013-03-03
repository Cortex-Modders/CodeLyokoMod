package matt.lyoko;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import matt.lyoko.entities.TileEntitySuperCalc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		EntityPlayer sender = (EntityPlayer) player;
		
		if (packet.channel.equals("Code_Lyoko"))
		{
            handleButtonPush(packet, sender);
		}
	}
     
     private void handleButtonPush(Packet250CustomPayload packet, EntityPlayer player)
     {
    	 DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
    	 String sector;
    	 int x;
    	 int y;
    	 int z;
    	 
    	 try
    	 {
    		 sector = inputStream.readUTF();
    		 x = inputStream.readInt();
    		 y = inputStream.readInt();
    		 z = inputStream.readInt();
    	 } 
    	 catch (IOException e)
    	 {
    		 e.printStackTrace();
    		 return;
    	 }
    	 
    	 System.out.println(sector + " " + x + " " + y + " " + z);
    	 
    	 World world = player.worldObj;
    	 TileEntitySuperCalc tsc = (TileEntitySuperCalc)world.getBlockTileEntity(x, y, z);
    	 tsc.selectedSector = sector;
    	 System.out.println(tsc.selectedSector);
     }
}