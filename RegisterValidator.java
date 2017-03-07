package com.dqc.trydaima.web.validate;

import javax.servlet.http.HttpServletRequest;

public class RegisterValidator extends Validator {

	protected void validate(HttpServletRequest request) {
		validateRequired("userName", "userNameIsNull", "测试一下");
		validateRequired("password", "passwordIsNull", "测试一下");
	}

	@Override
	protected void handleError(String controllerKey) {
		forword("user/login");
	}

}
