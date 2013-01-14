package matt.lyoko;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLyokoFuel extends ItemLyoko {

	public ItemLyokoFuel(int par1, int par2) {
		super(par1);
		this.setMaxDamage(par2);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B){ //Additional info (eg. the names of music discs)
	list.add(Integer.toString((10000 - stack.getItemDamage())) + "/" + Integer.toString(stack.getMaxDamage()));
	}
	
	@Override
	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if(stack.getItemDamage() > 10000)
		{
			stack.setItemDamage(10000);
		}
	}
}
