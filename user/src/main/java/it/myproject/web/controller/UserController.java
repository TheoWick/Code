package it.myproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.myproject.web.dao.UserDao;
import it.myproject.web.entity.User;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserDao userService;
	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		User c = new User();
		c.setUsername("Teo");
		c.setPassword("Bonza");
		c.setEmail("teo@info.com");
		c.setPhone("1234");
		c.setRole("user");
		userService.add(c);
		return null;
	}
	
	@ResponseBody
	@GetMapping("/getById")
	public String getById() {
		User c = userService.getById(1);
		return c.getUsername();
	}
	
	@ResponseBody
	@GetMapping("/update")
	public String update() {
		User c = userService.getById(1);
		c.setUsername("admin");
		c.setPassword("admin");
		c.setEmail("admin@admin.com");
		c.setPhone("5555");
		userService.update(c);
		return null;
	}
	
	@ResponseBody
	@GetMapping("/delete")
	public String delete() {
		userService.delete(1);
		return null;
	}
}
