package matt.lyoko;

import java.util.EnumSet;
import java.util.List;
import matt.lyoko.render.TileAnimator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{
	private void onPlayerTick(EntityPlayer player)
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