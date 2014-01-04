/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLyoko extends Block
{

    public BlockLyoko()
    {
        this(Material.field_151573_f);
    }

    public BlockLyoko(Material material)
    {
        super(material);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    // NO MORE
    // @Override
    // public void registerIcons(IIconRegister par1IconRegister)
    // {
    // int blockId = Block.func_149682_b(this);
    //
    // if (blockId == ModBlocks.Grass.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
    // if (blockId == ModBlocks.Stone.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokostone");
    // if (blockId == ModBlocks.Sand.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokosand");
    // if (blockId == ModBlocks.Log.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokolog");
    // if (blockId == ModBlocks.Carthage.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:carthage");
    // if (blockId == ModBlocks.QuantumOre.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:quantumore" +
    // (!CodeLyoko.useHDTextures ? "_16_16" : ""));
    // if (blockId == ModBlocks.LeadOre.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:lead");
    // if (blockId == ModBlocks.UraniumOre.blockID)
    // this.blockIcon = par1IconRegister.registerIcon("lyoko:uranium");
    // }

}