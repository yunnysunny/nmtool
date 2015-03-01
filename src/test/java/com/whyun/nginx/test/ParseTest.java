package com.whyun.nginx.test;

import com.whyun.nginx.bean.AbstractBean;

public class ParseTest {
	public static class LogRecord extends AbstractBean {
		private String serverName;
		private String status;
		private int bodyBytes;
		private String remoteAddr;
		private String time;
		private String requestPath;
		private String httpReferer;
		private String httpUserAgent;
		public String getServerName() {
			return serverName;
		}
		public void setServerName(String serverName) {
			this.serverName = serverName;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getBodyBytes() {
			return bodyBytes;
		}
		public void setBodyBytes(int bodyBytes) {
			this.bodyBytes = bodyBytes;
		}
		public String getRemoteAddr() {
			return remoteAddr;
		}
		public void setRemoteAddr(String remoteAddr) {
			this.remoteAddr = remoteAddr;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getRequestPath() {
			return requestPath;
		}
		public void setRequestPath(String requestPath) {
			this.requestPath = requestPath;
		}
		public String getHttpReferer() {
			return httpReferer;
		}
		public void setHttpReferer(String httpReferer) {
			this.httpReferer = httpReferer;
		}
		public String getHttpUserAgent() {
			return httpUserAgent;
		}
		public void setHttpUserAgent(String httpUserAgent) {
			this.httpUserAgent = httpUserAgent;
		}
		
	}
	private static void setRequestParams(LogRecord record, int wordCount, String wordNow) {
		/**
		 * 日志格式：
		 * log_format main '$server_name $status $body_bytes_sent $remote_addr [$time_local] "$request" '
					'"$http_referer" "$http_user_agent" $http_x_forwarded_for';
		 * */
		switch(wordCount) {
		case 1:
			record.setServerName(wordNow);
			break;
		case 2:
			record.setStatus(wordNow);
			break;
		case 3:
			int bodyBytes = 0;
			try {
				bodyBytes = Integer.parseInt(wordNow);
			} catch (Exception e) {
				
			}
			record.setBodyBytes(bodyBytes);
			break;
		case 4:
			record.setRemoteAddr(wordNow);
			break;
		case 5:
			record.setTime(wordNow);
			break;
		case 6:
			record.setRequestPath(wordNow);
			break;
		case 7:
			record.setHttpReferer(wordNow);
			break;
		case 8:
			record.setHttpUserAgent(wordNow);
			break;
		default:
			break;
		}
	}
	
	public static LogRecord parse(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		LogRecord record = new LogRecord();
		int wordCount = 0;
		int len = str.length();
		char lastToken = 0;
		boolean tokenBegin = false;
		boolean tokenEnd = false;
		String lastWord = "";
		for(int i=0;i<len;i++) {
			char c = str.charAt(i);
			if(i == len-1) {
				wordCount ++;
				lastWord +=c;
				setRequestParams(record,wordCount,lastWord);
				break;
			}
			switch (c) {
			case ' ':
				if (tokenBegin && !tokenEnd) {//token还未结束
					lastWord += c;
					continue;
				} else if (tokenEnd){//token已经结束
					lastToken = 0;
					tokenBegin = false;
					continue;//略过
				} else {//正常情况
					wordCount++;

					setRequestParams(record,wordCount,lastWord);
					lastWord = "";
				}
				break;
			case '[':
				lastToken = '[';
				lastWord = "";//置当前的单词为空
				tokenBegin = true;
				tokenEnd = false;
				break;
			case ']':
				lastToken = ']';
				tokenEnd = true;
				wordCount++;

				setRequestParams(record,wordCount,lastWord);
				lastWord = "";
				break;
			case '"':
				if (lastToken == '"') {//第二个引号
					lastToken = 0;
					tokenEnd = true;
					wordCount ++;
					setRequestParams(record,wordCount,lastWord);
					lastWord = "";//置当前的单词为空
				} else {//第一个引号
					lastToken = '"';
					tokenBegin = true;
					tokenEnd = false;
					lastWord = "";//置当前的单词为空
				}
				break;
			
			default:
				lastWord += c;
				break;
			}
		}
		return record;
	}
	public static void main(String[] args) {
		String log = "www.a.com 200 612 127.0.0.1 [31/Jan/2015:15:02:38 +0800] \"GET / HTTP/1.0\" \"-\" \"ApacheBench/2.3\" -";
		LogRecord r = parse(log);
		System.out.println(r);
	}
}
