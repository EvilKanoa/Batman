package ca.kanoa.batman.sql;

public class Saver implements Runnable {

	@Override
	public void run() {
		long sleepTime = Main.config.getInt("autosave_delay") * 1000;
		sleep(sleepTime);
		while (Main.autoSave) {
			Main.sqlhelp.sendStats(Main.getStats());
			sleep(sleepTime);
		}
	}
	
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
