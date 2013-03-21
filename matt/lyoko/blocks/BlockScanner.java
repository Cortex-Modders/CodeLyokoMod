package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockScanner extends BlockContainer
{
	protected BlockScanner(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return null;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("lyoko:scanner");
	}
	
	public boolean isMultiBlock(World world, int x, int y, int z)
    {
    	if(world.getBlockId(x+1, y, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+1, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+1, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+1, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+1, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+1, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+2, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+2, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+2, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+2, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+2, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+3, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+3, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+3, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+3, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+3, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+4, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+4, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+4, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+4, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+4, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+4, z-1) == CodeLyoko.TowerBlock.blockID)
    	{
    		return true;
    	}
    	else if(world.getBlockId(x-1, y, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x-1, y+1, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+1, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+1, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+1, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+1, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x-1, y+2, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+2, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+2, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+2, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+2, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x-1, y+3, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+3, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+3, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+3, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+3, z-1) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x-1, y+4, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+4, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+4, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+4, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+4, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+4, z-1) == CodeLyoko.TowerBlock.blockID)
    	{
    		return true;
    	}
    	else if(world.getBlockId(x+1, y, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y, z) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+1, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+1, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+1, z+1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+1, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+1, z) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+2, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+2, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+2, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x+1, y+2, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+2, z) == CodeLyoko.TowerBlock.blockID
    			
    	    	&& world.getBlockId(x+1, y+3, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+3, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+3, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x+1, y+3, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+3, z) == CodeLyoko.TowerBlock.blockID
    			
    	    	&& world.getBlockId(x+1, y+4, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+4, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+4, z+1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x+1, y+4, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+4, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+4, z) == CodeLyoko.TowerBlock.blockID)
    	{
    		return true;
    	}
    	else if(world.getBlockId(x+1, y, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y, z) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+1, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x, y+1, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+1, z-1) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x+1, y+1, z) == CodeLyoko.TowerBlock.blockID
    			&& world.getBlockId(x-1, y+1, z) == CodeLyoko.TowerBlock.blockID
    			
    			&& world.getBlockId(x+1, y+2, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+2, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+2, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x+1, y+2, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+2, z) == CodeLyoko.TowerBlock.blockID
    			
    	    	&& world.getBlockId(x+1, y+3, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+3, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+3, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x+1, y+3, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+3, z) == CodeLyoko.TowerBlock.blockID
    			
    	    	&& world.getBlockId(x+1, y+4, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+4, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+4, z-1) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x+1, y+4, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x, y+4, z) == CodeLyoko.TowerBlock.blockID
    	    	&& world.getBlockId(x-1, y+4, z) == CodeLyoko.TowerBlock.blockID)
    	{
    		return true;
    	}
    	return false;
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
    {
    	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    	if (tileEntity == null || player.isSneaking())
    	{
    		return false;
    	}
    	else
    	{
    		for(int i = -1; i < 2; i++)
    		{
    			for(int j = -1; j < 2; j++)
    			{
    				for(int k = 0; k < 5; k++)
    				{
    					if(this.isMultiBlock(world, x+i, y+k, z+j))
    					{
    						player.openGui(CodeLyoko.instance, 0, world, x+i, y+k, z+j);
    						return true;
    					}
    				}
    			}
    		}
    		return false;
    	}
    }
}
