/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.proxy;

import java.net.MalformedURLException;
import java.net.URL;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.client.render.ItemRenderGlove;
import net.cortexmodders.lyoko.client.render.mobs.RenderBlok;
import net.cortexmodders.lyoko.client.render.mobs.RenderTank;
import net.cortexmodders.lyoko.client.render.mobs.RenderXanafiedMob;
import net.cortexmodders.lyoko.client.render.tileentity.RenderCable;
import net.cortexmodders.lyoko.client.render.tileentity.RenderHolomapBlock;
import net.cortexmodders.lyoko.client.render.tileentity.RenderScanner;
import net.cortexmodders.lyoko.client.render.tileentity.RenderSuperCalcConsole;
import net.cortexmodders.lyoko.client.render.tileentity.RenderTower;
import net.cortexmodders.lyoko.client.render.tileentity.RenderTowerConsole;
import net.cortexmodders.lyoko.client.render.vehicles.RenderOverboard;
import net.cortexmodders.lyoko.client.render.vehicles.RenderSkid;
import net.cortexmodders.lyoko.entities.mobs.EntityBlok;
import net.cortexmodders.lyoko.entities.mobs.EntityMegaTank;
import net.cortexmodders.lyoko.entities.mobs.EntityXanafiedMob;
import net.cortexmodders.lyoko.entities.projectile.EntityEnergyField;
import net.cortexmodders.lyoko.entities.projectile.EntityFan;
import net.cortexmodders.lyoko.entities.projectile.EntityLaser;
import net.cortexmodders.lyoko.entities.projectile.EntityLaserArrow;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityCable;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityHolomap;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityTower;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityTowerConsole;
import net.cortexmodders.lyoko.entities.vehicles.EntityOverboard;
import net.cortexmodders.lyoko.entities.vehicles.EntitySkid;
import net.cortexmodders.lyoko.handlers.ClientTickHandler;
import net.cortexmodders.lyoko.handlers.KeyBindingHandler;
import net.cortexmodders.lyoko.items.ModItems;
import net.cortexmodders.lyoko.lib.ModProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import com.jadarstudios.developercapes.DevCapes;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public static final int scannerRenderId = RenderingRegistry.getNextAvailableRenderId();
    public static final int superCalcConsoleRenderId = RenderingRegistry.getNextAvailableRenderId();
    public static final int holomapRenderId = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void registerRenderInformation()
    {
        RenderingRegistry.addNewArmourRendererPrefix("lyoko:textures/armor/a");
        RenderingRegistry.addNewArmourRendererPrefix("lyoko:textures/armor/o");
        RenderingRegistry.addNewArmourRendererPrefix("lyoko:textures/armor/u");
        RenderingRegistry.addNewArmourRendererPrefix("lyoko:textures/armor/y");
        RenderingRegistry.addNewArmourRendererPrefix("lyoko:textures/armor/w");
        
        RenderingRegistry.registerEntityRenderingHandler(EntityFan.class, new RenderSnowball(ModItems.Fan));
        RenderingRegistry.registerEntityRenderingHandler(EntityEnergyField.class, new RenderSnowball(ModItems.EnergyField));
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserArrow.class, new RenderSnowball(ModItems.LaserArrow));
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderSnowball(ModItems.LaserShooter));

        RenderingRegistry.registerEntityRenderingHandler(EntityXanafiedMob.class, new RenderXanafiedMob());
        RenderingRegistry.registerEntityRenderingHandler(EntityBlok.class, new RenderBlok());
        RenderingRegistry.registerEntityRenderingHandler(EntityMegaTank.class, new RenderTank());
        /*
         * RenderingRegistry.registerEntityRenderingHandler(EntityHornet.class,
         * new RenderHornet(new ModelHornet(), 0.5F));
         * RenderingRegistry.registerEntityRenderingHandler
         * (EntityKankrelat.class, new RenderKankrelat(new ModelKankrelat(),
         * 0.5F));
         * RenderingRegistry.registerEntityRenderingHandler(EntityKrab.class,
         * new RenderKrab(new ModelKrab(), 0.5F));
         * RenderingRegistry.registerEntityRenderingHandler
         * (EntityLyokoCreeper.class, new RenderLyokoCreeper(new
         * ModelLyokoCreeper(), 0.5F));
         * RenderingRegistry.registerEntityRenderingHandler(EntityManta.class,
         * new RenderManta(new ModelManta(), 0.5F));
         * RenderingRegistry.registerEntityRenderingHandler
         * (EntityTarantula.class, new RenderTarantula(new ModelTarantula(),
         * 0.5F));
         */
        RenderingRegistry.registerEntityRenderingHandler(EntitySkid.class, new RenderSkid());
        RenderingRegistry.registerEntityRenderingHandler(EntityOverboard.class, new RenderOverboard());

        MinecraftForgeClient.registerItemRenderer(ModItems.Glove, new ItemRenderGlove());

        // ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuperCalc.class,
        // new RenderSuperCalc());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTowerConsole.class, new RenderTowerConsole());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTower.class, new RenderTower());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());

        RenderScanner scannerRenderer = new RenderScanner(scannerRenderId);
        RenderSuperCalcConsole superCalcConsoleRenderer = new RenderSuperCalcConsole(superCalcConsoleRenderId);
        RenderHolomapBlock holomapRenderer = new RenderHolomapBlock(holomapRenderId);

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityScanner.class, scannerRenderer);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuperCalcConsole.class, superCalcConsoleRenderer);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHolomap.class, holomapRenderer);

        RenderingRegistry.registerBlockHandler(scannerRenderer);
        RenderingRegistry.registerBlockHandler(superCalcConsoleRenderer);
        RenderingRegistry.registerBlockHandler(holomapRenderer);
    }

    @Override
    public void registerCapes()
    {
        try
        {
            DevCapes.getInstance().registerConfig(new URL(ModProperties.CAPE_CONFIG_URL), ModProperties.MOD_ID);
        }
        catch (MalformedURLException e)
        {
            CodeLyoko.instance.getLogger().warn("Could not initiate capes. Are you connected to the internet?");
        }
    }
    
    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
        
        MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
        MinecraftForge.EVENT_BUS.register(new KeyBindingHandler());
    }
    
    /**
     * 
     * called for things with alpha. thank you MachineMuse. :D
     */
    public static void alphaOn()
    {
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        if (Minecraft.isFancyGraphicsEnabled())
        {
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }
    }

    public static void alphaOff()
    {
        GL11.glPopAttrib();
        GL11.glPopAttrib();
    }
}
