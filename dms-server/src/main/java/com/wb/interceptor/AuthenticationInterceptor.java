package com.wb.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wb.annotation.PassToken;
import com.wb.annotation.UserLoginToken;
import com.wb.model.User;
import com.wb.service.UserService;
import com.wb.util.Result;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();
		// 检查是否有passtoken注释，有则跳过认证
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				return true;
			}
		}
		// 检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				// 执行认证
				if (token == null) {
					String json = Result.buildJson(0, "Token为空，请重新登录！", null);
					returnJson(httpServletResponse, json);
					return false;
//					throw new RuntimeException("无token，请重新登录");
				}
				// 获取 token 中的 user id
				String userId = null;
				try {
					userId = JWT.decode(token).getAudience().get(0);
				} catch (JWTDecodeException j) {
					String json = Result.buildJson(2, "Token解析异常！", null);
					returnJson(httpServletResponse, json);
					return false;
//					throw new RuntimeException("401");
				}
				User user = userService.findUserById(userId);
				if (user == null) {
					String json = Result.buildJson(0, "用户不存在，请重新登录！", null);
					returnJson(httpServletResponse, json);
					return false;
//					throw new RuntimeException("用户不存在，请重新登录");
				}
				// 验证 token
				JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
				try {
					jwtVerifier.verify(token);
				} catch (JWTVerificationException e) {
					String json = Result.buildJson(2, "Token验证失败，Token已过期或其他原因！", null);
					returnJson(httpServletResponse, json);
					return false;
//					throw new RuntimeException("401");
				}
				return true;
			}
		}
		return true;
	}
	
	/* 返回客户端数据 */
	private void returnJson(HttpServletResponse response, String json) throws Exception {
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			writer = response.getWriter();
			writer.print(json);

		} catch (IOException e) {
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}
