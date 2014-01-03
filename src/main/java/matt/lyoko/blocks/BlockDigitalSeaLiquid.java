/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.LyokoDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockDigitalSeaLiquid extends BlockFluidClassic
{
    public BlockDigitalSeaLiquid(Fluid fluid, Material material)
    {
        super(0, fluid, material);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    @Override
    //getIcon
    public IIcon func_149691_a(int side, int meta)
    {
        //func_149684_b - getBlockFromName
        return Block.func_149684_b("flowing_water").func_149691_a(side, meta);

    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        // fix later
        return 10;//this.maxScaledLight;
    }

    @Override
    //colorMultiplayer
    public int func_149720_d(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return this.getFluid().getColor();
    }

    @Override
    //onEntityCollidedWithBlock
    public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(LyokoDamageSource.digitalSea, 100);
    }
}