package com.whyun.nginx.util.config;

import com.whyun.util.config.PropertyConfig;



public class ManageConfigProperty extends PropertyConfig {
	
	private static final  String configName = "manage.properties";
	private volatile static ManageConfigProperty instance = null;
	public static final String KEY_SERVER_MODE = "serverMode";
	public static final String KEY_NGINX_LOG_FILENAME = "nginx.log.filename";
	public static final String KEY_NGINX_BIN_FILENAME = "nginx.bin.filename";
	public static final String KEY_NGINIX_VHOST_PATH = "nginx.vhost.path";
	public static final String KEY_NGINIX_WEB_ROOT = "nginx.webRoot";
//	public static final String KEY_MYSQL_DUMP_FILENAME = "mysql.dump.filename";
//	public static final String KEY_MYSQL_BACKUP_PATH = "mysql.backup.path";
//	public static final String KEY_MONITOR_HOST = "monitor.host.interval";
//	public static final String KEY_MONITOR_DB = "monitor.db.interval";
//	public static final String KEY_MYSQL_DATA_PATH = "mysql.data.path";
//	public static final String KEY_MONITOR_HOST_SDS = "monitor.host.sds";
//	public static final String KEY_MONITOR_DB_SDS = "monitor.db.sds";
//	public static final String KEY_SERVER_ID = "serverid";
//	public static final String KEY_SDS_CENTER = "sdsCenterKey";
//	public static final String KEY_SERVER_KEY = "serverKey";
//	public static final String KEY_IO_MODE = "ioMode";
//	public static final String KEY_IO_POOL_SIZE = "ioPoolSize";

	protected ManageConfigProperty() {
		super(configName, false);
	}
	
	public static ManageConfigProperty getInstance() {
		if (instance == null) {
			synchronized (ManageConfigProperty.class) {
				if (instance == null) {
					instance = new ManageConfigProperty();
				}
			}
		}
		
		return instance;
	}
	public  void reload() {
		instance = null;
	}
}
