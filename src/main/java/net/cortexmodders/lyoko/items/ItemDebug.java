package net.cortexmodders.lyoko.items;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.blocks.BlockMarabounta;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * Created by jacob on 6/6/14.
 */
public class ItemDebug extends Item
{

    private Mode mode;

    private EntityPlayer player;


    public ItemDebug()
    {
        this.maxStackSize = 1;
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.mode = Mode.GEN_FORREST_TREE;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                this.cycleMode(player);
                player.addChatComponentMessage(new ChatComponentText("Cycling to debug mode: " + this.mode.name));
            } else {
                this.player = player;
                try {
                    this.mode.method.invoke(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {


        return true;
    }

    public void cycleMode(EntityPlayer player)
    {
        if (this.mode.ordinal() < Mode.values().length - 1) {
            this.mode = Mode.values()[this.mode.ordinal() + 1];
        } else {
            this.mode = Mode.values()[0];
        }
    }

    public void genForrestTree()
    {
        System.out.println("Generate forrest tree!");
        Vec3 pos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
        CodeLyoko.instance.forrestTreeGen.generateTree(new Random(), (int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord, this.player.getEntityWorld());
    }

    public void destroyMarabounta()
    {
        System.out.println("Destroy Marabounta");
        Vec3 pos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
        int x = (int) pos.xCoord;
        int y = (int) pos.yCoord;
        int z = (int) pos.zCoord;
        if (player.getEntityWorld().getBlock(x, y, z) == ModBlocks.marabounta)
            ((BlockMarabounta) player.getEntityWorld().getBlock(x, y, z)).chainDestroyMarabounta(player.getEntityWorld(), x, y, z);
    }


    private static enum Mode
    {
        GEN_FORREST_TREE("Generate forrest tree", "genForrestTree"),
        DESTROY_MARABOUNTA("Destroy Marabounta", "destroyMarabounta");

        String name;
        Method method;

        private Mode(String name, String method)
        {
            this.name = name;

            try {
                this.method = ItemDebug.class.getMethod(method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

    }
}
