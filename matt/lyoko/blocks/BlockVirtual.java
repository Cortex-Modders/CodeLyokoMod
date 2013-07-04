package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.DimensionIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVirtual extends Block
{
	public BlockVirtual(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if(world.provider.dimensionId != DimensionIds.ICE && world.provider.dimensionId != DimensionIds.MOUNTAIN
				&& world.provider.dimensionId != DimensionIds.FOREST
				&& world.provider.dimensionId != DimensionIds.DESERT
				&& world.provider.dimensionId != DimensionIds.CARTHAGE)
		{
			world.setBlock(x, y, z, 0);
		}
	}
	
	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase ent)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer entp = ((EntityPlayer)ent);
			entp.setEntityHealth(entp.func_110143_aJ() - 1);
		}
	}
	
	private Icon virtualGrass;
	private Icon virtualStone;
	private Icon virtualSand;
	private Icon virtualIce;
	private Icon virtualCarthage;
	
	@Override
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
		if(access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokoforest))
		{
			return virtualGrass;
		}
		else if(access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokomountain))
		{
			return virtualStone;
		}
		else if(access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokodesert))
		{
			return virtualSand;
		}
		else if(access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokopolar))
		{
			return virtualIce;
		}
		else if(access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokocarthage))
		{
			return virtualCarthage;
		}
        return virtualGrass;
    }
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		virtualGrass = this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
		virtualStone = par1IconRegister.registerIcon("lyoko:lyokostone");
		virtualSand  = par1IconRegister.registerIcon("lyoko:lyokosand");
		virtualIce = par1IconRegister.registerIcon("lyoko:lyokoice");
		virtualCarthage = par1IconRegister.registerIcon("lyoko:carthage");
	}
}
