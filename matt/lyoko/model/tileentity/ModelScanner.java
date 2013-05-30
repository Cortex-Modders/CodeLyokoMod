package matt.lyoko.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class ModelScanner extends ModelBase {
    // fields
    ModelRenderer base;
    ModelRenderer baseL;
    ModelRenderer baseB;
    ModelRenderer baseF;
    ModelRenderer baseR;
    ModelRenderer sideLcornerB;
    ModelRenderer sideRcornerB;
    ModelRenderer sideLcornerF;
    ModelRenderer sideRcornerF;
    ModelRenderer sideB;
    ModelRenderer sideL;
    ModelRenderer sideR;
    ModelRenderer doorL;
    ModelRenderer doorR;
    ModelRenderer top;
    ModelRenderer topL;
    ModelRenderer topF;
    ModelRenderer topB;
    ModelRenderer topR;

    public ModelScanner() {
        textureWidth = 512;
        textureHeight = 512;

        base = new ModelRenderer(this, 0, 0);
        base.addBox(-10F, -8F, -10F, 20, 8, 20);
        base.setRotationPoint(0F, 0F, 0F);
        base.setTextureSize(512, 512);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        baseL = new ModelRenderer(this, 0, 0);
        baseL.addBox(-2F, -4F, -8F, 2, 8, 16);
        baseL.setRotationPoint(-10F, -4F, 0F);
        baseL.setTextureSize(512, 512);
        baseL.mirror = true;
        setRotation(baseL, 0F, 0F, 0F);
        baseB = new ModelRenderer(this, 0, 0);
        baseB.addBox(-8F, -4F, 0F, 16, 8, 2);
        baseB.setRotationPoint(0F, -4F, 10F);
        baseB.setTextureSize(512, 512);
        baseB.mirror = true;
        setRotation(baseB, 0F, 0F, 0F);
        baseF = new ModelRenderer(this, 0, 0);
        baseF.addBox(-8F, -4F, -2F, 16, 8, 2);
        baseF.setRotationPoint(0F, -4F, -10F);
        baseF.setTextureSize(512, 512);
        baseF.mirror = true;
        setRotation(baseF, 0F, 0F, 0F);
        baseR = new ModelRenderer(this, 0, 0);
        baseR.addBox(0F, -4F, -8F, 2, 8, 16);
        baseR.setRotationPoint(10F, -4F, 0F);
        baseR.setTextureSize(512, 512);
        baseR.mirror = true;
        setRotation(baseR, 0F, 0F, 0F);
        sideLcornerB = new ModelRenderer(this, 0, 0);
        sideLcornerB.addBox(-1F, -40F, -1F, 2, 80, 2);
        sideLcornerB.setRotationPoint(-9F, -48F, 9F);
        sideLcornerB.setTextureSize(512, 512);
        sideLcornerB.mirror = true;
        setRotation(sideLcornerB, 0F, 0F, 0F);
        sideRcornerB = new ModelRenderer(this, 0, 0);
        sideRcornerB.addBox(-1F, -40F, -1F, 2, 80, 2);
        sideRcornerB.setRotationPoint(9F, -48F, 9F);
        sideRcornerB.setTextureSize(512, 512);
        sideRcornerB.mirror = true;
        setRotation(sideRcornerB, 0F, 0F, 0F);
        sideLcornerF = new ModelRenderer(this, 0, 0);
        sideLcornerF.addBox(-1F, -40F, -1F, 2, 80, 2);
        sideLcornerF.setRotationPoint(-9F, -48F, -9F);
        sideLcornerF.setTextureSize(512, 512);
        sideLcornerF.mirror = true;
        setRotation(sideLcornerF, 0F, 0F, 0F);
        sideRcornerF = new ModelRenderer(this, 0, 0);
        sideRcornerF.addBox(-1F, -40F, -1F, 2, 80, 2);
        sideRcornerF.setRotationPoint(9F, -48F, -9F);
        sideRcornerF.setTextureSize(512, 512);
        sideRcornerF.mirror = true;
        setRotation(sideRcornerF, 0F, 0F, 0F);
        sideB = new ModelRenderer(this, 0, 0);
        sideB.addBox(-8F, -40F, -1F, 16, 80, 2);
        sideB.setRotationPoint(0F, -48F, 11F);
        sideB.setTextureSize(512, 512);
        sideB.mirror = true;
        setRotation(sideB, 0F, 0F, 0F);
        sideL = new ModelRenderer(this, 0, 0);
        sideL.addBox(-1F, -40F, -8F, 2, 80, 16);
        sideL.setRotationPoint(-11F, -48F, 0F);
        sideL.setTextureSize(512, 512);
        sideL.mirror = true;
        setRotation(sideL, 0F, 0F, 0F);
        sideR = new ModelRenderer(this, 0, 0);
        sideR.addBox(-1F, -40F, -8F, 2, 80, 16);
        sideR.setRotationPoint(11F, -48F, 0F);
        sideR.setTextureSize(512, 512);
        sideR.mirror = true;
        setRotation(sideR, 0F, 0F, 0F);
        doorL = new ModelRenderer(this, 0, 0);
        doorL.addBox(-1F, -40F, -8F, 2, 80, 8);
        doorL.setRotationPoint(-9F, -48F, 0F);
        doorL.setTextureSize(512, 512);
        doorL.mirror = true;
        setRotation(doorL, 0F, 0F, 0F);
        doorR = new ModelRenderer(this, 0, 0);
        doorR.addBox(-1F, -40F, -8F, 2, 80, 8);
        doorR.setRotationPoint(9F, -48F, 0F);
        doorR.setTextureSize(512, 512);
        doorR.mirror = true;
        setRotation(doorR, 0F, 0F, 0F);
        top = new ModelRenderer(this, 0, 0);
        top.addBox(-10F, -8F, -10F, 20, 8, 20);
        top.setRotationPoint(0F, -88F, 0F);
        top.setTextureSize(512, 512);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        topL = new ModelRenderer(this, 0, 0);
        topL.addBox(-2F, -4F, -8F, 2, 8, 16);
        topL.setRotationPoint(-10F, -92F, 0F);
        topL.setTextureSize(512, 512);
        topL.mirror = true;
        setRotation(topL, 0F, 0F, 0F);
        topF = new ModelRenderer(this, 0, 0);
        topF.addBox(-8F, -4F, -2F, 16, 8, 2);
        topF.setRotationPoint(0F, -92F, -10F);
        topF.setTextureSize(512, 512);
        topF.mirror = true;
        setRotation(topF, 0F, 0F, 0F);
        topB = new ModelRenderer(this, 0, 0);
        topB.addBox(-8F, -4F, 0F, 16, 8, 2);
        topB.setRotationPoint(0F, -92F, 10F);
        topB.setTextureSize(512, 512);
        topB.mirror = true;
        setRotation(topB, 0F, 0F, 0F);
        topR = new ModelRenderer(this, 0, 0);
        topR.addBox(0F, -4F, -8F, 2, 8, 16);
        topR.setRotationPoint(10F, -92F, 0F);
        topR.setTextureSize(512, 512);
        topR.mirror = true;
        setRotation(topR, 0F, 0F, 0F);
    }

    public void render(TileEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        base.render(f5);
        baseL.render(f5);
        baseB.render(f5);
        baseF.render(f5);
        baseR.render(f5);
        sideLcornerB.render(f5);
        sideRcornerB.render(f5);
        sideLcornerF.render(f5);
        sideRcornerF.render(f5);
        sideB.render(f5);
        sideL.render(f5);
        sideR.render(f5);
        doorL.render(f5);
        doorR.render(f5);
        top.render(f5);
        topL.render(f5);
        topF.render(f5);
        topB.render(f5);
        topR.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, TileEntity entity) {
    }

}
