
package org.souldbminer.reallyinspace.world.biome;

import org.souldbminer.reallyinspace.block.CompressedmagmaBlock;
import org.souldbminer.reallyinspace.RisModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.Biome;
import net.minecraft.block.Blocks;

@RisModElements.ModElement.Tag
public class SaturnbBiome extends RisModElements.ModElement {
	@ObjectHolder("ris:saturnb")
	public static final CustomBiome biome = null;
	public SaturnbBiome(RisModElements instance) {
		super(instance, 59);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0f).depth(0.1f).scale(0.2f).temperature(-1f).precipitation(Biome.RainType.NONE)
					.category(Biome.Category.NONE).waterColor(4159204).waterFogColor(329011)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.PACKED_ICE.getDefaultState(),
							CompressedmagmaBlock.block.getDefaultState(), CompressedmagmaBlock.block.getDefaultState())));
			setRegistryName("saturnb");
		}
	}
}
