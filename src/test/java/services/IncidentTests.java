package services;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class IncidentTests extends BaseRequest{
	
	
@Test
	public void getIncidents(){
	  RestAssured.given()
			.get("incident")
			.then()
			.assertThat()
			.statusCode(200)
			.body(containsString("number"),containsString("sys_id"))
			.extract().response();
				
	}
	
	@Test
	public void createIncident(){
		RestAssured.given()	.contentType("application/json").when()	
		.body(new File("./data/incident1.json"))
			.post("incident")
			.then()
			.assertThat()
			.statusCode(201)
			.body(containsString("number"),containsString("sys_id"));		
	}
	
	@Test
	public void updateIncident() {
		
		RestAssured.given()	.contentType("application/json").when()	
		.body(new File("./data/incident1.json"))
		.put("incident/c3c80d23977211105be87f5e6253af6d")
		.then()
		.assertThat()
		.statusCode(404);
		
	}
	
     
@Test
	public void deleteIncident() {
		RestAssured.when()
		.delete("incident/c3c80d23977211105be87f5e6253af6d")
		.then()
		.assertThat()
		.statusCode(404);
	}
	
	
	
	@Test
	public void getAllIncidents() {
		
		RestAssured.given().queryParam("sysparm_fields", "sys_id,number")
		.get("incident")
		.then()
		.assertThat()
		.statusCode(200);
	}
	
	
}
