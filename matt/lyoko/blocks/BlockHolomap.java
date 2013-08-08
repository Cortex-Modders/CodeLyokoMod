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

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityHolomap;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
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
				&& world.getBlockId(x + 1, y, z + 1) == ModBlocks.Holomap.blockID)
		{
			return true;
		}
		return false;
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
		world.setBlockMetadataWithNotify(x - 1, y, z - 1, 1, 3);
		world.setBlockMetadataWithNotify(x - 1, y, z, 1, 3);
		world.setBlockMetadataWithNotify(x - 1, y, z + 1, 1, 3);
		world.setBlockMetadataWithNotify(x, y, z - 1, 1, 3);
		world.setBlockMetadataWithNotify(x, y, z, 2, 3);
		world.setBlockMetadataWithNotify(x, y, z + 1, 1, 3);
		world.setBlockMetadataWithNotify(x + 1, y, z - 1, 1, 3);
		world.setBlockMetadataWithNotify(x + 1, y, z, 1, 3);
		world.setBlockMetadataWithNotify(x + 1, y, z + 1, 1, 3);
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
}