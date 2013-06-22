package matt.lyoko.render.tileentity;

import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.model.tileentity.ModelScanner;
import matt.lyoko.model.tileentity.ModelScannerBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScanner extends TileEntitySpecialRenderer {

    private ModelScanner modelFull = new ModelScanner();
    private ModelScannerBlock modelBlock = new ModelScannerBlock();
    
    /**
     * Renders the full model for when it is a multiblock.
     * 
     * @param entity
     * @param x
     * @param y
     * @param z
     * @param tick
     */
    public void renderFull(TileEntityScanner entity, double x, double y, double z, float tick) {
        int i;
        if (!entity.func_70309_m()) {
            i = 0;
        } else {
            i = entity.getBlockMetadata();
        }

        this.bindTextureByName("/mods/lyoko/textures/models/scanner.png");
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

        modelFull.doorL.rotateAngleY = (float)Math.toRadians(entity.doorRotationYaw);
        modelFull.doorL.rotationPointX = entity.doorPosX;
        modelFull.doorL.rotationPointZ = entity.doorPosZ;
        
        modelFull.doorR.rotateAngleY = -(float)Math.toRadians((entity.doorRotationYaw + 180));
        modelFull.doorR.rotationPointX = -entity.doorPosX;
        modelFull.doorR.rotationPointZ = entity.doorPosZ;
        
        modelFull.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    /**
     * Renders the model of a single scanner block for when it is not a multiblock.
     * 
     * @param entity
     * @param x
     * @param y
     * @param z
     * @param tick
     */
    public void renderBlock(TileEntityScanner entity, double x, double y, double z, float tick) {
        int i;
        if (!entity.func_70309_m()) {
            i = 0;
        } else {
            i = entity.getBlockMetadata();
        }
        
        this.bindTextureByName("/mods/lyoko/textures/models/scanner.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
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

        modelBlock.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
    
    
    /**
     * Chooses which model to render based on the isMultiblock field in TileEntityScanner.
     */
    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float tick) {
        if(!(entity instanceof TileEntityScanner)) return;
        TileEntityScanner scanner = (TileEntityScanner) entity;
        
        if(scanner.isMultiblock && scanner.isCoreBlock) this.renderFull(scanner, x, y, z, tick);
        else if(scanner.isMultiblock && !scanner.isCoreBlock) return;
        else this.renderBlock(scanner, x, y, z, tick);
    }
}