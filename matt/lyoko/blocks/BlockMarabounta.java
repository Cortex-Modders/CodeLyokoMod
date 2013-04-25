package matt.lyoko.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import matt.lyoko.*;
import matt.lyoko.entities.TileEntityMarabounta;
import matt.lyoko.entities.mobs.EntityLyoko;
import matt.lyoko.entities.vehicles.EntityVehicle;
import java.util.Random;

public class BlockMarabounta extends BlockContainer
{
	public BlockMarabounta(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
		this.setTickRandomly(true);
	}
	
	private Icon normalTexture;
	private Icon evilTexture;
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		normalTexture = this.blockIcon = par1IconRegister.registerIcon("lyoko:marabounta");
		evilTexture = par1IconRegister.registerIcon("lyoko:evilmarabounta");
	}
	
	//@Override
	//public int idDropped(int par1, Random par1Random, int par2)
	//{
	//	return 0;
	//}
	
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		TileEntityMarabounta tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
		
		if(tem.consumedBlock == 0)
		{
			return world.setBlockToAir(x, y, z);
		}
		
        return world.setBlock(x, y, z, tem.consumedBlock);
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		convertLyokoBlocks(world, x+1, y, z);
		convertLyokoBlocks(world, x-1, y, z);
		convertLyokoBlocks(world, x, y+1, z);
		convertLyokoBlocks(world, x, y-1, z);
		convertLyokoBlocks(world, x, y, z+1);
		convertLyokoBlocks(world, x, y, z-1);
	}
	
	public void convertLyokoBlocks(World world, int x, int y, int z)
	{
		TileEntityMarabounta tem;
		
		if(world.getBlockId(x, y, z) == CodeLyoko.LyokoLog.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoLog.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoIce.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoIce.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoGrass.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoGrass.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoSand.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoSand.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoStone.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoStone.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoCarthage.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoCarthage.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.VirtualBlock.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.VirtualBlock.blockID;
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((float)(par2 - 1) + f, (float)(par3 - 1) + f, (float)(par4 - 1) + f, (float)(par2 + 1) - f, (float)(par3 + 1) - f, (float)(par4 + 1) - f);
    }
	
	@Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
    {
		TileEntityMarabounta tem = ((TileEntityMarabounta)world.getBlockTileEntity(x, y, z));
    	if(ent instanceof EntityLyoko)
    	{
    		ent.attackEntityFrom(DamageSource.generic, 100);
    	}
    	else if(ent instanceof EntityVehicle && tem.shouldAttackPlayers)
    	{
    		((EntityVehicle)ent).setDead();
    	}
    	else if(ent instanceof EntityPlayer && tem.shouldAttackPlayers)
    	{
    		if(!((EntityPlayer)ent).capabilities.isCreativeMode)
    		{
    			ent.attackEntityFrom(DamageSource.generic, 100);
    		}
    	}
    }
	
	@Override
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
	{
		if(((TileEntityMarabounta)access.getBlockTileEntity(x, y, z)).shouldAttackPlayers)
		{
			return evilTexture;
		}
		return normalTexture;
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		super.onBlockClicked(world, x, y, z, player);
		((TileEntityMarabounta)world.getBlockTileEntity(x, y, z)).shouldAttackPlayers = true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityMarabounta();
	}
}