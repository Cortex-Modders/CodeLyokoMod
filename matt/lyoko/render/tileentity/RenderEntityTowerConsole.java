package matt.lyoko.render.tileentity;

import matt.lyoko.client.ClientProxy;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import matt.lyoko.model.tileentity.ModelConsole;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityTowerConsole extends TileEntitySpecialRenderer {

    //TODO: Render GUI Text on the console.
    //TODO: Custom font for GUI and terminal, maybe?
    private ClientProxy proxy;
    private ModelConsole model = new ModelConsole();
    private static final ResourceLocation texture = new ResourceLocation("/mods/lyoko/textures/models/ModelConsole.png");
    
    
    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void render(TileEntityTowerConsole entity, double x, double y, double z, float scale) {
        int i;

        if (!entity.func_70309_m()) {
            i = 0;
        } else {
            i = entity.getBlockMetadata();
        }
        
        this.func_110628_a(texture);
        GL11.glPushMatrix();
        proxy.alphaOn();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        
        short rotate = 0;

        if (i == 0) {
            rotate = 180;
        }

        if (i == 2) {
            rotate = 0;
        }

        if (i == 3) {
            rotate = -90;
        }

        if (i == 1) {
            rotate = 90;
        }

        GL11.glRotatef((float)rotate, 0F, 1F, 0F);

        model.render(entity, (float)x, (float)y, (float)z, 0.0F, 0.0F, 0.0625F);
        proxy.alphaOff();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.render((TileEntityTowerConsole) par1TileEntity, par2, par4, par6, par8);
    }
}