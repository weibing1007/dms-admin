package com.wb.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wb.model.User;

public class TokenUtil {
	private static long ONE_HOUR = System.currentTimeMillis() + 60 * 60 * 1000;	// 一小时有效时间
//	private static long ONE_MINUTE = System.currentTimeMillis() + 60 * 1000;	// 一分钟有效时间
	private static String TOKEN_SECRET = "123";	// Token秘钥
	
	/**
	 * 	生成Token签名，1分钟后过期
	 * @param user
	 * @return 加密的Token
	 */
	public static String createToken(User user) {
		try {
			String token = "";
			// 当前时间
			Date start = new Date();
			// 过期时间
			Date end = new Date(ONE_HOUR);
//			Date end = new Date(ONE_MINUTE);
			// 秘钥及加密算法
//			Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
			Algorithm algorithm = Algorithm.HMAC256("b97140ac-c732-47e6-ae4f-2dc3ddf81eb6");
			// 设置头部信息
			Map<String, Object> headerClaims = new HashMap<String, Object>(2);
			headerClaims.put("dfs", "java-jwt");
			headerClaims.put("dfa", "HS256");

			// {dfs=333, aud=long, exp=1589946056, iat=1589942456}
			token = JWT.create()
					.withHeader(headerClaims)
					.withClaim("dfs", "333")
					.withClaim("user_name", "{\"username\":\"001\",\"password\":\"001\",\"manageCom\":\"86\"}")
					.withAudience(user.getUserid())
					.withIssuedAt(start)
					.withExpiresAt(end)
					.sign(algorithm);
			return token;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 	解析Token
	 * @param token
	 */
	public static void  parseToken(String token) {
		
		String userid = JWT.decode(token).getAudience().get(0);
		Date date = JWT.decode(token).getExpiresAt();
		String head = JWT.decode(token).getHeader();
		Claim headerClaim = JWT.decode(token).getHeaderClaim("dfs");
		Claim claim = JWT.decode(token).getClaim("dfs");
		System.out.println(userid);
		System.out.println(date);
		System.out.println("head=" + head);
		System.out.println("headerClaim=" + headerClaim.asString());
		System.out.println("claim=" + claim.asString());
	}
	
	/**
	 * 	验证Token的有效性
	 * @param token
	 */
	public static boolean verifyToken(String token) {
		try {
			JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
			DecodedJWT result = jwtVerifier.verify(token);
			System.out.println(result.getExpiresAt());
			return true;
		} catch(Exception e) {
			System.out.println("Token已失效");
			return false;
		}
	}
	
	public static void main(String[] args) {
		// 测试案例一（生成Token） 
//		TokenUtil tu = new TokenUtil();
		User user = new User();
		user.setUserid("long");
		user.setPassword("123");
		String token = TokenUtil.createToken(user);
		System.out.println(token);
		
		// 测试案例二（获取Token信息）
//		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJsb25nIiwiZXhwIjoxNTc3OTM0NTI2LCJpYXQiOjE1Nzc5MzQ0NjZ9.cCk6x2is15DpZlp2EWKbFwYyEqYjKbIhNe-6hHQeSKY";
		TokenUtil.parseToken(token);
		
		// 测试案例三（验证Token有效性）
		TokenUtil.verifyToken(token);
	}
}
