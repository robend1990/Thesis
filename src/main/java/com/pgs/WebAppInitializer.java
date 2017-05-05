package com.pgs;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext
        .addFilter("securityFilter", 
                   new DelegatingFilterProxy("springSecurityFilterChain"))
        .addMappingForUrlPatterns(null, false, "/*");
		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
	}
	
	private AnnotationConfigWebApplicationContext getContext(){
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		//context.setConfigLocation("com.pgs.WebConfig");
		context.register(WebConfig.class);
		return context;
	}

}
