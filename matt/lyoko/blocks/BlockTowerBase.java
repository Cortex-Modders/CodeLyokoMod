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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityTower;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTowerBase extends BlockContainer
{
	public BlockTowerBase(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	Icon inside;
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("lyoko:towerbase");
        inside = par1IconRegister.registerIcon("lyoko:computer_0");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int meta)
    {
    	if(side == 2 && (meta == 0 || meta == 5))
    	{
    		return inside;
    	}
    	else if(side == 3 && (meta == 2 || meta == 7))
    	{
    		return inside;
    	}
    	else if(side == 4 && (meta == 3 || meta == 8))
    	{
    		return inside;
    	}
    	else if(side == 5 && (meta == 1 || meta == 6))
    	{
    		return inside;
    	}
    	return this.blockIcon;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityTower();
	}
	
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack)
    {
        int l = MathHelper.floor_double((double)(ent.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        
        if(ent.isSneaking())
        {
        	world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 5, 2);
        }
    }
    
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if(world.getBlockMetadata(x, y, z) >= 5 && world.getBlockMetadata(x, y, z) <= 8)
			return null;
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
	{
		if(ent instanceof EntityPlayer && !ent.worldObj.isRemote && world.getBlockMetadata(x, y, z) >= 5 && world.getBlockMetadata(x, y, z) <= 8)
		{
			EntityPlayer player = (EntityPlayer) ent;
			PlayerInformation pi = PlayerInformation.forPlayer(player);
			
			if(pi.getCoolDown() <= 0)
			{
				pi.increaseLifePoints(25);
				pi.resetCoolDown();
			}
		}
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
}