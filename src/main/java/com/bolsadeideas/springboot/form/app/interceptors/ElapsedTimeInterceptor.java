package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Este interceptor se aplica a todas las rutas, para modificar que rutas puede
 * interceptar se debe configurar desde la clase de configuracion en donde se
 * agrega el interceptor a Spring
 */
@Component("elapsedTimeInterceptor")
public class ElapsedTimeInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ElapsedTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// para aplicar el interceptor en el caso de existir 2 metodos con la misma ruta
		if (request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			logger.info("Es un método del controlador: " + method.getMethod().getName());

		}
		logger.info("RunningTimeInterseptor: preHandle() entrando...");
		logger.info("Intercepting: " + handler);

		request.setAttribute("startTime", System.currentTimeMillis());
		// emulando un demora entre 0 y 499 ms
		Random random = new Random();
		Integer delay = random.nextInt(100);
		Thread.sleep(delay);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		if (modelAndView != null) {
			modelAndView.addObject("elapsedTime", elapsedTime);

		}
		logger.info("RunningTimeInterseptor: " + elapsedTime + "ms");
		logger.info("Time taken: postHandle() saliendo...");

	}

}
