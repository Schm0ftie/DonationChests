package me.Schm0ftie.TownyCommunityChests;

import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class TownyCommunityChests extends JavaPlugin {

	public static final int chest_id = 54;
	public static final int sign_id = 323;
	
	private TccBlockListener tccBlockListener = new TccBlockListener();
	private Logger logger;
	
	@Override
	public void onDisable() {
		logger.info(Text.CONSOLE_DISABLED);
		
	}

	@Override
	public void onEnable() {
		logger = getServer().getLogger();
        getServer().getPluginManager().registerEvent(Type.SIGN_CHANGE, tccBlockListener, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Type.BLOCK_DAMAGE,tccBlockListener, Priority.Normal, this);
		logger.info(Text.CONSOLE_ENABLED);
	}

}
