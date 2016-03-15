package de.mindcrimeilab.cameltest;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;



@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		InterfaceProblemTest.TestConfig.class }, loader = CamelSpringDelegatingTestContextLoader.class)

@MockEndpoints
public class InterfaceProblemTest {
	@Produce(uri = "direct:doit")
	protected ProducerTemplate testProducer;

	@EndpointInject(uri = "mock:log:RESULT")
	protected MockEndpoint logEndpoint;

	@Configuration
	public static class TestConfig extends SingleRouteCamelConfiguration {

		@Bean
		@Override
		public RouteBuilder route() {
			return new HelloRoute();
		}

	}

	@Test
	public void testWithoutInterface() throws InterruptedException {
		logEndpoint.expectedMessageCount(1);
		logEndpoint.expectedBodiesReceived("ID[1] saying Hello World!");

        
		testProducer.sendBody(de.mindcrimeilab.cameltest.Entity.class);
		

		logEndpoint.assertIsSatisfied();
	}
	
	@Test
	public void testWithInterface() throws InterruptedException {
		logEndpoint.expectedMessageCount(1);
		logEndpoint.expectedBodiesReceived("ID[1] saying Hello World!");

		testProducer.sendBody(de.mindcrimeilab.cameltest.EntityWithInterface.class);

		logEndpoint.assertIsSatisfied();
	}

}