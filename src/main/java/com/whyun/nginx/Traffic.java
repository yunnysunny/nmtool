package com.whyun.nginx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whyun.nginx.IConst;
import com.whyun.nginx.bean.HostCalcBean;
import com.whyun.nginx.util.cmd.NginxCmd;

public class Traffic {
	
	private static final Logger LOGGER = LoggerFactory
		.getLogger(Traffic.class);

	/**
	 * 读取日志操作
	 *
	 * @param logFile 日志文件
	 * @return 返回日志统计信息的哈希数据结构，键名为站点的域名，
	 * 值为当前站点的流量统计，单位为bit
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Map<String, Double> readLog(File logFile)
			throws IOException {
		Map<String, Double> map = new HashMap<String, Double>();
		FileReader reader = new FileReader(logFile);
		BufferedReader br = null;
		String s1 = null;
		StringTokenizer tokenStat = null;
		/**
		 * 日志格式：
		 * log_format main '$server_name $status $body_bytes_sent $remote_addr [$time_local] "$request" '
					'"$http_referer" "$http_user_agent" $http_x_forwarded_for';
		 * */
		try {
			br = new BufferedReader(reader);
			while ((s1 = br.readLine()) != null) {
				tokenStat = new StringTokenizer(s1);
				String serverName = tokenStat.nextToken();//1.域名
				
				if (tokenStat.hasMoreTokens()) {
					tokenStat.nextToken();//2.staus
				} else {
					break;
				}
				
		        
				if (tokenStat.hasMoreTokens()) {
					String contentLen = tokenStat.nextToken();//7.请求数据大小

			        int contentLenInt = 0;
			        try {
			        	contentLenInt = Integer.parseInt(contentLen);
			        } catch (Exception e) {
			        	
			        }
			        if (map.containsKey(serverName)) {
			        	map.put(serverName, map.get(serverName) + contentLenInt);
			        } else {
			        	map.put(serverName, (double)contentLenInt);
			        }
				}
		        
			}
		} finally {
			if (br != null) {
				br.close();
			}
			if (reader != null) {
				reader.close();
			}			
		}
		
		
		return map;
	}
	
	

	/**
	 * 获取各个server的流量
	 *
	 * @return the host traffic
	 */
	public static List<HostCalcBean> getHostTraffic() {
		Map<String, Double> map = null;
		List<HostCalcBean> list = new ArrayList<HostCalcBean>();
		File logFile = new File(IConst.NGINX_ACCESS_LOG_PATH);

		if (logFile != null && logFile.exists()) {// file exist
			File newFile = new File(IConst.NGINX_ACCESS_LOG_PATH + ".read");
			newFile.delete();
			
			boolean rename = logFile.renameTo(newFile);//
			if (!rename) {
				LOGGER.warn("重命名失败");
			}
			NginxCmd.reload();//无论如何都重新加载一遍nginx日志
			
			try {
				map = readLog(newFile);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("日志分析完成");
				}
				HostCalcBean bean;
				for(Map.Entry <String, Double>   entry   :   map.entrySet()) {
					bean = new HostCalcBean();
					bean.setDomain(entry.getKey());
					double traffic = entry.getValue();
					long trafficLong = traffic < 1 ? 1 : (long)traffic;
					bean.setTraffic(trafficLong);
					
					list.add(bean);
				}
			} catch (IOException e) {
				LOGGER.warn("读取日志时出错", e);
			}
			//
			//
			newFile.delete();
		} else {
			LOGGER.warn("没有找到日志");
		}
		
		return list;
	}

	public static void main(String argv[]) {

		List<HostCalcBean> map = Traffic.getHostTraffic();
		if (map != null) {
			for (HostCalcBean bean:map) {
				System.out.println(bean);
			}
		} else {
			System.out.println("没有读取到数据");
		}
		
	}
}
