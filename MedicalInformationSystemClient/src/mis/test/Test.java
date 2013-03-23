package mis.test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class Test {
	static ClientConfig config;
	static Client client;
	static WebResource service;

	public static void initialize() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(getBaseURI());
	}

	public static void getAllUsers()
	{
		// Get XML
		// System.out.println(service.path("rest").path("user").accept(MediaType.TEXT_XML).get(String.class));
		// Get XML for application
//		System.out.println(service.path("rest").path("users")
//				.accept(MediaType.APPLICATION_JSON).get(String.class));
		// Get JSON for application
		System.out.println(service.path("rest").path("users")
				.accept(MediaType.APPLICATION_XML).get(String.class));
	}
	
	public static void getUserByUsername(String username)
	{
		String servicePath = "user/" + username; 
		
		//Get XML
		// System.out.println(service.path("rest").path("user").accept(MediaType.TEXT_XML).get(String.class));
		// Get XML for application
		System.out.println(service.path("rest").path(servicePath)
				.accept(MediaType.APPLICATION_JSON).get(String.class));
		// Get JSON for application
		System.out.println(service.path("rest").path(servicePath)
				.accept(MediaType.APPLICATION_XML).get(String.class));
	}
	
	public static void insertUser()
	{
		try{
		//Create a User
//	    Form form = new Form();
//	    form.add("username", "gao");
//	    form.add("password", "gao");
//	    ClientResponse response  = service.path("rest").path("users").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
//	    System.out.println("Form response " + response.getEntity(String.class));
	    
	    String input = "{\"username\":\"master\",\"password\":\"master\"}";
	    
		ClientResponse response = service.path("rest").path("users").type("application/json")
		   .post(ClientResponse.class, input);
 
		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
 
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void deleteUser()
	{
		service.path("rest").path("users/5").delete();
	}
	
	public static void main(String[] args) {
		initialize();
		getAllUsers();
		//getUserByUsername("mandar");
		//insertUser();
		deleteUser();
		getAllUsers();
		
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/MedicalInformationSystem").build();
	}

}
