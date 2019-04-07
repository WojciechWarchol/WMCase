package com.wojto.wmcase.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.xdevapi.CreateIndexParams;
import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;
import com.wojto.wmcase.service.Service;

@Controller
@RequestMapping("/")
public class WMCaseController {

	@Autowired
	private Service service;
	
	// sanity test
	@GetMapping("/hello")
	public String listClients() {
		
		return "hello";
	}
	
//	@GetMapping("/clientList")
//	public String listClients(Model theModel) {
//		
//		List<Client> theClients = 
//				service.getClients();
//		
//		theModel.addAttribute("clients", theClients);
//		
//		return "list-clients";
//	}
//	
//	@GetMapping("/orderList")
//	public String listOrder(Model theModel) {
//		
//		List<Order> theOrders =
//				service.getOrders(1);
//		
//		theModel.addAttribute("orders", theOrders);
//		
//		return "list-orders";
//	}
	
	@GetMapping("/caseList")
	public String listCases(Model theModel) {
		
		List<Case> theCases = 
				service.getCases(1);
		
		theModel.addAttribute("cases", theCases);
		
		return "list-cases";
	}
	
	@GetMapping("/newCase")
	public String createNewCase(Model theModel) {
		
		Case theCase = new Case();
		
		theModel.addAttribute("case", theCase);
		
		return "new-case";
	}
	
	@PostMapping("/saveCase")
	public String saveCase(@ModelAttribute("case") Case theCase) {
		
		service.saveCase(theCase);
		
		return "redirect:/caseList";
	}
	
	@GetMapping("/showCaseAddForm")
	public String showCaseAddForm(Model theModel) {
		
		Case theCase = new Case();
		
		theModel.addAttribute("case", theCase);
		
		return "case-form";
	}
	
}
