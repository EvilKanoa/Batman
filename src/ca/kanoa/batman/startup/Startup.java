package ca.kanoa.batman.startup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import ca.kanoa.batman.sql.Main;

public class Startup extends JavaPlugin implements CommandExecutor {

	Main sql;
	public static JavaPlugin plugin;
	
	public void onEnable() {
		plugin = this;
		ca.kanoa.batman.sql.CommandExecutor ce = new ca.kanoa.batman.sql.CommandExecutor();
		sql = new Main();
		sql.onEnable();
		getCommand("batman").setExecutor(this);
		getCommand("pvp").setExecutor(ce);
		getCommand("pvpa").setExecutor(ce);
	}
	
	public void onDisable() {
		sql.onDisable();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		PluginDescriptionFile pdf = getDescription();
		sender.sendMessage("MCC API version " + pdf.getVersion() + ", made by: ");
		for (String s : pdf.getAuthors())
			sender.sendMessage("!: " + s);
		return true;
	}

}
