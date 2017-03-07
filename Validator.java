package com.dqc.trydaima.web.validate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dqc.trydaima.web.exception.ValidatorException;

public abstract class Validator {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected abstract void validate(HttpServletRequest request);

	protected abstract void handleError(String controllerKey);

	protected final void validateBefore(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.request = request;
			validate(request);
		} catch (ValidatorException e) {
			this.response = response;
			handleError(request.getRequestURI());
		}
	}

	protected void error(String errorKey, String errorMessage) {
		request.setAttribute(errorKey, errorMessage);
		throw new ValidatorException();
	}

	protected void validateRequired(String field, String errorKey, String errorMessage) {
		String parameter = request.getParameter(field);
		if (parameter == null || "".equals(parameter)) {
			error(errorKey, errorMessage);
		}
	}

	protected void forword(String forwordPath) {
		try {
			request.getRequestDispatcher(forwordPath).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void redirect(String redirectPath) {
		try {
			response.sendRedirect(redirectPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
