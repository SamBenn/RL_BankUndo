package com.MyBulwark.BankUndo;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BankUndoTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BankUndoPlugin.class);
		RuneLite.main(args);
	}
}