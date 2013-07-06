package matt.lyoko.world;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.blocks.ModBlocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenLyokoOre implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId)
		{
		case -1: generateNether(world, random, chunkX*16,chunkZ*16);
		case 0: generateSurface(world, random, chunkX*16,chunkZ*16);
		
		
		}

	}

	private void generateSurface(World world, Random rand, int baseX, int baseZ)
	{		
							//rarity -smaller number = rarer
		   	for(int x = 0; x < 4; x++)
			{
				int Xcoord = baseX + rand.nextInt(16);				
				int Zcoord = baseZ + rand.nextInt(16);
				int Ycoord = rand.nextInt(30);
															//Max Vein Size
				(new WorldGenMinable(ModBlocks.QuantumOre.blockID, 4)).generate(world, rand, Xcoord, Ycoord, Zcoord);
			}
		   	
		   					//rarity -smaller number = rarer
		   	for(int x = 0; x < 10; x++)
			{
				int Xcoord = baseX + rand.nextInt(16);				
				int Zcoord = baseZ + rand.nextInt(16);
				int Ycoord = rand.nextInt(20) + 30;
															//Max Vein Size
				(new WorldGenMinable(ModBlocks.LeadOre.blockID, 5)).generate(world, rand, Xcoord, Ycoord, Zcoord);
			}
		   	
		   					//rarity -smaller number = rarer
		   	for(int x = 0; x < 10; x++)
			{
				int Xcoord = baseX + rand.nextInt(16);				
				int Zcoord = baseZ + rand.nextInt(16);
				int Ycoord = rand.nextInt(10) + 20;
															//Max Vein Size
				(new WorldGenMinable(ModBlocks.UraniumOre.blockID, 1)).generate(world, rand, Xcoord, Ycoord, Zcoord);
			}
	}

	private void generateNether(World world, Random random, int i, int j) {
		
		
	}

}
