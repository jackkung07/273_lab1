package com.lab.client;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient2 {
	private static final String url = "http://localhost:7001/com.lab.rest/api";
	public static void main(String[] args) {
		JerseyClient2 jerseyclient = new JerseyClient2();
		// jerseyclient.ClientGet();
		// jerseyclient.ClientPost();
		jerseyclient.ClientInsert(2, "new balance");
		jerseyclient.ClientVerify(2);

	}

	public void ClientInsert(int id, String data) {
		Client client = Client.create();
		WebResource webResource = client.resource(url + "/v1/status/insert");
		JSONObject jsonobject = new JSONObject();
		try {
			jsonobject.put("id", id);
			jsonobject.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
				jsonobject.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		System.out.println("insert");
		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
	public void ClientVerify(int id) {
		Client client = Client.create();
		WebResource webResource = client.resource(url + "/v1/status/verify");
		JSONObject jsonobject = new JSONObject();
		try {
			jsonobject.put("id", id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
				jsonobject.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		System.out.println("verify");
		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}

	public void ClientGet() {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource(url + "/v1/status/ping/get");

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

			WebResource webResource = client.resource(url + "/v1/status/ping/post");

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
