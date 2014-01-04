/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.fluids.ModFluids;
import matt.lyoko.lib.LyokoDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDigitalSea extends Block
{
    public BlockDigitalSea()
    {
        // Material.iron
        super(Material.field_151573_f);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    @Override
    // registerBlockIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("lyoko:digitalseablock");
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return ModFluids.digitalSea.getLuminosity();
    }

    @Override
    // quantityDropped
    public int func_149745_a(Random par1Random)
    {
        return 0;
    }

    @Override
    // getCollisionBoundingBoxFromPool
    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(par2 - 1 + f, par3 - 1 + f, par4 - 1 + f, par2 + 1 - f, par3 + 1 - f, par4 + 1 - f);
    }

    @Override
    // onEntityCollidedWithBlock
    public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(LyokoDamageSource.digitalSea, 100);
    }
}