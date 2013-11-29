/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.world;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBaseLyoko extends BiomeGenBase
{
    public byte blockID;

    @SuppressWarnings("rawtypes")
	protected BiomeGenBaseLyoko(int par1)
    {
        super(par1);
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.field_76754_C = 5169201;
        this.minHeight = 0.1F;
        this.maxHeight = 0.3F;
        this.temperature = 0.5F;
        this.rainfall = 0.5F;
        this.waterColorMultiplier = 16777215;
        this.spawnableMonsterList = new ArrayList();
        // this.spawnableCreatureList = new ArrayList();
        // this.spawnableWaterCreatureList = n ew ArrayList();
        // this.spawnableCaveCreatureList = new ArrayList();
        // this.worldGeneratorTrees = new WorldGenTrees(false);
        // this.worldGeneratorBigTree = new WorldGenBigTree(false);
        // this.worldGeneratorForest = new WorldGenForest(false);
        // this.worldGeneratorSwamp = new WorldGenSwamp();
        biomeList[par1] = this;
        this.theBiomeDecorator = this.createBiomeDecorator();
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