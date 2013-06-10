package matt.lyoko.render.tileentity;

import matt.lyoko.entities.tileentity.TileEntityScanner;
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
    public void render(TileEntityScanner entity, double x, double y, double z, float tick) {
        int i;
        System.out.println(tick);
        if (!entity.func_70309_m()) {
            i = 0;
        } else {
            i = entity.getBlockMetadata();
        }

        // /mods/lyoko/textures/models/scanner.png
        this.bindTextureByName("/mods/lyoko/textures/models/scanner.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0.5F, 0F, 0.5F);
        // Use this or else model renders upside-down. o.O
        GL11.glRotatef(180, 0F, 0F, 1F);

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

        model.doorL.rotateAngleY = (float)Math.toRadians(entity.doorRotationYaw);
        model.doorL.rotationPointX = entity.doorPosX;
        model.doorL.rotationPointZ = entity.doorPosZ;
        
        model.doorR.rotateAngleY = -(float)Math.toRadians((entity.doorRotationYaw + 180));
        model.doorR.rotationPointX = -entity.doorPosX;
        model.doorR.rotationPointZ = entity.doorPosZ;
        
        model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    /**
     * Returns a rotation angle that is inbetween two other rotation angles. par1 and par2 are the angles between which
     * to interpolate, par3 is probably a float between 0.0 and 1.0 that tells us where "between" the two angles we are.
     * Example: par1 = 30, par2 = 50, par3 = 0.5, then return = 40
     */
    private float interpolateRotation(float startAngle, float endAngle, float currentAngle)
    {
        // Note: stolen from RenderLiving.
        float f3;

        for (f3 = endAngle - startAngle; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return startAngle + currentAngle * f3;
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double par2, double par4, double par6, float par8) {
        this.render((TileEntityScanner) entity, par2, par4, par6, par8);
    }
}