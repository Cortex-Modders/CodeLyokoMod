/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.world;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBaseLyoko extends BiomeGenBase
{
	protected BiomeGenBaseLyoko(int id)
    {
        super(id);
        this.topBlock = ModBlocks.sectorBlock;
        this.fillerBlock = ModBlocks.sectorBlock;
        this.field_76754_C = 5169201;
        this.rootHeight = 0.1F;
        this.heightVariation = 0.2F;
        this.temperature = 0.5F;
        this.rainfall = 0.5F;
        this.waterColorMultiplier = 16777215;
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.setDisableRain();
    }

    public BiomeGenBase setLyokoBiomeName(String par1Str)
    {
        this.biomeName = par1Str;
        return this;
    }

    @Override
    public BiomeGenBase setColor(int par1)
    {
        this.color = par1;
        return this;
    }
}
