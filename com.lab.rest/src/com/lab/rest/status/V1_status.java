package com.lab.rest.status;

import com.lab.dao.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

@Path("/v1/status") // removed * wildcard to make this more compatible with
					// tomcat
public class V1_status {

	private static final String api_version = "00.01.00"; // version of the api
	private static Schemalab dao = new Schemalab();
	/**
	 * This method sits at the root of the api. It will return the name of this
	 * api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}

	/**
	 * This method will return the version number of the api Note: this is
	 * nested one down from the root. You will need to add version into the URL
	 * path.
	 * 
	 * Example: http://localhost:7001/com.lab.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}

	@GET
	@Path("/ping/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataInJSON() {

		JSONObject trackData = new JSONObject();
		try {
			trackData.put("title", "Enter Sandman");
			trackData.put("singer", "Metallica");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(201).entity(trackData.toString()).build();

	}

	@POST
	@Path("/ping/post")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postDataInJSON() {

		JSONObject trackData = new JSONObject();
		try {
			trackData.put("title", "Enter Sandman");
			trackData.put("singer", "Metallica");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(201).entity(trackData.toString()).build();

	}

	@Path("/insert")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItem(String incomingData) {
		
		boolean item = false;
		try {
			JSONObject jsonObject = new JSONObject(incomingData);
			int id = jsonObject.optInt("id");
			String data = jsonObject.optString("data");
			
			item = dao.insertItems(id, data);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (item == true) {
			return Response.ok(incomingData).build();
		}
		return Response.serverError().build();
	}
	@Path("/inventory")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnInventory() {
		return dao.displayAllItems();
	}
}
