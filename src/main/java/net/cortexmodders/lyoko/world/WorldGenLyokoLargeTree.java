package net.cortexmodders.lyoko.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * Created by jacob on 6/6/14.
 */
public class WorldGenLyokoLargeTree implements IWorldGenerator
{


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.dimensionId == DimensionIds.FOREST) {
            int blockX = chunkX * 16;
            int blockZ = chunkZ * 16;
            blockX += random.nextInt(16);
            blockZ += random.nextInt(16);

            int blockY = world.getHeightValue(blockX, blockZ) - random.nextInt(8);

            this.generateTree(random, blockX, 0, blockZ, world);
        }


    }

    public void generateTree(Random random, int blockX, int blockY, int blockZ, World world)
    {
        int height = 50 + random.nextInt(28);


        int xOffset = 0;
        int zOffset = 0;

        for (int i = 0; i <= height; i++) {

            world.setBlock(blockX + xOffset, blockY + i, blockZ + zOffset, ModBlocks.log);
        }

        this.makeBranches(random, blockX, blockY + height + 1, blockZ, world);
    }

    public void makeBranches(Random random, int centerX, int currentY, int centerZ, World world)
    {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (direction == ForgeDirection.DOWN || direction == ForgeDirection.UP) {
                continue;
            }

            int branchHeight = random.nextInt(9);
            int xOffset = direction.offsetX;
            int zOffset = direction.offsetZ;
            for (int i = 0; i <= branchHeight; i++) {
                if (i % 3 == 0 && random.nextInt(3) == 1) {
                    xOffset += direction.offsetX;
                    zOffset += direction.offsetZ;
                }

                world.setBlock(centerX + xOffset, currentY + i, centerZ + zOffset, ModBlocks.log);
            }
        }
    }
}

