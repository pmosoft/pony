package net.pmosoft.pony;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class PonyApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(PonyApplication.class, args);
	}


    @Configuration
    public class WebApplicationConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/notFound").setViewName("forward:/index.html");
        }

        @Bean
        public EmbeddedServletContainerCustomizer containerCustomizer() {
            return container -> {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/notFound"));
            };
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("explorer.exe http://localhost:9201");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
