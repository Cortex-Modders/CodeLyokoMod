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

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.lib.BlockIds;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
	public static Fluid digitalSea;
	
	public static void init()
	{
		digitalSea  = new FluidDigitalSea("Digital Sea").setIcons(Block.waterMoving.getIcon(0, 0)).setUnlocalizedName("DigitalSea");
		
		registerFluids();
	}
	
	public static void registerFluids()
	{
		FluidRegistry.registerFluid(digitalSea);
	}
}