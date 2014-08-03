package net.cortexmodders.lyoko.world.chunk;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;

import java.util.List;
import java.util.Random;

/**
 * @author jadar
 */
public abstract class LyokoBaseChunkProvider implements IChunkProvider
{

    protected Random rand;

    protected NoiseGeneratorOctaves noiseGen1;
    protected NoiseGeneratorOctaves noiseGen2;
    protected NoiseGeneratorOctaves noiseGen3;
    protected NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoiseGen;

    protected World world;
    protected long seed;

    protected final boolean mapFeaturesEnabled;

    protected double[] noiseArray;
    protected double[] stoneNoise = new double[256];
    protected BiomeGenBase[] biomesForGeneration;

    public double[] noise1;
    public double[] noise2;
    public double[] noise3;
    public double[] noise4;
    public double[] noise5;

    public float[] parabolicField;
    public int[][] field = new int[32][32];

    public LyokoBaseChunkProvider(World world, long seed, boolean mapFeaturesEnabled)
    {
        this.world = world;
        this.rand = new Random(seed);
        this.mapFeaturesEnabled = mapFeaturesEnabled;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    protected double[] initializeNoiseField(double[] noiseArray, int xPos, int yPos, int zPos, int xSize, int ySize, int zSize)
    {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, noiseArray, xPos, yPos, zPos, xSize, ySize, zSize);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return event.noisefield;

        if (noiseArray == null)
        {
            noiseArray = new double[xSize * ySize * zSize];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noise4 = this.noiseGen4.generateNoiseOctaves(this.noise4, xPos, yPos, zPos, xSize, 1, zSize, 1.0D, 0.0D, 1.0D);
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, xPos, yPos, zPos, xSize, 1, zSize, 100.0D, 0.0D, 100.0D);
        this.noise1 = this.noiseGen3.generateNoiseOctaves(this.noise1, xPos, yPos, zPos, xSize, ySize, zSize, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.noise2 = this.noiseGen1.generateNoiseOctaves(this.noise2, xPos, yPos, zPos, xSize, ySize, zSize, d0, d1, d0);
        this.noise3 = this.noiseGen2.generateNoiseOctaves(this.noise3, xPos, yPos, zPos, xSize, ySize, zSize, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[ySize];
        int i2;

        for (i2 = 0; i2 < ySize; ++i2)
        {
            adouble1[i2] = Math.cos((double)i2 * Math.PI * 6.0D / (double)ySize) * 2.0D;
            double d2 = (double)i2;

            if (i2 > ySize / 2)
            {
                d2 = (double)(ySize - 1 - i2);
            }

            if (d2 < 4.0D)
            {
                d2 = 4.0D - d2;
                adouble1[i2] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (i2 = 0; i2 < xSize; ++i2)
        {
            for (int k2 = 0; k2 < zSize; ++k2)
            {
                double d3 = (this.noise4[l1] + 256.0D) / 512.0D;

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.noise5[l1] / 8000.0D;

                if (d5 < 0.0D)
                {
                    d5 = -d5;
                }

                d5 = d5 * 3.0D - 3.0D;

                if (d5 < 0.0D)
                {
                    d5 /= 2.0D;

                    if (d5 < -1.0D)
                    {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                    d3 = 0.0D;
                }
                else
                {
                    if (d5 > 1.0D)
                    {
                        d5 = 1.0D;
                    }

                    d5 /= 6.0D;
                }

                d3 += 0.5D;
                d5 = d5 * (double)ySize / 16.0D;
                ++l1;

                for (int j2 = 0; j2 < ySize; ++j2)
                {
                    double d6 = 0.0D;
                    double d7 = adouble1[j2];
                    double d8 = this.noise2[k1] / 512.0D;
                    double d9 = this.noise3[k1] / 512.0D;
                    double d10 = (this.noise1[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d10 < 0.0D)
                    {
                        d6 = d8;
                    }
                    else if (d10 > 1.0D)
                    {
                        d6 = d9;
                    }
                    else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;
                    double d11;

                    if (j2 > ySize - 4)
                    {
                        d11 = (double)((float)(j2 - (ySize - 4)) / 3.0F);
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    if ((double)j2 < d4)
                    {
                        d11 = (d4 - (double)j2) / 4.0D;

                        if (d11 < 0.0D)
                        {
                            d11 = 0.0D;
                        }

                        if (d11 > 1.0D)
                        {
                            d11 = 1.0D;
                        }

                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    noiseArray[k1] = d6;
                    ++k1;
                }
            }
        }

        return noiseArray;
    }

    public abstract void generateTerrain(int chunkX, int chunkZ, Block[] blocks, byte[] blockMeta);
    public abstract void modifyTerrain(int chunkX, int chunkZ, Block[] blocks, byte[] blockMeta);


    /* ========== IChunkProvider ========== */

    /**
     * Checks to see if a chunk exists at x, y
     *
     * @param var1
     * @param var2
     */
    @Override
    public boolean chunkExists(int var1, int var2)
    {
        return false;
    }

    public void replaceBlocksForBiome(int chunkX, int chunkZ, Block[] blocks, byte[] blockMeta, BiomeGenBase[] biomesForGeneration) {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, blocks, blockMeta, biomesForGeneration);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return;

        double d0 = 0.03125D;
        int posX = chunkX * 16;
        int posZ = chunkZ * 16;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, posX, posZ, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int z = 0; z < 16; ++z)
        {
            for (int x = 0; x < 16; ++x)
            {
                BiomeGenBase biomegenbase = biomesForGeneration[x + z * 16];
                biomegenbase.genTerrainBlocks(this.world, this.rand, blocks, blockMeta, posX + z, posZ + x, this.stoneNoise[x + z * 16]);
            }
        }
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     *
     * @param chunkX
     * @param chunkZ
     */
    @Override
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        // Integer.MAX_VALUE * 2
        Block[] blocks = new Block[65536];
        byte[] blockMeta = new byte[65536];

        this.generateTerrain(chunkX, chunkZ, blocks, blockMeta);
        this.biomesForGeneration = this.world.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX*16, chunkZ*16, 16, 16);

        this.replaceBlocksForBiome(chunkX, chunkZ, blocks, blockMeta, this.biomesForGeneration);

        Chunk chunk = new Chunk(this.world, blocks, blockMeta, chunkX, chunkZ);
        byte[] ba1 = chunk.getBiomeArray();

        for (int i = 0; i < ba1.length; i++) {
            ba1[i] = (byte)this.biomesForGeneration[i].biomeID;
        }

        chunk.generateSkylightMap();

        return chunk;
    }

    /**
     * loads or generates the chunk at the chunk location specified
     *
     * @param var1
     * @param var2
     */
    @Override
    public Chunk loadChunk(int var1, int var2)
    {
        return null;
    }

    /**
     * Populates chunk with ores etc etc
     *
     * @param var1
     * @param var2
     * @param var3
     */
    @Override
    public void populate(IChunkProvider var1, int var2, int var3)
    {

    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     *
     * @param var1
     * @param var2
     */
    @Override
    public boolean saveChunks(boolean var1, IProgressUpdate var2)
    {
        return false;
    }

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    @Override
    public boolean canSave()
    {
        return false;
    }

    /**
     * Converts the instance data to a readable string.
     */
    @Override
    public String makeString()
    {
        return null;
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     *
     * @param var1
     * @param var2
     * @param var3
     * @param var4
     */
    @Override
    public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4)
    {
        return null;
    }

    @Override
    public ChunkPosition func_147416_a(World var1, String var2, int var3, int var4, int var5)
    {
        return null;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    @Override
    public void recreateStructures(int var1, int var2)
    {

    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    @Override
    public void saveExtraData()
    {

    }
}
