package me.Schm0ftie.TownyCommunityChests;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TccBlockListener extends BlockListener {

	public void onSignChange(SignChangeEvent event){
		Player player = event.getPlayer();
		int maxAmount = 0;
		
		if (!event.getLine(0).contains(Text.TAG)){
			return;	
		}
		if (!event.getLine(2).equalsIgnoreCase(Text.EMPTY_STRING)){
			maxAmount = Integer.parseInt(event.getLine(2));
		}
		Block block = event.getBlock();
		Chest chest = TccAction.getChest(block);
		if (chest == null){
			player.sendMessage(Text.ERROR_NO_CHEST);
			block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.SIGN, 1));
			block.setTypeId(0);
			return;
		}
		event.setLine(0, Text.TAG_COLORED);
		event.setLine(1, Text.CREATION_SELECT_FILTER);
		event.setLine(2, Text.EMPTY_STRING + maxAmount);
	}
	
	public void onBlockDamage(BlockDamageEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if (block.getState() instanceof Sign){
			Sign sign = (Sign) block.getState();
			
			if (sign.getLine(0).equalsIgnoreCase(Text.TAG_COLORED) && sign.getLine(1).equalsIgnoreCase(Text.CREATION_SELECT_FILTER)){
				if (player.getItemInHand().getType() != Material.AIR){
					int defaultMax = player.getItemInHand().getType().getMaxStackSize() * 27 ;
					int maxAmount = Integer.parseInt(sign.getLine(2));
					if (maxAmount > 0 && maxAmount < defaultMax){
						defaultMax = maxAmount;
					}
					sign.setLine(1, player.getItemInHand().getType().name());
					sign.setLine(2, "[?/" + defaultMax + "]");
					sign.update();
					return;
				}
				player.sendMessage(Text.NO_AIR);
				return;
			}
			
			if (sign.getLine(0).equalsIgnoreCase(Text.TAG_COLORED)){
				ItemStack holding = player.getItemInHand();
				int amount = holding.getAmount();
				if (TccAction.isFilteredItem(holding, sign.getLine(1))){
					Chest chest = TccAction.getChest(sign.getBlock());
					if (chest == null){
						player.sendMessage(Text.ERROR_CHEST_MISSING);
						return;
					}
					TccAction.updateProgress(sign, chest);
					Inventory inv = chest.getInventory();
					player.setItemInHand(null);
					if (TccAction.parseCurrAmount(sign.getLine(2)) >= TccAction.parseMaxAmount(sign.getLine(2))){
						player.sendMessage(Text.NO_DONATION_CHEST_FULL);
						player.setItemInHand(holding);
						return;
					}
					HashMap<Integer,ItemStack> itemsLeft = inv.addItem(holding);
					if (itemsLeft.get(0) != null){
						player.setItemInHand(itemsLeft.get(0));
					}
					if (player.getItemInHand().getAmount() == amount){
						player.sendMessage(Text.NO_DONATION_CHEST_FULL);
						return;
					}
					TccAction.updateProgress(sign, chest);
					player.sendMessage(Text.DONATED);
				}
			}
		}
	}
}



