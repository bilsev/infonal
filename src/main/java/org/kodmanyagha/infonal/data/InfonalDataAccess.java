package org.kodmanyagha.infonal.data;

import java.util.ArrayList;
import java.util.List;

import org.kodmanyagha.infonal.data.driver.DBDataObject;
import org.kodmanyagha.infonal.data.driver.DBDriver;
import org.kodmanyagha.infonal.data.driver.mongodb.MongoDBDriver;
import org.kodmanyagha.infonal.data.exception.DBConnectionException;
import org.kodmanyagha.infonal.model.User;
import org.kodmanyagha.infonal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

  private static final Logger logger = LoggerFactory.getLogger(InfonalDataAccess.class);

  private String connectionString;

  private DBDriver dbDriver;

  public DBDriver getDbDriver() {
    return dbDriver;
  }

  public String getConnectionString() {
    return connectionString;
  }

  @Autowired
  public void setConnectionString(String connectionString) {
    this.connectionString = connectionString;

    // we can not connect to database here becouse if we try to connect then Spring throwing
    // nested exception is NullPointerException
  }

  public void connect() throws DBConnectionException {
    if (dbDriver == null) {
      // TODO 15May14 1647 Think about this. Use factory pattern for here if time to be enough.
      logger.debug("--- getting new instance of MongoDBDriver");
      dbDriver = new MongoDBDriver(connectionString);
    }

    if (!dbDriver.isConnected()) {
      logger.debug("--- opening a new connection to database");
      dbDriver.connect();
    }
  }

  public List<User> getUsers() throws DBConnectionException {
    connect();

    logger.debug("--- getting DBDataObject list from data access object");
    List<DBDataObject> dbDataObjects = dbDriver.getData();
    List<User> users = new ArrayList<>();

    User user = null;
    DBDataObject dbDataObject;
    for (int i = 0; i < dbDataObjects.size(); i++) {
      dbDataObject = dbDataObjects.get(i);
      user = new User();
      user.setFirstname(dbDataObject.getValues().get("firstname").toString());
      user.setLastname(dbDataObject.getValues().get("lastname").toString());
      user.setId(dbDataObject.getValues().get("_id").toString());
      user.setPhone(dbDataObject.getValues().get("phone").toString());
      users.add(user);
    }

    return users;
  }
}
