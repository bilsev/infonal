package org.kodmanyagha.infonal.data;

import org.kodmanyagha.infonal.data.driver.DBDriver;
import org.kodmanyagha.infonal.data.driver.MongoDBDriver;
import org.kodmanyagha.infonal.data.driver.exception.DBConnectionException;

/********************************************************************************
 * Data Access for Infonal Project
 * 
 * Actually I can use spring-data-mongodb library but if I use libraries for everything how can you
 * understand that what I know?
 * 
 * @author emir
 * 
 */
public class InfonalDataAccess {

  private String connectionString;

  private DBDriver dbDriver;

  public InfonalDataAccess() {
    dbDriver = new MongoDBDriver(connectionString);
  }

  public void connect() throws DBConnectionException {
    dbDriver.connect();
  }

  public String getExampleString() {
    return this.connectionString;
  }

  public String getConnectionString() {
    return connectionString;
  }

  public void setConnectionString(String connectionString) {
    this.connectionString = connectionString;
  }

}
