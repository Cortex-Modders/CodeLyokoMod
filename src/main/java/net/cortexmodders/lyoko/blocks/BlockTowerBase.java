/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityTower;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTowerBase extends BlockContainer
{
    public BlockTowerBase()
    {
        super(Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    IIcon inside;

    @Override
    // registerBlockIcons
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        // blockIcon
        this.blockIcon = par1IconRegister.registerIcon("lyoko:towerbase");
        this.inside = par1IconRegister.registerIcon("lyoko:computer_0");
    }

    @SideOnly(Side.CLIENT)
    @Override
    // getIcon
    public IIcon getIcon(int side, int meta)
    {
        if (side == 2 && (meta == 0 || meta == 5))
            return this.inside;
        else if (side == 3 && (meta == 2 || meta == 7))
            return this.inside;
        else if (side == 4 && (meta == 3 || meta == 8))
            return this.inside;
        else if (side == 5 && (meta == 1 || meta == 6))
            return this.inside;
        // blockIcon
        return this.blockIcon;
    }

    @Override
    // createNewTileEntity
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityTower();
    }

    @Override
    // onBlockPlacedBy
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack)
    {
        int l = MathHelper.floor_double(ent.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (ent.isSneaking())
            world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 5, 2);
    }

    @Override
    // getCollisionBoundingBoxFromPool
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) >= 5 && world.getBlockMetadata(x, y, z) <= 8)
            return null;
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    // onEntityCollidedWithBlock
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
    {
        if (ent instanceof EntityPlayer && !ent.worldObj.isRemote && world.getBlockMetadata(x, y, z) >= 5 && world.getBlockMetadata(x, y, z) <= 8)
        {
            EntityPlayer player = (EntityPlayer) ent;
            PlayerInformation pi = PlayerInformation.forPlayer(player);

            if (pi.getCoolDown() <= 0)
            {
                pi.increaseLifePoints(25);
                pi.resetCoolDown();
            }
        }
    }

    @Override
    // renderAsNormalBlock
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    // isOpaqueCube
    public boolean isOpaqueCube()
    {
        return false;
    }
}
