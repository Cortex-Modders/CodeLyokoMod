/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

import java.util.Random;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.fluids.ModFluids;
import net.cortexmodders.lyoko.lib.LyokoDamageSource;
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
        super(Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("lyoko:digitalseablock");
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return ModFluids.digitalSea.getLuminosity();
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(par2 - 1 + f, par3 - 1 + f, par4 - 1 + f, par2 + 1 - f, par3 + 1 - f, par4 + 1 - f);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(LyokoDamageSource.digitalSea, 100);
    }
}
