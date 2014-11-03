/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.client.LyokoTab;
import net.cortexmodders.lyoko.client.gui.GuiHandler;
import net.cortexmodders.lyoko.client.render.TileAnimator;
import net.cortexmodders.lyoko.command.CommandDeleteDimension;
import net.cortexmodders.lyoko.command.CommandDevirtualize;
import net.cortexmodders.lyoko.entities.projectile.EntityLyokoRanged;
import net.cortexmodders.lyoko.fluids.ModFluids;
import net.cortexmodders.lyoko.items.ModItems;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.cortexmodders.lyoko.lib.ModLogger;
import net.cortexmodders.lyoko.lib.ModProperties;
import net.cortexmodders.lyoko.lib.Recipes;
import net.cortexmodders.lyoko.network.PacketHandler;
import net.cortexmodders.lyoko.proxy.CommonProxy;
import net.cortexmodders.lyoko.world.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Mod(modid = ModProperties.MOD_ID, name = ModProperties.MOD_NAME, version = ModProperties.MOD_VERSION, useMetadata = true)
public class CodeLyoko
{
    private static String[] developers = {"986523714", "MoonMagick", "Wolfspirit1st", "JadarMC"};
    public static List<Item> debugTools = new ArrayList<Item>();

    public static HashSet<TileAnimator> animatorInstances = new HashSet<TileAnimator>();

    public static boolean enableAdminPowers;
    public static boolean useHDTextures;

    public static CreativeTabs LyokoTabs = new LyokoTab("LyokoTabs");

    public static BiomeGenBaseLyoko lyokomountain;
    public static BiomeGenBaseLyoko lyokoforest;
    public static BiomeGenBaseLyoko lyokodesert;
    public static BiomeGenBaseLyoko lyokopolar;
    public static BiomeGenBaseLyoko lyokocarthage;

    public WorldGenLyokoLargeTree forrestTreeGen;

    /**
     * Common proxy instance. Used for utility function and keeping server and client code apart.
     */
    @SidedProxy(clientSide = ModProperties.CLIENT_PROXY, serverSide = ModProperties.COMMON_PROXY)
    public static CommonProxy proxy;

    /**
     * The instance of the mod.
     */
    @Instance
    public static CodeLyoko instance;

    private ModLogger modLogger;
    protected Configuration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        this.config = config;
//        proxy.registerDimensionIds(config);


        enableAdminPowers = config.get(ModProperties.ConfigCategories.OTHER.name(), "enableAdminPowers", false).getBoolean(false);
        useHDTextures = config.get(ModProperties.ConfigCategories.OTHER.name(), "useHDTextures", false).getBoolean(false);

        if (config.hasChanged())
            config.save();

        this.modLogger = new ModLogger(event.getModLog());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModFluids.init();
        ModItems.init();
        ModBlocks.init();
        DimensionIds.registerIds(this.config);

        lyokomountain = (BiomeGenBaseLyoko) new BiomeGenMountainSector(9).setColor(8421631).setBiomeName("Mountain Sector");
        lyokoforest = (BiomeGenBaseLyoko) new BiomeGenForestSector(10).setColor(8421631).setBiomeName("Forest Sector");
        lyokodesert = (BiomeGenBaseLyoko) new BiomeGenDesertSector(11).setColor(8421631).setBiomeName("Desert Sector");
        lyokopolar = (BiomeGenBaseLyoko) new BiomeGenPolarSector(12).setColor(8421631).setBiomeName("Polar Sector");
        lyokocarthage = (BiomeGenBaseLyoko) new BiomeGenCarthageSector(13).setColor(8421631).setBiomeName("Carthage Sector");

        Recipes.registerBlockRecipes();
        Recipes.registerSmelting();
        Recipes.registerItemRecipes();

        proxy.registerEntities();
        proxy.registerRenderInformation();
        proxy.registerKeyBindingHandler();
        proxy.registerOreDictionaryOres();
        proxy.addChestLoot();
        proxy.registerTileEntities();
        proxy.registerDimensions();
        proxy.registerEventHandlers();
        proxy.registerCapes();

        PacketHandler.INSTANCE.initPackets();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        GameRegistry.registerWorldGenerator(new WorldGenLyokoOre(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenTower(), 0);

        forrestTreeGen = new WorldGenLyokoLargeTree();

        // Block.setHarvestLevel(ModBlocks.QuantumOre, "pickaxe", 2);
        // MinecraftForge.setBlockHarvestLevel(ModBlocks.LeadOre, "pickaxe", 2);
        // MinecraftForge.setBlockHarvestLevel(ModBlocks.UraniumOre, "pickaxe", 2);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.registerClientEventHandlers();
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDevirtualize());
        event.registerServerCommand(new CommandDeleteDimension());
    }

    public ModLogger getLogger()
    {
        return this.modLogger;
    }

    public static boolean entityInLyoko(Entity ent)
    {
        if (ent != null)
            return ent.dimension == DimensionIds.CARTHAGE || ent.dimension == DimensionIds.ICE || ent.dimension == DimensionIds.MOUNTAIN || ent.dimension == DimensionIds.FOREST || ent.dimension == DimensionIds.DESERT || ent.dimension == DimensionIds.CORTEX || ent.dimension == DimensionIds.DIGITALSEA;
        return false;
    }

    public static String[] getDevelopers()
    {
        return developers;
    }

    public static DamageSource causeLyokoRangedDamage(EntityLyokoRanged par0EntityLaserArrow, Entity par1Entity)
    {
        return new EntityDamageSourceIndirect("arrow", par0EntityLaserArrow, par1Entity).setProjectile();
    }
}
