/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.blocks;

import java.util.List;
import matt.lyoko.CodeLyoko;
import matt.lyoko.client.ClientProxy;
import matt.lyoko.entities.tileentity.TileEntityHolomap;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHolomap extends BlockContainer
{
	public BlockHolomap(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	public static boolean isMultiBlock(World world, int x, int y, int z)
	{
		if(world.getBlockId(x - 1, y, z - 1) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x - 1, y, z) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x - 1, y, z + 1) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x, y, z - 1) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x, y, z) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x, y, z + 1) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x + 1, y, z - 1) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x + 1, y, z) == ModBlocks.Holomap.blockID
				&& world.getBlockId(x + 1, y, z + 1) == ModBlocks.Holomap.blockID
				&& clearOnSides(world, x, y, z))
		{
			return true;
		}
		return false;
	}
	
	public static boolean clearOnSides(World world, int x, int y, int z)
	{
		for(int i = -2; i < 3; i++)
		{
			for(int j = -2; j < 3; j++)
			{
				if(i == -2 || i == 2 || j == -2 || j == 2)
				{
					if(world.getBlockId(x + i, y, z + j) == ModBlocks.Holomap.blockID)
						return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
		meta = 0b1000;
        return meta;
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {
        super.breakBlock(world, x, y, z, id, meta);
        if(isMultiBlock(world, x, y, z))
        {
        	
        }
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, access.getBlockMetadata(x, y, z) < 8 ? 0.5F : 0.0625F, 1.0F);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y, (double)z, (double)x + 1.0D, (double)y + world.getBlockMetadata(x, y, z) < 8 ? 0.5D : 0.0625D, (double)z + 1.0D);
    }
	
	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		if(!world.isRemote)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int j = -1; j < 2; j++)
				{
					if(isMultiBlock(world, x + i, y, z + j))
					{
						for(int k = -1; k < 2; k++)
						{
							for(int l = -1; l < 2; l++)
							{
								world.setBlockMetadataWithNotify(x + k + i, y, z + l + j, 8, 3);
							}
						}
					}
				}
			}
		}
        return super.removeBlockByPlayer(world, player, x, y, z);
    }

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
		if(!world.isRemote)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int j = -1; j < 2; j++)
				{
					if(isMultiBlock(world, x + i, y, z + j))
					{
						for(int k = -1; k < 2; k++)
						{
							for(int l = -1; l < 2; l++)
							{
								world.setBlockMetadataWithNotify(x + k + i, y, z + l + j, 8, 3);
							}
						}
					}
				}
			}
		}
        super.onBlockExploded(world, x, y, z, explosion);
    }
	
	@Override
	public int damageDropped(int par1)
    {
        return 0b1000;
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if(!world.isRemote)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int j = -1; j < 2; j++)
				{
					if(isMultiBlock(world, x + i, y, z + j))
					{
						setMeta(world, x + i, y, z + j);
					}
				}
			}
		}
	}
	
	public void setMeta(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x + 1, y, z + 1, 0, 3);
		world.setBlockMetadataWithNotify(x + 1, y, z - 1, 1, 3);
		world.setBlockMetadataWithNotify(x - 1, y, z - 1, 2, 3);
		world.setBlockMetadataWithNotify(x - 1, y, z + 1, 3, 3);
		world.setBlockMetadataWithNotify(x, y, z + 1, 4, 3);
		world.setBlockMetadataWithNotify(x + 1, y, z, 5, 3);
		world.setBlockMetadataWithNotify(x, y, z - 1, 6, 3);
		world.setBlockMetadataWithNotify(x - 1, y, z, 7, 3);
		world.setBlockMetadataWithNotify(x, y, z, 8, 3);
	}
	
	@Override
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon("lyoko:holomap");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityHolomap();
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(ModBlocks.Holomap, 1, 0b1000));
    }
	
	@Override
	public int getRenderBlockPass()
    {
        return 0;
    }
	
	@Override
	public int getRenderType()
	{
		return ClientProxy.holomapRenderId;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}