package com.dqc.trydaima.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dqc.trydaima.web.common.RequestParamsValidate;
import com.dqc.trydaima.web.validate.Validator;

public class AnnotationInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Method[] declaredMethods = handler.getClass().getDeclaredMethods();
		String requestURI = request.getRequestURI();
		String substring = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.length());
		for (Method method : declaredMethods) {
			if (method.getName().toLowerCase().equals(substring.toLowerCase())) {
				RequestParamsValidate annotation = method.getAnnotation(RequestParamsValidate.class);
				if (annotation == null) {
					return true;
				}
				Class<?> value = annotation.value();
				if (value == null) {
					return true;
				}
				Object newInstance = value.newInstance();
				if (newInstance instanceof Validator) {
					Method method2 = value.getSuperclass().getDeclaredMethod("validateBefore", HttpServletRequest.class,
							HttpServletResponse.class);
					method2.setAccessible(true);
					method2.invoke(newInstance, request, response);
				}
			}
		}

		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
