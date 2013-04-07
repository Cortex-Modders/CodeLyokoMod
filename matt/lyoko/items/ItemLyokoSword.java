package matt.lyoko.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.*;
import net.minecraft.world.World;
import matt.lyoko.*;

public class ItemLyokoSword extends ItemSword
{
	public ItemLyokoSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ent;
			if(stack.getItemDamage() < stack.getMaxDamage())
			{
				stack.setItemDamage(stack.getItemDamage() + 1);
			}
			else
			{
				for(int i = 0; i < player.inventory.mainInventory.length; i++)
				{
					if(player.inventory.getStackInSlot(i) == stack)
					{
						player.inventory.setInventorySlotContents(i, null);
					}
				}
			}
		}
	}
	
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
		if(this.itemID == CodeLyoko.Katana.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:katana");
		if(this.itemID == CodeLyoko.Zweihander.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:zweihander");
	}
}
