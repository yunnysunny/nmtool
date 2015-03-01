package com.whyun.nginx.bean;

/**
 * 流量统计bean
 */
public class HostCalcBean extends AbstractBean {
	
	/**  域名. */
	private String domain;
	
	/**  流量，单位B. */
	private long traffic;
	
	/**
	 * Instantiates a new host calc bean.
	 */
	public HostCalcBean() {
		
	}
	
	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	/**
	 * Gets the traffic.
	 *
	 * @return the traffic
	 */
	public long getTraffic() {
		return traffic;
	}
	
	/**
	 * Sets the traffic.
	 *
	 * @param traffic the new traffic
	 */
	public void setTraffic(long traffic) {
		this.traffic = traffic;
	}
	
	
	
}
