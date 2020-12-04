package org.souldbminer.reallyinspace.procedures;

import org.souldbminer.reallyinspace.RisModElements;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.FunctionObject;
import net.minecraft.command.CommandSource;

import java.util.Optional;
import java.util.Map;
import java.util.Collections;

@RisModElements.ModElement.Tag
public class RocketliftoffProcedure extends RisModElements.ModElement {
	public RocketliftoffProcedure(RisModElements instance) {
		super(instance, 55);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Rocketliftoff!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure Rocketliftoff!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure Rocketliftoff!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure Rocketliftoff!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Rocketliftoff!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		for (int index0 = 0; index0 < (int) (10000); index0++) {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(x, (y + 0.11666666666), z);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (y + 0.11666666666), z, _ent.rotationYaw, _ent.rotationPitch,
							Collections.emptySet());
				}
			}
		}
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
			Optional<FunctionObject> _fopt = world.getWorld().getServer().getFunctionManager().get(new ResourceLocation("ris:rcfunc"));
			if (_fopt.isPresent()) {
				FunctionObject _fobj = _fopt.get();
				world.getWorld().getServer().getFunctionManager().execute(_fobj, new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z),
						Vec2f.ZERO, (ServerWorld) world.getWorld(), 4, "", new StringTextComponent(""), world.getWorld().getServer(), null));
			}
		}
	}
}
