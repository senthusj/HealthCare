package com;

import model.Doctor; 

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctors")
public class DoctorService {

Doctor docObj = new Doctor();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readDoctors() {
		
		return docObj.readDoctors(); 
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("Did") String id,
	 @FormParam("Dfirstname") String firstname,
	 @FormParam("Dlastname") String lastname,
	 @FormParam("Dphone") String phone,
	 @FormParam("Ddes") String desc)
	{
	 String output = docObj.insertDoctor(id, firstname, lastname, phone, desc);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String docData)
	{
		//Convert the input string to a JSON object
		 JsonObject docObject = new JsonParser().parse(docData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String no = docObject.get("Dno").getAsString();
		 String id = docObject.get("Did").getAsString();
		 String firstname = docObject.get("Dfirstname").getAsString();
		 String lastname = docObject.get("Dlastname").getAsString();
		 String phone = docObject.get("Dphone").getAsString();
		 String desc = docObject.get("Ddes").getAsString();
		 
		 String output = docObj.updateDoctor(no, id, firstname, lastname,  phone, desc);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String docData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(docData, "", Parser.xmlParser());
	
		//Read the value from the element <itemID>
		 String dno = doc.select("Dno").text();
		 
		 String output = docObj.deleteDoctor(dno);
		 
		return output;
	}
}
