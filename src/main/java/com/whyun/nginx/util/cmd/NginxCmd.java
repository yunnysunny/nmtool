package com.whyun.nginx.util.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whyun.nginx.IConst;
import com.whyun.util.cmd.CMDHelper;

public class NginxCmd {
	private static String RELOAD_NGINX_CMD = IConst.NGINX_BIN_PATH + " -s reload";
	private static final Logger LOGGER = LoggerFactory
		.getLogger(NginxCmd.class);
	
	static {
		if (IConst.IS_WIN) {
			int index = IConst.NGINX_BIN_PATH.lastIndexOf("nginx.exe");
			String nginxBasePath = IConst.NGINX_BIN_PATH.substring(0, index);
			RELOAD_NGINX_CMD = "start /D " + nginxBasePath + " nginx -s reload";
		} else {
			
		}
	}
	
	public static void reload() {
		CMDHelper cmd = new CMDHelper();
		cmd.execute(RELOAD_NGINX_CMD);
		String errmsg = cmd.getErrmsg();
		if (errmsg != null && !"".equals(errmsg)) {
			LOGGER.warn("重新加载nginx日志时出现异常：" + errmsg);
		}		
	}
}
