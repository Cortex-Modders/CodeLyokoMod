package matt.lyoko.world;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.src.*;
import net.minecraft.world.biome.SpawnListEntry;
import matt.lyoko.*;

public class BiomeGenForestSector extends BiomeGenBaseLyoko
{
    public BiomeGenForestSector(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 4, 4));
        this.topBlock = (byte)CodeLyoko.Lyoko_Grass;
        this.fillerBlock = (byte)CodeLyoko.Lyoko_Grass;
    }
}