package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemInWorldManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemLyokoSword extends ItemSword
{
	public ItemLyokoSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
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
					if(stack.getItem() != ModItems.EnergyField)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.OddHelmet && chest.getItem() == ModItems.OddChest
						&& legs.getItem() == ModItems.OddLegs && boots.getItem() == ModItems.OddBoots)
				{
					if(stack.getItem() != ModItems.Glove)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.YumiHelmet && chest.getItem() == ModItems.YumiChest
						&& legs.getItem() == ModItems.YumiLegs && boots.getItem() == ModItems.YumiBoots)
				{
					if(stack.getItem() != ModItems.Fan)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.UlrichHelmet && chest.getItem() == ModItems.UlrichChest
						&& legs.getItem() == ModItems.UlrichLegs && boots.getItem() == ModItems.UlrichBoots)
				{
					if(stack.getItem() != ModItems.Katana)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.WilliamHelmet && chest.getItem() == ModItems.WilliamChest
						&& legs.getItem() == ModItems.WilliamLegs && boots.getItem() == ModItems.WilliamBoots)
				{
					if(stack.getItem() != ModItems.Zweihander)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
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
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(this.itemID == ModItems.Katana.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:katana");
		if(this.itemID == ModItems.Zweihander.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:zweihander");
	}
}
