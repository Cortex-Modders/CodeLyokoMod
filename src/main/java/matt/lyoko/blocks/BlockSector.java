/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntitySector;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
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
    
    @SideOnly(Side.CLIENT)
    private Icon virtualGrass;
    @SideOnly(Side.CLIENT)
    private Icon virtualStone;
    @SideOnly(Side.CLIENT)
    private Icon virtualSand;
    @SideOnly(Side.CLIENT)
    private Icon virtualIce;
    @SideOnly(Side.CLIENT)
    private Icon virtualCarthage;

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
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
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.virtualGrass = this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
        this.virtualStone = par1IconRegister.registerIcon("lyoko:lyokostone");
        this.virtualSand = par1IconRegister.registerIcon("lyoko:lyokosand");
        this.virtualIce = par1IconRegister.registerIcon("lyoko:lyokoice");
        this.virtualCarthage = par1IconRegister.registerIcon("lyoko:carthage");
    }
}
