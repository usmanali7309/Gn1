package com.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class UserWsConfiguration extends WsConfigurerAdapter {

    // This is configuration class for generate Wsdl file and url
    @Bean
    public ServletRegistrationBean<?> messageDispatcherServlet(ApplicationContext applicationContext){

//        this method for create url
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet,"/ws/*");

    }

//    http://localhost:7080/ws/user.wsdl
    @Bean(name = "user")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
//        this method for generate wsdl file
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("UserPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://samplebanking.com/soap");
        definition.setSchema(schema);
       return definition;
    }

    @Bean
    public XsdSchema xsdSchema(){
//     this is for give the path of xsd file
     return new SimpleXsdSchema(new ClassPathResource("sampleBanking.xsd"));
    }




}
