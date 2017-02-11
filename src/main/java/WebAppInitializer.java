import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 *
 * @author armantulegenov
 *
 */
public class WebAppInitializer extends
AbstractAnnotationConfigDispatcherServletInitializer {
	
	private Logger localLogger = LoggerFactory.getLogger(WebAppInitializer.class);
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	    registration.setInitParameter("dispatchOptionsRequest", "true");
	    registration.setAsyncSupported(true);
	}
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		WebApplicationContext context = super.createRootApplicationContext();
		((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles(profiles());
		return context;
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AsyncConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { ServletContextConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	public String[] profiles() {
		InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
		Properties properties = new Properties();
		try {
			properties.load(input);
			return properties.getProperty("profiles").split(",");
		} catch (IOException e) {
			localLogger.error(e.getMessage(),e);
			String[] defaultProfiles = { "development" };
			return defaultProfiles;
			// I really think that here we shouldn't return a default profile
		}

	}
	
	@Override
	protected Filter[] getServletFilters() {
	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
	    return new Filter[] { characterEncodingFilter };
	}

}
