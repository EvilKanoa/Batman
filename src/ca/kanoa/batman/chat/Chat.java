package ca.kanoa.batman.chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.bukkit.Bukkit;

import ca.kanoa.batman.startup.Startup;

public class Chat {

	private List<String> chatLog;
	private static Chat plugin;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
	private Calendar date;
	
	public void onEnable() {
		plugin = this;
		date = Calendar.getInstance(TimeZone.getDefault());
		Bukkit.getPluginManager().registerEvents(new ChatListener(), Startup.getInstance());
		chatLog = new ArrayList<String>();
	}
	
	public void onDisable() {
		//save chatLog to plugins/our-folder/chat/log-date-time.txt;
	}
	
	/**
	 * Adds a message to the file log buffer
	 * @param msg The message to be added
	 */
	public void log(String msg) {
		chatLog.add("[Chat][" + dateFormat.format(date.getTime()) + "] " + msg);
	}
	
	/**
	 * Gets this instance
	 * @return The current instance of the Chat class
	 */
	public static Chat getInstance() {
		return plugin;
	}
	
}
