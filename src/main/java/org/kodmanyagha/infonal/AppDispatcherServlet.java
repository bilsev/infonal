package org.kodmanyagha.infonal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SuppressWarnings("serial")
public class AppDispatcherServlet extends DispatcherServlet {
  // TODO 13May14 2106 Write necessary configurations here
  private static final Logger logger = LoggerFactory.getLogger(AppDispatcherServlet.class);

  // TODO 14May14 2051 Think about this. Is this method works for you?
  @Override
  protected void initStrategies(ApplicationContext context) {
    logger.debug("--- initStrategies calling");

    super.initStrategies(context);
  }

}
