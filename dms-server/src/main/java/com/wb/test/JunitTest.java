package com.wb.test;

import org.junit.Test;
import org.springframework.util.StringUtils;

import com.wb.model.User;

public class JunitTest {

	@Test
	public void StringUtilsTest() {
		User user = new User();
		boolean boo = StringUtils.isEmpty(user);
		System.out.println(boo);
	}
}
