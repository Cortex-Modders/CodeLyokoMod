package matt.lyoko.render.tileentity;

import matt.lyoko.client.ClientProxy;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.lib.ModProperties;
import matt.lyoko.model.tileentity.ModelScanner;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScanner extends TileEntitySpecialRenderer {

    private ModelScanner model = new ModelScanner();

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void render(TileEntityScanner entity, double x, double y, double z, float scale) {
        int i;

        if (!entity.func_70309_m()) {
            i = 0;
        } else {
            i = entity.getBlockMetadata();
        }

        // /mods/lyoko/textures/models/scanner.png
        this.bindTextureByName("/mods/lyoko/textures/models/scanner.png");
        GL11.glPushMatrix();
        // proxy.alphaOn();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0.5F, 5F, 0.5F);

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

        GL11.glRotatef(rotate, 0F, 1F, 0F);

        model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        // proxy.alphaOff();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double par2, double par4, double par6, float par8) {
        this.render((TileEntityScanner) entity, par2, par4, par6, par8);
    }
}