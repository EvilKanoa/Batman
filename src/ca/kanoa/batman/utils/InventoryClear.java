package ca.kanoa.batman.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryClear implements Util {

	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Inventory Clearer";
	}

	@Override
	public String getDescription() {
		return "Allows easy clearing of inventorys";
	}

	@Override
	public UtilType getType() {
		return UtilType.INVENTORY;
	}

	public static void clearEveryones() {
		for (Player p : Bukkit.getOnlinePlayers())
			clear(p);
	}
	
	public static void clear(String player) {
		clear(Bukkit.getPlayer(player));
	}
	
	public static void clear(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(new ItemStack[]{null, null, null, null});
	}

}
