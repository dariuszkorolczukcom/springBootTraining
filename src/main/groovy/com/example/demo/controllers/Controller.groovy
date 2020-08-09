package com.example.demo.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
	
	@Value('${spring.application.name}')
	private String name;
	
	@RequestMapping(value = "/")
	String home() {
		System.out.println("home")
		"homepage"
	}
	
	@RequestMapping(value = "/info")
	String info() {
		System.out.println("info")
		"info"
	}
	
	@RequestMapping(value = "/name/{id}")
	String name(@PathVariable("id") String id, @RequestParam(value = "name", required = false, defaultValue = "honey") String name2) {
		System.out.println(name + " " + id + " " + name2)
		name + " " + id + " " + name2
	}
}
