package matt.lyoko.world;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.lib.ThreadTowerGen;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StructureTower extends WorldGenerator
{
	protected int[] GetValidSpawnBlocks()
	{
		return new int[] {Block.stone.blockID, ModBlocks.Grass.blockID, ModBlocks.Stone.blockID, ModBlocks.Sand.blockID, ModBlocks.Ice.blockID, ModBlocks.Carthage.blockID};
	}
	
	public boolean LocationIsValidSpawn(World world, int x, int y, int z)
	{
		int distanceToAir = y + 1;
		while(distanceToAir < 256)
		{
			if(world.getBlockId(x, distanceToAir, z) != 0)
			{
				return false;
			}
			distanceToAir++;
		}
		
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				if(!((i > 0 && i < 6) || (j > 0 && j < 6)))
				{
					if(world.getBlockId(x + i, y, z + j) == 0)
					{
						return false;
					}
				}
			}
		}
		
		int blockID = world.getBlockId(x, y, z);
		int blockIDAbove = world.getBlockId(x, y + 1, z);
		int blockIDBelow = world.getBlockId(x, y - 1, z);
		for (int i : GetValidSpawnBlocks())
		{
			if(blockIDAbove != 0)
			{
				return false;
			}
			if(blockID == i)
			{
				return true;
			}
			else if(blockID == Block.snow.blockID && blockIDBelow == i)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (!LocationIsValidSpawn(world, x, y, z))
		{
			return false;
		}
		
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new ThreadTowerGen(world, x, y, z, this));
		
		return true;
	}
	
	public void makeTower(World world, int x, int y, int z)
	{
		clearArea(world, x, y, z);
		makeWalls(world, x, y, z);
		makeBaseAndRoof(world, x, y, z);
	}
	
	private void makeWalls(World world, int x, int y, int z)
	{
		for(int i = 4; i < 23; i++)
		{
			makeLayer(world, x, y + i, z);
		}
	}
	
	private void makeBaseAndRoof(World world, int x, int y, int z)
	{
		// base
		for(int i = 1; i < 6; i++)
		{
			for(int j = 1; j < 6; j++)
			{
				world.setBlock(x + i, y - 6, z + j, ModBlocks.DigitalSeaBlock.blockID);
			}
		}
		for(int i = -5; i < 1; i++)
		{
			makeLayer(world, x, y + i, z);
		}
		for(int i = 1; i < 4; i++)
		{
			for(int j = 2; j < 5; j++)
			{
				world.setBlock(x + i, y, z + j, ModBlocks.TowerFloor.blockID);
			}
		}
		world.setBlock(x + 4, y, z + 3, ModBlocks.TowerFloor.blockID);
		world.setBlock(x + 5, y, z + 3, ModBlocks.TowerFloor.blockID);
		makeBaseLayer(world, x, y + 1, z);
		makeBaseLayer(world, x, y + 2, z);
		makeBaseLayer(world, x, y + 3, z);
		
		// roof
		for(int i = 1; i < 4; i++)
		{
			for(int j = 2; j < 5; j++)
			{
				world.setBlock(x + i, y + 19, z + j, ModBlocks.TowerFloor.blockID, 1, 3);
			}
		}
		world.setBlock(x + 1, y + 21, z + 3, ModBlocks.TowerConsole.blockID, 1, 3);
		makeRoofLayer(world, x, y + 23, z);
	}
	
	private void clearArea(World world, int x, int y, int z)
	{
		for(int i = 0; i < 11; i++)
		{
			for(int j = 1; j < 25; j++)
			{
				for(int k = 0; k < 11; k++)
				{
					world.setBlock(x + i - 2, y + j, z + k - 2, 0);
				}
			}
		}
		for(int i = 1; i < 6; i++)
		{
			for(int j = 0; j > -6; j--)
			{
				for(int k = 1; k < 6; k++)
				{
					world.setBlock(x + i, y + j, z + k, 0);
				}
			}
		}
	}
	
	private void makeBaseLayer(World world, int x, int y, int z)
	{
		world.setBlock(x + 1, y, z, ModBlocks.TowerBaseFake.blockID, 2, 2);
		world.setBlock(x + 2, y, z, ModBlocks.TowerBaseFake.blockID, 2, 2);
		world.setBlock(x + 3, y, z, ModBlocks.TowerBaseFake.blockID, 2, 2);
		world.setBlock(x + 4, y, z, ModBlocks.TowerBaseFake.blockID, 2, 2);
		world.setBlock(x + 5, y, z, ModBlocks.TowerBaseFake.blockID, 2, 2);
		world.setBlock(x, y, z + 1, ModBlocks.TowerBaseFake.blockID, 1, 2);
		world.setBlock(x, y, z + 2, ModBlocks.TowerBaseFake.blockID, 1, 2);
		world.setBlock(x, y, z + 3, ModBlocks.TowerBaseFake.blockID, 1, 2);
		world.setBlock(x, y, z + 4, ModBlocks.TowerBaseFake.blockID, 1, 2);
		world.setBlock(x, y, z + 5, ModBlocks.TowerBaseFake.blockID, 1, 2);
		world.setBlock(x + 6, y, z + 1, ModBlocks.TowerBaseFake.blockID, 3, 2);
		world.setBlock(x + 6, y, z + 2, ModBlocks.TowerBase.blockID);
		world.setBlock(x + 6, y, z + 3, ModBlocks.TowerBase.blockID);
		world.setBlock(x + 6, y, z + 4, ModBlocks.TowerBase.blockID);
		world.setBlock(x + 6, y, z + 5, ModBlocks.TowerBaseFake.blockID, 3, 2);
		world.setBlock(x + 1, y, z + 6, ModBlocks.TowerBaseFake.blockID, 0, 2);
		world.setBlock(x + 2, y, z + 6, ModBlocks.TowerBaseFake.blockID, 0, 2);
		world.setBlock(x + 3, y, z + 6, ModBlocks.TowerBaseFake.blockID, 0, 2);
		world.setBlock(x + 4, y, z + 6, ModBlocks.TowerBaseFake.blockID, 0, 2);
		world.setBlock(x + 5, y, z + 6, ModBlocks.TowerBaseFake.blockID, 0, 2);
	}
	
	private void makeLayer(World world, int x, int y, int z)
	{
		world.setBlock(x + 1, y, z, ModBlocks.TowerBlock.blockID, 2, 2);
		world.setBlock(x + 2, y, z, ModBlocks.TowerBlock.blockID, 2, 2);
		world.setBlock(x + 3, y, z, ModBlocks.TowerBlock.blockID, 2, 2);
		world.setBlock(x + 4, y, z, ModBlocks.TowerBlock.blockID, 2, 2);
		world.setBlock(x + 5, y, z, ModBlocks.TowerBlock.blockID, 2, 2);
		world.setBlock(x, y, z + 1, ModBlocks.TowerBlock.blockID, 1, 2);
		world.setBlock(x, y, z + 2, ModBlocks.TowerBlock.blockID, 1, 2);
		world.setBlock(x, y, z + 3, ModBlocks.TowerBlock.blockID, 1, 2);
		world.setBlock(x, y, z + 4, ModBlocks.TowerBlock.blockID, 1, 2);
		world.setBlock(x, y, z + 5, ModBlocks.TowerBlock.blockID, 1, 2);
		world.setBlock(x + 6, y, z + 1, ModBlocks.TowerBlock.blockID, 3, 2);
		world.setBlock(x + 6, y, z + 2, ModBlocks.TowerBlock.blockID, 3, 2);
		world.setBlock(x + 6, y, z + 3, ModBlocks.TowerBlock.blockID, 3, 2);
		world.setBlock(x + 6, y, z + 4, ModBlocks.TowerBlock.blockID, 3, 2);
		world.setBlock(x + 6, y, z + 5, ModBlocks.TowerBlock.blockID, 3, 2);
		world.setBlock(x + 1, y, z + 6, ModBlocks.TowerBlock.blockID, 0, 2);
		world.setBlock(x + 2, y, z + 6, ModBlocks.TowerBlock.blockID, 0, 2);
		world.setBlock(x + 3, y, z + 6, ModBlocks.TowerBlock.blockID, 0, 2);
		world.setBlock(x + 4, y, z + 6, ModBlocks.TowerBlock.blockID, 0, 2);
		world.setBlock(x + 5, y, z + 6, ModBlocks.TowerBlock.blockID, 0, 2);
	}
	
	private void makeRoofLayer(World world, int x, int y, int z)
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				if(!((i == 0 || i == 6) && (j == 0 || j == 6)))
				{
					world.setBlock(x + i, y, z + j, ModBlocks.TowerBlock.blockID, 4, 2);
				}
			}
		}
		for(int i = 1; i < 6; i++)
		{
			for(int j = 1; j < 6; j++)
			{
				world.setBlock(x + i, y + 1, z + j, ModBlocks.TowerBlock.blockID, 4, 2);
			}
		}
	}
}