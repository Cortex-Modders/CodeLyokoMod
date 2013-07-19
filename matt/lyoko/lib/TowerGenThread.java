package matt.lyoko.lib;

import java.util.Random;

import net.minecraft.world.World;
import matt.lyoko.world.StructureTower;

public class TowerGenThread implements Runnable
{
	private World world;
	private Random rand;
	private int x;
	private int y;
	private int z;
	
	public TowerGenThread(World world, Random rand, int x, int y, int z)
	{
		this.world = world;
		this.rand = rand;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void run()
	{
		(new StructureTower()).generate(world, rand, x, y, z);
	}
}