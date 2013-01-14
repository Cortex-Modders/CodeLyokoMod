package matt.lyoko;

// These are the required imports
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

// This is required and all you do is create a class and implement IPacketHadnler
public class PacketHandler implements IPacketHandler{
// This method, is required and again just copy the code, because it will be pretty much the same for every mod
// Unless it has special things that need to be done, and I assume (not judging or anything but just saying) that none
// Of you have any knowledge of what goes here, so really don't mess with it, I don't even know what can go here
@Override
public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player){
DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
EntityPlayer sender = (EntityPlayer) player;
}
}