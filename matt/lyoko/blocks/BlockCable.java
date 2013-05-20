package matt.lyoko.blocks;

import scala.util.Random;
import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if(!world.isRemote)
		{
			Random rand = new Random();
			((TileEntityCable)world.getBlockTileEntity(x, y, z)).setSector(Integer.toString(rand.nextInt(10)));
			world.markBlockForUpdate(x, y, z);
			return true;
		}
		return true;
	}
	
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
		if(block == this.blockID || block == CodeLyoko.SuperCalc.blockID/* || world.getBlockId(x, y, z) == CodeLyoko.Scanner.blockID*/)
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