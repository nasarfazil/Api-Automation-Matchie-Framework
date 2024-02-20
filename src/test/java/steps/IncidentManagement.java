package steps;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

import hooks.SetUp;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class IncidentManagement extends Common{
	
	
	public static Response response;
	public static RequestSpecification inputRequest;
	public static String sys_id;
	
	@Given("Set the endpoint")
	public void setEnpoint() {
		RestAssured.baseURI="https://dev200784.service-now.com/api/now/table/";
	}

	@And("Set the Auth")
	public void auth() {
		RestAssured.authentication=RestAssured.basic("admin","I-Ks*dzGjO63");
	}
	
	@When("get Incidents")
	public void getIncidents() {
		 response = RestAssured.get("incident");
	}
	
	@Then("validate response code as {int}")
	public void validateResponseCode(int statuCode) {
		response.then().assertThat().statusCode(Matchers.equalTo(statuCode));
	}
	
	@When("Create incident with String body {string}")
	public void createIncident(String  body) {
		 inputRequest = RestAssured.given().contentType("application/json").when().body(body);
		 response=inputRequest.post("incident");
		  sys_id = response.jsonPath().getString("result.sys_id");
	}
	
	@When("Update incident with String body {string}")
	public void updateIncident(String  body) {
		 inputRequest = RestAssured.given().contentType("application/json").when().body(body);
		 response=inputRequest.put("incident/"+sys_id);
	}
	
	
	@When("Delete Incident")
	public void deleteIncident() {
		
		response=RestAssured.delete("incident/"+sys_id);
	}
	
	@When("Create new multiple incident using file {string}")
	public void createmultipleincident(String filename) {
        File files=new File("./src/test/resources/"+filename);
		inputRequest = RestAssured.given().contentType("application/json").when().body(files);
		 response= inputRequest.post("incident");		 
	}
}


