package com.todo.webservices;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.models.RandomUUID;

@RestController
@RequestMapping(value = "/api/random")
public class RandomUUIDController {

	@RequestMapping(value = "/ids", method = RequestMethod.GET)
	public ArrayList<RandomUUID> RandomUUIDs(
			@RequestParam(name = "quantity", defaultValue = "1") int quantity)
	{
		ArrayList<RandomUUID> randomsUUIDs = new ArrayList<RandomUUID>();
		
		if(quantity > 1000*5){
			//Too much resources !
			return randomsUUIDs; 
		}
		
		for(int i=0;i < quantity;i++){
			randomsUUIDs.add(new RandomUUID());
		}
		
		return randomsUUIDs;
	}
	
	//Randomly generate numbers user provides the number of digits a 
	//number can have i.e 3 so max number is 100, 4 -> 1000 etc
	//Generate numbers based on digits and quantity user wants
	@RequestMapping(value = "/numbers/{digit}", method = RequestMethod.GET)
	public ArrayList<Integer> RandomNumbers(
			@RequestParam(name = "quantity", defaultValue = "1") int quantity,
			@PathVariable(value = "digit") int digits){
		
		Random rand = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		if(Math.pow(10.0,(digits - 1)) > Integer.MAX_VALUE){
			return null;
		}
		
		int maxValue = (int) Math.pow(10.0,(double) (digits - 1));
		
		for(int i = 0; i < quantity;i++){
			numbers.add(rand.nextInt(maxValue));
		}
		return numbers;
	}
	
	
	
	
}
