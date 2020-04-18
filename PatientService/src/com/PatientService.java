package com;

import model.Patient;

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

@Path("/patient")
public class PatientService {
	Patient patientObj = new Patient();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getPatientDetails() {
		return patientObj.getPatientDetails();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPatientDetails(@FormParam("patientID") String patientID, @FormParam("firstname") String firstname,
			@FormParam("lastName") String lastName, @FormParam("gender") String gender,
			@FormParam("phone") String phone, @FormParam("address") String address, @FormParam("age") String age,
			@FormParam("bloodGroup") String bloodGroup, @FormParam("nextOfKin") String nextOfKin) {
		String output = patientObj.addPatientDetails(patientID, firstname, lastName, gender, phone, address, age,
				bloodGroup, nextOfKin);
		return output;
	}
}
