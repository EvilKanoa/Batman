package ca.kanoa.batman.chat;

import org.bukkit.Bukkit;

import ca.kanoa.batman.startup.Startup;

public class Chat {

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new ChatListener(), Startup.getInstance());
	}
	
	public void onDisable() {}
	
}
