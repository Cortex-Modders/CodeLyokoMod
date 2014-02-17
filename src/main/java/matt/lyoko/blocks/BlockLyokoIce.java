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

public class BlockLyokoIce extends BlockBreakable implements ILyokoTerrain
{

    public BlockLyokoIce(String par2, Material material, boolean flag)
    {
        super(par2, material, flag);
        // setCreativeTab
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        // setBlockUnbreakable
        this.setBlockUnbreakable();
    }

    @Override
    // registerIcons
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        // blockIcon
        this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokoice");
    }

    @Override
    // getRenderBlockPass
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    // quantityDropped
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public LyokoTerrainTypes getType()
    {
        return LyokoTerrainTypes.ICE;
    }
}
