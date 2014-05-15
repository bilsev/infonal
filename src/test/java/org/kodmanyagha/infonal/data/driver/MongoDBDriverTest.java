package org.kodmanyagha.infonal.data.driver;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class MongoDBDriverTest extends TestCase {

  public void testCheckConnectionStringSyntaxNull() {
    MongoDBDriver driver = mock(MongoDBDriver.class);

    driver.setConnectionString(null);

  }
}
