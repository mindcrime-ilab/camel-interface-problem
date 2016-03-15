package de.mindcrimeilab.cameltest;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import de.mindcrimeilab.cameltest.Identifiable; 
import de.mindcrimeilab.cameltest.AbstractEntity;

import de.mindcrimeilab.cameltest.Entity;

import java.lang.Exception;

@Component
public class HelloRoute extends RouteBuilder {
    @Override
	public void configure() throws Exception {
    
        from("direct:doit")
        .process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
					    
						Message in = exchange.getIn();
						Class c = in.getBody(Class.class);
						Object obj = c.newInstance();
						if(obj instanceof Entity) {
						    Entity en = (Entity) obj;
						    en.setId(1l);
						    en.setText("Hello World!");
						}
						else if(obj instanceof EntityWithInterface) {
                            EntityWithInterface en = (EntityWithInterface) obj;
						    en.setId(1l);
						    en.setText("Hello World!");
						}
						else {
						    obj = "ERROR: unexpected class";
						}
						
						in.setBody(obj);
					}
				})
        .transform().simple("ID[${body?.id}] saying ${body?.text}")
        .to("log:RESULT")
        .end();
        
    }
}