package com.hhly.smartdata.security;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String errorClassName = (String) request.getAttribute("shiroLoginFailure");
		//校验失败
		if(!StringUtils.isEmpty(errorClassName)){
       	 return true;
       }
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				return true;
			}
		} else {
			redirectToLogin(request, response);
			return false;
		}
	}
}
