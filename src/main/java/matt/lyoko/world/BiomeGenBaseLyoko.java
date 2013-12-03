/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.world;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBaseLyoko extends BiomeGenBase
{
    public byte blockID;

	protected BiomeGenBaseLyoko(int id)
    {
        super(id);
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.field_76754_C = 5169201;
        this.minHeight = 0.1F;
        this.maxHeight = 0.3F;
        this.temperature = 0.5F;
        this.rainfall = 0.5F;
        this.waterColorMultiplier = 16777215;
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        biomeList[id] = this;
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