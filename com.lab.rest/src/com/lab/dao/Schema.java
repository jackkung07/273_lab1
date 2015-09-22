package com.lab.dao;

import java.util.ArrayList;
import java.util.List;

public class Schema {
	private static List<Items> list = new ArrayList<Items>();
	
	public boolean insertItems(int id, String data){
		Items newItems = new Items();
		newItems.CLIENT_ID = id;
		newItems.CLIENT_DATA = data;
		
		return list.add(newItems);
	}
	
	public String displayAllItems(){
		String inventory = "";
		for(int i = 0; i < list.size(); i++) {
			inventory += "<p>id: " + list.get(i).CLIENT_ID + " data: " + list.get(i).CLIENT_DATA + "</p>";
		}
		return inventory;
	}
	
	public boolean verifyID(int id) {
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).CLIENT_ID == id) {
				return true;
			}
		}
		return false;
	}
	
	public String getData(int id) {
		String data = "";
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).CLIENT_ID == id) {
				data = list.get(i).CLIENT_DATA;
			}
		}
		return data;
	}
	
	
}

class Items {
	public int CLIENT_ID;
	public String CLIENT_DATA;
}
