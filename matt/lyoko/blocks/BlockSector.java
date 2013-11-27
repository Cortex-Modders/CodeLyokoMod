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

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntitySector;
import matt.lyoko.lib.DimensionIds;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSector extends BlockContainer
{
	public BlockSector(int par1)
	{
		super(par1, Material.iron);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		super.updateTick(world, x, y, z, rand);
		world.getBlockTileEntity(x, y, z).updateEntity();
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity ent)
	{
		this.updateTick(world, x, y, z, null);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySector();
	}
}
