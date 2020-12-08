package org.souldbminer.reallyinspace.procedures;

import org.souldbminer.reallyinspace.RisModElements;

import java.util.Map;

@RisModElements.ModElement.Tag
public class LiftoffProcedure extends RisModElements.ModElement {
	public LiftoffProcedure(RisModElements instance) {
		super(instance, 66);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		boolean funcran = false;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
	}
}
