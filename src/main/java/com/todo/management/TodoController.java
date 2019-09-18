package com.todo.management;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;

//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService service;

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodosList(ModelMap model) {
		String user = getLoggedInUserName();
		model.addAttribute("todos", service.retrieveTodos(user));
		
		model.addAttribute("name", user);
		return "list-todos";
	}

	
	
	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String showTodosList(ModelMap model) {

		model.addAttribute("todo", new Todo());
		return "todo";
	}
	
	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid @ModelAttribute Todo todo, BindingResult result) {
		
		if (result.hasErrors()) {
			return "todo";
		}
		else {
			service.addTodo(getLoggedInUserName(), todo.getDesc(), new Date(), false);
			model.clear();// to prevent request parameter "name" to be passed
			return "redirect:/list-todos";
		}
	}
	
	@RequestMapping(value = "/update-todos", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {
		Todo todo=service.retrieveTodo(id);
		model.addAttribute(todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todos", method = RequestMethod.POST)
	public String updateTodos(ModelMap model, @Valid @ModelAttribute Todo todo, BindingResult result) {
		
		if (result.hasErrors()) {
			return "todo";
		}
		else {
			todo.setUser(getLoggedInUserName());
			todo.setTargetDate(new Date());
			service.updateTodo(todo);
			return "redirect:/list-todos";
		}
	}
	
	@RequestMapping(value = "/delete-todos", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}


	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

}
