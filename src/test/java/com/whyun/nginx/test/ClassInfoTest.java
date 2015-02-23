package com.whyun.nginx.test;

import org.junit.Test;

import com.whyun.nginx.bean.HostCalcBean;

public class ClassInfoTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		HostCalcBean bean = new HostCalcBean();
		bean.setDomain("xxxx");
		bean.setTraffic(2222);
		System.out.println(bean);
	}

}
