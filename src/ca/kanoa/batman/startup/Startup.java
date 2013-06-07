package ca.kanoa.batman.startup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Startup extends JavaPlugin implements CommandExecutor {

	public void onEnable() {
		getCommand("batman").setExecutor(this);
	}
	
	public void onDisable() {}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		PluginDescriptionFile pdf = getDescription();
		sender.sendMessage("Batman API version " + pdf.getVersion() + ", made by: ");
		for (String s : pdf.getAuthors())
			sender.sendMessage("~" + s);
		return true;
	}

}
