package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.Greeting;

@RestController
public class GreetingController {

	public static final String template = "Hello, %s";
	
	public final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")
	public Greeting greetingReq(@RequestParam(value="name", defaultValue = "World")String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	
	
}
