package com.whyun.nginx.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.whyun.nginx.Traffic;
import com.whyun.nginx.bean.HostCalcBean;

public class TrafficTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		List<HostCalcBean> map = Traffic.getHostTraffic();
		if (map != null) {
			for (HostCalcBean bean:map) {
				System.out.println(bean);
			}
		} else {
			System.out.println("没有读取到数据");
			fail("没有读取到数据");
		}
		
	}

}
