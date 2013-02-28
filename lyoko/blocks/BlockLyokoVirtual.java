package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.TileEntityVirtualBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLyokoVirtual extends BlockContainer
{
	public BlockLyokoVirtual(int par1, int par2) {
		super(par1, Material.iron);
		this.blockIndexInTexture = par2;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if(world.provider.dimensionId == 0 || world.provider.dimensionId == -1 || world.provider.dimensionId == 1)
		{
			world.setBlockWithNotify(x, y, z, 0);
		}
	}
	
	public int getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
		if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Forest_Sector_ID)
		{
			return 2;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Mountain_Sector_ID)
		{
			return 3;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Desert_Sector_ID)
		{
			return 4;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Polar_Sector_ID)
		{
			return 5;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Carthage_Sector_ID)
		{
			return 7;
		}
        return this.blockIndexInTexture;
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityVirtualBlock();
	}
	
	public String getTextureFile()
    {
    	return "/matt/lyoko/terrain/terrain.png";
    }
}
