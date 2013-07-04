package matt.lyoko.world;

import java.util.Random;

import net.minecraft.src.*;
import net.minecraft.world.biome.SpawnListEntry;
import matt.lyoko.*;
import matt.lyoko.entities.mobs.*;
import matt.lyoko.lib.BlockIds;

public class BiomeGenPolarSector extends BiomeGenBaseLyoko
{
    public BiomeGenPolarSector(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBlok.class, 4, 4, 1));
        this.topBlock = (byte)BlockIds.LYOKO_ICE;
        this.fillerBlock = (byte)BlockIds.LYOKO_ICE;
    } 
    
}
