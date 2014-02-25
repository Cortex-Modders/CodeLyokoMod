/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

import java.util.Random;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.tileentity.TileEntitySector;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSector extends BlockContainer
{
    public BlockSector()
    {
        // iron
        super(Material.iron);
    }
    
    @Override
    // createNewTileEntity
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntitySector();
    }
    
    @Override
    // updateTick
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);
        // updateTileEntity
        // TODO: figure out what this does
        world.getTileEntity(x, y, z).updateEntity();
    }
    
    @Override
    // onEntityWalking
    public void onEntityWalking(World world, int x, int y, int z, Entity ent)
    {
        // updateTick
        this.updateTick(world, x, y, z, null);
    }
    
    @SideOnly(Side.CLIENT)
    private IIcon virtualGrass;
    @SideOnly(Side.CLIENT)
    private IIcon virtualStone;
    @SideOnly(Side.CLIENT)
    private IIcon virtualSand;
    @SideOnly(Side.CLIENT)
    private IIcon virtualIce;
    @SideOnly(Side.CLIENT)
    private IIcon virtualCarthage;
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
    {
        if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokoforest))
            return this.virtualGrass;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokomountain))
            return this.virtualStone;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokodesert))
            return this.virtualSand;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokopolar))
            return this.virtualIce;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokocarthage))
            return this.virtualCarthage;
        return this.virtualGrass;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    // registerBlockIcons
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.virtualGrass = this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
        this.virtualStone = par1IconRegister.registerIcon("lyoko:lyokostone");
        this.virtualSand = par1IconRegister.registerIcon("lyoko:lyokosand");
        this.virtualIce = par1IconRegister.registerIcon("lyoko:lyokoice");
        this.virtualCarthage = par1IconRegister.registerIcon("lyoko:carthage");
    }
}
