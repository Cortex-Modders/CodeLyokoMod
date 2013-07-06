package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockLyoko extends Block
{
	public BlockLyoko(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		if(this.blockID == ModBlocks.Grass.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
		}
		if(this.blockID == ModBlocks.Stone.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokostone");
		}
		if(this.blockID == ModBlocks.Sand.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokosand");
		}
		if(this.blockID == ModBlocks.Log.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokolog");
		}
		if(this.blockID == ModBlocks.Carthage.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:carthage");
		}
		if(this.blockID == ModBlocks.QuantumOre.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:quantumore");
		}
		if(this.blockID == ModBlocks.LeadOre.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lead");
		}
		if(this.blockID == ModBlocks.UraniumOre.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:uranium");
		}
	}
	
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if(this.blockID == ModBlocks.LeadOre.blockID || this.blockID == ModBlocks.UraniumOre.blockID)
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
					((EntityPlayer)par5Entity).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 500, 2));
				}
			}
		}
		
		else if(this.blockID == ModBlocks.QuantumOre.blockID)
		{
			if(par5Entity instanceof EntityLiving)
			{
				((EntityLiving)par5Entity).clearActivePotions();
				((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, 2));
			}
		}
	}
}