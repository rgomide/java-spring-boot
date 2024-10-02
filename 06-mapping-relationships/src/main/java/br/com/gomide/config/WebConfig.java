package br.com.gomide.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${cors.originPatterns}")
  private String corsOriginPatterns = "";

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    String[] allowedPatterns = corsOriginPatterns.split(",");

    registry.addMapping("/**")
        .allowedOrigins(allowedPatterns)
        .allowedMethods("*")
        .allowCredentials(true);

    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET");
  }

}
