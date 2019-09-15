package automation.core.config;

import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import automation.core.annotation.ComponentObject;
import automation.core.annotation.PageObject;

public class PageObjectPostProcessor implements BeanPostProcessor {
  private final ApplicationContext appContext;

  public PageObjectPostProcessor(ApplicationContext appContext) {
    this.appContext = appContext;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    Class beanType = bean.getClass();
    if (beanType.isAnnotationPresent(ComponentObject.class) || beanType.isAnnotationPresent(PageObject.class)) {
      PageFactory.initElements(new SpringSeleniumFieldDecorator(appContext), bean);
    }

    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    return bean;
  }
}
