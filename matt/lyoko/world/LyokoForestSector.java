package matt.lyoko.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import matt.lyoko.*;
import matt.lyoko.lib.DimensionIds;

public class LyokoForestSector extends WorldProvider{

	public int getDimensionID() 
	{
		return DimensionIds.FOREST;
	}

	@Override
	public void registerWorldChunkManager()
	{
		worldChunkMgr = new WorldChunkManagerHell(CodeLyoko.lyokoforest, 0.8F, 0F);
	}

	public IChunkProvider getChunkProvider()
	{
		return new LyokoForestChunkProvider(worldObj, worldObj.getSeed());
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}


	//Ender sky if set true
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

	//Darken the sky if it rains is true
	public boolean darkenSkyDuringRain()
	{
		return false;
	}

	//Star brightness 1.0F lets you see stars in day
	public float getStarBrightness(World world, float f)
	{
		return 1.0F;
	}

	//Fog Color
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2)
	{
		int var3 = 10518688;
		float var4 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

		if (var4 < 0.0F)
		{
			var4 = 0.0F;
		}

		if (var4 > 1.0F)
		{
			var4 = 1.0F;
		}

		float var5 = (float)(var3 >> 16 & 255) / 255.0F;
		float var6 = (float)(var3 >> 8 & 255) / 255.0F;
		float var7 = (float)(var3 & 255) / 255.0F;
		var5 *= var4 * 0.0F + 0.15F;
		var6 *= var4 * 0.0F + 0.15F;
		var7 *= var4 * 0.0F + 0.15F;
		return this.worldObj.getWorldVec3Pool().getVecFromPool((double)var5, (double)var6, (double)var7);
	}

	//removes clouds if set to false
	public boolean renderClouds()
	{
		return false;
	}

	//Removes the Sky
	public boolean hasNoSky() {
		return false;
	}

	@Override
	public String getDimensionName() {
		return "Forest Sector";
	}



}
