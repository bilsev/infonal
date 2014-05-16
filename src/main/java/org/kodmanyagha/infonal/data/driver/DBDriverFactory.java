package org.kodmanyagha.infonal.data.driver;

import org.kodmanyagha.infonal.data.driver.mongodb.MongoDBDriver;
import org.kodmanyagha.infonal.data.driver.mongodb.MysqlDBDriver;
import org.kodmanyagha.infonal.data.exception.ConnectionStringSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DBDriverFactory {
  private static final Logger logger = LoggerFactory.getLogger(DBDriverFactory.class);

  private String connectionString;

  private DBDriver dbDriver = null;

  @Autowired
  public void setConnectionString(String connectionString) {
    this.connectionString = connectionString;
  }

  // TODO 16May14 2101 Write test codes for this method.
  public DBDriver getDBDriver() throws ConnectionStringSyntaxException {
    if (connectionString == null)
      throw new ConnectionStringSyntaxException("Connection string can not be null");

    if (dbDriver != null)
      return dbDriver;

    if (connectionString.startsWith("mongodb")) {
      logger
          .debug("--- connectionString starts with mogodb and creating a new instance of mongo db driver");
      dbDriver = new MongoDBDriver(connectionString);
    } else if (connectionString.startsWith("mysql")) {
      logger
          .debug("--- connectionString starts with mysql and creating a new instance of mysql db driver");
      dbDriver = new MysqlDBDriver(connectionString);
    }

    return dbDriver;
  }

}
