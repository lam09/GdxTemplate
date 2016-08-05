package gdx.game.template;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Configuration {
	
	private enum Type {
		BOOLEAN, INTEGER, LONG, FLOAT, DOUBLE, STRING, STRING_ARRAY
	}

	private static final String CONFIG_FILE = "config.properties";
	private static final String VERSION_FILE = "version";
	
	private static Properties properties;
	private static Map<String, Object> cache;
	
	public static void loadConfiguration() {

		properties = new Properties();
		cache = new HashMap<>();
		InputStream inputStream = null;

		try {
			File file = new File(CONFIG_FILE);
			
			/* should return path of jar */
			String path = MyGame.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			path = URLDecoder.decode(path, "UTF-8");
			
			File directory = new File(path);
			if (directory.getPath().contains(".jar")) {
				directory = directory.getParentFile();
				file = new File(directory, CONFIG_FILE);
			}

			if (file.exists()) {
				inputStream = new FileInputStream(file);
				properties.load(inputStream);
				inputStream.close();
				
//				properties.list(System.out);
				
				System.out.println("");
				List<String> keys = new ArrayList<>();
				for (Object key : properties.keySet()) {
					keys.add((String)key);
				}
				Collections.sort(keys);
				for (String key : keys) {
					System.out.println(key+"="+properties.getProperty(key));
				}
				System.out.println();

				//read version file

			} else {
				System.out.println("Configuration file not found: "+file.getAbsolutePath()+"");
				System.out.println("Goodbye cruel world!");
				System.exit(1);
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



	public static void set(Map<String,String> conf) {
		if(conf == null) return;
		for (Map.Entry<String, String> entry : conf.entrySet())
		{
			properties.put(entry.getKey(),entry.getValue());
			System.out.println("Configuration override: " + entry.getKey() + " = " + entry.getValue());
		}
	}
	
	
	public static Object get(String key, Object defaultValue, Type type) {
		Object cached = cache.get(key);
		if (cached != null) {
			return cached;
		}
		if (!properties.containsKey(key)) {
			cache.put(key, defaultValue);
			return defaultValue;
		}
		String rawValue = properties.getProperty(key);
		Object value = null;
		switch (type) {
		case STRING:
			value = rawValue;
			break;
		case BOOLEAN:
			value = Boolean.parseBoolean(rawValue);
			break;
		case INTEGER:
			value = Integer.parseInt(rawValue);
			break;
		case LONG:
			value = Long.parseLong(rawValue);
			break;
		case FLOAT:
			value = Float.parseFloat(rawValue);
			break;
		case DOUBLE:
			value = Double.parseDouble(rawValue);
			break;
		case STRING_ARRAY:
			value = rawValue.split(",");
			break;
		default:
			value = defaultValue;
			break;
		}
		cache.put(key, value);
		return value;
	}
	
	public static boolean get(String key, boolean defaultValue) {
		return (Boolean)get(key, defaultValue, Type.BOOLEAN);
	}

	public static String get(String key, String defaultValue) {
		return (String)get(key, defaultValue, Type.STRING);
	}
	
	public static int get(String key, int defaultValue) {
		return (Integer)get(key, defaultValue, Type.INTEGER);
	}
	
	public static long get(String key, long defaultValue) {
		return (Long)get(key, defaultValue, Type.LONG);
	}
	
	public static String[] get(String key, String[] defaultValue) {
		return (String[])get(key, defaultValue, Type.STRING_ARRAY);
	}
	
	public static double get(String key, double defaultValue) {
		return (Double)get(key, defaultValue, Type.DOUBLE);
	}
	
	public static float get(String key, float defaultValue) {
		return (Float)get(key, defaultValue, Type.FLOAT);
	}

}
