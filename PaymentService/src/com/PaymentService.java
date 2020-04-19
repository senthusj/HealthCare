package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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

import model.Payment;

@Path("/Payments")
public class PaymentService {
	Payment paymentObj = new Payment(); 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertpaymentdetails(@FormParam("paymentID") String paymentID,
							@FormParam("CARD_NUMBER") String CARD_NUMBER, 
							@FormParam("EXPIRATIONEXP_DATE") String EXPIRATIONEXP_DATE,    
							@FormParam("CV_CODE") String CV_CODE,       
							@FormParam("COUPON_CODE")String COUPON_CODE) 
	{  
		String output = paymentObj.insertpaymentdetails(paymentID,CARD_NUMBER,EXPIRATIONEXP_DATE, CV_CODE, COUPON_CODE); 
		return output; 
	} 
	
	@GET 
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readpaymentdetails()  
	{   
		return paymentObj.readpaymentdetails();  
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatepaymentdetails(String paymentdata) 
	{
		//Convert the input string to a 
		  JsonObject paymentObject = new JsonParser().parse(paymentdata).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		  String payno = paymentObject.get("payno").getAsString();
		  String paymentID = paymentObject.get("paymentID").getAsString();
		  String CARD_NUMBER =paymentObject.get("CARD_NUMBER").getAsString(); 
		  String EXPIRATIONEXP_DATE= paymentObject.get("EXPIRATIONEXP_DATE").getAsString();
		  String CV_CODE = paymentObject.get("CV_CODE").getAsString();
		  String COUPON_CODE =paymentObject.get("COUPON_CODE").getAsString(); 
		  
		
		  
		  String output = paymentObj.updatepaymentdetails( payno,paymentID,CARD_NUMBER, EXPIRATIONEXP_DATE, CV_CODE,COUPON_CODE); 
		 
		 return output; 
		 } 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletepaymentdetails(String paymentdata) 
	{  
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(paymentdata, "", Parser.xmlParser());
		//Read the value from the element <payno>  
		String payno = doc.select("payno").text(); 
		 
		 String output = paymentObj.deletepaymentdetails(payno); 
		 
		 return output; } 
	
}
