package ca.kanoa.batman.utils;

import java.util.HashMap;

public class Variables implements Util {

	private static HashMap<String, Object> objects = new HashMap<String, Object>();
	
	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		return "Variables";
	}

	@Override
	public String getDescription() {
		return "Cross plugin variable setter";
	}

	@Override
	public UtilType getType() {
		return UtilType.SYSTEM;
	}
	
	public static boolean set(String key, Object value) {
		if (objects.containsKey(key) && objects.get(key) == value) {
			objects.put(key, value);
			return false;
		} 
		else {
			objects.put(key, value);
			return true;
		}
			
	}
	
	public static Object get(String key) {
		if (objects.containsKey(key))
			return objects.get(key);
		else
			return null;
	}
	
	public static boolean delete(String key) {
		if (!objects.containsKey(key))
			return false;
		else {
			objects.remove(key);
			return true;
		}
	}
	
	public static boolean exists(String key) {
		return objects.containsKey(key);
	}
	

}
