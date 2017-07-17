package com.rjil.ws;

import javax.servlet.ServletContext;

import org.apache.coyote.AbstractProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.rjil.ws.resource.WSSocketMediator;

@Configuration
@WebAppConfiguration
public class EndpointConfig {

    @Bean
    public WSSocketMediator echoCinemaEventSocketMediator() {
        return new WSSocketMediator();
    }

    
    @Bean
    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
        return new ServletContextAware() {

            @Override
            public void setServletContext(ServletContext servletContext) {
                ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
                serverEndpointExporter.setApplicationContext(applicationContext);
                try {
                    serverEndpointExporter.afterPropertiesSet();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }               
            }           
        };
    }
    
    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();

        factory.addConnectorCustomizers(connector -> 
                ((AbstractProtocol) connector.getProtocolHandler()).setConnectionTimeout(10000));
        factory.addConnectorCustomizers(connector -> 
        ((AbstractProtocol) connector.getProtocolHandler()).setMaxConnections(100000));
        factory.addConnectorCustomizers(connector -> 
        ((AbstractProtocol) connector.getProtocolHandler()).setMaxThreads(5000));
        
        // configure some more properties

        return factory;
    }
    
}
