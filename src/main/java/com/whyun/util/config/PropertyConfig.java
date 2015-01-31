package com.whyun.util.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.whyun.util.observer.Changeable;
import com.whyun.util.observer.PropertyChangeListener;

public abstract class PropertyConfig implements Changeable {
	private static  Logger logger = Logger.getLogger(PropertyConfig.class);
	protected  String configName;
	private  Properties p = null;
	
	public String getConfigName() {
		return configName;
	}

	protected   PropertyConfig(String configName) throws IOException {
		p = new OrderedProperties();
		String configPath = ConfigFileUtil.getPath(PropertyConfig.class,configName);
		if (configPath != null) {			
			InputStream input = new FileInputStream(configPath);
			p.load(input);
			this.configName = configName;
			PropertyChangeListener.addToListener(this);
		} else {
			logger.error("没有发现配置文件" + configName);
			throw new IOException("没有发现配置文件" + configName);
		}
	}
	
	protected   PropertyConfig(String configName, boolean failedOnError) {
		p = new OrderedProperties();
		String configPath = ConfigFileUtil.getPath(PropertyConfig.class,configName);
		if (configPath != null) {			
			InputStream input;
			try {
				input = new FileInputStream(configPath);
				p.load(input);
				this.configName = configName;
				PropertyChangeListener.addToListener(this);
			} catch (IOException e) {
				logger.warn("读取配置文件时" + configName + "失败",e);
				if (failedOnError) {
					throw new RuntimeException("读取配置文件时" + configName + "失败",e);
				}
			}
		} else {
			if (failedOnError) {
				throw new RuntimeException("没有发现配置文件" + configName);
			}
		}
	}
	
	/**
	 * 根据配置文件中的键，返回其字符串类型的值
	 * 
	 * @param key the key
	 * 
	 * @return the value
	 */
	public  String getValue(String key) {
		String value = p.getProperty(key);
		return value;
	}
	
	public String getValue(String key,String defaultValue) {
		String value = getValue(key);
		value = (value == null || "".equals(value)) ?
				defaultValue :
					value;
		return value;
	}
	
	private String fullPath(String path) {
		if (path != null && !"".equals(path) && !path.endsWith(File.separator)) {
			path += File.separator;
		}
		return path;
	}
	
	public String getPath(String key) {
		String value = p.getProperty(key);
		
		return fullPath(value);
	}
	
	public String getPath(String key,String defaultValue) {
		String value = getValue(key, defaultValue);
		return fullPath(value);
	}
	
	/**
	 * 根据配置文件中的键，返回其整数类型的值，如果不能转化为整数，返回0.
	 * 
	 * @param key the key
	 * 
	 * @return the int
	 */
	public  int getInt(String key) {
		String str = getValue(key);
		int valueInt = 0;
		if (str != null) {
			try {
				valueInt = Integer.parseInt(str);
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		logger.debug(key + "->" + valueInt);
		return valueInt;
	}
	
	public int getInt(String key, int defaultValue) {
		int value = getInt(key);
		value = value > 0?
				value:
					defaultValue;
		return value;
	}
	
	public  void traceInfo(String key) {
		logger.info(key + "->" + p.getProperty(key));
	}

	public Properties getProperties() {
		return p;
	}
	
	
	
	public static class OrderedProperties extends Properties {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Vector<String> keys;

		public OrderedProperties() {
			super();
			keys = new Vector<String>();
		}

		public Enumeration<String> propertyNames() {
			return keys.elements();
		}

		public Object put(Object key, Object value) {
			if (keys.contains(key)) {
				keys.remove(key);
			}

			keys.add((String)key);

			return super.put(key, value);
		}

		public Object remove(Object key) {
			keys.remove(key);

			return super.remove(key);
		}

	}
}

