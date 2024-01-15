package main.rest;

import main.rest.provider.AuthenticationFilter;
import main.rest.provider.GsonMessageBodyHandler;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;



public class CustomApplication extends ResourceConfig 
{
	public CustomApplication() 
	{
		packages("main.rest");
		register(LoggingFilter.class);
		register(GsonMessageBodyHandler.class);
		register(AuthenticationFilter.class);
	}
}
