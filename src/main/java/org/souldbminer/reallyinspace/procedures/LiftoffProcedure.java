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
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Liftoff!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean funcran = false;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		for (int index0 = 0; index0 < (int) (70); index0++) {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(x, (y + 7), z);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (y + 7), z, _ent.rotationYaw, _ent.rotationPitch,
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
