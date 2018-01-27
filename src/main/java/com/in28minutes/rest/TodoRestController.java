package com.in28minutes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.todo.Todo;
import com.in28minutes.todo.TodoService;

@RestController
public class TodoRestController {

    @Autowired
    TodoService todoService;

    @RequestMapping(path = "/all-todos")
    public List<Todo> getAllTodos() {
	return todoService.retrieveTodo();

    }

    @RequestMapping(path = "/all-todos/{user}")
    public List<Todo> getUserTodos(@PathVariable String user) {
	return todoService.retrieveTodo(user);
    }
}
