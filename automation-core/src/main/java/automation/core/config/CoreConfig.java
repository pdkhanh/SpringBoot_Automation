package automation.core.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(WebDriverSettings.class)
public class CoreConfig {
  @Bean
  @Scope("cucumber-glue")
  public WebDriver webDriver(@Autowired WebDriverSettings settings) {
    return WebDriverFactory.newInstance(settings);
  }

  @Bean
  public PageObjectPostProcessor pageObjectPostProcessor(@Autowired ApplicationContext appContext) {
    return new PageObjectPostProcessor(appContext);
  }
}
