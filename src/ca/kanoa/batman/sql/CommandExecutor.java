package ca.kanoa.batman.sql;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.kanoa.batman.startup.Startup;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

	//Colors
	final ChatColor green  = ChatColor.GREEN;
	final ChatColor red    = ChatColor.RED;
	final ChatColor blue   = ChatColor.BLUE;
	final ChatColor black  = ChatColor.BLACK;
	final ChatColor purple = ChatColor.DARK_PURPLE;

	private boolean pvpCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Stats stats;
		if(args.length == 0 && sender.hasPermission("cod.check.self"))
			stats = Main.getStatsForPlayer(sender.getName());
		else if(args.length == 1 && sender.hasPermission("cod.check.other"))
			stats = Main.getStatsForPlayer(args[0]);
		else if(args.length > 1 && (sender.hasPermission("cod.check.self") || sender.hasPermission("cod.check.other"))){
			sender.sendMessage(red + "Too many arguments");
			return false;
		}
		else{
			sender.sendMessage(red + "You don't have permission!");
			return false;
		}
	
		String[] statStrings = {"Stats for " + purple + stats.getPlayerName() + ":",
				"Kills: " + red + stats.getKills(), "Deaths: " + red + stats.getDeaths(), 
				"KDR: " + red + stats.getKDR(), "Rods Used: " + red + stats.getUsedRods(), 
				"Wins: " + red + stats.getWins(), "Exp: " + red + stats.getMoney()};
		for (String s : statStrings)
			sender.sendMessage(blue + s);
		return true;
	}

	private boolean pvpaCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
			sender.sendMessage(red + "Reloading config...");
			
			Startup.plugin.reloadConfig();
			Main.config = Startup.plugin.getConfig();
			
			Main.confighelp.saveConfig(Main.killstreakConfig, "Killstreaks.yml");
			Main.killstreakConfig = Main.confighelp.getConfig("Killstreaks.yml");
			
			sender.sendMessage(green + "The configuration has been reloaded.");
			return true;
		}
		else if(args.length > 0 && args[0].equalsIgnoreCase("set")){
			if(args.length == 4){
				Stats stats = Main.getStatsForPlayer(args[2]);
				String item = args[1];
				String value = args[3];

				try{
					if(item.equalsIgnoreCase("kills"))
						stats.setKills(Integer.valueOf(value));
					else if(item.equalsIgnoreCase("deaths"))
						stats.setDeaths(Integer.valueOf(value));
					else if(item.equalsIgnoreCase("killstreak"))
						stats.setKillstreak(Integer.valueOf(value));
					else{
						sender.sendMessage(red + "Unknown item type!");
						return true;
					}
					sender.sendMessage(blue + item + " has been set to: " + red + value);
				} catch (NumberFormatException e){
					sender.sendMessage(red + "The value needs to be a number!");
				}
				return true;
			}
			else{
				sender.sendMessage("/pvpa set <item> <player> <value>");
				return true;
			}
		}
		else if(args.length == 1 && args[0].equalsIgnoreCase("send")){
			sender.sendMessage(red + "Sending current stats to SQL database...");
			Main.sqlhelp.sendStats(Main.getStats());
			sender.sendMessage(green + "Stats sent!");
			return true;
		}
		else if(args.length > 0 && args[0].equalsIgnoreCase("killstreaks")){
			for(Killstreak ks : Main.killstreaks)
				sender.sendMessage(green + ks.getName() + ": " + red + ks.getRequired() + " kills needed, " + purple + ks.getStringCommand());
			return true;
		}
		else{
			sender.sendMessage(black + "Unknown Command and/or arguments!");
			sender.sendMessage(blue + "CODStats admin commands:");
			sender.sendMessage(red + "Set: " + green + "Used to set a players kills/deaths/killstreak");
			sender.sendMessage(red + "Send: " + green + "Sends the current stats to the database.");
			sender.sendMessage(red + "Reload: " + green + "Reloads the configuration file from disk.");
			sender.sendMessage(red + "Killstreaks: " + green + "Lists all the currently loaded killstreaks.");
			return true;
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			if(cmd.getName().equalsIgnoreCase("pvp")) return pvpCommand(sender, cmd, label, args);
			else if(cmd.getName().equalsIgnoreCase("pvpa")) return pvpaCommand(sender, cmd, label, args);
			else return false;
		}
		else{
			sender.sendMessage("Player only command");
			return true;
		}
	}

}
