package ca.kanoa.batman.utils;

import org.bukkit.Location;
import org.bukkit.Material;

public class CubeBuilder implements Util {

	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		return "Cube Builder";
	}

	@Override
	public String getDescription() {
		return "Builds cubes";
	}

	@Override
	public UtilType getType() {
		return UtilType.WORLD;
	}
	
	public static void solidCube(Location center, Material m, int size) {
		for (int x = (center.getBlockX() - size); x < (center.getBlockX() + size); x++)
			for (int y = (center.getBlockY() - size); y < (center.getBlockY() + size); y++)
				for (int z = (center.getBlockZ() - size); z < (center.getBlockZ() + size); z++)
					center.getWorld().getBlockAt(x, y, z).setType(m);
	}
	
	public static void emptyCube(Location center, Material m, int size) {
		size /= 2;
		for (int x = (center.getBlockX() - size); x < (center.getBlockX() + size); x++)
			for (int y = (center.getBlockY() - size); y < (center.getBlockY() + size); y++)
				for (int z = (center.getBlockZ() - size); z < (center.getBlockZ() + size); z++)
					center.getWorld().getBlockAt(x, y, z).setType(m);
		size -= 1;
		for (int x = (center.getBlockX() - size); x < (center.getBlockX() + size); x++)
			for (int y = (center.getBlockY() - size); y < (center.getBlockY() + size); y++)
				for (int z = (center.getBlockZ() - size); z < (center.getBlockZ() + size); z++)
					center.getWorld().getBlockAt(x, y, z).setType(m);
	}
	
}
