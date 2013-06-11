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
		return unload(worldName, false);
	}
	
	public static boolean unload(String worldName, boolean save) {
		
		World world = Bukkit.getWorld(worldName);
		world.setAutoSave(save);
		Bukkit.getLogger().info("Unloading world (Autosave: " + save + "): " + world.getName());
		boolean unloaded = Bukkit.unloadWorld(world, save);
		Bukkit.getLogger().info("Unloaded fully (" + world.getName() + "): " + unloaded);
		return unloaded;
	}
	
	public static boolean load(String worldName) {
		return load(worldName, false);
	}
	
	public static boolean load(String worldName, boolean save) {
		Bukkit.getLogger().info("Loading world (Autosave: " + save + "): " + worldName);
		World w = Bukkit.createWorld(new WorldCreator(worldName));
		w.setAutoSave(save);
		boolean loaded = (w != null);
		Bukkit.getLogger().info("Loaded fully (" + worldName + "): " + loaded);
		return loaded;
	}
	
}
