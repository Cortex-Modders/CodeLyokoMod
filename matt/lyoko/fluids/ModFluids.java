/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.lib.BlockIds;
import net.minecraft.block.Block;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
	public static Fluid digitalSea;
	
	public static void init()
	{
		digitalSea  = new FluidDigitalSea("Digital Sea").setUnlocalizedName("DigitalSea");
		
		registerFluids();
	}
	
	public static void registerFluids()
	{
		FluidRegistry.registerFluid(digitalSea);
	}
	
	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event)
	{
		if(event.map.textureType == 0)
		{
			digitalSea.setIcons(ModBlocks.DigitalSeaLiquid.getBlockTextureFromSide(1));
		}
	}
}