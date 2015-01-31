package com.whyun.nginx.test;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whyun.nginx.util.cmd.NginxCmd;

public class FileTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileTest.class);
	public static void main(String[] args) {
		File logFile = new File("E:\\mywamp\\nginx-1.6.2\\logs\\access.log");
		if (logFile != null && logFile.exists()) {// file exist
			File newFile = new File("E:\\mywamp\\nginx-1.6.2\\logs\\access.log.read");
			newFile.delete();
			
			boolean rename = logFile.renameTo(newFile);//
			if (!rename) {
				LOGGER.warn("重命名失败");
			} else {
				NginxCmd.reload();
			}
			newFile.delete();
		} else {
			LOGGER.warn("file not exist");			
		}
	}
}
