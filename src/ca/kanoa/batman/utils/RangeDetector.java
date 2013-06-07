package ca.kanoa.batman.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RangeDetector implements Util {

	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		return "Range Detector";
	}

	@Override
	public String getDescription() {
		return "Allows range detecting";
	}

	@Override
	public UtilType getType() {
		return UtilType.WORLD;
	}

	public static boolean playerNearBlock(Player p, Material m, int range) {
		for (int x = (p.getLocation().getBlockX() - range); x < (p.getLocation().getBlockX() + range); x++)
			for (int y = (p.getLocation().getBlockY() - range); y < (p.getLocation().getBlockY() + range); y++)
				for (int z = (p.getLocation().getBlockZ() - range); z < (p.getLocation().getBlockZ() + range); z++)
					if (p.getWorld().getBlockAt(x, y, z).getType() == m)
						return true;
		return false;
	}
	
	public static boolean playerInArea2d(Player p, int x1, int z1, int x2, int z2) {
		Location loc = p.getLocation();
		return (loc.getBlockX() > x1 && loc.getBlockX() < x2) && (loc.getBlockZ() > z1 && loc.getBlockZ() < z2);
	}
	
}
