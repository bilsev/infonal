package org.kodmanyagha.infonal.data;

import java.util.ArrayList;
import java.util.List;

import org.kodmanyagha.infonal.data.driver.DBDriver;
import org.kodmanyagha.infonal.data.driver.MongoDBDriver;
import org.kodmanyagha.infonal.data.driver.exception.DBConnectionException;
import org.kodmanyagha.infonal.model.User;
import org.kodmanyagha.infonal.util.StringUtil;
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

  private String connectionString;

  private DBDriver dbDriver;

  public void connect() throws DBConnectionException {
    if (dbDriver == null)
      throw new DBConnectionException("DBDriver is null");

    dbDriver.connect();
  }

  public DBDriver getDbDriver() {
    return dbDriver;
  }

  public String getConnectionString() {
    return connectionString;
  }

  @Autowired
  public void setConnectionString(String connectionString) {
    this.connectionString = connectionString;
    // TODO 15May14 1647 Think about this. Use factory
    // pattern for here if time to be enough.
    dbDriver = new MongoDBDriver(connectionString);
  }

  public List<User> getUsers() {
    List<User> users = new ArrayList<>();

    User user = null;
    for (int i = 0; i < 5; i++) {
      user = new User();
      user.setFirstname("Name" + i);
      user.setLastname("lastname" + i);
      user.setId(i);
      user.setPhone("537 123 " + StringUtil.intToString(i, 2) + " "
          + StringUtil.intToString(i + 3, 2));
      users.add(user);
    }

    return users;
  }
}
