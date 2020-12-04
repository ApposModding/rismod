// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelrocketmodel extends EntityModel<Entity> {
	private final ModelRenderer bb_main;

	public Modelrocketmodel() {
		textureWidth = 16;
		textureHeight = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 0).addBox(-11.0F, -62.0F, -9.0F, 20.0F, 50.0F, 20.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-11.0F, -111.0F, -9.0F, 20.0F, 50.0F, 20.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-11.0F, -160.0F, -9.0F, 20.0F, 50.0F, 20.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-2.0F, -259.0F, 0.0F, 2.0F, 100.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-32.0F, -92.0F, -1.0F, 21.0F, 76.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-26.0F, -115.0F, -1.0F, 15.0F, 23.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(9.0F, -92.0F, -1.0F, 21.0F, 76.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(9.0F, -115.0F, -1.0F, 15.0F, 23.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-3.0F, -92.0F, 11.0F, 2.0F, 76.0F, 21.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-3.0F, -115.0F, 11.0F, 2.0F, 23.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-3.0F, -115.0F, -24.0F, 2.0F, 23.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-3.0F, -92.0F, -30.0F, 2.0F, 76.0F, 21.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-2.0F, -264.0F, -1.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
		bb_main.setTextureOffset(0, 0).addBox(-9.0F, -14.0F, -6.0F, 15.0F, 10.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-14.0F, -5.0F, -11.0F, 25.0F, 10.0F, 25.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-18.0F, 4.0F, -16.0F, 35.0F, 10.0F, 35.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(4.0F, -15.0F, 6.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(15.0F, -3.0F, 17.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(26.0F, 9.0F, 27.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-22.0F, -15.0F, 6.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-22.0F, -15.0F, -21.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(6.0F, -15.0F, -21.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(15.0F, -3.0F, -33.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-33.0F, -3.0F, -33.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-33.0F, -3.0F, 18.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(26.0F, 9.0F, -45.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-45.0F, 9.0F, -45.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-45.0F, 9.0F, 29.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}