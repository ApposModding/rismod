
package org.souldbminer.reallyinspace.item;

import org.souldbminer.reallyinspace.itemgroup.RISItemGroup;
import org.souldbminer.reallyinspace.RisModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

@RisModElements.ModElement.Tag
public class AleinMealItem extends RisModElements.ModElement {
	@ObjectHolder("ris:alein_meal")
	public static final Item block = null;
	public AleinMealItem(RisModElements instance) {
		super(instance, 163);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(RISItemGroup.tab).maxStackSize(64).rarity(Rarity.RARE)
					.food((new Food.Builder()).hunger(8).saturation(0.3f).build()));
			setRegistryName("alein_meal");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.DRINK;
		}

		@Override
		public net.minecraft.util.SoundEvent getEatSound() {
			return net.minecraft.util.SoundEvents.ENTITY_GENERIC_DRINK;
		}
	}
}
