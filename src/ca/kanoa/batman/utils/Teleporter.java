package ca.kanoa.batman.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Teleporter implements Util {

	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		return "Teleporter";
	}

	@Override
	public String getDescription() {
		return "Allows extra methods of teleportation";
	}

	@Override
	public UtilType getType() {
		return UtilType.WORLD;
	}

	public static void teleportAll(Location toLoc) {
		for (Player p : Bukkit.getOnlinePlayers())
			p.teleport(toLoc);
	}
	
	public static void teleportAll(World world, double x, double y, double z) {
		teleportAll(new Location(world, x,y,z));
	}

	public static void teleportEntity(Entity e, double x, double y, double z) {
		e.teleport(new Location(e.getWorld(), x, y, z));
	}

	public static void saveLocation(Configuration config, Location loc, String where, String world) {
		config.set(where + ".world", world == null ? loc.getWorld().getName() : world);
		config.set(where + ".x",      loc.getX());
		config.set(where + ".y",      loc.getY());
		config.set(where + ".z",      loc.getZ());
		config.set(where + ".pitch",  loc.getPitch());
		config.set(where + ".yaw",    loc.getYaw());
	}
	
	public static Location getLocation(Configuration config, String where) {
		World world = Bukkit.getWorld(config.getString(where + ".world"));
		double x = config.getDouble(where + ".x"), y = config.getDouble(where + ".y"), z = config.getDouble(where + ".z");
		float pitch = (float) config.getDouble(where + ".pitch"), yaw = (float) config.getDouble(where + ".yaw");
		return new Location(world, x, y, z, yaw, pitch);
	}
	
}
