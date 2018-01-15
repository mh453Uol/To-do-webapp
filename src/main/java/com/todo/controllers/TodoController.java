package com.todo.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.todo.models.Todo;

@Controller
@RequestMapping(value = "todo")
public class TodoController {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	
	public TodoController() {
		//seedTodos();
	}
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView Index(ModelAndView model){		
		todos.sort(Comparator.comparing(o -> o.getCompleteBy()));
		
		model.addObject("todos",todos);
		model.setViewName("index");
		
		return model;
	}
	
	@RequestMapping(value = "/create",method = RequestMethod.GET)
	public ModelAndView Create(ModelAndView model){
		
		model.setViewName("todoform");
		return model;
	}
	
	//@Valid executes validation annotations!
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView OnCreate(@Valid @ModelAttribute("todo") Todo todo,
			BindingResult result,
			ModelAndView model){
		
		if(result.hasErrors()){
			model.setViewName("todoform");
			return model;
		}
		
		todos.add(todo);
		model.setViewName("redirect:/spring-mvc/todo/index");
		return model;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable(value = "id") UUID id, 
			ModelAndView model){
		
		Todo edit = null;
		
		for(Todo t: todos){
			if(t.getId().equals(id)){
				edit = t;
			}
		}
	
		if(edit == null){
			model.setViewName("error");
			return model;
		}
		
		model.setViewName("todoform");
		model.addObject("todo", edit);
		model.addObject("isEditing",true);
	
		return model;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView onEdit(@Valid @ModelAttribute("todo") Todo todo,
			BindingResult result,
			ModelAndView model){
		
		if(result.hasErrors()){
			model.setViewName("todoform");
			model.addObject("isEditing",true);
			return model;
		}
		
		Integer index = null;
		
		for(int i=0; i < todos.size();i++){
			if(todos.get(i).getId().equals(todo.getId())){
				index = i;
			}
		}
	
		if(index == null){
			model.setViewName("error");
			return model;
		}
				
		todos.set(index, todo);
		
		model.setViewName("redirect:/spring-mvc/todo/index");
		
		return model;
	}
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable("id") UUID id,
			ModelAndView model){
		
		Integer index = null;

		for(int i=0; i < todos.size();i++){
			if(todos.get(i).getId().equals(id)){
				index = i;
			}
		}
		
		if(index == null){
			model.setViewName("error");
			return model;
		}
		
		todos.remove(index.intValue());
		
		model.setViewName("redirect:/spring-mvc/todo/index");
		
		return model;
	}
	
	/*
	@SuppressWarnings("deprecation")
	public void seedTodos(){
		
		if(todos.size() == 0){
			//seed list with data
			todos.add(new Todo("Learn Spring Web Services", "Follow official documentation",new Date(2018,1,17)));
		}
	}
	*/
}

