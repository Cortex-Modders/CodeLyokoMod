/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntitySector;
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
        super(Material.field_151573_f);
    }

    @Override
    // createNewTileEntity
    public TileEntity func_149915_a(World world, int metadata)
    {
        return new TileEntitySector();
    }

    @Override
    //updateTick
    public void func_149674_a(World world, int x, int y, int z, Random rand)
    {
        super.func_149674_a(world, x, y, z, rand);
        //updateTileEntity
        //TODO: figure out what this does
        world.func_147438_o(x, y, z).func_145845_h();
    }

    @Override
    //onEntityWalking
    public void func_149724_b(World world, int x, int y, int z, Entity ent)
    {
        //updateTick
        this.func_149674_a(world, x, y, z, null);
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
    public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side)
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
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        this.virtualGrass = this.field_149761_L = par1IconRegister.registerIcon("lyoko:lyokograss");
        this.virtualStone = par1IconRegister.registerIcon("lyoko:lyokostone");
        this.virtualSand = par1IconRegister.registerIcon("lyoko:lyokosand");
        this.virtualIce = par1IconRegister.registerIcon("lyoko:lyokoice");
        this.virtualCarthage = par1IconRegister.registerIcon("lyoko:carthage");
    }
}
