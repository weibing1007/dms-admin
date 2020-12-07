package com.wb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wb.model.User;
import com.wb.service.UserService;
import com.wb.util.Result;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	HttpServletRequest request;

	/**
	 * 	注册
	 * @param user
	 * @return
	 */
	@PostMapping("/api/user/register")
	public int userRegister(@RequestBody User user) {
		return userService.userRegister(user);
	}
	
	/**
	 * 	登录
	 * @param user
	 * @return
	 */
	@PostMapping("/api/user/login")
	public Result<Map<String, String>> userLogin(@RequestBody User user) {
		return userService.userLogin(user);
	}
	
	/**
	 * 	获取个人信息
	 * @param userid
	 * @return
	 */
	@GetMapping("/api/user/getUserInfo")
	public User findUserById(@RequestParam String userid) {
		System.out.println(userid);
		return userService.findUserById(userid);
	}
	
	
	@PostMapping("/api/user/modifyUser")
	public int modifyUserById(@RequestBody User user) {
		return userService.modifyUserById(user);
	}
	
	/*测试token  不登录没有token*/
//    @UserLoginToken
    @PostMapping("/api/user/getMessage")
    public String getMessage(@RequestBody String json){
//        return "你已通过验证";
    	System.out.println("请求数据： " + json);
//    	System.out.println(request.getHeader("Appkey"));
    	
    	
    	
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("retCode", "001");
    	jsonObject.put("retMessage", "成功");
    	jsonObject.put("insurerUuid", "In000000000098");
    	
    	JSONObject data = new JSONObject();
    	data.put("multiCompany", "Y");
    	data.put("majorDiseasePayment", "Y");
    	data.put("disability", "Y");
    	data.put("dense", "Y");
    	data.put("accumulativeMoney", "Y");
    	data.put("pageQueryCode", "201801101140007520509");
    	data.put("tagDate", "2017-12-19");
    	data.put("displayPage", "Y");
    	jsonObject.put("data", data);
    	
    	JSONObject rootObject = new JSONObject();
    	JSONObject headObject = new JSONObject();
    	JSONObject bodyObject = new JSONObject();
    	headObject.put("tid", "");
		headObject.put("appid", "191928188002038");
		headObject.put("zone", "UTC+8");
		headObject.put("time", "201901018153423");
		headObject.put("asyn", "N");
		headObject.put("sign", "1211191999191993");
		bodyObject.put("code", "0");
		bodyObject.put("message", "转发成功，返回数据！");
		bodyObject.put("data", jsonObject);
		rootObject.put("head", headObject);
		rootObject.put("body", bodyObject);
    	
    	return rootObject.toString();
    }
}
