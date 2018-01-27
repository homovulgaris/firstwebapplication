package com.in28minutes.todo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int todoCount = 3;

	static {

		todos.add(new Todo(1, "test", "Make your homeworks", LocalDateTime.now().plusSeconds(885), false));
		todos.add(new Todo(2, "test", "Prepare some lunch", LocalDateTime.now().plusSeconds(12), false));
		todos.add(new Todo(3, "test", "Do your job", LocalDateTime.now().minusSeconds(1552), true));

	}

	public List<Todo> retrieveTodo(String user) {

		return todos.stream().filter(p -> p.getUser().equals(user)).collect(Collectors.toList());
	}

	public boolean addTodo(String name, String desc, LocalDateTime targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
		return true;
	}

	public boolean addTodo(Todo todo) {
		todos.add(new Todo(++todoCount, todo.getUser(), todo.getDesc(), todo.getTargetDate(), todo.isDone()));
		return true;
	}

	public boolean deleteTodo(int id) {
		if (findTodo(id).isPresent()) {
			todos.remove(findTodo(id).get());
			return true;
		}
		return false;
	}

	private Optional<Todo> findTodo(int id) {
		return todos.stream().filter(p -> p.getId() == id).findAny();
	}

	public Todo retrieveTodo(int id) {

		return findTodo(id).isPresent() ? findTodo(id).get() : null;

	}

	public List<Todo> retrieveTodo() {
		return todos;
	}

	public void addTodo(Todo todo, String currentUser) {
		todos.add(new Todo(++todoCount, currentUser, todo.getDesc(), todo.getTargetDate(), todo.isDone()));
	}

}
