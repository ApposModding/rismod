
package org.souldbminer.reallyinspace.entity;

import org.souldbminer.reallyinspace.procedures.LiftoffProcedure;
import org.souldbminer.reallyinspace.itemgroup.RISItemGroup;
import org.souldbminer.reallyinspace.RisModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@RisModElements.ModElement.Tag
public class RocketEntity extends RisModElements.ModElement {
	public static EntityType entity = null;
	public RocketEntity(RisModElements instance) {
		super(instance, 20);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f)).build("rocket")
						.setRegistryName("rocket");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16777216, -11513776, new Item.Properties().group(RISItemGroup.tab))
				.setRegistryName("rocket_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelrocketmodel(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("ris:textures/rocketmodel.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof ArrowEntity)
				return false;
			if (source.getImmediateSource() instanceof PlayerEntity)
				return false;
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			super.processInteract(sourceentity, hand);
			sourceentity.startRiding(this);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			return retval;
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				LiftoffProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.3);
		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					float forward = ((LivingEntity) entity).moveForward;
					float strafe = ((LivingEntity) entity).moveStrafing;
					super.travel(new Vec3d(strafe, 0, forward));
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.getPosX() - this.prevPosX;
				double d0 = this.getPosZ() - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(dir);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

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
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
