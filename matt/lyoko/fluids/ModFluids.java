package matt.lyoko.fluids;

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.lib.BlockIds;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
	public static Fluid digitalSea;
	
	public static void init()
	{
		digitalSea  = new Fluid("Digital Sea").setBlockID(BlockIds.LYOKO_DIGITAL_SEA_LIQUID).setUnlocalizedName("DigitalSea");
		
		registerFluids();
	}
	
	public static void registerFluids()
	{
		FluidRegistry.registerFluid(digitalSea);
	}
}