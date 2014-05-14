package org.kodmanyagha.infonal;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SuppressWarnings("serial")
public class AppDispatcherServlet extends DispatcherServlet {
  // TODO 13May14 2106 Write necessary configurations here

  // TODO 14May14 2051 Think about this. Is this method works for you?
  @Override
  protected void initStrategies(ApplicationContext context) {
    super.initStrategies(context);

  }

}
