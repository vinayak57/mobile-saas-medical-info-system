package mis.test;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;

public class Test {
	static ClientConfig config;
	static Client client;
	static WebResource service;

	public static void initialize() {
		config = new DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		//config.getClasses().add(JacksonConfigurator.class); 
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
		System.out.println(service.path("rest").path("users").path("6")
				.accept(MediaType.APPLICATION_XML).get(String.class));
	}
	
	public static void getUserByUsername(String username)
	{
		String servicePath = "users/6" + username; 
		
		//Get XML
		// System.out.println(service.path("rest").path("user").accept(MediaType.TEXT_XML).get(String.class));
		// Get XML for application
		System.out.println(service.path("rest").path(servicePath)
				.accept(MediaType.APPLICATION_JSON).get(String.class));
		// Get JSON for application
		System.out.println(service.path("rest").path(servicePath)
				.accept(MediaType.APPLICATION_XML).get(String.class));
	}
	
	public static void insertPatient()
	{
		try{
    

		String input = "{\"username\":\"master244\",\"password\":\"master23\",\"tenantid\":\"6\",\"roleid\":\"11\",\"fname\":\"master23\",\"lname\":\"master244\",\"email\":\"a.a@abc.com\",\"gender\":\"female\",\"phone\":\"6131\",\"addr1\":\"201 D 4th Street\",\"addr2\":\"Apt#527\",\"city\":\"san jos\",\"state\":\"CA\",\"country\":\"US\",\"zipcode\":\"95112\",\"dob\":\"2000-01-11\"}";
		
		
		ClientResponse response = service.path("rest").path("patients").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void changePassword()
	{
		try{
    

		String input = "{\"userid\":\"32\",\"username\":\"master244\",\"password\":\"adfsd\",\"tenantid\":\"6\",\"roleid\":\"11\"}";
		
		
		ClientResponse response = service.path("rest").path("users").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void updatePatient()
	{
		try{
    

		String input = "{\"username\":\"master6\",\"password\":\"master4\",\"tenantid\":\"6\",\"roleid\":\"11\",\"patientId\":\"14\",\"fname\":\"master100\",\"lname\":\"master01\",\"email\":\"a.a@abc.com\",\"gender\":\"female\",\"phone\":\"6131\",\"addr1\":\"201 S 4th Street\",\"addr2\":\"Apt23\",\"city\":\"san jose\",\"state\":\"California\",\"country\":\"US\",\"zipcode\":\"95112\",\"dob\":\"2000-12-11\"}";
		
		
		ClientResponse response = service.path("rest").path("patients").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertDrug()
	{
		try{
    
		String input = "{\"name\":\"combiflam\",\"power\":\"10mg\",\"tenantid\":\"6\"}";
		ClientResponse response = service.path("rest").path("drug").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertHospital()
	{
		try{
    
		String input = "{\"hospital_name\":\"Kaiser Sunnyvale\",\"location_id\":\"7\",\"tenantid\":\"6\"}";
		ClientResponse response = service.path("rest").path("hospital").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertVisitType()
	{
		try{
    
		String input = "{\"visit_type\":\"monthly\",\"description\":\"monthly\",\"tenantid\":\"6\"}";
		ClientResponse response = service.path("rest").path("visittype").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertAppointmentDetails()
	{
		try{
    
		String input = "{\"visit_type_id\":\"1\",\"hospital_staff_id\":\"1\",\"location_id\":\"3\",\"appointment_date\":\"2011-03-01T20:27:05\",\"tenantid\":\"6\",\"patient_id\":\"4\"}";
		ClientResponse response = service.path("rest").path("appointments").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void updateAppointmentDetails()
	{
		try{
    
		String input = "{\"appointment_id\":\"16\",\"visit_type_id\":\"1\",\"hospital_staff_id\":\"2\",\"location_id\":\"3\",\"appointment_date\":\"2012-01-01T20:27:05\",\"tenantid\":\"6\",\"patient_id\":\"4\"}";
		ClientResponse response = service.path("rest").path("appointments").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertStaff()
	{
		try{
    
		String input = "{\"username\":\"newdoc\",\"password\":\"newdoc\",\"tenantid\":\"6\",\"roleid\":\"12\",\"fname\":\"staff3\",\"lname\":\"staff3\",\"details\":\"physician\",\"speciality\":\"general\",\"hospital_id\":\"1\"}";
		ClientResponse response = service.path("rest").path("staff").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertLocation()
	{
		try{
    
		String input = "{\"addr1\":\"201 S 4th Street\",\"addr2\":\"Apt#527\",\"city\":\"san jose\",\"state\":\"CA\",\"country\":\"US\",\"zipcode\":\"95112\",\"location_id\":\"6\"}";
		ClientResponse response = service.path("rest").path("location").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertPrescription()
	{
		try{
    
		String input = "{\"drug_id\":\"1\",\"dose\":\"normal\",\"start_date\":\"2013-04-01\",\"end_date\":\"2013-05-01\",\"instruction\":\"instruction\",\"appointment_id\":\"17\",\"tenantid\":\"6\"}";
		ClientResponse response = service.path("rest").path("prescription").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertPatientMedInfo()
	{
		try{
    
		String input = "{\"bloodgroup\":\"O+\",\"weight\":\"16\",\"height\":\"4\",\"allergies\":\"viral\",\"precautions\":\"no ice cream\",\"side_effects\":\"cold\",\"patient_id\":\"5\"}";
		ClientResponse response = service.path("rest").path("patientmedicalinfo").type("application/json")
		   .put(ClientResponse.class, input);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void insertEmergencyRequest()
	{
		try{
    
		String input = "{\"userid\":\"10\",\"emergencylocation\":\"201 S 4th Street San Jose CA 95112\",\"latitude\":\"1.999930940\",\"longitude\":\"-245.9540588\",\"tenantid\":\"6\"}";
		ClientResponse response = service.path("rest").path("emergency").type("application/json")
		   .put(ClientResponse.class, input);
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
		//getAllUsers();
		//getUserByUsername("mandar");
		//insertPatient();
		//updatePatient();
		//deleteUser();
		//getAllUsers();
		//insertStaff();
		//insertDrug();
		//insertLocation();
		//insertVisitType();
		//insertAppointmentDetails();
		//updateAppointmentDetails();
		//changePassword();
		//insertPrescription();
		//insertPatientMedInfo();
		//insertHospital();
		insertEmergencyRequest();
//		System.out.println(service.path("rest").path("appointments").path("6")
//				.accept(MediaType.APPLICATION_XML).get(String.class));
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/MedicalInformationSystem").build();
	}

}
