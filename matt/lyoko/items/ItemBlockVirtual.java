package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockVirtual extends ItemBlock
{
	public ItemBlockVirtual(int par1)
	{
		super(par1);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity ent, int slot, boolean par5)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ent;
			
			if (player.getCurrentItemOrArmor(4) != null && player.getCurrentItemOrArmor(3) != null
					&& player.getCurrentItemOrArmor(2) != null && player.getCurrentItemOrArmor(1) != null)
			{
				ItemStack helmet = player.getCurrentItemOrArmor(4);
				ItemStack chest = player.getCurrentItemOrArmor(3);
				ItemStack legs = player.getCurrentItemOrArmor(2);
				ItemStack boots = player.getCurrentItemOrArmor(1);
				if (helmet.getItem() == ModItems.AelitaHelmet && chest.getItem() == ModItems.AelitaChest
						&& legs.getItem() == ModItems.AelitaLegs && boots.getItem() == ModItems.AelitaBoots)
				{
					if(stack.getItem().itemID != CodeLyoko.VirtualBlock.blockID)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else
				{
					player.inventory.setInventorySlotContents(slot, null);
				}
			}
			else if ((player.getCurrentItemOrArmor(4) == null || player.getCurrentItemOrArmor(3) == null
					|| player.getCurrentItemOrArmor(2) == null || player.getCurrentItemOrArmor(1) == null)
					&& player.capabilities.isCreativeMode == false)
			{
				player.inventory.setInventorySlotContents(slot, null);
			}
		}
	}
}