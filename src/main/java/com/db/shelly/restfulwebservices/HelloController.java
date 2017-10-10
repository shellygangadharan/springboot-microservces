package com.db.shelly.restfulwebservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	ResourceBundleMessageSource messageSource;
	
	@GetMapping(value="/hello/helloWorld")
	public String helloWorld(){
		return "Hello World";
	}

	@GetMapping(value="/helloWorldBean/path/{name}")
	public HelloWorldBean helloWorldBean( @PathVariable String name){
		return new HelloWorldBean("Hello World"+name);
	}
	
	@GetMapping(value="/helloWorldBean")
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hello World");
	}

	@GetMapping(value="/helloWorldBeanInt")
	public String helloWorldBeanInt(@RequestHeader(name="Accept-Language",required=false) Locale locale){
		String msg= messageSource.getMessage("good.morning.message", null,locale) ;
		return msg;
	}
}
