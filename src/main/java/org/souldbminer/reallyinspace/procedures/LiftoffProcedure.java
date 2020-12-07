package org.souldbminer.reallyinspace.procedures;

import org.souldbminer.reallyinspace.RisModElements;
import org.souldbminer.reallyinspace.RisMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.FunctionObject;

import java.util.Optional;
import java.util.Map;
import java.util.Collections;

@RisModElements.ModElement.Tag
public class LiftoffProcedure extends RisModElements.ModElement {
	public LiftoffProcedure(RisModElements instance) {
		super(instance, 58);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Liftoff!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure Liftoff!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure Liftoff!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure Liftoff!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		boolean funcran = false;
		RisMod.LOGGER.info("Lifting Off rocket");
		for (int index0 = 0; index0 < (int) (1000); index0++) {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(x, (y + 1), z);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (y + 1), z, _ent.rotationYaw, _ent.rotationPitch,
							Collections.emptySet());
				}
			}
		}
		RisMod.LOGGER.info("Liftoff finished sucsessfuly, TPing into Space");
		if (!entity.world.isRemote && entity.world.getServer() != null) {
			Optional<FunctionObject> _fopt = entity.world.getServer().getFunctionManager().get(new ResourceLocation("ris:ric"));
			if (_fopt.isPresent()) {
				FunctionObject _fobj = _fopt.get();
				entity.world.getServer().getFunctionManager().execute(_fobj, entity.getCommandSource());
			}
		}
		RisMod.LOGGER.info("Opened Pannel");
	}
}
