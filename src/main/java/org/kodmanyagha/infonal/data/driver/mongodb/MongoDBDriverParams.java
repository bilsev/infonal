package org.kodmanyagha.infonal.data.driver.mongodb;

public class MongoDBDriverParams {
  private String dbHost;
  private String dbPort;
  private String dbName;
  private String dbCollectionName;
  private String dbUsername;
  private String dbPassword;

  public String getDbHost() {
    return dbHost;
  }

  public void setDbHost(String dbHost) {
    this.dbHost = dbHost;
  }

  public String getDbPort() {
    return dbPort;
  }

  public void setDbPort(String dbPort) {
    this.dbPort = dbPort;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getDbCollectionName() {
    return dbCollectionName;
  }

  public void setDbCollectionName(String dbCollectionName) {
    this.dbCollectionName = dbCollectionName;
  }

  public String getDbUsername() {
    return dbUsername;
  }

  public void setDbUsername(String dbUsername) {
    this.dbUsername = dbUsername;
  }

  public String getDbPassword() {
    return dbPassword;
  }

  public void setDbPassword(String dbPassword) {
    this.dbPassword = dbPassword;
  }

}
