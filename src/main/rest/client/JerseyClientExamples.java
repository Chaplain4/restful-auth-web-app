package main.rest.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.rest.beans.Car;
import main.rest.beans.Cars;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.filter.LoggingFilter;



public class JerseyClientExamples {
	public static void main(String[] args) throws IOException 	{
		httpGETCollectionExample();
		httpGETEntityExample();
		httpPOSTMethodExample();
		httpPUTMethodExample();
		httpDELETEMethodExample();
	}
	
	private static void httpGETCollectionExample() 	{
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
																    .nonPreemptive()
																    .credentials("John", "J")
																    .build();

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8086/RESTful_web_app_war/rest").path("cars");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Cars cars = response.readEntity(Cars.class);
		List<Car> listOfCars = cars.getCarList();
			
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfCars.toArray(new Car[listOfCars.size()]) ));
	}
	
	private static void httpGETEntityExample() {
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8086/RESTful_web_app_war/rest").path("cars").path("1");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Car car = response.readEntity(Car.class);
			
		System.out.println(response.getStatus());
		System.out.println(car);
	}

	private static void httpPOSTMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8086/RESTful_web_app_war/rest").path("cars");
		
		Car car = new Car();
		car.setId(1);
		car.setModel("BMW X5");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(car, MediaType.APPLICATION_XML));
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	
	private static void httpPUTMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8086/RESTful_web_app_war/rest").path("cars").path("1");
		
		Car emp = new Car();
		emp.setId(1);
		emp.setModel("BMW X5");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(emp, MediaType.APPLICATION_XML));
		
		Car car = response.readEntity(Car.class);
			
		System.out.println(response.getStatus());
		System.out.println(car);
	}
	
	private static void httpDELETEMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8086/RESTful_web_app_war/rest").path("cars").path("1");
		
		Invocation.Builder invocationBuilder =	webTarget.request();
		Response response = invocationBuilder.delete();
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
}
