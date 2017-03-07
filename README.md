# spring-mvc-validator 
## 教程

1、添加spring-mvc拦截器
<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/*/*" />
			<bean class="com.dqc.trydaima.web.interceptor.AnnotationInterceptor" />
		</mvc:interceptor>
	</mvc:interceptors>
2、编写自己的验证器类（如）
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

3、方法上使用注解
@RequestParamsValidate(RegisterValidator.class)

第一次提交，如果有什么好的想法，请评论！
