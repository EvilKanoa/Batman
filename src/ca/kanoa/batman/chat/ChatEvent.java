package ca.kanoa.batman.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChatEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	
	private boolean cancelled;
	private String prefix;
	private String suffix;
	private String message;
	private final Player player;
	
	/**
	 * More advanced version of AsyncPlayerChatEvent, allows prefix and suffix (for ranks)
	 * @param player The player that used chat
	 * @param prefix The String that shows up before the players name
	 * @param suffix The String that shows up after the player name
	 * @param message The message itself, shows up after suffix
	 */
	public ChatEvent(Player player, String prefix, String suffix, String message) {
		this.setCancelled(false);
		this.player = player;
		this.setPrefix(prefix);
		this.setSuffix(suffix);
		this.setMessage(message);
	}
	
	/**
	 * More advanced version of AsyncPlayerChatEvent, allows prefix and suffix (for ranks)
	 * @param player The player that used chat
	 * @param prefix The String that shows up before the players name
	 * @param suffix The String that shows up after the player name
	 * @param message The message itself, shows up after suffix
	 * @param cancelled If the event starts out cancelled
	 */
	public ChatEvent(Player player, String prefix, String suffix, String message, boolean cancelled) {
		this.setCancelled(cancelled);
		this.player = player;
		this.setPrefix(prefix);
		this.setSuffix(suffix);
		this.setMessage(message);
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Player getPlayer() {
		return player;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public static HandlerList getHandlerList() {       
		return handlers;   
	}

}
