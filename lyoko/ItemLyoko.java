package matt.lyoko;

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
		if(stack.getItem() == CodeLyoko.LaserArrow)
		{
			list.add("If you didn't get this item from creative, then please inform 986523714 or catchaser about this glitch");
		}
	}
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
	{
		if(stack.getItem() == CodeLyoko.AdminOrb)
		{
			this.maxStackSize = 1;
		}
		else
		{
			this.maxStackSize = 64;
		}
		
		if(stack.getItem() == CodeLyoko.AdminOrb && ent instanceof EntityPlayer)
		{
			if(((EntityPlayer)ent).username == "986523714" || ((EntityPlayer)ent).username == "MoonMagick" || ((EntityPlayer)ent).username == "Wolfspirit1st")
			{
				((EntityPlayer)ent).capabilities.allowFlying = true;
				((EntityPlayer)ent).capabilities.setFlySpeed(0.10F);
				((EntityPlayer)ent).capabilities.disableDamage = true;
			}
		}
		
		if(ent instanceof EntityPlayer && stack.getItem() == CodeLyoko.LyokoLead)
		{
			if(((EntityPlayer)ent).username == "986523714" || ((EntityPlayer)ent).username == "MoonMagick" || ((EntityPlayer)ent).username == "Wolfspirit1st")
			{
				
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
