/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.world;

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.lib.BlockIds;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenCarthageSector extends BiomeGenBaseLyoko
{
    public BiomeGenCarthageSector(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = ((byte)ModBlocks.Carthage.blockID);
        this.fillerBlock = ((byte)ModBlocks.Carthage.blockID);
        this.setBiomeName("Carthage");
        
        this.waterColorMultiplier = 0xE42D14;
    }
}
