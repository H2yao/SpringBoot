package com.example.springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
@Controller换成@RestController，下面的方法的返回值就全都是@ResponseBody的形式，
就不用再写@ResponseBody了，下面可以换掉试一下
*/
@RestController
@RequestMapping("/controller")
public class Controller {

	@Autowired
	private UserDao userDao;

	@RequestMapping("/findAll")
	public String findAll() {
		Iterable<User> u = userDao.findAll();
		JSONArray jsonobj = JSONArray.fromObject(u);
		return jsonobj.toString();
	}

	@RequestMapping("/add")
	public String addNewUser(@RequestParam String username, @RequestParam String password) {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		userDao.save(user);
		return "saved";
	}

	// Spring Boot 2.0 去掉了findOne() 改为 findById(id) 来查询
	@RequestMapping("/getUserById")
	public String getUserById(@RequestParam Integer id) {
		User u = userDao.findById(id).get();
		JSONObject jsonobj = JSONObject.fromObject(u);
		return jsonobj.toString();
	}

	@RequestMapping("/deleteById")
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}

	@RequestMapping("/sayHello")
	public String sayHello() {
		return "Hello SpringBoot !";
	}
}
