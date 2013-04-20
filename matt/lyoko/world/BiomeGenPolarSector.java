package matt.lyoko.world;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.mobs.EntityBlok;
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
        this.topBlock = (byte)CodeLyoko.Lyoko_Ice;
        this.fillerBlock = (byte)CodeLyoko.Lyoko_Ice;
    } 
    
}
