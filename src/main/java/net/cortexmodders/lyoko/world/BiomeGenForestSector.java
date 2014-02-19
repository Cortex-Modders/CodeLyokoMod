/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.world;

import net.cortexmodders.lyoko.blocks.ModBlocks;

public class BiomeGenForestSector extends BiomeGenBaseLyoko
{
    public BiomeGenForestSector(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = ModBlocks.SectorBlock;
        this.fillerBlock = ModBlocks.SectorBlock;
    }
}
