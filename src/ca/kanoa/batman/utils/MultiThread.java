package ca.kanoa.batman.utils;

public class MultiThread implements Util {

	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		return "MultiThreader";
	}

	@Override
	public String getDescription() {
		return "Allows easy MultiThreading methods";
	}

	@Override
	public UtilType getType() {
		return UtilType.FILE;
	}

	public static Thread runInParrallel(Runnable code) {
		Thread t = new Thread(code);
		t.start();
		return t;
	}

}
