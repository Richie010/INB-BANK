package com.mind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hellodemo {

	public static void main(String[] args) {
		SpringApplication.run(Hellodemo.class, args);
		System.out.println("hello");
	}

}
