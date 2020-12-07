
package org.souldbminer.reallyinspace.painting;

import org.souldbminer.reallyinspace.RisModElements;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

@RisModElements.ModElement.Tag
public class RtingPainting extends RisModElements.ModElement {
	public RtingPainting(RisModElements instance) {
		super(instance, 77);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(16, 16).setRegistryName("rting"));
	}
}
