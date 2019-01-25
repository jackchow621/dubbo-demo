package com.ghost.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestPageController {

	
	@ResponseBody
	@RequestMapping(value = "/health",method={RequestMethod.GET,RequestMethod.HEAD})
	public String health() {
		return "OK!";
	}
	
}
