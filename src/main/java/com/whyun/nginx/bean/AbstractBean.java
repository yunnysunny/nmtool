package com.whyun.nginx.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whyun.util.ClassInfo;

public abstract class AbstractBean {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractBean.class);
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		try {
			return ClassInfo.show(this);
		} catch (Exception e) {
			LOGGER.warn("", e);
			return "";
		}
		
	}
}
