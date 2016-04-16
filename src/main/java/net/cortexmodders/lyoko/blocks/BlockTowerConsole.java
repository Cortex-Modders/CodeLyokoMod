/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.tileentity.TileEntityTowerConsole;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTowerConsole extends BlockContainer
{
    public BlockTowerConsole()
    {
        // material.iron
        super(Material.iron);
        // setCreativeTab
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    @Override
    // registerIcons
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("lyoko:towerconsole");
    }

    @Override
    // createNewTileEntity
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityTowerConsole();
    }

    @Override
    // getCollisionBoundingBoxFromPool
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    // Set to transparent.
    @Override
    // isOpaqueCube
    public boolean isOpaqueCube()
    {

        return false;
    }

    // Will not render actual block, just the tile entity.
    @Override
    // getRenderType
    public int getRenderType()
    {
        return -1;
    }

    @Override
    // onBlockActivated
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking())
            return false;
        else {
            player.openGui(CodeLyoko.instance, 1, world, x, y, z);
            return true;
        }
    }

    @Override
    // setBlockBoundsBasedOnState
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
    {
        super.setBlockBoundsBasedOnState(blockAccess, x, y, z);
        int meta = blockAccess.getBlockMetadata(x, y, z);

        if (meta == 0 || meta == 2)
            this.setBlockBounds(-0.25F, 0.0F, 0.0F, 1.25F, 1.0F, 1.0F);
        else if (meta == 1 || meta == 3)
            this.setBlockBounds(0.0F, 0.0F, -0.25F, 1.0F, 1.0F, 1.25F);
        else
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    }

    @Override
    // onBlockPlacedBy
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        super.onBlockPlacedBy(par1World, x, y, z, par5EntityLiving, par6ItemStack);
        int l = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0)
            par1World.setBlockMetadataWithNotify(x, y, z, 0, 2);

        if (l == 1)
            par1World.setBlockMetadataWithNotify(x, y, z, 1, 2);

        if (l == 2)
            par1World.setBlockMetadataWithNotify(x, y, z, 2, 2);

        if (l == 3)
            par1World.setBlockMetadataWithNotify(x, y, z, 3, 2);
    }
}
