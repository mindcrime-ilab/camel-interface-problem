package de.mindcrimeilab.cameltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;




@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		  SpringApplication.run(Application.class, args);
	}
	
    /** Prefix to call camel services */
	private static final String CAMEL_URL_MAPPING = "/camel/*";

	/** Serlvet name to map to camel services */
	private static final String CAMEL_SERVLET_NAME = "CamelServlet";

	/**
	 * Register camel servlet as spring bean
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),
				CAMEL_URL_MAPPING);
		registration.setName(CAMEL_SERVLET_NAME);
		registration.setLoadOnStartup(1);
		return registration;
	}
}
