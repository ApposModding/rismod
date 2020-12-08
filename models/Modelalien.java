// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelalien extends EntityModel<Entity> {
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;

	public Modelalien() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 0).addBox(-5.0F, -23.0F, -2.0F, 11.0F, 14.0F, 6.0F, 0.0F, false);
		bb_main.setTextureOffset(37, 14).addBox(6.0F, -23.0F, -2.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bb_main.setTextureOffset(37, 37).addBox(-7.0F, -23.0F, -2.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bb_main.setTextureOffset(34, 0).addBox(8.0F, -20.0F, -2.0F, 2.0F, 11.0F, 3.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 20).addBox(-4.0F, -34.0F, 1.0F, 10.0F, 10.0F, 3.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 3).addBox(-2.0F, -32.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(3.0F, -32.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(26, 20).addBox(-1.0F, -24.0F, 1.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(5.0F, -34.0F, 1.0F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.2182F, 0.3927F, 0.0F);
		cube_r1.setTextureOffset(24, 40).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-3.0F, -34.0F, 1.0F);
		bb_main.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.2182F, -0.3927F, 0.0F);
		cube_r2.setTextureOffset(30, 40).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-8.0F, -16.0F, 0.0F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -0.1745F, 0.0F);
		cube_r3.setTextureOffset(14, 33).addBox(-1.0F, -4.0F, -2.0F, 2.0F, 11.0F, 3.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(2.0F, 0.0F, -1.0F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -0.0873F, 0.0F);
		cube_r4.setTextureOffset(26, 26).addBox(-1.0F, -11.0F, 0.0F, 4.0F, 11.0F, 3.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-3.0F, 0.0F, -1.0F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0873F, 0.0F);
		cube_r5.setTextureOffset(0, 33).addBox(-1.0F, -11.0F, 0.0F, 4.0F, 11.0F, 3.0F, 0.0F, false);
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