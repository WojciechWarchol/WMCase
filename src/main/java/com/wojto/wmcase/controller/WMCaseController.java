package com.wojto.wmcase.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/clientList")
	public String listClients(Model theModel) {
		
		List<Client> theClients = 
				service.getAllClients();
		
		theModel.addAttribute("clients", theClients);
		
		return "list-clients";
	}

	@GetMapping("/clientOrders")
	public String clientOrders(@RequestParam("clientId") int theId, Model theModel) {
		
		List<Order> theOrders = service.getOrdersForClient(theId);
		
		theModel.addAttribute("orders", theOrders);
		theModel.addAttribute("clientId", theId);
		
		return "list-orders";
	}
	
	@GetMapping("/deleteClient")
	public String deleteClient(@RequestParam("clientId") int theId) {
		
		service.deleteClient(theId);
		
		return "redirect:/clientList";
	}
	
	@GetMapping("/orderList")
	public String listOrders(Model theModel) {
		List<Order> theOrders = service.getAllOrders();
		
	theModel.addAttribute("orders", theOrders);
	
	return "list-orders";
	}
	
	@GetMapping("/updateOrder")
	public String updateOrder(@RequestParam("orderId") int theId, 
							  @RequestParam("clientId") int clientId,
								Model theModel) {
		
		List<Case> theCases = service.getCasesForOrder(theId);
		
		theModel.addAttribute("cases", theCases);
		theModel.addAttribute("orderId", theId);
		theModel.addAttribute("clientId", clientId);
		System.out.println("The Case Id is: " + theId);
		
		return "list-cases";
	}
	
	@GetMapping("/deleteOrder")
	public String deleteOrder(@RequestParam("orderId") int theId) {
		
		service.deleteOrder(theId);
		
		return "redirect:/orderList";
	}
	
	@GetMapping("/caseList")
	public String listCases(Model theModel) {
		
		List<Case> theCases = 
				service.getAllCases();
		
		theModel.addAttribute("cases", theCases);
		
		return "list-cases";
	}
	
	@GetMapping("/newCase")
	public String createNewCase(@RequestParam("orderId") int orderId,
								@RequestParam("clientId") int clientId,
								Model theModel) {
		
		Case theCase = new Case();
		
		theModel.addAttribute("case", theCase);
		theModel.addAttribute("orderId", orderId);
		theModel.addAttribute("clientId", clientId);
		System.out.println("The order Is (in newCase) is: " + orderId);
		
		return "new-case";
	}
	
	@GetMapping("/updateCase")
	public String updateCase(@RequestParam("caseId") int theId, 
							 @RequestParam("orderId") int orderId,
							 @RequestParam("clientId") int clientId,
							 Model theModel) {
		
		Case theCase = service.getCase(theId);
		
		theModel.addAttribute("case", theCase);
		theModel.addAttribute("orderId", orderId);
		theModel.addAttribute("clientId", clientId);
		
		return "new-case";
	}
	
	@PostMapping("/saveCase")
	public String saveCase(@ModelAttribute("case") Case theCase,
						   @RequestParam("orderId") int orderId,
						   @RequestParam("clientId") int clientId,
						   Model theModel) {
		
		theCase.setOrder(service.getOrder(orderId));	
		
		service.saveCase(theCase);
		theModel.addAttribute("orderId", orderId);
		theModel.addAttribute("clientId", clientId);
		
		// was "redirect:/caseList"
		return "redirect:/updateOrder";
	}
	
	@GetMapping("/deleteCase")
	public String deleteCase(@RequestParam("caseId") int theId,
							 @RequestParam("orderId") int orderId, 
							 @RequestParam("clientId") int clientId,
							 Model theModel) {
		
		service.deleteCase(theId);
		theModel.addAttribute("orderId", orderId);
		theModel.addAttribute("clientId", clientId);

		return "redirect:/updateOrder";
	}
	

	
}
