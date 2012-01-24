package me.Schm0ftie.DonationChests;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class DonationChests extends JavaPlugin {

	public static final int chest_id = 54;
	public static final int sign_id = 323;

	private Logger logger;
	
	@Override
	public void onDisable() {
		logger.info(Text.CONSOLE_DISABLED);
	}

	@Override
	public void onEnable() {
		logger = getServer().getLogger();
		new DCBlockListener(this);
		logger.info(Text.CONSOLE_ENABLED);
	}

}
