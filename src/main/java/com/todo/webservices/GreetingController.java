package com.todo.webservices;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.models.Greeting;
import com.todo.models.KeyValueModel;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {

	public static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	public Greeting greeting(
			@RequestParam(value = "name", defaultValue = "World")
			String name){
		
		//Spring uses Jackson to convert to json
		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}
	
	@RequestMapping(value = "/langgreet", method = RequestMethod.GET)
	public KeyValueModel greetingLocal(
			@RequestParam(value = "lang", defaultValue = "en-gb") String iso_lang){
		
		String output = iso_lang;
		String lang = "United Kingdom";
		
		switch(iso_lang){
			case "en": 
				output = "Hello";
				break;
			case "de-ch":
				lang = "German";
				output = "Grüezi";
				break;
			case "ar-ye":
				lang = "Arabic";
				output = "Al salaam a'alaykum";
				break;
		}
		
		return new KeyValueModel(lang, output);
	}
}
