package matt.lyoko.items;

import java.util.List;

import matt.lyoko.CodeLyoko;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemLyoko extends Item
{
	public ItemLyoko(int par1) {
		super(par1);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
	{
		if(stack.getItem() == ModItems.LaserArrow)
		{
			list.add("This is the mod's equivalent of an infinite battery.");
			list.add("Use this as a fuel cell in the Super Computer.");
			list.add("This is also the debug tool for the mod as well.");
		}
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(this.itemID == ModItems.LaserArrow.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:laserarrow");
		if(this.itemID == ModItems.QuantumOrb.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:quantumorb");
		if(this.itemID == ModItems.Lead.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:leadingot");
		if(this.itemID == ModItems.EmptyCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:emptycell");
		if(this.itemID == ModItems.DepletedLeadCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:depletedleadcell");
		if(this.itemID == ModItems.Uranium.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:uraniumingot");
		if(this.itemID == ModItems.DepletedUraniumCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:depleteduraniumcell");
		if(this.itemID == ModItems.QuantumMatrix.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:quantummatrix");
		if(this.itemID == ModItems.QuantumContainmentCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:quantumcontainmentcell");
	}
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
	{
		
		if(stack.getItem() == ModItems.Lead || stack.getItem() == ModItems.Uranium)
		{
			if(ent instanceof EntityPlayer)
			{
				if(!((EntityPlayer)ent).capabilities.isCreativeMode)
				{
					((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.hunger.getId(), 100, 0)));
					((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.poison.getId(), 100, 0)));
				}
			}
			else
			{
				((EntityLiving)ent).addPotionEffect((new PotionEffect(Potion.hunger.getId(), 100, 0)));
				((EntityLiving)ent).addPotionEffect((new PotionEffect(Potion.poison.getId(), 100, 0)));
			}
		}
		else if(stack.getItem() == ModItems.QuantumOrb)
		{
			if(ent instanceof EntityLiving)
			{
				((EntityLiving)ent).clearActivePotions();
				((EntityLiving)ent).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 100, 0));
			}
		}
	}
}
