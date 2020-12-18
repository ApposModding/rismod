
package org.souldbminer.reallyinspace.itemgroup;

import org.souldbminer.reallyinspace.item.AEK971GunItem;
import org.souldbminer.reallyinspace.RisModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@RisModElements.ModElement.Tag
public class ReallyInSpaceGunsItemGroup extends RisModElements.ModElement {
	public ReallyInSpaceGunsItemGroup(RisModElements instance) {
		super(instance, 251);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabreally_in_space_guns") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(AEK971GunItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
