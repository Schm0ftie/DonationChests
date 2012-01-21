package me.Schm0ftie.TownyCommunityChests;

import org.bukkit.ChatColor;

public class Text {

	public static final String TAG = "[TCC]";
	public static final String TAG_COLORED = ChatColor.AQUA +"[TCC]";
	
	
	public static final String CONSOLE_ENABLED = TAG + " Towny Community Chests loaded!";
	public static final String CONSOLE_DISABLED = TAG + " Towny Community Chests unloaded!";
	
	public static final String CREATION_TEXT_1 = "To set up the filter punsh the sign with the item you want.";
	public static final String CREATION_TEXT_2 = "To allow all items punsh it without holding an item.";
	public static final String CREATION_SELECT_FILTER = ChatColor.YELLOW + "Filter mode?";
	public static final String DONATED = ChatColor.GREEN + "Thank you for your donation!";
	public static final String CHEST_FULL = ChatColor.RED + "FULL";
	public static final String NO_DONATION_CHEST_FULL = ChatColor.YELLOW + "This chest is already full.";
	public static final String EMPTY_STRING = "";
	public static final String NO_AIR = ChatColor.YELLOW + TAG + " No item found in hand!";
	
	public static final String ERROR_NO_CHEST = ChatColor.YELLOW + TAG + " No chest found! Place the sign above a chest!";
	public static final String ERROR_CHEST_MISSING = ChatColor.YELLOW + " Oops, chest is missing! Place a chest below the sign!";
	
}
