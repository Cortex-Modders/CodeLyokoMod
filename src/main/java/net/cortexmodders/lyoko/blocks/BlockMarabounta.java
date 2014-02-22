/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

import java.util.Random;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.mobs.EntityLyoko;
import net.cortexmodders.lyoko.entities.vehicles.EntityVehicle;
import net.cortexmodders.lyoko.lib.LyokoDamageSource;
import net.cortexmodders.lyoko.tileentity.TileEntityMarabounta;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMarabounta extends BlockContainer
{
    public BlockMarabounta()
    {
        super(Material.iron);
        // setCreativeTab
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        // setTickRandomly
        this.setTickRandomly(true);
    }

    private IIcon normalTexture;
    private IIcon evilTexture;

    @Override
    @SideOnly(Side.CLIENT)
    // registerIcons
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.normalTexture = par1IconRegister.registerIcon("lyoko:marabounta" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
        this.evilTexture = par1IconRegister.registerIcon("lyoko:evilmarabounta" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
    }

    @Override
    @SideOnly(Side.CLIENT)
    // getIcon
    public IIcon getIcon(int side, int meta)
    {
        switch (meta)
        {
            case 0:
                return this.normalTexture;
            case 1:
                return this.evilTexture;
            default:
                return this.normalTexture;
        }
    }

    // @Override
    // public int idDropped(int par1, Random par1Random, int par2)
    // {
    // return 0;
    // }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        TileEntityMarabounta tem = (TileEntityMarabounta) world.getTileEntity(x, y, z);

        if (tem.consumedBlock == Blocks.air)
            return world.setBlockToAir(x, y, z);

        return world.setBlock(x, y, z, tem.consumedBlock, 0, 0);
    }

    @Override
    // updateTick
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        this.convertLyokoBlocks(world, x + 1, y, z);
        this.convertLyokoBlocks(world, x - 1, y, z);
        this.convertLyokoBlocks(world, x, y + 1, z);
        this.convertLyokoBlocks(world, x, y - 1, z);
        this.convertLyokoBlocks(world, x, y, z + 1);
        this.convertLyokoBlocks(world, x, y, z - 1);
    }

    public void convertLyokoBlocks(World world, int x, int y, int z)
    {
        TileEntityMarabounta tem;

        Block block = world.getBlock(x, y, z);

        // getBlock

        if (block instanceof ILyokoTerrain)
        {
            // setBlock
            world.setBlock(x, y, z, ModBlocks.marabounta, 0, 2);
            tem = (TileEntityMarabounta) world.getTileEntity(x, y, z);
            tem.consumedBlock = block;
        }
    }

    @Override
    // getCollisionBoundingBoxFromPool
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(par2 - 1 + f, par3 - 1 + f, par4 - 1 + f, par2 + 1 - f, par3 + 1 - f, par4 + 1 - f);
    }

    @Override
    // onEntityCollidedWithBlock
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
    {
        if (ent instanceof EntityLyoko)
            ent.attackEntityFrom(LyokoDamageSource.marabounta, 100);
        else if (ent instanceof EntityVehicle && world.getBlockMetadata(x, y, z) == 1)
            ((EntityVehicle) ent).setDead();
        else if (ent instanceof EntityPlayer && world.getBlockMetadata(x, y, z) == 1)
            if (!((EntityPlayer) ent).capabilities.isCreativeMode)
                ent.attackEntityFrom(LyokoDamageSource.marabounta, 9);
    }

    @Override
    // onBlockClicked
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        super.onBlockClicked(world, x, y, z, player);
        TileEntityMarabounta temp = (TileEntityMarabounta) world.getTileEntity(x, y, z);
        world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        // setTileEntity
        world.setTileEntity(x, y, z, temp);
    }

    @Override
    // createNewTileEntity
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityMarabounta();
    }
}
