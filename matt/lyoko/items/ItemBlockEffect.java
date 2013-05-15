package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBlockEffect extends ItemBlock
{
	public ItemBlockEffect(int par1)
	{
		super(par1);
	}
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
	{
		if(stack.itemID == CodeLyoko.LeadOre.blockID || stack.itemID == CodeLyoko.UraniumOre.blockID)
		{
			if(ent instanceof EntityPlayer)
			{
				if(!((EntityPlayer)ent).capabilities.isCreativeMode)
				{
					((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.hunger.getId(), 500, 0)));
					((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.poison.getId(), 500, 0)));
				}
			}
			else if(ent instanceof EntityLiving)
			{
				((EntityLiving)ent).addPotionEffect((new PotionEffect(Potion.poison.getId(), 500, 0)));
			}
		}
		else if(stack.itemID == CodeLyoko.QuantumOre.blockID)
		{
			if(ent instanceof EntityLiving)
			{
				((EntityLiving)ent).clearActivePotions();
				((EntityLiving)ent).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, 0));
			}
		}
	}
}