package tr.ogm.gov.krestakip;

import com.sun.faces.config.ConfigureListener;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tr.ogm.gov.krestakip.util.scope.ViewScope;

import javax.faces.webapp.FacesServlet;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Configuration
public class KrestakipApplication extends SpringBootServletInitializer implements WebMvcConfigurer {


	public static void main(String[] args) {
		SpringApplication.run(KrestakipApplication.class, args);
	}

	//JSF Configration Başlangıc
	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistraiton() {
		ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<FacesServlet>(new FacesServlet(),
				new String[]{"*.xhtml"});
		registration.setName("Faces Servlet");
		registration.setLoadOnStartup(1);
		return registration;
	}

	@Bean
	public static CustomScopeConfigurer customScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.addScope("view", new ViewScope());
		return configurer;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			//Primefacesin ücretsiz temalarından bootstrap örneği yaptık değiştirebilirsiniz
			servletContext.setInitParameter("primefaces.THEME", "ui-lightness");
			//Primefaces client browser tarafında kontrol edilebilme örneğin textbox 10 karakter olmalı vs..
			servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
			//Xhtml sayfalarında commentlerin parse edilmemesi.
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", Boolean.TRUE.toString());
			//primefaces icon set için
			servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
		};
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}
	//JSF Configration Sonu

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/index.xhtml");
	}



}
