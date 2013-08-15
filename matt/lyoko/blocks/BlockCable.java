/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityCable;
import matt.lyoko.entities.tileentity.TileEntityHolomap;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.entities.tileentity.TileEntitySuperCalc;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer
{
	public BlockCable(int par1)
	{
		super(par1, Material.cloth);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		if(world.getBlockTileEntity(x, y, z) instanceof TileEntityCable)
		{
			TileEntityCable tile = (TileEntityCable)world.getBlockTileEntity(x, y, z);
			if(neighborID == this.blockID && tile != null)
			{
				String test1 = tile.getSector();
				syncBlock(world, x + 1, y, z, tile);
				syncBlock(world, x - 1, y, z, tile);
				syncBlock(world, x, y + 1, z, tile);
				syncBlock(world, x, y - 1, z, tile);
				syncBlock(world, x, y, z + 1, tile);
				syncBlock(world, x, y, z - 1, tile);
				String test2 = tile.getSector();
				if(!(test1.equals(test2)))
				{
					world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
				}
				syncBlock2(world, x + 1, y, z, tile);
				syncBlock2(world, x - 1, y, z, tile);
				syncBlock2(world, x, y + 1, z, tile);
				syncBlock2(world, x, y - 1, z, tile);
				syncBlock2(world, x, y, z + 1, tile);
				syncBlock2(world, x, y, z - 1, tile);
				syncBlock3(world, x + 1, y, z, tile);
				syncBlock3(world, x - 1, y, z, tile);
				syncBlock3(world, x, y + 1, z, tile);
				syncBlock3(world, x, y - 1, z, tile);
				syncBlock3(world, x, y, z + 1, tile);
				syncBlock3(world, x, y, z - 1, tile);
				syncBlock4(world, x + 1, y, z, tile);
				syncBlock4(world, x - 1, y, z, tile);
				syncBlock4(world, x, y + 1, z, tile);
				syncBlock4(world, x, y - 1, z, tile);
				syncBlock4(world, x, y, z + 1, tile);
				syncBlock4(world, x, y, z - 1, tile);
			}
		}
	}
	
	public void syncBlock(World world, int x, int y, int z, TileEntityCable localCable)
	{
		if(world.getBlockId(x, y, z) == this.blockID && world.getBlockTileEntity(x, y, z) instanceof TileEntityCable)
		{
			TileEntityCable tile = (TileEntityCable)world.getBlockTileEntity(x, y, z);
			if(tile != null && localCable.getCoolDown() == 0 && !(tile.getSector().equals("")) && localCable.getSector().equals(""))
			{
				localCable.resetCoolDown();
				localCable.setSector(tile.getSector());
			}
		}
	}
	
	public void syncBlock2(World world, int x, int y, int z, TileEntityCable localCable)
	{
		if(world.getBlockId(x, y, z) == ModBlocks.Scanner.blockID && world.getBlockTileEntity(x, y, z) instanceof TileEntityScanner)
		{
			TileEntityScanner tile = (TileEntityScanner)world.getBlockTileEntity(x, y, z);
			if(tile != null && !(localCable.getSector().equals("")) && tile.sector == -1)
			{
				tile.sector = convertSectorToInt(localCable.getSector());
				world.markBlockForUpdate(x, y, z);
			}
		}
	}
	
	public void syncBlock3(World world, int x, int y, int z, TileEntityCable localCable)
	{
		if(world.getBlockId(x, y, z) == ModBlocks.SuperCalc.blockID && world.getBlockTileEntity(x, y, z) instanceof TileEntitySuperCalc)
		{
			TileEntitySuperCalc tile = (TileEntitySuperCalc)world.getBlockTileEntity(x, y, z);
			if(tile != null && !(localCable.getSector().equals("")) && tile.sector.equals(""))
			{
				tile.sector = localCable.getSector();
				world.markBlockForUpdate(x, y, z);
			}
		}
	}
	
	public void syncBlock4(World world, int x, int y, int z, TileEntityCable localCable)
	{
		if(world.getBlockId(x, y, z) == ModBlocks.Holomap.blockID && world.getBlockTileEntity(x, y, z) instanceof TileEntityHolomap)
		{
			TileEntityHolomap tile = (TileEntityHolomap)world.getBlockTileEntity(x, y, z);
			if(tile != null && !(localCable.getSector().equals("")))
			{
				tile.sector = (byte) (convertSectorToInt(localCable.getSector()) + 1);
				world.markBlockForUpdate(x, y, z);
			}
		}
	}
	
	public int convertSectorToInt(String sector)
	{
		if(sector.toLowerCase().equals("polar") || sector.toLowerCase().equals("ice"))
		{
			return 0;
		}
		else if(sector.toLowerCase().equals("desert"))
		{
			return 1;
		}
		else if(sector.toLowerCase().equals("forest"))
		{
			return 2;
		}
		else if(sector.toLowerCase().equals("mountain"))
		{
			return 3;
		}
		else if(sector.toLowerCase().equals("carthage"))
		{
			return 4;
		}
		return -1;
	}
	
	/*public String convertIntToSector(int sector)
	{
		switch(sector)
		{
		case 0:
			return "polar";
		case 1:
			return "desert";
		case 2:
			return "forest";
		case 3:
			return "mountain";
		case 4:
			return "carthage";
		default:
			return "";
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if(!world.isRemote)
		{
			Random rand = new Random();
			String temp = convertIntToSector(rand.nextInt(5));
			((TileEntityCable)world.getBlockTileEntity(x, y, z)).setSector(temp);
			((TileEntityCable)world.getBlockTileEntity(x, y, z)).resetCoolDown();
			world.markBlockForUpdate(x, y, z);
			world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
			return true;
		}
		return false;
	}*/
	
	@Override
	public int getRenderBlockPass()
    {
        return 1;
    }
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("lyoko:cable");
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
		
	}
	
	private boolean validBlock(int block)
	{
		if(block == this.blockID || block == ModBlocks.SuperCalc.blockID || block == ModBlocks.Scanner.blockID
				|| block == ModBlocks.SuperCalcConsole.blockID || block == ModBlocks.Holomap.blockID)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
	{
		this.setBlockBounds(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
		float minx = (float)this.minX;
		float maxx = (float)this.maxX;
		float miny = (float)this.minY;
		float maxy = (float)this.maxY;
		float minz = (float)this.minZ;
		float maxz = (float)this.maxZ;
		
		if (validBlock(par1IBlockAccess.getBlockId(x-1, y, z)))
			minx = 0;
		
		if (validBlock(par1IBlockAccess.getBlockId(x+1, y, z)))
			maxx = 1;
		
		if (validBlock(par1IBlockAccess.getBlockId(x, y-1, z)))
			miny = 0;
		
		if (validBlock(par1IBlockAccess.getBlockId(x, y+1, z)))
			maxy = 1;
		
		if (validBlock(par1IBlockAccess.getBlockId(x, y, z-1)))
			minz = 0;
		
		if (validBlock(par1IBlockAccess.getBlockId(x, y, z+1)))
			maxz = 1;
		
		this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityCable();
	}
}