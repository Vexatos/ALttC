package org.ttp.alttc.client.renderer;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.ttp.alttc.client.model.ModelKeese;
import org.ttp.alttc.common.entity.EntityKeese;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKeese extends RenderLiving
{
	private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");

	/**
	 * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
	 * it seems a good match for a bats size
	 */
	private int renderedBatSize;

	public RenderKeese()
	{
		super(new ModelKeese(), 0.25F);
		this.renderedBatSize = ((ModelKeese)this.mainModel).getBatSize();
	}

	public void func_82443_a(EntityKeese par1EntityKeese, double par2, double par4, double par6, float par8, float par9)
	{
		int i = ((ModelKeese)this.mainModel).getBatSize();

		if (i != this.renderedBatSize)
		{
			this.renderedBatSize = i;
			this.mainModel = new ModelKeese();
		}

		super.doRenderLiving(par1EntityKeese, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation getBatTextures(EntityKeese par1EntityKeese)
	{
		return batTextures;
	}

	protected void func_82442_a(EntityKeese par1EntityKeese, float par2)
	{
		GL11.glScalef(0.35F, 0.35F, 0.35F);
	}

	protected void func_82445_a(EntityKeese par1EntityKeese, double par2, double par4, double par6)
	{
		super.renderLivingAt(par1EntityKeese, par2, par4, par6);
	}

	protected void func_82444_a(EntityKeese par1EntityKeese, float par2, float par3, float par4)
	{
		if (!par1EntityKeese.getIsBatHanging())
		{
			GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
		}
		else
		{
			GL11.glTranslatef(0.0F, -0.1F, 0.0F);
		}

		super.rotateCorpse(par1EntityKeese, par2, par3, par4);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.func_82443_a((EntityKeese)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	 protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	 {
		 this.func_82442_a((EntityKeese)par1EntityLivingBase, par2);
	 }

	 protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	 {
		 this.func_82444_a((EntityKeese)par1EntityLivingBase, par2, par3, par4);
	 }

	 /**
	  * Sets a simple glTranslate on a LivingEntity.
	  */
	 protected void renderLivingAt(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6)
	 {
		 this.func_82445_a((EntityKeese)par1EntityLivingBase, par2, par4, par6);
	 }

	 public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
	 {
		 this.func_82443_a((EntityKeese)par1EntityLivingBase, par2, par4, par6, par8, par9);
	 }

	 /**
	  * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	  */
	 protected ResourceLocation getEntityTexture(Entity par1Entity)
	 {
		 return this.getBatTextures((EntityKeese)par1Entity);
	 }

	 /**
	  * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	  * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	  * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
	  * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	  */
	 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	 {
		 this.func_82443_a((EntityKeese)par1Entity, par2, par4, par6, par8, par9);
	 }
}
