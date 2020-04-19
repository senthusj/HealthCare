package com;

import model.Appointment;

import java.sql.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointments")
public class AppointmentService {
	Appointment appointmentObj = new Appointment();

	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public String readItems() {
//		return " I'AM Hello Inthirajith ";
//	}
	
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
