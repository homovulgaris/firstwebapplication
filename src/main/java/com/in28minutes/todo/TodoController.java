package com.in28minutes.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.exception.BusinessException;
import com.in28minutes.security.SecurityUtils;

@Controller
@Scope("session")
@SessionAttributes("name")
public class TodoController {

    private static final Logger logger = Logger.getLogger(TodoController.class);

    @Autowired
    TodoService todoService;

    @Autowired
    SecurityUtils security;

    /*
     * treba dat bacha. existuje v springu zakladna konfiguracia (je v
     * toto-servlet.xml) tato konfiguracia vytvara spring kontext a vytvori
     * dispatchera tento dispatcher v zasade prijima vsetky requesty a rozdeluje ich
     * berue veci z web.xml
     */
    // @formatter:off
    /*
     * <servlet> <servlet-name>dispatcher</servlet-name> <servlet-class>
     * org.springframework.web.servlet.DispatcherServlet </servlet-class>
     * <init-param> <param-name>contextConfigLocation</param-name>
     * <param-value>/WEB-INF/todo-servlet.xml</param-value> </init-param>
     * <load-on-startup>1</load-on-startup> </servlet>
     *
     * <servlet-mapping> <servlet-name>dispatcher</servlet-name>
     * <url-pattern>/spring-mvc/*</url-pattern> </servlet-mapping>
     */
    // @formatter:on
    // cize toto z localhost:8080/spring-mvc/login
    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String listTodosOfUser(ModelMap model, HttpSession session) {

	String currentUser = security.getLoggedInUserName(model);
	model.addAttribute("todos", todoService.retrieveTodo(currentUser));
	model.addAttribute("name", currentUser);
	model.addAttribute("quantity", "Your");
	return "list-todos";

    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showTodosPage(ModelMap model) {
	model.put("todo", new Todo("Type in some description"));
	return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    logger.error("some error happend " + bindingResult.getAllErrors());
	    return "todo";
	}
	model.clear();
	todoService.addTodo(todo);
	// pojde na URL
	return "redirect:list-todos";
    }

    // delete-todo?id

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteToDo(@RequestParam int id) {
	todoService.deleteTodo(id);
	return "redirect:list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {

	Todo theTodo = todoService.retrieveTodo(id);
	model.put("todo", theTodo);
	logger.info("Updating old todo " + theTodo);
	return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
	model.clear();
	int i = 1;
	if (i == 0) {
	    throw new RuntimeException("Simulated exception");
	}
	if (i == 1) {
	    throw new BusinessException(BusinessException.BUSINESS_EXCEPTION);
	}
	String currentUser = security.getLoggedInUserName(model);
	if (bindingResult.hasErrors()) {
	    model.addAttribute("errorMessage", "Some error ocured during updating given todo!");
	    return "todo";
	}
	logger.info("Previous todo " + todoService.retrieveTodo(todo.getId()));
	logger.info("New todo " + todo);
	todoService.deleteTodo(todo.getId());
	todoService.addTodo(todo, todo.getUser() == null ? currentUser : todo.getUser());

	return "redirect:list-todos";
    }

    @RequestMapping(value = "/list-todos-all", method = RequestMethod.GET)
    public String showAllTodos(ModelMap model) {
	model.addAttribute("todos", todoService.retrieveTodo());
	model.addAttribute("quantity", "All");
	return "list-todos";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, Exception exception) {
	logger.error(new StringBuilder().append("Request").append(" ").append(req.getRequestURL()).append(" raised ")
		.append(exception));
	req.setAttribute("errorMessage", exception.getMessage());
	return "error";
    }

}
