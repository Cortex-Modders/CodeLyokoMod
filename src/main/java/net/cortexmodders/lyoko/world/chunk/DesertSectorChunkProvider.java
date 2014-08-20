package net.cortexmodders.lyoko.world.chunk;

import cpw.mods.fml.common.eventhandler.Event;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.world.BiomeGenBaseLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.List;
import java.util.Random;

/**
 * @author jadar 
 */
public class DesertSectorChunkProvider implements IChunkProvider
{

    protected Random random;

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

    // TODO: ADD FEATURE GENERATORS

    private BiomeGenBaseLyoko[] biomes;

    public DesertSectorChunkProvider(World world, long seed, boolean mapFeaturesEnabled)
    {
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.world = world;
        this.random = new Random(seed);

        this.noiseGen1          = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGen2          = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGen3          = new NoiseGeneratorOctaves(this.random, 8);
        this.noiseGen4          = new NoiseGeneratorOctaves(this.random, 4);
        this.noiseGen5          = new NoiseGeneratorOctaves(this.random, 10);
        this.noiseGen6          = new NoiseGeneratorOctaves(this.random, 16);
        this.mobSpawnerNoiseGen = new NoiseGeneratorOctaves(this.random, 8);

        NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6, mobSpawnerNoiseGen};
        noiseGens = TerrainGen.getModdedNoiseGenerators(this.world, this.random, noiseGens);

        this.noiseGen1          = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2          = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3          = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4          = (NoiseGeneratorOctaves)noiseGens[3];
        this.noiseGen5          = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6          = (NoiseGeneratorOctaves)noiseGens[5];
        this.mobSpawnerNoiseGen = (NoiseGeneratorOctaves)noiseGens[6];

//        for (int j = -2; j <= 2; ++j)
//        {
//            for (int k = -2; k <= 2; ++k)
//            {
//                float f = 10.0F / MathHelper.sqrt_float((float) (j * j + k * k) + 0.2F);
//                this.parabolicField[j + 2 + (k + 2) * 5] = f;
//            }
//        }

    }

    /**
     * Populates chunk with ores etc etc
     *
     * @param chunkProvider
     * @param chunkX the x position of the chunk
     * @param chunkZ the z position of the chunk
     */
    @Override
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ)
    {
//        BlockSand.fallInstantly = true;
//        int xPos = chunkX * 16;
//        int zPos = chunkZ * 16;
//
//        BiomeGenBase biome = this.world.getBiomeGenForCoords(xPos, zPos);
//        // make sure our seed is always set right.
//        this.random.setSeed(this.world.getSeed());
//
//        long i1 = this.random.nextLong() / 2L * 2L + 1L;
//        long j1 = this.random.nextLong() / 2L * 2L + 1L;
//
//        // new unique seed for this chunk.
//        this.random.setSeed((long)chunkX*i1 + (long)chunkZ*j1 ^ this.world.getSeed());
//        boolean hasVillageGenerated = false;
//
//        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunkProvider, world, random, chunkX, chunkZ, hasVillageGenerated));
//
//        if (this.mapFeaturesEnabled) {
//            // gen our features. plateaus, boulders, bridges, towers
//        }
//
//        int k1;
//        int l1;
//        int i2;
//
//        if (true) {
//
//        }
//
//
//        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunkProvider, world, random, chunkX, chunkZ, hasVillageGenerated));
//        BlockSand.fallInstantly = false;
    }

    /**
     * Checks to see if a chunk exists at x, y
     *
     * @param var1
     * @param var2
     */
    @Override
    public boolean chunkExists(int var1, int var2)
    {
        return true;
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
        this.random.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
        Block[] blockArray = new Block[65536];
        byte[] blockMetadata = new byte[65536];

        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;

        this.generateTerrain(chunkX, chunkZ, blockArray, blockMetadata);

//        this.biomesForGeneration = this.world.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, xPos, zPos, 16, 61);

//        this.replaceBlocksForBiome(chunkX, chunkZ, blockArray, blockMetadata, this.biomesForGeneration);

        Chunk chunk = new Chunk(this.world, blockArray, blockMetadata, chunkX, chunkZ);
//        byte[] ba1 = chunk.getBiomeArray();

//        for (int i = 0; i < ba1.length; i++) {
//            ba1[i] = (byte)this.biomesForGeneration[i].biomeID;
//        }

        chunk.generateSkylightMap();

        return chunk;
    }

    protected void generateTerrain(int chunkX, int chunkZ, Block[] blockArray, byte[] blockMetadata) {
        // 16*16=256
        double[] heightMap = new double[256];
        double[] bottomHeightMap = new double[256];
        int[] topBlock = new int[256];
        int[] bottomBlock = new int[256];

        int xPos = chunkX*16;
        int zPos = chunkZ*16;

        // map, chunkX offset, chunkY offset, chunkZ offset, chunkW, chunkH, chunkL, chunkScaleX, chunkScaleY, chunkScaleZ
        heightMap = this.noiseGen5.generateNoiseOctaves(heightMap, chunkX * 16, 10, chunkZ * 16, 16, 1, 16, .1, 0, .1);
        bottomHeightMap = this.noiseGen4.generateNoiseOctaves(bottomHeightMap, xPos, 1, zPos, 16, 1, 16, .003, 0, .2);

        for (int i = 0; i < 256; i++) {
            int y = (int)(32 + bottomHeightMap[i]);
            bottomBlock[i] = y <= 255 ? y : 255 - y;
        }

        for (int i = 0; i < 256; i++) {
            int y = (int)(128 + heightMap[i]);
            topBlock[i] = y <= 255 ? y : 255 - y;
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                blockArray[x << 12 | z << 8] = Blocks.bedrock;

                for (int y = 1; y <= 2; y++) {
                    blockArray[x << 12 | z << 8 | y] = ModBlocks.digitalSeaBlock;
                }

                int y;
                int yMax = topBlock[x << 4 | z] - 2;
                int yMin = bottomBlock[x << 4 | z] - 2;
                for (y = yMin; y < yMax; y++) {
                    blockArray[x << 12 | z << 8 | y] = ModBlocks.sand;
//                    blockMetadata[x << 12 | z << 8 | y] = 0;
                }

                for (; y < yMax+2; y++) {
                    blockArray[x << 12 | z << 8 | y] = ModBlocks.sand;
//                    blockMetadata[x << 12 | z << 8 | y] = 0;
                }
            }
        }
    }

    private void replaceBlocksForBiome(int chunkX, int chunkZ, Block[] blockArray, byte[] ba, BiomeGenBase[] biomesForGeneration) {
//        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, blockArray, biomesForGeneration);
//        MinecraftForge.EVENT_BUS.post(event);
//
//        if (event.getResult() == Event.Result.DENY) return;
//
//        int xPos = chunkX * 16;
//        int zPos = chunkZ * 16;
//        double d0 = 0.03125D;
//        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, xPos, zPos, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
//
//        for (int x = 0; x < 16; ++x)
//        {
//            for (int z = 0; z < 16; ++z)
//            {
//                BiomeGenBase biomegenbase = biomesForGeneration[z + x * 16];
//                biomegenbase.genTerrainBlocks(this.world, this.random, blockArray, ba, xPos + x, zPos * 16 + z, this.stoneNoise[z + x * 16]);
//            }
//        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     *
     * @param chunkX
     * @param chunkZ
     */
    @Override
    public Chunk loadChunk(int chunkX, int chunkZ)
    {
        return this.provideChunk(chunkX, chunkZ);
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
        return true;
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
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    @Override
    public String makeString()
    {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     *
     * @param creatureType
     * @param xPos
     * @param yPos
     * @param zPos
     */
    @Override
    public List getPossibleCreatures(EnumCreatureType creatureType, int xPos, int yPos, int zPos)
    {
        return null;
    }

    @Override
    // gets the nearest structure
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
        if (this.mapFeaturesEnabled)
        {
            // generate features again
        }
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    @Override
    public void saveExtraData() {}
}
