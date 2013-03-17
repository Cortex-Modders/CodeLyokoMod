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
	public void func_94581_a(IconRegister iconRegister)
	{
		if(this.itemID == CodeLyoko.LaserArrow.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:laserarrow");
		if(this.itemID == CodeLyoko.KatanaFragment1.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:katanafragment1");
		if(this.itemID == CodeLyoko.KatanaFragment2.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:katanafragment2");
		if(this.itemID == CodeLyoko.ZweihanderFragment1.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:zweihanderfragment1");
		if(this.itemID == CodeLyoko.ZweihanderFragment2.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:zweihanderfragment2");
		if(this.itemID == CodeLyoko.FanFragment1.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:fanfragment1");
		if(this.itemID == CodeLyoko.FanFragment2.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:fanfragment2");
		if(this.itemID == CodeLyoko.EnergyFieldCore.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:energyfieldcore");
		if(this.itemID == CodeLyoko.EnergyFieldStarter.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:energyfieldstarter");
		if(this.itemID == CodeLyoko.GloveFragment1.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:glovefragment1");
		if(this.itemID == CodeLyoko.GloveFragment2.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:glovefragment2");
		if(this.itemID == CodeLyoko.LyokoIngot.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:lyokoingot");
		if(this.itemID == CodeLyoko.LyokoLead.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:leadingot");
		if(this.itemID == CodeLyoko.LyokoCell.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:emptycell");
		if(this.itemID == CodeLyoko.LyokoDepletedLeadCell.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:depletedleadcell");
		if(this.itemID == CodeLyoko.Uranium.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:uraniumingot");
		if(this.itemID == CodeLyoko.LyokoDepletedUraniumCell.itemID)
	         iconIndex = iconRegister.func_94245_a("lyoko:depleteduraniumcell");
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
