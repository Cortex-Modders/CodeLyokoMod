package matt.lyoko.world;

import java.util.Random;

import matt.lyoko.lib.DimensionIds;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenTower implements IWorldGenerator
{
	 public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	 {
		 if(world.provider.dimensionId == DimensionIds.ICE || world.provider.dimensionId == DimensionIds.MOUNTAIN
				|| world.provider.dimensionId == DimensionIds.FOREST || world.provider.dimensionId == DimensionIds.DESERT
				|| world.provider.dimensionId == DimensionIds.CARTHAGE)
		 {
			 generateTower(world, random, chunkX*16, chunkZ*16);
		 }
	 }
	 
	 private void generateTower(World world, Random random, int blockX, int blockZ) 
	 {
		  int x = blockX + random.nextInt(16);
		  int y = random.nextInt(128);
		  int z = blockZ + random.nextInt(16);
		  
		  (new StructureTower()).generate(world, random, x, y, z);
	 }
}