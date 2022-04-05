package org.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
	
	@GetMapping("/test/{name}")
	public String sayHello(@PathVariable String name) {
		System.out.println(name);
		return "Hello " + name;
	}

}
