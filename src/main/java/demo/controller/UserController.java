package demo.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.bean.User;
import demo.service.IRoleService;
import demo.service.IUserService;

@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;

	@RequestMapping("/demo")
	private ModelAndView toDemo(){
		User u = new User();
		u.setAge(18);
		u.setName("test");
		u.setSex("男");
		userService.addUser(u);
		return new ModelAndView("demo");
	}
}
