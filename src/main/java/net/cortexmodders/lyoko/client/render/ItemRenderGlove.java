/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.render;

import net.cortexmodders.lyoko.client.model.ModelGlove;
import net.cortexmodders.lyoko.items.ItemGlove;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRenderGlove implements IItemRenderer
{
    protected ModelGlove gloveModel;

    private static final ResourceLocation texture = new ResourceLocation("lyoko:textures/models/ModelGlove.png");

    public ItemRenderGlove()
    {
        this.gloveModel = new ModelGlove();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type) {
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            default:
                return false;
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
        switch (type) {
            case EQUIPPED:
                this.renderEquipped(false, data);
                break;
            case EQUIPPED_FIRST_PERSON:
                this.renderEquipped(true, data);
                break;
            default:
                break;
        }
    }

    private void renderEquipped(boolean firstPerson, Object... data)
    {
        GL11.glPushMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glRotatef(-55f, 0f, 0f, 1f);
        GL11.glRotatef(90f, 0f, 1f, 0f);
        GL11.glRotatef(90f, 1f, 0f, 0f);
        GL11.glRotatef(5f, 0f, 1f, 0f);

        float scale = 1.0F;
        if (data[1] != null && data[1] instanceof EntityPlayer) {
            if (!firstPerson && !((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
                scale = 1.0F;
            else {
                scale = 2F;
                GL11.glRotatef(15F, 1F, 0F, 0F);
                GL11.glTranslatef(0.3F, -0.5F, 0F);
            }

            EntityPlayer player = (EntityPlayer) data[1];
            if (player.isUsingItem() && player.getItemInUse() != null && player.getItemInUse().getItem() instanceof ItemGlove)
                this.gloveModel.clenchHand();
            else
                this.gloveModel.unclenchHand();
        }

        GL11.glTranslatef(0.079f, 0.078f, -0.9f);
        GL11.glScalef(scale, scale, scale);
        this.gloveModel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.03125F, firstPerson);

        GL11.glPopMatrix();
    }
}
