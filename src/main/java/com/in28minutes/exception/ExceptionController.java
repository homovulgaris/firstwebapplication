package com.in28minutes.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice /* will run the code for all controllers */
/**
 *
 * Specialization of @Component for classes that
 * declare @ExceptionHandler, @InitBinder, or @ModelAttribute methods to be
 * shared across multiple @Controller classes. Classes with @ControllerAdvice
 * can be declared explicitly as Spring beans or auto-detected via classpath
 * scanning. All such beans are sorted via AnnotationAwareOrderComparator, i.e.
 * based on @Order and Ordered, and applied in that order at runtime. For
 * handling exceptions, an @ExceptionHandler will be picked on the first advice
 * with a matching exception handler method. For model attributes and InitBinder
 * initialization, @ModelAttribute and @InitBinder methods will also
 * follow @ControllerAdvice order.
 *
 * Note: For @ExceptionHandler methods, a root exception match will be preferred
 * to just matching a cause of the current exception, among the handler methods
 * of a particular advice bean. However, a cause match on a higher-priority
 * advice will still be preferred to a any match (whether root or cause level)
 * on a lower-priority advice bean. As a consequence, please declare your
 * primary root exception mappings on a prioritized advice bean with a
 * corresponding order!
 *
 * By default the methods in an @ControllerAdvice apply globally to all
 * Controllers. Use selectors annotations(), basePackageClasses(), and
 * basePackages() (or its alias value()) to define a more narrow subset of
 * targeted Controllers. If multiple selectors are declared, OR logic is
 * applied, meaning selected Controllers should match at least one selector.
 * Note that selector checks are performed at runtime and so adding many
 * selectors may negatively impact performance and add complexity.
 *
 */
@EnableWebMvc
/**
 *
 * @EnableWebMvc is equivalent to <mvc:annotation-driven /> in XML. It enables
 *               support for @Controller-annotated classes that
 *               use @RequestMapping to map incoming requests to a certain
 *               method. You can read detailed information about what it
 *               configures by default and how to customise the configuration in
 *               the reference
 *
 */

/**
 *
 * @author d60548
 *
 *         This class with help of ControllerAdvice will handle all exceptions
 *         Which are occurred (because of exception handler on method with value
 *         Exception.class)
 *
 *         Normally, we use @ExceptionHandler to decide which “view” should be
 *         returned back if certain exception is raised.
 */
public class ExceptionController {

    private Logger log = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(value = BusinessException.class)
    public String handleError(HttpServletRequest req, Exception exception) {
	log.error(new StringBuilder().append("Request").append(" ").append(req.getRequestURL()).append(" raised ")
		.append(exception));
	req.setAttribute("errorMessage", exception.getMessage());
	return "error";
    }
}
