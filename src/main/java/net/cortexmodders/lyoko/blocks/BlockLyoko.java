/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

import net.cortexmodders.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLyoko extends Block
{

    public BlockLyoko()
    {
        this(Material.iron);
    }

    public BlockLyoko(Material material)
    {
        super(material);
        // setCreativeTab
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    // NO MORE
    // @Override
    // public void registerIcons(IIconRegister par1IconRegister)
    // {
    // int blockId = Block.getIdFromBlock(this);
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
