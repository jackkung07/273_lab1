package com.lab.client;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {
	public static void main(String[] args) {
		JerseyClient jerseyclient = new JerseyClient();
		// jerseyclient.ClientGet();
		// jerseyclient.ClientPost();
		jerseyclient.ClientInsert();

	}

	public void ClientInsert() {
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:7001/com.lab.rest/api/v1/status/insert");
		JSONObject jsonobject = new JSONObject();
		try {
			jsonobject.put("id", 2);
			jsonobject.put("data", "cdefd");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,
				jsonobject.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);
		System.out.println("Insert");
		System.out.println("POST");
		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}

	public void ClientGet() {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:7001/com.lab.rest/api/v1/status/ping/get");

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

			WebResource webResource = client.resource("http://localhost:7001/com.lab.rest/api/v1/status/ping/post");

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
