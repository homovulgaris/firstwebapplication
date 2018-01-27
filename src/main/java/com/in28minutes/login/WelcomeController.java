package com.in28minutes.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.in28minutes.security.SecurityUtils;

@Controller
public class WelcomeController {

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
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		/*
		 * z todo-servlet vo web-inf pouzije resolvera pozri bean
		 * org.springframework.web.servlet.view.InternalResourceViewResolver
		 */
		model.addAttribute("title", "Welcome");
		model.addAttribute("name", security.getLoggedInUserName(model));
		
		return "welcome";
	}

}
