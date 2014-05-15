package org.kodmanyagha.infonal.data.driver;

import org.kodmanyagha.infonal.data.driver.mongodb.MongoDBDriver;

public class DBDriverFactory {

  // TODO 15May14 2143 Think about this. Will you create new instance for every method call?
  public static DBDriver getDBDriver() {
    return new MongoDBDriver();
  }
}
