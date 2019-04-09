package com.wojto.wmcase.config;

//import java.util.logging.Logger;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.wojto.wmcase")
@PropertySource("classpath:persistence-mysql-jdbc.properties")
public class SpringConfig implements WebMvcConfigurer {

//	@Autowired
//	private Environment env;
	
//	// NIe wiem czy dobry element config Hibernate
//	@Autowired
//	public static SessionFactory sessionFactory;
	
//	// To chyba bêdzie mo¿na skasowaæ
//	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
        .setCachePeriod(3600)
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
    }
	

	
}
