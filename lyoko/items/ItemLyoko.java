package matt.lyoko.items;

import java.util.List;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.Block;
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
	
	public String getTextureFile()
    {
            return "/matt/lyoko/gui/items.png";
    }
}
