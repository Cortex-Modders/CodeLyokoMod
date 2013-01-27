package matt.lyoko;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{
	private void onPlayerTick(EntityPlayer player)
	{
		/*
		if(player.inventory.armorInventory == new ItemStack[]{new ItemStack(CodeLyoko.AelitaBoots), new ItemStack(CodeLyoko.AelitaLegs), new ItemStack(CodeLyoko.AelitaChest), new ItemStack(CodeLyoko.AelitaHelmet)})
		{
			player.capabilities.allowFlying = true;
		}
		*/
		if (player.getCurrentItemOrArmor(4) != null && player.getCurrentItemOrArmor(3) != null
				&& player.getCurrentItemOrArmor(2) != null && player.getCurrentItemOrArmor(1) != null)
		{    
			ItemStack helmet = player.getCurrentItemOrArmor(4);
			ItemStack chest = player.getCurrentItemOrArmor(3);
			ItemStack legs = player.getCurrentItemOrArmor(2);
			ItemStack boots = player.getCurrentItemOrArmor(1);
			if (helmet.getItem() == CodeLyoko.AelitaHelmet && chest.getItem() == CodeLyoko.AelitaChest
					&& legs.getItem() == CodeLyoko.AelitaLegs && boots.getItem() == CodeLyoko.AelitaBoots)
			{
				player.capabilities.allowFlying = true;
				player.fallDistance = 0;
			}
			else if (helmet.getItem() == CodeLyoko.OddHelmet && chest.getItem() == CodeLyoko.OddChest
					&& legs.getItem() == CodeLyoko.OddLegs && boots.getItem() == CodeLyoko.OddBoots)
			{
				player.addPotionEffect((new PotionEffect(Potion.jump.getId(), 20, 3)));
				player.fallDistance = 0;
			}
			else if (helmet.getItem() == CodeLyoko.UlrichHelmet && chest.getItem() == CodeLyoko.UlrichChest
					&& legs.getItem() == CodeLyoko.UlrichLegs && boots.getItem() == CodeLyoko.UlrichBoots)
			{
				player.addPotionEffect((new PotionEffect(Potion.moveSpeed.getId(), 20, 2)));
				player.fallDistance = 0;
			}
			else if (helmet.getItem() == CodeLyoko.YumiHelmet && chest.getItem() == CodeLyoko.YumiChest
					&& legs.getItem() == CodeLyoko.YumiLegs && boots.getItem() == CodeLyoko.YumiBoots)
			{
				player.fallDistance = 0;
			}
			else if (helmet.getItem() == CodeLyoko.WilliamHelmet && chest.getItem() == CodeLyoko.WilliamChest
					&& legs.getItem() == CodeLyoko.WilliamLegs && boots.getItem() == CodeLyoko.WilliamBoots)
			{
				player.fallDistance = 0;
			}
		}
		
		if(CodeLyoko.isSpecialAbilityModeEnabled)
		{
			if(player.username == "986523714" || player.username == "MoonMagick" || player.username == "Wolfspirit1st")
			{
				player.capabilities.allowFlying = true;
				player.capabilities.disableDamage = true;
			}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
