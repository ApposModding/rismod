package org.souldbminer.reallyinspace.procedures;

import org.souldbminer.reallyinspace.entity.Tier2rocketEntity;
import org.souldbminer.reallyinspace.entity.RocketEntity;
import org.souldbminer.reallyinspace.block.NASAStatoionBlock;
import org.souldbminer.reallyinspace.block.NASABIGStationBlock;
import org.souldbminer.reallyinspace.RisModElements;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;

@RisModElements.ModElement.Tag
public class CraftrocketsProcedure extends RisModElements.ModElement {
	public CraftrocketsProcedure(RisModElements instance) {
		super(instance, 244);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure Craftrockets!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure Craftrockets!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure Craftrockets!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Craftrockets!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = (double) (-3);
		found = (boolean) (false);
		for (int index0 = 0; index0 < (int) (6); index0++) {
			sy = (double) (-3);
			for (int index1 = 0; index1 < (int) (6); index1++) {
				sz = (double) (-3);
				for (int index2 = 0; index2 < (int) (6); index2++) {
					if (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
							.getBlock() == NASAStatoionBlock.block.getDefaultState().getBlock())) {
						found = (boolean) (true);
					}
					sz = (double) ((sz) + 1);
				}
				sy = (double) ((sy) + 1);
			}
			sx = (double) ((sx) + 1);
		}
		if (((found) == (true))) {
			sx = (double) (-3);
			found = (boolean) (false);
			for (int index3 = 0; index3 < (int) (6); index3++) {
				sy = (double) (-3);
				for (int index4 = 0; index4 < (int) (6); index4++) {
					sz = (double) (-3);
					for (int index5 = 0; index5 < (int) (6); index5++) {
						if (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
								.getBlock() == Blocks.DIAMOND_BLOCK.getDefaultState().getBlock())) {
							found = (boolean) (true);
						}
						sz = (double) ((sz) + 1);
					}
					sy = (double) ((sy) + 1);
				}
				sx = (double) ((sx) + 1);
			}
			if (((found) == (true))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new RocketEntity.CustomEntity(RocketEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
			}
		}
		sx = (double) (-3);
		found = (boolean) (false);
		for (int index6 = 0; index6 < (int) (6); index6++) {
			sy = (double) (-3);
			for (int index7 = 0; index7 < (int) (6); index7++) {
				sz = (double) (-3);
				for (int index8 = 0; index8 < (int) (6); index8++) {
					if (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
							.getBlock() == NASABIGStationBlock.block.getDefaultState().getBlock())) {
						found = (boolean) (true);
					}
					sz = (double) ((sz) + 1);
				}
				sy = (double) ((sy) + 1);
			}
			sx = (double) ((sx) + 1);
		}
		if (((found) == (true))) {
			sx = (double) (-3);
			found = (boolean) (false);
			for (int index9 = 0; index9 < (int) (6); index9++) {
				sy = (double) (-3);
				for (int index10 = 0; index10 < (int) (6); index10++) {
					sz = (double) (-3);
					for (int index11 = 0; index11 < (int) (6); index11++) {
						if (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
								.getBlock() == Blocks.EMERALD_BLOCK.getDefaultState().getBlock())) {
							found = (boolean) (true);
						}
						sz = (double) ((sz) + 1);
					}
					sy = (double) ((sy) + 1);
				}
				sx = (double) ((sx) + 1);
			}
			if (((found) == (true))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new Tier2rocketEntity.CustomEntity(Tier2rocketEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, (float) 0, (float) 0);
					entityToSpawn.setRenderYawOffset((float) 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
			}
		}
	}
}
