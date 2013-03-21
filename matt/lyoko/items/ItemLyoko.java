package matt.lyoko.items;

import java.util.List;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.src.*;
import net.minecraft.world.World;
import matt.lyoko.*;
import matt.lyoko.entities.*;

public class ItemLyoko extends Item
{
	public ItemLyoko(int par1) {
		super(par1);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
		this.setNoRepair();
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
	{
		//if(stack.getItem() == CodeLyoko.LaserArrow)
		//{
		//	list.add("If you didn't get this item from creative, then please inform 986523714 or catchaser about this glitch");
		//}
	}
	
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
		if(this.itemID == CodeLyoko.LaserArrow.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:laserarrow");
		if(this.itemID == CodeLyoko.KatanaFragment1.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:katanafragment1");
		if(this.itemID == CodeLyoko.KatanaFragment2.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:katanafragment2");
		if(this.itemID == CodeLyoko.ZweihanderFragment1.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:zweihanderfragment1");
		if(this.itemID == CodeLyoko.ZweihanderFragment2.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:zweihanderfragment2");
		if(this.itemID == CodeLyoko.FanFragment1.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:fanfragment1");
		if(this.itemID == CodeLyoko.FanFragment2.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:fanfragment2");
		if(this.itemID == CodeLyoko.EnergyFieldCore.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:energyfieldcore");
		if(this.itemID == CodeLyoko.EnergyFieldStarter.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:energyfieldstarter");
		if(this.itemID == CodeLyoko.GloveFragment1.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:glovefragment1");
		if(this.itemID == CodeLyoko.GloveFragment2.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:glovefragment2");
		if(this.itemID == CodeLyoko.LyokoIngot.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:lyokoingot");
		if(this.itemID == CodeLyoko.LyokoLead.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:leadingot");
		if(this.itemID == CodeLyoko.LyokoCell.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:emptycell");
		if(this.itemID == CodeLyoko.LyokoDepletedLeadCell.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:depletedleadcell");
		if(this.itemID == CodeLyoko.Uranium.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:uraniumingot");
		if(this.itemID == CodeLyoko.LyokoDepletedUraniumCell.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:depleteduraniumcell");
	}
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
	{
		
		if(stack.getItem() == CodeLyoko.LyokoLead || stack.getItem() == CodeLyoko.Uranium)
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
				((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.hunger.getId(), 100, 0)));
				((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.poison.getId(), 100, 0)));
			}
		}
	}
}
