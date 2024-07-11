package com.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstAPI {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Rakesh";
	}
	@RequestMapping("/hello/{name}")
	public String sayHelloname(@PathVariable("name") String name) {
		return "hello" + name;
	}

}
