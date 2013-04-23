package matt.lyoko.items;

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
	
	private int life = 10;
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int slot, boolean par5)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ent;
			if(life > 0)
			{
				life--;
			}
			else
			{
				player.inventory.setInventorySlotContents(slot, null);
				life = 10;
			}
		}
	}
}