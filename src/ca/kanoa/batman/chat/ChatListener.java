package ca.kanoa.batman.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler(priority=EventPriority.MONITOR)
	public void onChat(AsyncPlayerChatEvent event) {
		//TODO: Do stuff
		ChatEvent chat = new ChatEvent(event.getPlayer(), "<", "> ", event.getMessage(), event.isCancelled());
		Bukkit.getPluginManager().callEvent(chat);
		if (!chat.isCancelled()) {
			String msg = (new StringBuilder()).
					append(chat.getPrefix()).
					append(chat.getPlayer().getDisplayName()).
					append(chat.getSuffix()).
					append(chat.getMessage()).
					toString().trim();
			Bukkit.getLogger().info("[Chat] " + msg);
			for (Player player : Bukkit.getOnlinePlayers())
				player.sendMessage(msg);
		}
		
		event.setCancelled(true);
	}
	
}
