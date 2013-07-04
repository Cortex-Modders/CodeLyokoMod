package matt.lyoko.render.tileentity;

import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.model.tileentity.ModelScanner;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScanner extends TileEntitySpecialRenderer {

    private ModelScanner model = new ModelScanner();
    private static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/scanner.png");
    
    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void render(TileEntityScanner entity, double x, double y, double z, float tick) {
        int i;
        System.out.println(tick);
        if (!entity.func_70309_m()) {
            i = 0;
        } else {
            i = entity.getBlockMetadata();
        }

        this.func_110628_a(texture);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0.5F, 0F, 0.5F);
        // Use this or else model renders upside-down. o.O
        GL11.glRotatef(180, 0F, 0F, 1F);

        short rotate = 0;

        if (i == 0) {
            rotate = 0;
        }

        if (i == 1) {
            rotate = 90;
        }
        
        if (i == 2) {
            rotate = 180;
        }

        if (i == 3) {
            rotate = -90;
        }

        GL11.glRotatef(rotate, 0F, 1F, 0F);

        model.doorL.rotateAngleY = (float)Math.toRadians(entity.doorRotationYaw);
        model.doorL.rotationPointX = entity.doorPosX;
        model.doorL.rotationPointZ = entity.doorPosZ;
        
        model.doorR.rotateAngleY = -(float)Math.toRadians((entity.doorRotationYaw + 180));
        model.doorR.rotationPointX = -entity.doorPosX;
        model.doorR.rotationPointZ = entity.doorPosZ;
        
        model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double par2, double par4, double par6, float par8) {
        this.render((TileEntityScanner) entity, par2, par4, par6, par8);
    }
}