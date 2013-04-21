package matt.lyoko.world;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import matt.lyoko.*;

public class WorldGenTower implements IWorldGenerator {
	
	 public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	 {
	  /*switch (world.provider.dimensionId)
	  {
	   case 3: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 4: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 5: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 6: generateTower(world, random, chunkX*16, chunkZ*16);
	   case 8: generateTower(world, random, chunkX*16, chunkZ*16);
	  }*/
		 if(world.provider.dimensionId == CodeLyoko.Polar_Sector_ID || world.provider.dimensionId == CodeLyoko.Mountain_Sector_ID
				|| world.provider.dimensionId == CodeLyoko.Forest_Sector_ID || world.provider.dimensionId == CodeLyoko.Desert_Sector_ID
				|| world.provider.dimensionId == CodeLyoko.Carthage_Sector_ID)
		 {
			 generateTower(world, random, chunkX*16, chunkZ*16);
		 }
	 }
	 
	  private void generateTower(World world, Random random, int blockX, int blockZ) 
	 {
		  int Xcoord1 = blockX + random.nextInt(16);
		  int Ycoord1 = random.nextInt(128);
		  int Zcoord1 = blockZ + random.nextInt(16);
		  
		  (new StructureTower()).generate(world, random, Xcoord1, Ycoord1, Zcoord1); 
	 }

}
