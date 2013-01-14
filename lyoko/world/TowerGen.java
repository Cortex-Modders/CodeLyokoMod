package matt.lyoko.world;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class TowerGen implements IWorldGenerator {
	
	 public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	 {
	  switch (world.provider.dimensionId)
	  {
	   case 3: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 4: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 5: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 6: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 7: generateTower(world, random, chunkX*16, chunkZ*16);
	  }
	 }

	  

	  private void generateTower(World world, Random random, int blockX, int blockZ) 
	 {
		  int Xcoord1 = blockX + random.nextInt(16);
		  int Ycoord1 = random.nextInt(60);
		  int Zcoord1 = blockZ + random.nextInt(16);
		  
		  (new WorldGen_code_lyoko_tower()).generate(world, random, Xcoord1, Ycoord1, Zcoord1); 
	 }

}
