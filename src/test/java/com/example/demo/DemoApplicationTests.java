package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		
		 BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
		 
		 System.out.println(encoder.encode("123456").trim());
		
	}

}
