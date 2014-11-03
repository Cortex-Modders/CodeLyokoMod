/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.lib.ModProperties;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems
{
    public static ToolMaterial toolLYOKO = EnumHelper.addToolMaterial("LYOKO", 3, 100, 14F, 30, 30);
    public static ArmorMaterial armorLYOKO = EnumHelper.addArmorMaterial("LYOKO", 200, new int[]{5, 10, 8, 5}, 30);

    public static Item katana;
    public static Item zweihander;
    public static Item fan;
    public static Item energyField;
    public static Item glove;
    public static Item laserArrow;
    public static Item quantumOrb;
    public static Item lead;
    public static Item emptyCell;
    public static Item leadCell;
    public static Item depletedLeadCell;
    public static Item aelitaHelmet;
    public static Item aelitaChest;
    public static Item aelitaLegs;
    public static Item aelitaBoots;
    public static Item oddHelmet;
    public static Item oddChest;
    public static Item oddLegs;
    public static Item oddBoots;
    public static Item ulrichHelmet;
    public static Item ulrichChest;
    public static Item ulrichLegs;
    public static Item ulrichBoots;
    public static Item yumiHelmet;
    public static Item yumiChest;
    public static Item yumiLegs;
    public static Item yumiBoots;
    public static Item williamHelmet;
    public static Item williamChest;
    public static Item williamLegs;
    public static Item williamBoots;
    public static Item dataFragment;
    public static Item uranium;
    public static Item uraniumCell;
    public static Item depletedUraniumCell;
    public static Item overboard;
    public static Item skidbladnir;
    public static Item quantumMatrix;
    public static Item quantumContainmentCell;
    public static Item laserShooter;
    public static Item debugItem;

    public static void init()
    {
        katana = new ItemLyokoSword(toolLYOKO).setUnlocalizedName("Katana");
        zweihander = new ItemLyokoSword(toolLYOKO).setUnlocalizedName("Zweihander");
        fan = new ItemFan().setUnlocalizedName("Fan");
        energyField = new ItemEnergyField().setUnlocalizedName("EnergyField");
        glove = new ItemGlove().setUnlocalizedName("Glove");
        laserArrow = new ItemLyoko().setUnlocalizedName("LaserArrow");
        quantumOrb = new ItemLyoko().setUnlocalizedName("LyokoIngot");
        lead = new ItemLyoko().setUnlocalizedName("Lead210");
        emptyCell = new ItemLyoko().setUnlocalizedName("Cell");
        leadCell = new ItemLyokoFuel(5000, depletedLeadCell).setUnlocalizedName("Lead210Cell");
        depletedLeadCell = new ItemLyoko().setUnlocalizedName("DepletedLead210Cell");
        aelitaHelmet = new ArmorLyoko(armorLYOKO, 5, 0, "aelita").setUnlocalizedName("DepletedLead210Cell");
        aelitaChest = new ArmorLyoko(armorLYOKO, 5, 1, "aelita").setUnlocalizedName("AelitaChest");
        aelitaLegs = new ArmorLyoko(armorLYOKO, 5, 2, "aelita").setUnlocalizedName("AelitaPants");
        aelitaBoots = new ArmorLyoko(armorLYOKO, 5, 3, "aelita").setUnlocalizedName("AelitaBoots");
        oddHelmet = new ArmorLyoko(armorLYOKO, 6, 0, "odd").setUnlocalizedName("OddHelmet");
        oddChest = new ArmorLyoko(armorLYOKO, 6, 1, "odd").setUnlocalizedName("OddChest");
        oddLegs = new ArmorLyoko(armorLYOKO, 6, 2, "odd").setUnlocalizedName("OddPants");
        oddBoots = new ArmorLyoko(armorLYOKO, 6, 3, "odd").setUnlocalizedName("OddBoots");
        ulrichHelmet = new ArmorLyoko(armorLYOKO, 7, 0, "ulrich").setUnlocalizedName("UlrichHelmet");
        ulrichChest = new ArmorLyoko(armorLYOKO, 7, 1, "ulrich").setUnlocalizedName("UlrichChest");
        ulrichLegs = new ArmorLyoko(armorLYOKO, 7, 2, "ulrich").setUnlocalizedName("UlrichPants");
        ulrichBoots = new ArmorLyoko(armorLYOKO, 7, 3, "ulrich").setUnlocalizedName("UlrichBoots");
        yumiHelmet = new ArmorLyoko(armorLYOKO, 8, 0, "yumi").setUnlocalizedName("YumiHelmet");
        yumiChest = new ArmorLyoko(armorLYOKO, 8, 1, "yumi").setUnlocalizedName("YumiChest");
        yumiLegs = new ArmorLyoko(armorLYOKO, 8, 2, "yumi").setUnlocalizedName("YumiPants");
        yumiBoots = new ArmorLyoko(armorLYOKO, 8, 3, "yumi").setUnlocalizedName("YumiBoots");
        williamHelmet = new ArmorLyoko(armorLYOKO, 9, 0, "william").setUnlocalizedName("WilliamHelmet");
        williamChest = new ArmorLyoko(armorLYOKO, 9, 1, "william").setUnlocalizedName("WilliamChest");
        williamLegs = new ArmorLyoko(armorLYOKO, 9, 2, "william").setUnlocalizedName("WilliamPants");
        williamBoots = new ArmorLyoko(armorLYOKO, 9, 3, "william").setUnlocalizedName("WilliamBoots");
        dataFragment = new ItemDataFragment().setUnlocalizedName("DataFragment");
        uranium = new ItemLyoko().setUnlocalizedName("Uranium");
        uraniumCell = new ItemLyokoFuel(20000, depletedUraniumCell).setUnlocalizedName("UraniumCell");
        depletedUraniumCell = new ItemLyoko().setUnlocalizedName("DepletedUraniumCell");
        overboard = new ItemOverboard().setUnlocalizedName("Overboard");
        skidbladnir = new ItemSkid().setUnlocalizedName("Skidbladnir");
        quantumMatrix = new ItemLyoko().setUnlocalizedName("QuantumMatrix");
        quantumContainmentCell = new ItemLyoko().setUnlocalizedName("QuantumContainmentCell");
        laserShooter = new ItemLaserShooter().setUnlocalizedName("LaserShooter");

        if (CodeLyoko.DEBUG)
            debugItem = new ItemDebug().setUnlocalizedName("DebugItem");

        GameRegistry.registerItem(katana, "katana", ModProperties.MOD_ID);
        GameRegistry.registerItem(zweihander, "zweihander", ModProperties.MOD_ID);
        GameRegistry.registerItem(fan, "fan", ModProperties.MOD_ID);
        GameRegistry.registerItem(energyField, "energy_field", ModProperties.MOD_ID);
        GameRegistry.registerItem(glove, "glove", ModProperties.MOD_ID);
        GameRegistry.registerItem(laserArrow, "laser_arrow", ModProperties.MOD_ID);
        GameRegistry.registerItem(quantumOrb, "quantum_orb", ModProperties.MOD_ID);
        GameRegistry.registerItem(lead, "lead", ModProperties.MOD_ID);
        GameRegistry.registerItem(emptyCell, "cell_empty", ModProperties.MOD_ID);
        GameRegistry.registerItem(leadCell, "cell_lead", ModProperties.MOD_ID);
        GameRegistry.registerItem(depletedLeadCell, "cell_lead_depleted", ModProperties.MOD_ID);
        GameRegistry.registerItem(aelitaHelmet, "helmet_aelita", ModProperties.MOD_ID);
        GameRegistry.registerItem(aelitaChest, "chest_aelita", ModProperties.MOD_ID);
        GameRegistry.registerItem(aelitaLegs, "legs_aelita", ModProperties.MOD_ID);
        GameRegistry.registerItem(aelitaBoots, "boots_aelita", ModProperties.MOD_ID);
        GameRegistry.registerItem(oddHelmet, "helmet_odd", ModProperties.MOD_ID);
        GameRegistry.registerItem(oddChest, "chest_odd", ModProperties.MOD_ID);
        GameRegistry.registerItem(oddLegs, "legs_odd", ModProperties.MOD_ID);
        GameRegistry.registerItem(oddBoots, "boots_odd", ModProperties.MOD_ID);
        GameRegistry.registerItem(ulrichHelmet, "helmet_ulrich", ModProperties.MOD_ID);
        GameRegistry.registerItem(ulrichChest, "chest_ulrich", ModProperties.MOD_ID);
        GameRegistry.registerItem(ulrichLegs, "legs_ulrich", ModProperties.MOD_ID);
        GameRegistry.registerItem(ulrichBoots, "boots_ulrich", ModProperties.MOD_ID);
        GameRegistry.registerItem(yumiHelmet, "helmet_yumi", ModProperties.MOD_ID);
        GameRegistry.registerItem(yumiChest, "chest_yumi", ModProperties.MOD_ID);
        GameRegistry.registerItem(yumiLegs, "legs_yumi", ModProperties.MOD_ID);
        GameRegistry.registerItem(yumiBoots, "boots_yumi", ModProperties.MOD_ID);
        GameRegistry.registerItem(williamHelmet, "helmet_william", ModProperties.MOD_ID);
        GameRegistry.registerItem(williamChest, "chest_william", ModProperties.MOD_ID);
        GameRegistry.registerItem(williamLegs, "legs_william", ModProperties.MOD_ID);
        GameRegistry.registerItem(williamBoots, "boots_william", ModProperties.MOD_ID);
        GameRegistry.registerItem(dataFragment, "fragment_data", ModProperties.MOD_ID);
        GameRegistry.registerItem(uranium, "uranium", ModProperties.MOD_ID);
        GameRegistry.registerItem(uraniumCell, "cell_uranium", ModProperties.MOD_ID);
        GameRegistry.registerItem(depletedUraniumCell, "cell_uranium_depleted", ModProperties.MOD_ID);
        GameRegistry.registerItem(overboard, "overboard", ModProperties.MOD_ID);
        GameRegistry.registerItem(skidbladnir, "skidbladnir", ModProperties.MOD_ID);
        GameRegistry.registerItem(quantumMatrix, "matrix_quantum", ModProperties.MOD_ID);
        GameRegistry.registerItem(quantumContainmentCell, "cell_quantum_containment", ModProperties.MOD_ID);
        GameRegistry.registerItem(laserShooter, "laser_shooter", ModProperties.MOD_ID);

        if (CodeLyoko.DEBUG) {
            GameRegistry.registerItem(debugItem, "debug_item", ModProperties.MOD_ID);
            registerDebugTools();
        }
    }

    public static void registerDebugTools()
    {
        CodeLyoko.debugTools.add(laserArrow);
        CodeLyoko.debugTools.add(laserShooter);
    }
}
