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
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBlok.class, 4, 4, 1));
        this.topBlock = (byte)BlockIds.LYOKO_ICE;
        this.fillerBlock = (byte)BlockIds.LYOKO_ICE;
    } 
    
}
