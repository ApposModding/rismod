
package org.souldbminer.reallyinspace.world.biome;

import org.souldbminer.reallyinspace.RisModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.block.Blocks;

@RisModElements.ModElement.Tag
public class MakeMakeBiomeBiome extends RisModElements.ModElement {
	@ObjectHolder("ris:make_make_biome")
	public static final CustomBiome biome = null;
	public MakeMakeBiomeBiome(RisModElements instance) {
		super(instance, 151);
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
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.STRIPPED_ACACIA_WOOD.getDefaultState(),
							Blocks.STRIPPED_JUNGLE_WOOD.getDefaultState(), Blocks.STRIPPED_JUNGLE_WOOD.getDefaultState())));
			setRegistryName("make_make_biome");
			DefaultBiomeFeatures.addCarvers(this);
		}
	}
}
