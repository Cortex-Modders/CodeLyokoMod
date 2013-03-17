package matt.lyoko.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.*;
import net.minecraft.src.*;
import net.minecraft.world.World;
import matt.lyoko.*;

import java.util.Random;

public class BlockLyoko extends Block
{
	public BlockLyoko(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void func_94332_a(IconRegister par1IconRegister)
	{
		if(this.blockID == CodeLyoko.LyokoGrass.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:lyokograss");
		}
		if(this.blockID == CodeLyoko.LyokoStone.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:lyokostone");
		}
		if(this.blockID == CodeLyoko.LyokoSand.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:lyokosand");
		}
		if(this.blockID == CodeLyoko.LyokoLog.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:lyokolog");
		}
		if(this.blockID == CodeLyoko.LyokoCarthage.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:carthage");
		}
		if(this.blockID == CodeLyoko.LyokoOre.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:lyokoore");
		}
		if(this.blockID == CodeLyoko.LeadOre.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:lead");
		}
		if(this.blockID == CodeLyoko.UraniumOre.blockID)
		{
			this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:uranium");
		}
	}
	
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	
	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLiving ent)
	{
		if(ent instanceof EntityPlayer && blockID == CodeLyoko.VirtualBlock.blockID)
		{
			EntityPlayer entp = ((EntityPlayer)ent);
			entp.setEntityHealth(entp.getHealth() - 1);
		}
	}
	
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if(this.blockID == CodeLyoko.LeadOre.blockID || this.blockID == CodeLyoko.UraniumOre.blockID)
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