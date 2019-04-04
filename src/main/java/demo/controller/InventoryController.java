package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import demo.bean.Actor;
import demo.service.IInventoryService;
import demo.util.PageEntity;

@Controller
public class InventoryController {

	// @Resource(name="userService")
	// private IUserService userService;

	// @Autowired
	// @Qualifier("roleService")
	// private IRoleService roleService;

	@Autowired
	@Qualifier("inventoryService")
	private IInventoryService inventoryService;

	@RequestMapping("/demo")
	private ModelAndView toDemo() {
		return new ModelAndView("demo");
	}

	@RequestMapping(value = "/getActorData", method = RequestMethod.GET)
	@ResponseBody
	private PageEntity<Actor> getActorData(@RequestParam(defaultValue = "1") int page) {
		int rowSize = 20;
		int startRow = (page - 1) * rowSize;
		return new PageEntity<>(inventoryService.findActor(startRow, rowSize), inventoryService.getActorCount(), rowSize, page);
	}

}
