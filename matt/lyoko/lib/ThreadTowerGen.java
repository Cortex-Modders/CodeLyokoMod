package matt.lyoko.lib;

import java.util.Random;

import net.minecraft.world.World;
import matt.lyoko.world.StructureTower;

public class ThreadTowerGen implements Runnable
{
	private World world;
	private int x;
	private int y;
	private int z;
	private StructureTower st;
	
	public ThreadTowerGen(World world, int x, int y, int z, StructureTower st)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.st = st;
	}
	
	@Override
	public void run()
	{
		st.makeTower(world, x, y, z);
	}
}