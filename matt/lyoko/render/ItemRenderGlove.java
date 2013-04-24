package matt.lyoko.render;

import matt.lyoko.items.ModItems;
import matt.lyoko.lib.ItemIds;
import matt.lyoko.model.ModelGlove;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRenderGlove implements IItemRenderer
{
    protected ModelGlove gloveModel;

    public ItemRenderGlove()
    {
        gloveModel = new ModelGlove();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch(type)
        {
            case EQUIPPED: return true;
            default: return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case EQUIPPED:
            {
                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lyoko/textures/models/ModelGlove.png");

                boolean isFirstPerson = false;


                GL11.glRotatef(-55f, 0f, 0f, 1f);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glRotatef(90f, 1f, 0f, 0f);
                GL11.glRotatef(5f, 0f, 1f, 0f);

                float scale = 1.0F;
                if(data[1] != null && data[1] instanceof EntityPlayer) {
                    if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F))) {
                        scale = 1.0F;
                    }
                    else {
                        isFirstPerson = true;
                        scale = 1.44F;
                    }
                    
                    EntityPlayer player = (EntityPlayer) data[1];
                    if(player.isUsingItem() && player.getItemInUse() != null 
                            && player.getItemInUse().itemID == ModItems.Glove.itemID) {
                        gloveModel.clenchHand();
                        System.out.println("clenching hand");
                    }
                    else {
                        gloveModel.unclenchHand();
                    }
                }

                GL11.glTranslatef(0.079f, 0.078f, -0.9f);
                GL11.glScalef(scale, scale, scale);
                gloveModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, isFirstPerson);

                GL11.glPopMatrix();
            }
            default:
                break;
        }
    }
}