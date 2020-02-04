package com.wojto.wmcase.controller;


import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;
import com.wojto.wmcase.entity.Quantity;
import com.wojto.wmcase.enums.OrderStatus;
import com.wojto.wmcase.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("order")
public class WMCaseController {

	@Autowired
	private Service service;
	
	// sanity test
	@GetMapping("/hello")
	public String listClients() {
		
		return "hello";
	}

	/*
	 * Admin side methods
	 */

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
	public String deleteOrder(@RequestParam("orderId") int theId,
							  @RequestParam("clientId") int clientId,
							  Model theModel) {
		
		service.deleteOrder(theId);
		theModel.addAttribute("clientId", clientId);
		
		return "redirect:/clientOrders";
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
		System.out.println("The order ID (in newCase) is: " + orderId);
		
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

	/*
	 * Client side Methods
	 */
	
	@GetMapping("/newClientOrder")
	public String createNewOrder(Model theModel) {
		
		System.out.println("Initializing new order");
		
		Order theOrder = new Order();
		Client theClient = new Client();
		theOrder.setClient(theClient);
		theOrder.setDate(new Date());
//		theClient.getOrders().add(theOrder);
		
		theModel.addAttribute("order", theOrder);
		theModel.addAttribute("client", theClient);
		
		return "new-full-order";
	}

	@GetMapping("/newCaseInOrder")
	public String createNewCaseInOrder(@ModelAttribute("order") Order theOrder,
									   Model theModel) {

		Case theCase = new Case();
		int intQuantity = 1;

		theModel.addAttribute("case", theCase);
		theModel.addAttribute("order", theOrder);
		theModel.addAttribute("quantity", intQuantity);

		return "new-case-in-order";
	}

	@PostMapping("/addCaseToOrder")
	public String addCaseToOrder(@ModelAttribute("case") Case theCase,
								 @ModelAttribute("order") Order theOrder,
								 @ModelAttribute("quantity") int intQuantity,
								 Model theModel) {

		System.out.println("Executing the addCaseToOrderMethod");
		theCase.evaluation();
		theOrder.addCase(theCase, new Quantity(intQuantity));
		theOrder.getCharge();
		System.out.println(theCase.toString());
		theModel.addAttribute("order", theOrder);
		System.out.println(theOrder.getCases().size());

		System.out.println("The Order contains: " + theOrder.getCases().toString());

		return "redirect:/continueOrder";
	}
	
	@GetMapping("/continueOrder")
	public String continueOrder(@ModelAttribute("order") Order theOrder,
								Model theModel) {
		
		theModel.addAttribute("order", theOrder);

		Quantity tempQuantity = new Quantity();
		theModel.addAttribute("tempQuantity", tempQuantity);
		
		System.out.println("Executing the continueOrder method");
		System.out.println(theOrder.getCases().size());
		
		return "new-full-order";
	}

	@PostMapping("/updateQuantity")
	public String updateQuantity(@ModelAttribute("tempQuantity") Quantity tempQuantity,
								 @ModelAttribute("tempCase") String tempCaseString,
								 @ModelAttribute("order") Order theOrder,
								 Model theModel) {

		Case theCase = service.findCaseInOrder(theOrder, tempCaseString);
		theOrder.getCases().get(theCase).setQuantity(tempQuantity.getQuantity());

		theModel.addAttribute(theOrder);

		return "redirect:/continueOrder";
	}
	
	@GetMapping("/updateCaseInClientOrder")
	public String modifyOrderCase(@ModelAttribute("tempCase") String tempCaseString,
								  @ModelAttribute("order") Order theOrder,
								  Model theModel) {

		Case theCase = service.findCaseInOrder(theOrder, tempCaseString);
		int intQuantity = theOrder.getCases().get(theCase).getQuantity();
		theOrder.getCases().remove(theCase);

		theModel.addAttribute("case", theCase);
		theModel.addAttribute("order", theOrder);
		theModel.addAttribute("quantity", intQuantity);

		return "new-case-in-order";
	}


	//TODO Delete Case method

	
	@PostMapping("/sendOrder")
	public String sendOrder(@ModelAttribute("order") Order theOrder,
							Model theModel) {
		
		System.out.println("The client id is: " + theOrder.getClient().getName());
		theOrder.setOrderStatus(OrderStatus.ZAPYTANIE);
		theOrder.setCharge(100);
		Client theClient = theOrder.getClient();
		service.saveClient(theClient);
		List<Case> caseList = theOrder.getCaseList();
		for (Case theCase : caseList){
			service.saveCase(theCase);
		}
		service.saveOrder(theOrder);
		System.out.println("Code got here");
		return "list-clients";
	}
	
	// Add Update, and Delete for client side
	
}
