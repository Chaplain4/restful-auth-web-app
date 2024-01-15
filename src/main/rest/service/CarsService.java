package main.rest.service;

import main.rest.beans.Car;
import main.rest.beans.Cars;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/cars")
public class CarsService
{
	@RolesAllowed("ADMIN")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Cars getAllCars()
	{
		Cars list = new Cars();
		list.setCarList((new ArrayList<Car>()));
		
		list.getCarList().add(new Car(1, "Audi A6"));
		list.getCarList().add(new Car(2, "Audi A3"));
		list.getCarList().add(new Car(3, "Audi A4"));
		
		return list;
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCar( Car car ) throws URISyntaxException
	{
		if(car == null){
			return Response.status(400).entity("Please add car details !!").build();
		}
		
		if(car.getModel() == null) {
			return Response.status(400).entity("Please provide the car model !!").build();
		}
		
		return Response.created(new URI("/RESTful_web_app_war/rest/cars/"+car.getId())).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCarById(@PathParam("id") Integer id)
	{
		if(id  < 0){
			return Response.noContent().build();
		}
		Car car = new Car();

		car.setId(id);
		car.setModel("BMW X7");
		
		GenericEntity<Car> entity = new GenericEntity<Car>(car, Car.class);
		return Response.ok().entity(entity).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCarById(@PathParam("id") Integer id, Car car)
	{
		Car updatedCar = new Car();
		
		if(car.getModel() == null) {
			return Response.status(400).entity("Please provide the car model !!").build();
		}
		
		updatedCar.setId(id);
		updatedCar.setModel(car.getModel());
		
		return Response.ok().entity(updatedCar).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCarById(@PathParam("id") Integer id)
	{		
		return Response.status(202).entity("Car deleted successfully !!").build();
	}
}
