package com.meek.donald;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.meek.donald.model.projects.Project;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

@SpringBootApplication
public class ProjectServicesApplication {
	
	public static void main(String[] args) {
	    try {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            props.put(Environment.URL, 
            		"jdbc:mysql://@127.0.0.1:3306/project?useSSL=false&amp;useUnicode=true&amp;" +
            		"useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;" +
            		"serverTimezone=UTC");
            props.put(Environment.USER, "uname");
            props.put(Environment.PASS, "g0odLuck*");
            props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            props.put(Environment.SHOW_SQL, "true");
            props.put(Environment.POOL_SIZE, "1");
            configuration.setProperties(props);
            configuration.addAnnotatedClass(Project.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
	    	//new Configuration().configure().buildSessionFactory(serviceRegistry);
	    } catch (Throwable ex) { 
	    	System.err.println("Exception building session factory " + ex);
	    	throw new ExceptionInInitializerError(ex); 
	    }
		SpringApplication.run(ProjectServicesApplication.class, args);
	}
}
