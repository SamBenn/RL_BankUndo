package com.MyBulwark.BankUndo;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Bank Undo"
)
public class BankUndoPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BankUndoConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Bank Undo started!");
		keyManager.registerKeyListener(keyListener);
	}

	@Override
	protected void shutDown() throws Exception
	{
		keyManager.unregisterKeyListener(keyListener);
		log.info("Bank Undo stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	private final KeyListener keyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (config.toggleKeybind().matches(e)) {
				Widget bankContainer = client.getWidget(WidgetInfo.BANK_ITEM_CONTAINER);
				if (bankContainer == null || bankContainer.isSelfHidden()) {
					return;
				}

				configManager.setConfiguration(CONFIG_GROUP_NAME, RecentBankConfig.VIEW_TOGGLE, !config.recentViewToggled());
				e.consume();
			}

			if (config.toggleLockKeybind().matches(e)) {
				Widget bankContainer = client.getWidget(WidgetInfo.BANK_ITEM_CONTAINER);
				if (bankContainer == null || bankContainer.isSelfHidden()) {
					return;
				}

				configManager.setConfiguration(CONFIG_GROUP_NAME, RecentBankConfig.LOCK_TOGGLE, !config.lockToggled());
				e.consume();
			}
		}
	};

	@Provides
	BankUndoConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankUndoConfig.class);
	}
}
