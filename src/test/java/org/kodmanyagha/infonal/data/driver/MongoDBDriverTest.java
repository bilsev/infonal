package org.kodmanyagha.infonal.data.driver;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.kodmanyagha.infonal.data.driver.mongodb.MongoDBDriver;
import org.kodmanyagha.infonal.data.exception.ConnectionStringParseException;

public class MongoDBDriverTest extends TestCase {

  public void testCheckConnectionStringSyntaxNull() throws IllegalArgumentException,
      ConnectionStringParseException {
    MongoDBDriver driver = new MongoDBDriver(null);

    try {
      driver.checkConnectionStringSyntax();
      fail("You must check connection string is null");
    } catch (IllegalArgumentException ex) {
    }
  }

  public void testCheckConnectionStringSyntax1() throws IllegalArgumentException,
      ConnectionStringParseException {
    MongoDBDriver driver = new MongoDBDriver(null);

    // you can increase examples
    List<String> connStrings = Arrays.asList("123123123", "mongo://", "mongodb://aergaertsrht");
    for (int i = 0; i < connStrings.size(); i++) {
      try {
        driver.setConnectionString(connStrings.get(i));
        driver.checkConnectionStringSyntax();
        fail("It must throw exception for that connection string: " + connStrings.get(i));
      } catch (IllegalArgumentException ex) {
        assertTrue(ex.getLocalizedMessage()
            .contains("Connection string does not match any pattern"));
      }
    }
  }

  public void testCheckConnectionStringSyntax2() throws IllegalArgumentException,
      ConnectionStringParseException {
    MongoDBDriver driver = new MongoDBDriver(null);

    // you can increase examples
    List<String> connStrings = Arrays.asList("mongodb://localhost/test/testData");
    for (int i = 0; i < connStrings.size(); i++) {
      driver.setConnectionString(connStrings.get(i));
      driver.checkConnectionStringSyntax();
    }
  }
}
