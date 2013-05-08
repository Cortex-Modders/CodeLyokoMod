package matt.lyoko.render.tileentity;

import matt.lyoko.entities.tileentity.TileEntityTower;
import matt.lyoko.render.TileAnimator;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTower extends TileEntitySpecialRenderer {
    
    private final TileAnimator animator;
    
    public RenderTower() {
        animator = new TileAnimator(1.0F, 1.0F, 1, 8, 0.1F);
    }
    
    int index = 0;
    float speed = 0.5F;
    int frames = 8;
    
    float yPos = 0;
    
    /**
     * Renders the TileEntity for the Tower at a position.
     */
    public void render(TileEntityTower entity, double x, double y, double z, float scale) {
        Tessellator tessellator = Tessellator.instance;

        this.bindTextureByName("/mods/lyoko/textures/blocks/towerwall.png");
 /*       
        // if the currentFrame is greater than the total frames/speed (if speed is <0 then frames is basically multiplied by 4(0.25), 2(0.5) to make it last longer.
        if(index >= frames/speed) {
            index = 0;
            yPos = 0;
        }
        
        float f = (float) Math.ceil(index*speed);
        float f1 = 1.0F / frames;
        
        float pXTop = 0;
        float pYTop = f*f1;
        
        float pXBot = 1.0F;
        float pYBot = pYTop+f1;
/*
        int i;
        if (!entity.func_70309_m()) {
            i = 0;
        } else {
            i = entity.getBlockMetadata();
        }
*/
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
        GL11.glTranslatef((float)x, (float)y, (float)z-0.20F);
        
        tessellator.startDrawingQuads();
        
        // TOP RIGHT
        float[] topRight = this.animator.getTopRight();
        tessellator.addVertexWithUV(1.0, 0.0, 0.0, topRight[0], topRight[1]);
        // TOP LEFT
        float[] topLeft = this.animator.getTopLeft();
        tessellator.addVertexWithUV(0.0, 0.0, 0.0, topLeft[0], topLeft[1]);
        
        // BOTTOM LEFT
        float[] botLeft = this.animator.getBottomLeft();
        tessellator.addVertexWithUV(0.0, 1.0, 0.0, botLeft[0], botLeft[1]);

        // BOTTOM RIGHT
        float[] botRight = this.animator.getBottomRight();
        tessellator.addVertexWithUV(1.0, 1.0, 0.0, botRight[0], botRight[1]);
        
        tessellator.draw();
        
        GL11.glPopMatrix();
        
//        index++;
        this.animator.animate();
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.render((TileEntityTower) par1TileEntity, par2, par4, par6, par8);
    }
}