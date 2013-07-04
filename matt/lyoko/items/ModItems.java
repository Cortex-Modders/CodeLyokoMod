package matt.lyoko.items;

import matt.lyoko.entities.projectile.EntityEnergyField;
import matt.lyoko.entities.projectile.EntityFan;
import matt.lyoko.entities.projectile.EntityLaserArrow;
import matt.lyoko.lib.ItemIds;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;

public class ModItems
{
    private static EnumToolMaterial toolLYOKO = EnumHelper.addToolMaterial("LYOKO", 3, 100, 14F, 30, 30);
    private static EnumArmorMaterial armorLYOKO = EnumHelper.addArmorMaterial("LYOKO", 40, new int[] {5, 10, 8, 5}, 30);
    
    public static Item Katana;
    public static Item Zweihander;
    public static Item Fan;
    public static Item EnergyField;
    public static Item Glove;
    public static Item LaserArrow;
    public static Item QuantumOrb;
    public static Item Lead;
    public static Item EmptyCell;
    public static Item LeadCell;
    public static Item DepletedLeadCell;
    public static Item AelitaHelmet;
    public static Item AelitaChest;
    public static Item AelitaLegs;
    public static Item AelitaBoots;
    public static Item OddHelmet;
    public static Item OddChest;
    public static Item OddLegs;
    public static Item OddBoots;
    public static Item UlrichHelmet;
    public static Item UlrichChest;
    public static Item UlrichLegs;
    public static Item UlrichBoots;
    public static Item YumiHelmet;
    public static Item YumiChest;
    public static Item YumiLegs;
    public static Item YumiBoots;
    public static Item WilliamHelmet;
    public static Item WilliamChest;
    public static Item WilliamLegs;
    public static Item WilliamBoots;
    public static Item DataFragment;
    public static Item Uranium;
    public static Item UraniumCell;
    public static Item DepletedUraniumCell;
    public static Item Overboard;
    public static Item Skid;
    public static Item QuantumMatrix;
    public static Item QuantumContainmentCell;
    
    public static void init()
    {
        Katana = new ItemLyokoSword(ItemIds.WEAPON_LYOKO_1, toolLYOKO).setUnlocalizedName("Katana");
        Zweihander = new ItemLyokoSword(ItemIds.WEAPON_LYOKO_2, toolLYOKO).setUnlocalizedName("Zweihander");
        Fan = new ItemFan(ItemIds.WEAPON_LYOKO_3).setUnlocalizedName("Fan");
        EnergyField = new ItemEnergyField(ItemIds.WEAPON_LYOKO_4).setUnlocalizedName("EnergyField");
        Glove = new ItemGlove(ItemIds.WEAPON_LYOKO_5).setUnlocalizedName("Glove");
        LaserArrow = new ItemLyoko(ItemIds.WEAPON_LYOKO_6).setUnlocalizedName("LaserArrow");
        QuantumOrb = new ItemLyoko(ItemIds.ITEM_LYOKO_11).setUnlocalizedName("LyokoIngot");
        Lead = new ItemLyoko(ItemIds.ITEM_LYOKO_12).setUnlocalizedName("Lead210");
        EmptyCell = new ItemLyoko(ItemIds.ITEM_LYOKO_13).setUnlocalizedName("Cell");
        LeadCell = new ItemLyokoFuel(ItemIds.ITEM_LYOKO_14, 1250, DepletedLeadCell).setUnlocalizedName("Lead210Cell");
        DepletedLeadCell = new ItemLyoko(ItemIds.ITEM_LYOKO_15).setUnlocalizedName("DepletedLead210Cell");
        AelitaHelmet = new ArmorLyoko(ItemIds.AELITA_ARMOR_HELMET, armorLYOKO, 5, 0, "aelita").setUnlocalizedName("AelitaHelmet");
        AelitaChest = new ArmorLyoko(ItemIds.AELITA_ARMOR_CHEST, armorLYOKO, 5, 1, "aelita").setUnlocalizedName("AelitaChest");
        AelitaLegs = new ArmorLyoko(ItemIds.AELITA_ARMOR_PANTS, armorLYOKO, 5, 2, "aelita").setUnlocalizedName("AelitaPants");
        AelitaBoots = new ArmorLyoko(ItemIds.AELITA_ARMOR_BOOTS, armorLYOKO, 5, 3, "aelita").setUnlocalizedName("AelitaBoots");
        OddHelmet = new ArmorLyoko(ItemIds.ODD_ARMOR_HELMET, armorLYOKO, 6, 0, "odd").setUnlocalizedName("OddHelmet");
        OddChest = new ArmorLyoko(ItemIds.ODD_ARMOR_CHEST, armorLYOKO, 6, 1, "odd").setUnlocalizedName("OddChest");
        OddLegs = new ArmorLyoko(ItemIds.ODD_ARMOR_PANTS, armorLYOKO, 6, 2, "odd").setUnlocalizedName("OddPants");
        OddBoots = new ArmorLyoko(ItemIds.ODD_ARMOR_BOOTS, armorLYOKO, 6, 3, "odd").setUnlocalizedName("OddBoots");
        UlrichHelmet = new ArmorLyoko(ItemIds.ULRICH_ARMOR_HELMET, armorLYOKO, 7, 0, "ulrich").setUnlocalizedName("UlrichHelmet");
        UlrichChest = new ArmorLyoko(ItemIds.ULRICH_ARMOR_CHEST, armorLYOKO, 7, 1, "ulrich").setUnlocalizedName("UlrichChest");
        UlrichLegs = new ArmorLyoko(ItemIds.ULRICH_ARMOR_PANTS, armorLYOKO, 7, 2, "ulrich").setUnlocalizedName("UlrichPants");
        UlrichBoots = new ArmorLyoko(ItemIds.ULRICH_ARMOR_BOOTS, armorLYOKO, 7, 3, "ulrich").setUnlocalizedName("UlrichBoots");
        YumiHelmet = new ArmorLyoko(ItemIds.YUMI_ARMOR_HELMET, armorLYOKO, 8, 0, "yumi").setUnlocalizedName("YumiHelmet");
        YumiChest = new ArmorLyoko(ItemIds.YUMI_ARMOR_CHEST, armorLYOKO, 8, 1, "yumi").setUnlocalizedName("YumiChest");
        YumiLegs = new ArmorLyoko(ItemIds.YUMI_ARMOR_PANTS, armorLYOKO, 8, 2, "yumi").setUnlocalizedName("YumiPants");
        YumiBoots = new ArmorLyoko(ItemIds.YUMI_ARMOR_BOOTS, armorLYOKO, 8, 3, "yumi").setUnlocalizedName("YumiBoots");
        WilliamHelmet = new ArmorLyoko(ItemIds.WILLIAM_ARMOR_HELMET, armorLYOKO, 9, 0, "william").setUnlocalizedName("WilliamHelmet");
        WilliamChest = new ArmorLyoko(ItemIds.WILLIAM_ARMOR_CHEST, armorLYOKO, 9, 1, "william").setUnlocalizedName("WilliamChest");
        WilliamLegs = new ArmorLyoko(ItemIds.WILLIAM_ARMOR_PANTS, armorLYOKO, 9, 2, "william").setUnlocalizedName("WilliamPants");
        WilliamBoots = new ArmorLyoko(ItemIds.WILLIAM_ARMOR_BOOTS, armorLYOKO, 9, 3, "william").setUnlocalizedName("WilliamBoots");
        DataFragment = new ItemDataFragment(ItemIds.DATA_FRAGMENT).setUnlocalizedName("DataFragment");
        Uranium = new ItemLyoko(ItemIds.ITEM_LYOKO_URANIUM).setUnlocalizedName("Uranium");
        UraniumCell = new ItemLyokoFuel(ItemIds.ITEM_LYOKO_URANIUM_CELL, 5000, DepletedUraniumCell).setUnlocalizedName("UraniumCell");
        DepletedUraniumCell = new ItemLyoko(ItemIds.ITEM_LYOKO_DEPLETED_URANIUM).setUnlocalizedName("DepletedUraniumCell");
        Overboard = new ItemOverboard(ItemIds.ITEM_OVERBOARD).setUnlocalizedName("Overboard");
        Skid = new ItemSkid(ItemIds.ITEM_SKID).setUnlocalizedName("Skidbladnir");
        QuantumMatrix = new ItemLyoko(ItemIds.ITEM_QUANTUM_MATRIX).setUnlocalizedName("QuantumMatrix");
        QuantumContainmentCell = new ItemLyoko(ItemIds.ITEM_QUANTUM_CONTAINMENT_CELL).setUnlocalizedName("QuantumContainmentCell");
        
        registerRecipes();
    }
    
    private static void registerRecipes() {
        
    }
    
}
