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

import matt.lyoko.entities.mobs.EntityBlok;
import matt.lyoko.lib.BlockIds;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenPolarSector extends BiomeGenBaseLyoko
{
    public BiomeGenPolarSector(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = (byte)BlockIds.LYOKO_ICE;
        this.fillerBlock = (byte)BlockIds.LYOKO_ICE;
    } 
    
}
