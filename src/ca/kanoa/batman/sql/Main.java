package ca.kanoa.batman.sql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ca.kanoa.batman.startup.Startup;

public class Main {

	public static boolean debug = true;
	
	public static Main plugin;
	public static boolean autoSave;
	
	public static HashSet<Stats> stats;
	public static ArrayList<Killstreak> killstreaks = new ArrayList<Killstreak>();

	public static FileConfiguration config;
	public static FileConfiguration killstreakConfig;

	public static boolean trackDeathByMob;
	public static boolean canTrackOtherStats;

	public static SQLHelper sqlhelp;
	public static ConfigHelper confighelp;
	private Thread autosaver;
	
	public void onEnable() {

		Startup.plugin.saveDefaultConfig();
		config = Startup.plugin.getConfig();

		confighelp = new ConfigHelper(Startup.plugin);

		killstreakConfig = confighelp.getConfig("Killstreaks.yml");
		loadKillstreaksFromConfig();

		Main.trackDeathByMob = config.getBoolean("trackDeathByMob");
		Main.canTrackOtherStats = config.getBoolean("canTrackOtherStats");

		sqlhelp = new SQLHelper(config.getBoolean("useMySQL"), getMySQLInfo());
		Startup.plugin.getLogger().info("Getting stats!");
		stats = sqlhelp.getStats();
		stats = stats == null ? new HashSet<Stats>() : stats;
		autoSave = true;

		CommandExecutor CE = new CommandExecutor();
		Startup.plugin.getCommand("pvp").setExecutor(CE);
		Startup.plugin.getCommand("pvpa").setExecutor(CE);

		autosaver = new Thread(new Saver());
		autosaver.start();
		
		Startup.plugin.getLogger().info("Enabled!");
		
	}
	
	private void loadKillstreaksFromConfig() {
		List<String> list = killstreakConfig.getStringList("Killstreaks");
		final String split = ",";

		for(String s : list){
			String[] string = s.replace(", ", ",").split(split);
			try{
				killstreaks.add(new Killstreak(string[0], Integer.parseInt(string[1]), new Command(string[2])));
			} catch (ArrayIndexOutOfBoundsException e){
				Startup.plugin.getLogger().warning("Found badly formated killstreak near: '" + s + "'");
			} catch (NumberFormatException e){
				Startup.plugin.getLogger().warning("Could not find a number in the needed kills section near: '" + s + "'");
			} catch (Exception e){
				if(debug) e.printStackTrace();
				Startup.plugin.getLogger().warning("Unknown error (" + e.toString() + ") while parsing killstreak: '" + s + "'");
			}
		}
	}

	public void onDisable(){
		
		autoSave = false;
		
		Startup.plugin.getLogger().info("Disabling!");
		Startup.plugin.getLogger().info("Saving stats to file...");
		
		sqlhelp.sendStats(stats);
	}

	private MySQLInfo getMySQLInfo() {
		if(config.getBoolean("useMySQL")) 
			return new MySQLInfo(
					config.getString("MySQL.address"), 
					config.getString("MySQL.database"), 
					config.getString("MySQL.username"), 
					config.getString("MySQL.password"), 
					config.getString("MySQL.port"));
		else return new MySQLInfo();
	}


	public static Stats getStatsForPlayer(String player){
		for(Stats s : stats)
			if(s.getPlayerName().equals(player))
				return s;
		Stats stat = new Stats(player);
		stats.add(stat);
		return stat;
	}

	public static Stats getStatsForPlayer(Player player){
		return getStatsForPlayer(player.getName());
	}

	public String killstreakMessage() {
		return config.getString("killstreakMessage");
	}
	
}
