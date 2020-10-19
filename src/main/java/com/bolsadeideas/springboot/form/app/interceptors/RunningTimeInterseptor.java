package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RunningTimeInterseptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(RunningTimeInterseptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("RunningTimeInterseptor: preHandle entrando...");

		request.setAttribute("startTime", System.currentTimeMillis());
		// emulando un demora entre 0 y 499 ms
		Random random = new Random();
		Integer delay = random.nextInt(500);
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
		logger.info("Time taken: postHandle saliendo...");

	}

}
