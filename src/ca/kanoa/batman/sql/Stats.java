package ca.kanoa.batman.sql;

public class Stats {
	
	
	//The real stats are here ;)
	private String playerName;
	private int kills;
	private int deaths;
	private int killstreak;
	private int currentKillstreak;
	private int wins;
	private int usedRods;
	private int money;
	
	
	//Constructors
	public Stats(String playerName){
		this.setPlayerName(playerName);
		this.setKills(0);
		this.setDeaths(0);
		this.setKillstreak(0);
		this.setCurrentKillStreak(0);
		this.setWins(0);
		this.setUsedRods(0);
		this.setMoney(0);
	}
	
	public Stats(String playerName, int kills, int deaths, int killstreak, int currKillstreak, int wins, int usedRods, int money){
		this.setPlayerName(playerName);
		this.setKills(kills);
		this.setDeaths(deaths);
		this.setKillstreak(killstreak);
		this.setCurrentKillStreak(currKillstreak);
		this.setWins(wins);
		this.setUsedRods(usedRods);
		this.setMoney(money);
	}

	
	//"Make thing easier" methods, gotta love em :P
	public float getKDR(){
		if(this.deaths == 0) 
			return (this.kills);
		else{
			return ((float)this.kills / this.deaths);
		}
	}
	
	public int addKill(){
		this.kills++;
		this.currentKillstreak++;
		if(this.currentKillstreak > this.killstreak)
			this.killstreak = this.currentKillstreak;
		return kills;
	}
	
	public int addWin() {
		this.wins++;
		return this.wins;
	}
	
	public int addRodUsed() {
		this.usedRods++;
		return this.usedRods;
	}
	
	public int addDeath(){
		this.deaths++;
		resetKillstreak();
		return deaths;
	}
	
	public int addMoney(int money) {
		this.money += money;
		return this.money;
	}
	
	public int removeMoney(int money) {
		this.money -= money;
		return this.money;
	}
	
	private boolean resetKillstreak(){
		if(this.currentKillstreak > this.killstreak){
			this.killstreak = this.currentKillstreak;
			this.currentKillstreak = 0;
			return true;
		}
		else{
			this.currentKillstreak = 0;
			return false;
		}
	}
	
	
	/*
	 * returns a string with the stat info to use with a database eg:
	 * useLongString = true:
	 * 	"playerName='12323op', kills=232, deaths=6, killstreak=70, currentKillstreak=12"
	 * useLongString = false:
	 * 	"'12323op', 232, 6, 70, 12"
	 */
	public String getMySQL(boolean useLongString){
		if(useLongString)
			return "playerName='" + this.playerName + "', kills=" + this.kills + ", deaths=" + 
				this.deaths + ", killstreak=" + this.killstreak + ", currentKillstreak=" + 
			this.currentKillstreak +  ", wins=" + this.wins + ", usedRods=" + this.usedRods + ", money=" + this.money;
		else
			return "'" + this.playerName + "', " + this.kills + ", " + this.deaths + ", " + this.killstreak + ", " + 
				this.currentKillstreak + ", " + this.wins + ", " + this.usedRods + ", " + this.money;
	}
	
	//Getter and Setters, boring stuff...
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getKillstreak() {
		return killstreak;
	}

	public void setKillstreak(int killstreak) {
		this.killstreak = killstreak;
	}

	public int getCurrentKillStreak() {
		return currentKillstreak;
	}

	public void setCurrentKillStreak(int currentKillstreak) {
		this.currentKillstreak = currentKillstreak;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getUsedRods() {
		return usedRods;
	}

	public void setUsedRods(int usedRods) {
		this.usedRods = usedRods;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
