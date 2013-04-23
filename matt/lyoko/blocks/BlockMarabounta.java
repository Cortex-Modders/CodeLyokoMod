package matt.lyoko.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.src.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import matt.lyoko.*;
import matt.lyoko.entities.mobs.EntityLyoko;
import matt.lyoko.entities.vehicles.EntityVehicle;
import java.util.Random;

public class BlockMarabounta extends Block
{
	public BlockMarabounta(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
		this.setTickRandomly(true);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("lyoko:terrain");
	}
	
	//@Override
	//public int idDropped(int par1, Random par1Random, int par2)
	//{
	//	return 0;
	//}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if(world.getBlockMetadata(x, y, z) == 0)
		{
			convertLyokoBlocks(world, x+1, y, z);
			convertLyokoBlocks(world, x-1, y, z);
			convertLyokoBlocks(world, x, y+1, z);
			convertLyokoBlocks(world, x, y-1, z);
			convertLyokoBlocks(world, x, y, z+1);
			convertLyokoBlocks(world, x, y, z-1);
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		}
	}
	
	public void convertLyokoBlocks(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) == CodeLyoko.LyokoLog.blockID || world.getBlockId(x, y, z) == CodeLyoko.LyokoIce.blockID
				|| world.getBlockId(x, y, z) == CodeLyoko.LyokoGrass.blockID
				|| world.getBlockId(x, y, z) == CodeLyoko.LyokoSand.blockID
				|| world.getBlockId(x, y, z) == CodeLyoko.LyokoStone.blockID
				|| world.getBlockId(x, y, z) == CodeLyoko.LyokoCarthage.blockID)
		{
			world.setBlock(x, y, z, blockID);
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
    	if(ent instanceof EntityLyoko || ent instanceof EntityVehicle)
    	{
    		ent.attackEntityFrom(DamageSource.generic, 100);
    	}
    }
}