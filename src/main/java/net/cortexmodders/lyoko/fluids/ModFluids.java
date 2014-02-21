/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.fluids;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModFluids
{
    public static Fluid digitalSea;

    public static void init()
    {
        digitalSea = new FluidDigitalSea("Digital Sea").setUnlocalizedName("DigitalSea");

        registerFluids();
    }

    public static void registerFluids()
    {
        FluidRegistry.registerFluid(digitalSea);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Post event)
    {
        if (event.map.getTextureType() == 0)
            digitalSea.setIcons(ModBlocks.digitalSeaLiquid.getBlockTextureFromSide(1));
    }
}
