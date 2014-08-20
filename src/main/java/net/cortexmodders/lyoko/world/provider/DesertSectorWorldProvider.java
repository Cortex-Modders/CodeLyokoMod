/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.world.provider;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.cortexmodders.lyoko.world.chunk.DesertSectorChunkProvider;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DesertSectorWorldProvider extends WorldProvider
{

    protected IChunkProvider chunkProvider;

    public int getDimensionID()
    {
        return DimensionIds.DESERT;
    }

    @Override
    public void registerWorldChunkManager()
    {
        super.registerWorldChunkManager();
        isHellWorld = false;
        this.worldChunkMgr = new WorldChunkManagerHell(CodeLyoko.lyokodesert, 0.0F);
        this.hasNoSky = false;
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        if (chunkProvider == null) {
            this.chunkProvider = new DesertSectorChunkProvider(this.worldObj, this.worldObj.getSeed(), false);
        }
        return this.chunkProvider;
    }

    public IChunkProvider getChunkProvider() {
        return this.chunkProvider;
    }


    @Override
    public boolean canRespawnHere()
    {
        return false;
    }
    
    // Ender sky if set true
    public boolean renderEndSky()
    {
        return true;
    }
    
    public float setSunSize()
    {
        return 2.0F;
    }
    
    public float setMoonSize()
    {
        return 0.5F;
    }

    // Darken the sky if it rains is true
    public boolean darkenSkyDuringRain()
    {
        return false;
    }
    
    // Star brightness 1.0F lets you see stars in day
    public float getStarBrightness(World world, float f)
    {
        return 1.0F;
    }



    // Fog Color
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {
        return Vec3.createVectorHelper(255f / 255f, 166f / 255f, 63f / 255f);
    }

    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        return Vec3.createVectorHelper(63f / 255f, 159f / 255f, 255f / 255f);
    }

    // removes clouds if set to false
    public boolean renderClouds()
    {
        return false;
    }
    
    @Override
    public String getDimensionName()
    {
        return "Desert Sector";
    }
    
}
