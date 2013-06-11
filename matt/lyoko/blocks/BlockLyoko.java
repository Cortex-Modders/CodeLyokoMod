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
		if(this.blockID == CodeLyoko.LyokoGrass.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
		}
		if(this.blockID == CodeLyoko.LyokoStone.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokostone");
		}
		if(this.blockID == CodeLyoko.LyokoSand.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokosand");
		}
		if(this.blockID == CodeLyoko.LyokoLog.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokolog");
		}
		if(this.blockID == CodeLyoko.LyokoCarthage.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:carthage");
		}
		if(this.blockID == CodeLyoko.QuantumOre.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:quantumore");
		}
		if(this.blockID == CodeLyoko.LeadOre.blockID)
		{
			this.blockIcon = par1IconRegister.registerIcon("lyoko:lead");
		}
		if(this.blockID == CodeLyoko.UraniumOre.blockID)
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
					((EntityPlayer)par5Entity).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 500, 2));
				}
			}
		}
		
		else if(this.blockID == CodeLyoko.QuantumOre.blockID)
		{
			if(par5Entity instanceof EntityLiving)
			{
				((EntityLiving)par5Entity).clearActivePotions();
				((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, 2));
			}
		}
	}
}