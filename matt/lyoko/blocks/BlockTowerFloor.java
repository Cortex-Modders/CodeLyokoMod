package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTowerFloor extends Block
{
	public BlockTowerFloor(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	public boolean isMultiBlock(IBlockAccess access, int x, int y, int z)
    {
    	if(access.getBlockId(x+1, y, z+1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x+1, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x+1, y, z-1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x, y, z+1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x, y, z-1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x-1, y, z+1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x-1, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x-1, y, z-1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x + 2, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x + 3, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockMetadata(x, y, z) == 0)
    	{
    		return true;
    	}
    	else if(access.getBlockId(x+1, y, z+1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x+1, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x+1, y, z-1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x, y, z+1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x, y, z-1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x-1, y, z+1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x-1, y, z) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockId(x-1, y, z-1) == ModBlocks.TowerFloor.blockID
    			&& access.getBlockMetadata(x, y, z) == 1)
    	{
    		return true;
    	}
    	return false;
    }
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
	{
		int distance = y + 2;
		if(world.getBlockMetadata(x, y, z) == 0 && isMultiBlock(world, x, y, z))
		{
			for(int i = y + 1; i < 256; i++)
			{
				if(world.getBlockId(x, i, z) == ModBlocks.TowerFloor.blockID)
				{
					distance = i + 1;
					break;
				}
			}
			ent.setLocationAndAngles(x + 1.75, distance, z + 0.5, 90, ent.rotationPitch);
			if(!ent.worldObj.isRemote)
			{
				ent.setVelocity(0.0D, 0.0D, 0.0D);
			}
		}
		else if(world.getBlockMetadata(x, y, z) == 1 && isMultiBlock(world, x, y, z))
		{
			for(int i = 0; i < y; i++)
			{
				if(world.getBlockId(x, i, z) == ModBlocks.TowerFloor.blockID)
				{
					distance = i + 1;
					break;
				}
			}
			ent.setLocationAndAngles(x + 1.75, distance, z + 0.5, 90, ent.rotationPitch);
			if(!ent.worldObj.isRemote)
			{
				ent.setVelocity(0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((float)(par2 - 1), (float)(par3 - 1), (float)(par4 - 1), (float)(par2 + 1), (float)(par3 + 1) - f, (float)(par4 + 1));
    }
	
	private Icon tr;
    private Icon tc;
    private Icon tl;
    private Icon cr;
    private Icon cc;
    private Icon cl;
    private Icon cl2;
    private Icon br;
    private Icon bc;
    private Icon bl;
    private Icon bottomSide;
    private Icon extension;
    
    private Icon[][] topIcons = new Icon[3][3];
    
    @Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		tr = par1IconRegister.registerIcon("lyoko:tr");
		tc = par1IconRegister.registerIcon("lyoko:tc");
		tl = par1IconRegister.registerIcon("lyoko:tl");
		cr = par1IconRegister.registerIcon("lyoko:cr");
		cc = this.blockIcon = par1IconRegister.registerIcon("lyoko:cc");
		cl = par1IconRegister.registerIcon("lyoko:cl");
		cl2 = par1IconRegister.registerIcon("lyoko:cl2");
		br = par1IconRegister.registerIcon("lyoko:br");
		bc = par1IconRegister.registerIcon("lyoko:bc");
		bl = par1IconRegister.registerIcon("lyoko:bl");
		bottomSide = par1IconRegister.registerIcon("lyoko:computer_0");
		extension = par1IconRegister.registerIcon("lyoko:extension");
		
		topIcons[0][0] = tr;
		topIcons[0][1] = tc;
		topIcons[0][2] = tl;
		topIcons[1][0] = cr;
		topIcons[1][1] = cc;
		topIcons[1][2] = cl;
		topIcons[2][0] = br;
		topIcons[2][1] = bc;
		topIcons[2][2] = bl;
	}
    
    @Override
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
    	if(side == 1)
    	{
    		for(int i = 0; i < 3; i++)
    		{
    			for(int j = 0; j < 3; j++)
    			{
    				if(isMultiBlock(access, x - i + 1, y, z - j + 1))
    				{
    					if(j == 1 && i == 2)
    					{
    						if(access.getBlockId(x + 1, y, z) == ModBlocks.TowerFloor.blockID
    								&& access.getBlockId(x + 2, y, z) == ModBlocks.TowerFloor.blockID
    								&& access.getBlockMetadata(x - 1, y, z) == 0)
    						{
    							return cl2;
    						}
    					}
    					return topIcons[j][i];
    				}
    			}
    		}
    		if(isMultiBlock(access, x - 2, y, z) || isMultiBlock(access, x - 3, y, z))
    		{
    			return extension;
    		}
    	}
    	return bottomSide;
    }
}