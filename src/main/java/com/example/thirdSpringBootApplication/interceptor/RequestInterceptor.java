package com.example.thirdSpringBootApplication.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.thirdSpringBootApplication.POJO.StudentException;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getHeader("auth-key").isEmpty()) {
			throw new StudentException(401, "Forbidden Error");
		}
		return super.preHandle(request, response, handler);
	}

}
