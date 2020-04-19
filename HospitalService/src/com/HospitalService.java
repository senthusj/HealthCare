package com;

import model.Hospital;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospitals")
public class HospitalService {
	Hospital hospitalObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospitals() {
		return hospitalObj.readHospitals();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("hospitalCode") String hospitalCode, @FormParam("name") String name,
			@FormParam("chiefDoc") String chiefDoc, @FormParam("type") String type, @FormParam("phone") int phone,
			@FormParam("address") String address, @FormParam("desc") String desc) {
		String output = hospitalObj.insertHospital(hospitalCode, name, chiefDoc, type, phone, address, desc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String hospitalData) {
		// Convert the input string to a JSON object
		JsonObject hospitalObject = new JsonParser().parse(hospitalData).getAsJsonObject();
		// Read the values from the JSON object
		String hospitalID = hospitalObject.get("hospitalID").getAsString();
		String hospitalCode = hospitalObject.get("hospitalCode").getAsString();
		String name = hospitalObject.get("name").getAsString();
		String chiefDoc = hospitalObject.get("chiefDoc").getAsString();
		String type = hospitalObject.get("type").getAsString();
		String phone = hospitalObject.get("phone").getAsString();
		String address = hospitalObject.get("address").getAsString();
		String desc = hospitalObject.get("desc").getAsString();

		String output = hospitalObj.updateHospital(hospitalID, hospitalCode, name, chiefDoc, type, phone, address,
				desc);

		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String hospitalData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String hospitalID = doc.select("hospitalID").text();
		String output = hospitalObj.deleteHospital(hospitalID);
		
		return output;
	}

}