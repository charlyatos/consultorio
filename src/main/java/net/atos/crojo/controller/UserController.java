package net.atos.crojo.controller;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.atos.crojo.service.UserServiceImpl;
import net.atos.crojo.model.User;
@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl srv;
	
	@GetMapping("")
	public void getAll() throws Exception {
		
		srv.findAll();
					
	} 

}
