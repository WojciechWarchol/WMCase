package com.wojto.wmcase.test;

import java.util.ArrayList;

import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;
import com.wojto.wmcase.enums.Color;
import com.wojto.wmcase.enums.Material;
import com.wojto.wmcase.enums.Type;
import com.wojto.wmcase.enums.Handle;
import com.wojto.wmcase.enums.Filling;
import com.wojto.wmcase.enums.Locks;

public class TestClass {

	public static void main(String[] args) {
		
		Case case1 = new Case(1, 1000, 600, 600, Type.KUFER, Material.CON_PEARL, Color.OTHER, Filling.FOAM, Handle.KASETOWY_W_MISECZCE, 2, true, 4, Locks.KLAMROWE, "Brak uwag");
		Case case2 = new Case(1, 1200, 500, 400, Type.KUFER, Material.SKLEJKA, Color.BLACK, Filling.FOAM, Handle.WALIZKOWY, 1, false, 0, Locks.KLAMROWE, "Ma byæ zrobione idealnie");

		
		ArrayList<Case> dwaCasey = new ArrayList<Case>();
		dwaCasey.add(case1);
		dwaCasey.add(case2);
		
		Order zamowienie1 = new Order(1, dwaCasey, "Szybciutko zrobiæ");
		
		Client klient1 = new Client(1, "Józef", "Ma³olepszy", "jozef@gmail.com", "666999666", new ArrayList<Order>());
		klient1.addOrder(zamowienie1);
		
		klient1.getOrders().get(0).orderSummary();

		case1.hasWheels()
		
	}

}
