package ca.kanoa.batman.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldLoader implements Util {

	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		return "World Loader";
	}

	@Override
	public String getDescription() {
		return "Loads and unloads bukkit worlds";
	}

	@Override
	public UtilType getType() {
		return UtilType.WORLD;
	}

	public static boolean unload(String worldName) {
		
		World world = Bukkit.getWorld(worldName);
		
		return Bukkit.unloadWorld(world, false);
		
	}
	
	public static boolean load(String worldName) {
		return load(worldName, false);
	}
	
	public static boolean load(String worldName, boolean save) {
		World w = Bukkit.createWorld(new WorldCreator(worldName));
		w.setAutoSave(save);
		return w != null;
	}
	
}
