package com.whyun.nginx;

import com.whyun.nginx.util.config.ManageConfigProperty;

public interface IConst {
	public static final boolean IS_WIN = System.getProperty("os.name").toUpperCase().substring(0,3).equals("WIN");
	public static final ManageConfigProperty MANAGE_CONFIG = ManageConfigProperty.getInstance();
	
//	public static final int MODE_SERVER_OTHER = 0;
//	public static final int MODE_SERVER_WEB = 1;
//	public static final int MODE_SERVER_DB = 2;
	
//	public static final int MODE_IN_USE = MANAGE_CONFIG.getInt(
//			ManageConfigProperty.KEY_SERVER_MODE, MODE_SERVER_OTHER);
	
//	public static final int MONITOR_DISABLE = -1;
//	public static final int MONITOR_INTERVAL_DEFAULT = 3600;
	
//	public static final int MONITRO_HOST_INTERVAL = MANAGE_CONFIG.getInt(
//			ManageConfigProperty.KEY_MONITOR_HOST, MONITOR_INTERVAL_DEFAULT);
//	
//	public static final int MONITOR_DB_INTERVAL = MANAGE_CONFIG.getInt(
//			ManageConfigProperty.KEY_MONITOR_DB, MONITOR_INTERVAL_DEFAULT);
	
	public static final String NGINX_ACCESS_LOG_PATH_DEFAULT = IS_WIN ?
			"D:\\nginx-1.2.9\\logs\\access.log" :
				"/usr/nginx/logs/access.log";
	public static final String NGINX_ACCESS_LOG_PATH = MANAGE_CONFIG.getValue(
			ManageConfigProperty.KEY_NGINX_LOG_FILENAME, NGINX_ACCESS_LOG_PATH_DEFAULT);
	
	public static final String NGINX_BIN_PATH_DEFAULT= IS_WIN ?
			"d:\\nginx-1.2.9\\nginx.exe"  :
				"/usr/nginx/sbin/nginx";
	public static final String NGINX_BIN_PATH = MANAGE_CONFIG.getValue(
			ManageConfigProperty.KEY_NGINX_BIN_FILENAME, NGINX_BIN_PATH_DEFAULT);
	
	public static final String NGINX_VHOST_PATH_DEFAULT = IS_WIN ?
			"d:\\nginx-1.2.9\\conf\\vhost\\" :
				"/etc/nginx/conf/vhost/";
	public static final String NGINX_VHOST_PATH = MANAGE_CONFIG.getPath(
			ManageConfigProperty.KEY_NGINIX_VHOST_PATH, NGINX_VHOST_PATH_DEFAULT);
	
	public static final String NGINX_WEB_ROOT_DEFAULT = IS_WIN ?
			"D:\\nginx-1.2.9\\html\\" :
				"/data/www/";
	public static final String NGINX_WEB_ROOT = MANAGE_CONFIG.getPath(
			ManageConfigProperty.KEY_NGINIX_WEB_ROOT, NGINX_WEB_ROOT_DEFAULT);
	
//	public static final String MYSQL_BIN_PATH_DEFAULT = IS_WIN ?
//			"E:\\xampp\\mysql\\bin\\mysqldump.exe":
//				"/usr/mysql/bin/mysqldump";
//	public static final String MYSQL_DUMP_BIN_PATH = MANAGE_CONFIG.getValue(
//			ManageConfigProperty.KEY_MYSQL_DUMP_FILENAME, MYSQL_BIN_PATH_DEFAULT);
	
//	public static final String MYSQL_BACKUP_PATH_DEFAULT = IS_WIN ?
//			"E:\\" :
//				"/tmp/";
//	public static final String MYSQL_BACKUP_PATH = MANAGE_CONFIG.getPath(
//			ManageConfigProperty.KEY_MYSQL_BACKUP_PATH, MYSQL_BACKUP_PATH_DEFAULT);
	
//	public static final String MYSQL_DATA_PATH_DEFAULT = IS_WIN ?
//			"E:\\xampp\\mysql\\data\\" :
//				"/var/lib/mysql/";
//	public static final String MYSQL_DATA_PATH = MANAGE_CONFIG.getPath(
//			ManageConfigProperty.KEY_MYSQL_DATA_PATH, MYSQL_DATA_PATH_DEFAULT);
	
//	public static final String SERVER_ID = MANAGE_CONFIG.getValue(ManageConfigProperty.KEY_SERVER_ID);
	
//	public static final int IO_POOL_SIZE = MANAGE_CONFIG.getInt(ManageConfigProperty.KEY_IO_POOL_SIZE, 0);
	
//	public static final int IO_MODE_BIO = 0;
//	public static final int IO_MODE_NIO = 1;
//	public static final int IO_MODE = MANAGE_CONFIG.getInt(ManageConfigProperty.KEY_IO_MODE, IO_MODE_BIO);
	
//	public static final int MAX_READ_LINES = 100;
//	public static final int READ_INTEVAL = 7200;
//	public static final int SERVER_NODE_LISTEN_PORT = 8011;
	
//	public static final String REG_DB_NAME = "^[a-zA-Z0-9\\-_]{3,64}$";
//	public static final String REG_USERNAME = "^[a-zA-Z0-9\\-_]{3,16}$";
//	public static final String REG_HOST_IP = "^((%)|(\\d+\\.\\d+\\.(\\d+|%)\\.(\\d+|%)))$";
	
}
