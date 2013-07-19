package matt.lyoko.render.tileentity;

import matt.lyoko.blocks.BlockScanner;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.model.tileentity.ModelScannerMiddle;
import matt.lyoko.model.tileentity.ModelScannerBottom;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScanner extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    private ModelScannerBottom modelBot = new ModelScannerBottom();
    private ModelScannerMiddle modelMid = new ModelScannerMiddle();
    private static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/scanner.png");
    private final int renderId;
    
    public RenderScanner(int id) {
        renderId = id;
    }
    
    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void doRender(TileEntityScanner entity, double x, double y, double z, float tick) {


        int i = entity.getBlockMetadata();

        //Binds the texture
        this.func_110628_a(texture);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0.5F, 0F, 0.5F);
        // Use this or else model renders upside-down.
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

        // placement in multi block
        int p = BlockScanner.getPositionInMultiBlock(entity.worldObj, entity.xCoord, entity.yCoord, entity.zCoord);

        // if top
        if(p == 5) {
            GL11.glRotatef(180F, 1F, 0F, 0F);
            GL11.glRotatef(180F, 0F, 1F, 0F);
            GL11.glTranslatef(0F, 1F, 0F);
        }
        // if in middle or not at all
        if((p >= 2 & p <= 4) || p == -1) {
            
            modelMid.doorL.rotateAngleY = (float)Math.toRadians(entity.doorRotationYaw);
            modelMid.doorL.rotationPointX = entity.doorPosX;
            modelMid.doorL.rotationPointZ = entity.doorPosZ;

            modelMid.doorR.rotateAngleY = -(float)Math.toRadians((entity.doorRotationYaw + 180));
            modelMid.doorR.rotationPointX = -entity.doorPosX;
            modelMid.doorR.rotationPointZ = entity.doorPosZ;
            
            modelMid.render(null, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        }
        // if bottom or top
        if(p == 1 || p == 5) {

            modelBot.doorL.rotateAngleY = (float)Math.toRadians(entity.doorRotationYaw);
            modelBot.doorL.rotationPointX = entity.doorPosX;
            modelBot.doorL.rotationPointZ = entity.doorPosZ;

            modelBot.doorR.rotateAngleY = -(float)Math.toRadians((entity.doorRotationYaw + 180));
            modelBot.doorR.rotationPointX = -entity.doorPosX;
            modelBot.doorR.rotationPointZ = entity.doorPosZ;

            modelBot.render(null, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        }


        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double par2, double par4, double par6, float par8) {
        this.doRender((TileEntityScanner) entity, par2, par4, par6, par8);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        float scale = 0.875F;
        
        GL11.glPushMatrix();
        //Binds the texture
        this.func_110628_a(texture);
        
        GL11.glRotatef(180, 0F, 1F, 0F);
        GL11.glTranslatef(0F, 0.5F, 0F);
        GL11.glScalef(scale, scale, scale);
        modelMid.render(null, 0F, 0F, 0F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}