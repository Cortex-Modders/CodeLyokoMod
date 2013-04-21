package matt.lyoko;

import matt.lyoko.entities.mobs.EntityBlok;
import matt.lyoko.entities.mobs.EntityMegaTank;
import matt.lyoko.entities.vehicles.EntityOverboard;
import matt.lyoko.entities.vehicles.EntitySkid;
import matt.lyoko.lib.EntityIds;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

//    private CodeLyoko instance = CodeLyoko.instance;
    
    /**
     * client side only register stuff...
     */
    public void registerRenderInformation() {
        // unused server side. -- see ClientProxy for implementation
    }

    public void registerServerTickHandler() {
        TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
    }

    public void registerKeyBindingHandler() {
    }

    public void registerOres() {
        OreDictionary.registerOre("ingotRadioactiveLead", CodeLyoko.LyokoLead);
        OreDictionary.registerOre("oreRadioactiveLead", CodeLyoko.LeadOre);
        OreDictionary.registerOre("ingotUranium", CodeLyoko.Uranium);
        OreDictionary.registerOre("oreUranium", CodeLyoko.UraniumOre);
    }

    public void registerFragmentRecipes() {
        GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoPolarPortal), new Object[] { "###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.ice });
        GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoMountainPortal), new Object[] { "###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.stone });
        GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoForestPortal), new Object[] { "###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.grass });
        GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoDesertPortal), new Object[] { "###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), new ItemStack(Block.sandStone, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoCarthagePortal), new Object[] { "###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.beacon });
    }

    public void addChestLoot() {
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.LyokoIngot), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.LyokoLead), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.Uranium), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamBoots), 1, 1, 005));
    }
    
    public void registerEntities() {
        // Monsters.
        EntityRegistry.registerGlobalEntityID(EntityBlok.class, "Blok", EntityIds.BLOK, 0xe3b434, 0x000000);
        EntityRegistry.registerGlobalEntityID(EntityMegaTank.class, "Megatank", EntityIds.MEGATANK, 0xe3b434, 0x000000);

        // Vehicles
        EntityRegistry.registerModEntity(EntitySkid.class, "Skidbladnir", EntityIds.SKID, CodeLyoko.instance, 50, 1, true);
        EntityRegistry.registerModEntity(EntityOverboard.class, "Overboard", EntityIds.OVERBOARD, CodeLyoko.instance, 50, 1, true);
        
        // Naturally spawn in Lyoko.
        EntityRegistry.addSpawn(matt.lyoko.entities.mobs.EntityBlok.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.instance.lyokoCarthageBiome, CodeLyoko.instance.lyokoForestBiome, CodeLyoko.instance.lyokoMountainBiome, CodeLyoko.instance.lyokoPolarBiome, CodeLyoko.instance.lyokoDesertBiome);
        EntityRegistry.addSpawn(matt.lyoko.entities.mobs.EntityMegaTank.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.instance.lyokoCarthageBiome, CodeLyoko.instance.lyokoForestBiome, CodeLyoko.instance.lyokoMountainBiome, CodeLyoko.instance.lyokoPolarBiome, CodeLyoko.instance.lyokoDesertBiome);
    }
    
    public void registerStrings() {
        // Monsters.
        LanguageRegistry.instance().addStringLocalization("entity.Blok.name", "en_US", "Blok");
        LanguageRegistry.instance().addStringLocalization("entity.Megatank.name", "en_US", "Megatank");
        
        // Vehicles.
        LanguageRegistry.instance().addStringLocalization("entity.Skidbladnir.name", "en_US", "Skidbladnir");
        LanguageRegistry.instance().addStringLocalization("entity.Overboard.name", "en_US", "Overboard");
    }
}