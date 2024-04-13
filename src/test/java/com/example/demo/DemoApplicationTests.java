package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(System.getProperty("user.dir"));
		var file = new File("2022.xml");
	}

}
