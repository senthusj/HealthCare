package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Appointment;

@Path("/Appointments")
public class AppointmentService {
	Appointment appointmentObj = new Appointment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return appointmentObj.readAppointment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("appointmentid") String appointmentid, @FormParam("doctorid") String doctorid, @FormParam("doctorName") String doctorName,
			@FormParam("patientid") String patientid, @FormParam("patientName") String patientName,
			@FormParam("hospitalName") String hospitalName, @FormParam("date") String date) {
		String output = appointmentObj.insertAppointment(appointmentid, doctorid, doctorName, patientid, patientName, hospitalName,
				date);

		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appointmentData) {
//Convert the input string to a JSON object
		JsonObject appointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();
//Read the values from the JSON object
		String appointmentid = appointmentObject.get("appointmentid").getAsString();
		String doctorid = appointmentObject.get("doctorid").getAsString();
		String doctorName = appointmentObject.get("doctorName").getAsString();
		String patientid = appointmentObject.get("patientid").getAsString();
		String patientName = appointmentObject.get("patientName").getAsString();
		String hospitalName = appointmentObject.get("hospitalName").getAsString();
		String date = appointmentObject.get("date").getAsString();

		String output = appointmentObj.updateAppointment( appointmentid, doctorid, doctorName, patientid, patientName,
				hospitalName, date);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appointmentData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());

//Read the value from the element <itemID>
		String appointmentid = doc.select("appointmentid").text();
		String output = appointmentObj.deleteAppointment(appointmentid);
		return output;
	}

	
	
}
