
package org.souldbminer.reallyinspace.gui;

import org.souldbminer.reallyinspace.procedures.VentpProcedure;
import org.souldbminer.reallyinspace.procedures.SuntpProcedure;
import org.souldbminer.reallyinspace.procedures.SattpProcedure;
import org.souldbminer.reallyinspace.procedures.MoontpProcedure;
import org.souldbminer.reallyinspace.procedures.Moon2tpProcedure;
import org.souldbminer.reallyinspace.procedures.MerctpProcedure;
import org.souldbminer.reallyinspace.procedures.MarsdimtpProcedure;
import org.souldbminer.reallyinspace.procedures.JuptpProcedure;
import org.souldbminer.reallyinspace.procedures.Guitrigger1Procedure;
import org.souldbminer.reallyinspace.procedures.EtpProcedure;
import org.souldbminer.reallyinspace.procedures.AtpProcedure;
import org.souldbminer.reallyinspace.RisModElements;
import org.souldbminer.reallyinspace.RisMod;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@RisModElements.ModElement.Tag
public class Pannel2Gui extends RisModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public Pannel2Gui(RisModElements instance) {
		super(instance, 233);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("pannel_2"));
	}
	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private World world;
		private PlayerEntity entity;
		private int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;
		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(0);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class GuiWindow extends ContainerScreen<GuiContainerMod> {
		private World world;
		private int x, y, z;
		private PlayerEntity entity;
		TextFieldWidget Search;
		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entity = container.entity;
			this.xSize = 424;
			this.ySize = 238;
		}

		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			this.renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
			Search.render(mouseX, mouseY, partialTicks);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			GL11.glColor4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("ris:textures/modlogo.png"));
			this.blit(this.guiLeft + 169, this.guiTop + 221, 0, 0, 0, 0, 0, 0);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("ris:textures/rocket.png"));
			this.blit(this.guiLeft + 235, this.guiTop + 5, 0, 0, 0, 0, 0, 0);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("ris:textures/bigrocket.png"));
			this.blit(this.guiLeft + -3, this.guiTop + -8, 0, 0, -1, -1, -1, -1);
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				this.minecraft.player.closeScreen();
				return true;
			}
			if (Search.isFocused())
				return Search.keyPressed(key, b, c);
			return super.keyPressed(key, b, c);
		}

		@Override
		public void tick() {
			super.tick();
			Search.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			this.font.drawString("Dwarf Planets", 167, 3, -3407872);
			this.font.drawString("Astroid Belts", 87, 4, -16776961);
			this.font.drawString("Moons", 258, 3, -1);
			this.font.drawString("Planets", 26, 4, -205);
			this.font.drawString("Stars", 352, 2, -13369549);
			this.font.drawString("Page 1/2", 127, 220, -256);
			this.font.drawString("Tier 2 Rocket Is needed", 104, 50, -1);
		}

		@Override
		public void removed() {
			super.removed();
			Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
		}

		@Override
		public void init(Minecraft minecraft, int width, int height) {
			super.init(minecraft, width, height);
			minecraft.keyboardListener.enableRepeatEvents(true);
			Search = new TextFieldWidget(this.font, this.guiLeft + 2, this.guiTop + 215, 120, 20, "Search Planets") {
				{
					setSuggestion("Search Planets");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Search Planets");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Search Planets");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:Search", Search);
			Search.setMaxStringLength(32767);
			this.children.add(this.Search);
			this.addButton(new Button(this.guiLeft + 14, this.guiTop + 21, 60, 20, "Mercury", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(0, x, y, z));
				handleButtonAction(entity, 0, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 18, this.guiTop + 46, 50, 20, "Venus", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(1, x, y, z));
				handleButtonAction(entity, 1, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 19, this.guiTop + 71, 50, 20, "Earth", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(2, x, y, z));
				handleButtonAction(entity, 2, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 22, this.guiTop + 96, 45, 20, "Mars", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(3, x, y, z));
				handleButtonAction(entity, 3, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 15, this.guiTop + 120, 60, 20, "Jupiter", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(4, x, y, z));
				handleButtonAction(entity, 4, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 17, this.guiTop + 141, 55, 20, "Saturn", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(5, x, y, z));
				handleButtonAction(entity, 5, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 92, this.guiTop + 23, 55, 20, "Astroid", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(6, x, y, z));
				handleButtonAction(entity, 6, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 337, this.guiTop + 19, 60, 20, "The Sun", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(7, x, y, z));
				handleButtonAction(entity, 7, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 234, this.guiTop + 19, 100, 20, "Earth's Moon #1", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(8, x, y, z));
				handleButtonAction(entity, 8, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 234, this.guiTop + 42, 100, 20, "Earth's Moon #2", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(9, x, y, z));
				handleButtonAction(entity, 9, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 257, this.guiTop + 70, 55, 20, "Phobos", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(10, x, y, z));
				handleButtonAction(entity, 10, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 256, this.guiTop + 99, 55, 20, "Deimos", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(11, x, y, z));
				handleButtonAction(entity, 11, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 259, this.guiTop + 127, 50, 20, "Titan", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(12, x, y, z));
				handleButtonAction(entity, 12, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 256, this.guiTop + 151, 55, 20, "Europa", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(13, x, y, z));
				handleButtonAction(entity, 13, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 267, this.guiTop + 179, 35, 20, "Io", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(14, x, y, z));
				handleButtonAction(entity, 14, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 371, this.guiTop + 211, 45, 20, ">>>>", e -> {
				RisMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(15, x, y, z));
				handleButtonAction(entity, 15, x, y, z);
			}));
		}
	}

	public static class ButtonPressedMessage {
		int buttonID, x, y, z;
		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;
		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}
	private static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				MerctpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 1) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				VentpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 2) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				EtpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 3) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				MarsdimtpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 4) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				JuptpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 5) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				SattpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 6) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				AtpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 7) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				SuntpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 8) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				MoontpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 9) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				Moon2tpProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 15) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				Guitrigger1Procedure.executeProcedure($_dependencies);
			}
		}
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
