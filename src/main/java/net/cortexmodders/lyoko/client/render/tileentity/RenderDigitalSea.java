package net.cortexmodders.lyoko.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author jadar
 */
public class RenderDigitalSea extends TileEntitySpecialRenderer
{

    protected static ResourceLocation vertexShaderLocation = new ResourceLocation("lyoko", "shaders/BlockDigitalSeaLiquidVertex.glsl");
    protected static ResourceLocation fragmentShaderLocation = new ResourceLocation("lyoko", "shaders/BlockDigitalSeaLiquidFragment.glsl");

    protected static ResourceLocation texture1 = new ResourceLocation("lyoko", "textures/digitalsea.png");
    protected static ResourceLocation texture2 = new ResourceLocation("lyoko", "textures/digitalsea2.png");

    protected boolean useShader;
    private int programId;

    public RenderDigitalSea()
    {
        int vertShader = 0, fragShader = 0;

        try {
            vertShader = createShader(vertexShaderLocation, ARBVertexShader.GL_VERTEX_SHADER_ARB);
            fragShader = createShader(fragmentShaderLocation, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
        } catch (Exception exc) {
            exc.printStackTrace();
            return;
        }
//        finally {
//            if(vertShader == 0 || fragShader == 0)
//                return;
//        }

        this.programId = ARBShaderObjects.glCreateProgramObjectARB();
        if (this.programId == 0) {
            return;
        }

        ARBShaderObjects.glAttachObjectARB(programId, vertShader);
        ARBShaderObjects.glAttachObjectARB(programId, fragShader);

        ARBShaderObjects.glLinkProgramARB(programId);
        if (ARBShaderObjects.glGetObjectParameteriARB(programId, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println("Houston we have a problem!");
            return;
        }

        ARBShaderObjects.glValidateProgramARB(programId);
        if (ARBShaderObjects.glGetObjectParameteriARB(programId, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println("Houston we have a problem!");
            return;
        }

        useShader = true;
    }

    protected static int createShader(ResourceLocation location, int shaderType) throws Exception
    {
        int shader = 0;
        try {
            shader = GL20.glCreateShader(shaderType);
            if (shader == 0) {
                return 0;
            }

            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
            if (is == null) {
                return 0;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (is.available() > 0) {
                baos.write(is.read());
            }

            ARBShaderObjects.glShaderSourceARB(shader, new String(baos.toByteArray()));
            ARBShaderObjects.glCompileShaderARB(shader);

            if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
                throw new RuntimeException("Error creating shader!");
            }

        } catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            throw e;
        }

        return shader;
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double renderX, double renderY, double renderZ, float partialTick)
    {
        if (useShader) {
            ARBShaderObjects.glUseProgramObjectARB(this.programId);
            int loc = GL20.glGetUniformLocation(programId, "");

        }

        int vertexAttrib = GL20.glGetAttribLocation(this.programId, "vertex");
        int viewposUniform = GL20.glGetUniformLocation(this.programId, "viewpos");
        int lightposUniform = GL20.glGetUniformLocation(this.programId, "lightpos");

        GL11.glPushMatrix();
        {
            GL11.glTranslated(renderX, renderY + 0.5, renderZ);
            GL11.glColor3f(0.0f, 0.0f, 1.0f);

            GL11.glBegin(GL11.GL_QUADS);

            GL20.glBindAttribLocation(this.programId, 0, "vertexLoc");
            GL11.glVertex3f(-1.0f, 0.0f, 1.0f);

            GL20.glBindAttribLocation(this.programId, 0, "vertexLoc");
            GL11.glVertex3f(1.0f, 0.0f, 1.0f);

            GL20.glBindAttribLocation(this.programId, 0, "vertexLoc");
            GL11.glVertex3f(1.0f, 0.0f, -1.0f);

            GL20.glBindAttribLocation(this.programId, 0, "vertexLoc");
            GL11.glVertex3f(-1.0f, 0.0f, -1.0f);
            GL11.glEnd();
        }
        GL11.glPopMatrix();

        if (useShader) {
            ARBShaderObjects.glUseProgramObjectARB(0);
        }
    }
}
