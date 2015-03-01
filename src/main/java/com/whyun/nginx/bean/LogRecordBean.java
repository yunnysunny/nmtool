package com.whyun.nginx.bean;

public class LogRecordBean extends AbstractBean {
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
