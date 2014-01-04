/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockLyokoIce extends BlockBreakable
{
    public BlockLyokoIce(String par2, Material material, boolean flag)
    {
        super(par2, material, flag);
        //setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
        //setBlockUnbreakable
        this.func_149722_s();
    }

    @Override
    			//registerIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
    	//blockIcon
        this.field_149761_L = par1IconRegister.registerIcon("lyoko:lyokoice");
    }

    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    		//quantityDropped
    public int func_149745_a(Random par1Random)
    {
        return 1;
    }
}