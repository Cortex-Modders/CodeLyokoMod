package matt.lyoko.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.PlayerInformation;
import matt.lyoko.world.LyokoTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ServerTickHandler implements ITickHandler
{
	private void onPlayerTick(EntityPlayer player)
	{
		if(CodeLyoko.enableAdminPowers)
		{
			if(player.username.equals(CodeLyoko.getDevelopers()[0]))
			{
				player.capabilities.allowFlying = true;
				player.fallDistance = 0;
				player.sendPlayerAbilities();
			}
			else if(player.username.equals(CodeLyoko.getDevelopers()[1]))
			{
				player.addPotionEffect((new PotionEffect(Potion.jump.getId(), 20, 3)));
				player.fallDistance = 0;
			}
			else if(player.username.equals(CodeLyoko.getDevelopers()[2]))
			{
				player.addPotionEffect((new PotionEffect(Potion.jump.getId(), 20, 3)));
				player.fallDistance = 0;
			}
			else if(player.username.equals(CodeLyoko.getDevelopers()[3]))
			{
				if(player.isSprinting())
				{
					player.addPotionEffect((new PotionEffect(Potion.moveSpeed.getId(), 20, 2)));
				}
				player.fallDistance = 0;
			}
		}
		
		PlayerInformation pi = PlayerInformation.forPlayer(player);
		
		if(pi.getCoolDown() > 0)
		{
			pi.decreaseCoolDown(1);
		}
		
		if(pi.getLifePoints() <= 0 && CodeLyoko.entityInLyoko(player))
		{
			if(player instanceof EntityPlayerMP)
			{
				LyokoTeleporter.transferPlayerToDimension((EntityPlayerMP)player, pi.scannerDim);
			}
			player.setPositionAndRotation(pi.getScannerPosX() + 0.5D, pi.getScannerPosY(), pi.getScannerPosZ() + 0.5D, pi.scannerYaw, 0.0F);
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream(32);
		    DataOutputStream outputStream = new DataOutputStream(bos);
		    try
		    {
		    	outputStream.writeDouble(player.posX);
		    	outputStream.writeDouble(player.posY);
		    	outputStream.writeDouble(player.posZ);
		    	outputStream.writeFloat(player.rotationYaw);
		    }
		    catch (Exception ex)
		    {
		    	ex.printStackTrace();
		    }
		    
		    Packet250CustomPayload packet = new Packet250CustomPayload();
		    packet.channel = "Devirt";
		    packet.data = bos.toByteArray();
		    packet.length = bos.size();
		    
		    PacketDispatcher.sendPacketToPlayer(packet,(Player) player);
		    
		    pi.setLifePoints(100);
		}
	}
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.PLAYER)))
        {
        	onPlayerTick((EntityPlayer)tickData[0]);
        }
    }
    
    @Override
    public EnumSet<TickType> ticks() 
    {
        return EnumSet.of(TickType.PLAYER, TickType.SERVER);
    }
    
    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
    	
    }
    
    @Override
    public String getLabel()
    {
        return "Code Lyoko Server Tick Handler";
    }
}