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
	private static Schema dao = new Schema();
	
	@Path("/initialize")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDBStatus() {
		dao.insertItems(0, "apple");
		dao.insertItems(1, "oracle");
		dao.insertItems(2, "ibm");
	
		return "<p> initialized </p>";
	}
		
	
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
			return Response.ok("insert successful").build();
		}
		return Response.serverError().build();
	}
	
	@Path("/verify")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyItem(String incomingData) {
		
		boolean verify = false;
		int id = -1;
		String data = "";
		Response rb = null;
		try {
			JSONObject jsonObject = new JSONObject(incomingData);
			id = jsonObject.optInt("id");
			verify = dao.verifyID(id);
			if (verify == true) {
				data = dao.getData(id);
				data = "Server has your id: " + id + " and data is " + data;
				JSONObject jb = new JSONObject();
				jb.put("status", "success");
				jb.put("id", id);
				jb.put("data", data);
				System.out.println("server verify item success");
				return rb.ok(jb.toString()).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rb.serverError().build();
	}
	
	@Path("/inventory")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnInventory() {
		return dao.displayAllItems();
	}
}
