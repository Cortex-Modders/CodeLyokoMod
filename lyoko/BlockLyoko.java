package matt.lyoko;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.*;
import net.minecraft.src.*;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLyoko extends Block
{
	public BlockLyoko(int par1, int par2)
	{
		super(par1, par2, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	
	public String getTextureFile()
    {
            return "/matt/lyoko/terrain/terrain.png";
    }
	
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if(this.blockID == CodeLyoko.LeadOre.blockID)
		{
			if(par5Entity instanceof EntityLiving)
			{
				if(((EntityLiving)par5Entity).isPotionActive(Potion.regeneration.getId()))
				{
					((EntityLiving)par5Entity).removePotionEffect(Potion.regeneration.getId());
				}
				((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 2));
				if(par5Entity instanceof EntityPlayer)
				{
					((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 500, 2));
				}
			}
		}
		
		else if(this.blockID == CodeLyoko.LyokoOre.blockID)
		{
			if(par5Entity instanceof EntityLiving)
			{
				((EntityLiving)par5Entity).clearActivePotions();
				((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, 2));
				if(par5Entity instanceof EntityPlayer)
				{
					((EntityPlayer)par5Entity).getFoodStats().setFoodLevel(20);
					((EntityPlayer)par5Entity).getFoodStats().setFoodSaturationLevel(5.0F);
				}
			}
		}
	}
}