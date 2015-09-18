package com.lab.client;

public class SampleClient {
	private int id;
	private String data;
	
	public SampleClient() {
		
	}
	
	public SampleClient(int id, String data) {
		this.id = id;
		this.data = data;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public int getID() {
		return id;
	}
	
	public String getData() {
		return data;
	}
}
