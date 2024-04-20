package StepDefinations;

import org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import PojoClass.CreateUserRequest;
import PojoClass.UpdateUserRequest;
import Utility.JsonReader;

import org.hamcrest.MatcherAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

public class StepDefs {
	String Id;
	RestAssured Given;
	Response response;
	RequestSpecBuilder reqSpec = new RequestSpecBuilder();
	RequestSpecification spec;
	ObjectMapper objMapper;

	@Given("I set all the paramter for the request")
	public void i_set_all_the_paramter_for_the_request() {
		objMapper = new ObjectMapper();
	    JsonReader jReader= new JsonReader();
        reqSpec.setBaseUri(jReader.getURI());

	}

	@When("I send the request to get all the user of Page {string}")
	public void i_send_the_request_to_get_all_the_user(String pageNo) {
		reqSpec.addQueryParam("page", pageNo);
		spec = reqSpec.build();
		response = (Response) io.restassured.RestAssured.given().spec(spec).get();
		System.out.println(response.statusCode());
		System.out.println(response.body().asString());
	}

	@Then("I validate response message {string}")
	public void i_validate_response_message(String responseMessage) {
		Assert.assertTrue(response.body().asString().contains(responseMessage));
	}

	@When("I send the request to get specific user {string}")
	public void i_send_the_request_to_get_specific_user(String userID) {
		spec = reqSpec.build();
		System.out.println(userID);

		response = (Response) io.restassured.RestAssured.given().spec(spec).get("/" + userID);
		System.out.println(response.body().asString());
		System.out.println(response.statusCode());
	}

	@When("I send the request to post create a user")
	public void i_send_the_request_to_post(DataTable data) throws JsonMappingException, JsonProcessingException {

		List<Map<String, String>> userMap = data.asMaps();
		Map<String, String> map = userMap.get(0);
		CreateUserRequest userRequest = new CreateUserRequest(map.get("name"), map.get("job"));
		String payload = objMapper.writeValueAsString(userRequest);
		reqSpec.setBody(payload);

		spec = reqSpec.build();

		response = (Response) io.restassured.RestAssured.given().spec(spec).contentType("application/json").post();
		System.out.println(response.body().asString());
		JSONObject object = new JSONObject(response.body().asString());
		System.out.println(response.statusCode());
		Id = (String) object.getString("id");

	}

	@When("I send the request to get specific user of Post Request")
	public void i_send_the_request_to_get_specific_user_of_Post_Request() {
		spec = reqSpec.build();
		System.out.println(Id);

		response = (Response) io.restassured.RestAssured.given().spec(spec).get("/" + Id);
		System.out.println(response.body().asString());
		System.out.println(response.statusCode());
	}

	@When("I send the request to put update a user")
	public void i_send_the_request_to_put_update_a_user(DataTable dataTable) throws JsonProcessingException {

		List<Map<String, String>> data = dataTable.asMaps();
		Map<String, String> map = data.get(0);
		UpdateUserRequest updateUserRequest = new UpdateUserRequest(map.get("name"), map.get("job"));
		String payload = objMapper.writeValueAsString(updateUserRequest);
		reqSpec.setBody(payload);
		

		spec = reqSpec.build();

		response = (Response) io.restassured.RestAssured.given().spec(spec).contentType("application/json")
				.put("/" + map.get("userID"));
		System.out.println(response.body().asString());

	}
	
	@When("I send the request to Delete a user")
	public void i_send_the_request_to_Delete_a_user(DataTable dataTable) {
     
		List<Map<String, String>> data= dataTable.asMaps();
		Map<String ,String> map= data.get(0);
	   spec= reqSpec.build();
	    response = io.restassured.RestAssured.given().spec(spec).contentType("application/json").delete("/" + map.get("userID"));
	    System.out.println(response.statusCode());
	    		 
		
	}

}
