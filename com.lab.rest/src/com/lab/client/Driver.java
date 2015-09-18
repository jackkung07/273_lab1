package com.lab.client;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Driver extends Thread {
	private static final String url = "http://localhost:7001/com.lab.rest/api";
	private Thread t;
	private SampleClient c;

	public Driver(int id, String data) {
		c = new SampleClient(id, data);
		this.ClientInsert(c);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				this.ClientVerify(c);
			} catch (InterruptedException e) {
				System.out.println("thread error");
			}
		}

	}

	public void start() {
		
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public static void main(String args[]) {
		Driver dr1 = new Driver(1, "Apple");

		Driver dr2 = new Driver(2, "Orange");

		Driver dr3 = new Driver(3, "Peach");
		
		dr1.start();
		dr2.start();
		dr3.start();

	}

	public void ClientInsert(SampleClient c) {
		Client client = Client.create();
		WebResource webResource = client.resource(url + "/v1/insert");
		JSONObject jsonobject = new JSONObject();
		try {
			jsonobject.put("id", c.getID());
			jsonobject.put("data", c.getData());
		} catch (Exception e) {
			// e.printStackTrace();
		}

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
				jsonobject.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		System.out.println("insert");
		System.out.println("Output from Server .... " + output);
	}

	public void ClientVerify(SampleClient c) {
		Client client = Client.create();
		WebResource webResource = client.resource(url + "/v1/verify");
		JSONObject jsonobject = new JSONObject();
		try {
			jsonobject.put("id", c.getID());

		} catch (Exception e) {
			e.printStackTrace();
		}

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
				jsonobject.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		JSONObject jsonobj;
		try {
			jsonobj = new JSONObject(output);

			System.out.println("\n\nverify");
			System.out.println("Response from Server .... ");
			System.out.println(jsonobj.opt("data").toString());
		} catch (JSONException e) {
			System.out.println("json error");
		}

	}

	public void ClientGet() {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource(url + "/v1/ping/get");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("GET");
			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void ClientPost() {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource(url + "/v1/ping/post");

			String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			System.out.println("POST");
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
