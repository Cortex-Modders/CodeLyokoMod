package matt.lyoko.world;

import matt.lyoko.lib.BlockIds;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenForestSector extends BiomeGenBaseLyoko
{
    public BiomeGenForestSector(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = (byte)BlockIds.LYOKO_GRASS;
        this.fillerBlock = (byte)BlockIds.LYOKO_GRASS;
    }
}
