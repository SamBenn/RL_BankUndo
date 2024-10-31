package com.MyBulwark.BankUndo;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@ConfigGroup("BankUndo")
public interface BankUndoConfig extends Config
{
	String UNDO_KEYBIND = "undoKeybind";

	@ConfigItem(
			keyName = UNDO_KEYBIND,
			name = "Undo deposit Keybind",
			description = "Keybind to undo recent deposit"
	)
	default Keybind undoKeybind() {
		return new Keybind(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
	}
}
