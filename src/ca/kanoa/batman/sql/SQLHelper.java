package ca.kanoa.batman.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ca.kanoa.batman.startup.Startup;

public class SQLHelper {

	private final boolean online;
	private final MySQLInfo info;
	private String dbLocation;

	public SQLHelper(boolean online, MySQLInfo info){
		this.online = online;
		this.info = info;
        File dbFile = new File(Startup.getInstance().getDataFolder(), "Stats.db");

		try {
			if(!online){
				Class.forName("org.sqlite.JDBC");
				Startup.getInstance().getDataFolder().mkdirs();
				dbFile.createNewFile();
				this.dbLocation = "jdbc:sqlite:" + dbFile.getPath();
			}
			else{
				Class.forName("com.mysql.jdbc.Driver");
				this.dbLocation = "jdbc:mysql://" + info.getAddress() + ":" + info.getPort() + "/" + info.getDatabase();
			}
		} catch (Exception e) { e.printStackTrace(); }

		createTable();

	}

	public synchronized void sendStats(Set<Stats> stats){
		try {
			createTable();

			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			final String insertQuery = "INSERT INTO stats VALUES (%%shortInfo)";
			final String updateQuery = "UPDATE stats SET %%longInfo WHERE playerName='%%playerName'";
			final String checkQuery = "SELECT * FROM stats WHERE playerName='%%playerName'";
			String editedQuery;

			ResultSet rs;

			for(Stats stat : stats){
				rs = stmt.executeQuery(checkQuery.replaceAll("%%playerName", stat.getPlayerName()));
				if(!rs.next())
					editedQuery = insertQuery.replaceAll("%%shortInfo", stat.getMySQL(false));
				else
					editedQuery = updateQuery.replaceAll("%%longInfo", stat.getMySQL(true)).replaceAll("%%playerName", stat.getPlayerName());
				stmt.executeUpdate(editedQuery);
			}

			conn.close();
			stmt.close();
		} catch (SQLException e) { e.printStackTrace(); }
	}

	public synchronized HashSet<Stats> getStats(){
		HashSet<Stats> stats = new HashSet<Stats>();
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM stats");
			while(rs.next()){
				stats.add(new Stats(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) { e.printStackTrace(); }
		return stats;
	}

	private void createTable() {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS stats(" +
					"playerName VARCHAR(32) NOT NULL, " +
					"kills int, " +
					"deaths int, " +
					"killstreak int, " +
					"currentKillstreak int, " +
					"wins int, " +
					"usedRods int, " + 
					"money int, " + 
					"PRIMARY KEY (playerName))");

			//Close up that shizzle!
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public synchronized Connection getConnection() throws SQLException{
		if(online)
			return DriverManager.getConnection(dbLocation, info.getUsername(), info.getPassword());
		else
			return DriverManager.getConnection(this.dbLocation);

	}
}
