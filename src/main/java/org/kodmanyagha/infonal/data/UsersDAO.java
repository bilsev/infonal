package org.kodmanyagha.infonal.data;

import java.util.ArrayList;
import java.util.List;

import org.kodmanyagha.infonal.data.driver.DBDataObject;
import org.kodmanyagha.infonal.data.driver.DBDriver;
import org.kodmanyagha.infonal.data.driver.DBDriverFactory;
import org.kodmanyagha.infonal.data.exception.ConnectionStringSyntaxException;
import org.kodmanyagha.infonal.data.exception.DBConnectionException;
import org.kodmanyagha.infonal.data.exception.DBDriverProcessException;
import org.kodmanyagha.infonal.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/************************************************************
 * Data Access Object for users collection of MongoDB.
 * 
 * @author emir
 * 
 */
public class UsersDAO {
  private static final Logger logger = LoggerFactory.getLogger(UsersDAO.class);

  private DBDriver dbDriver;
  private DBDriverFactory dbDriverFactory;

  private final String tableName = "users";

  @Autowired
  public void setDbDriverFactory(DBDriverFactory dbDriverFactory) {
    this.dbDriverFactory = dbDriverFactory;
  }

  public void connect() throws DBConnectionException, ConnectionStringSyntaxException {
    if (dbDriver == null) {
      logger.debug("--- getting DBDriver object from db driver factory");
      dbDriver = dbDriverFactory.getDBDriver();
    }

    if (!dbDriver.isConnected()) {
      logger.debug("--- opening a new connection to database");
      dbDriver.connect();
    }
  }

  public List<User> getUsers() throws DBConnectionException, ConnectionStringSyntaxException, DBDriverProcessException {
    connect();

    logger.debug("--- getting DBDataObject list from data access object");
    List<DBDataObject> dbDataObjects = dbDriver.get(tableName);
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

  public List<User> findUsers(User user) {
    return null;
  }

  public void updateUser(User updatedUser) throws DBDriverProcessException {
    DBDataObject whereDataObject = new DBDataObject();
    whereDataObject.getValues().put("_id", updatedUser.getId());

    DBDataObject updateDataObject = new DBDataObject();
    updateDataObject.getValues().put("firstname", updatedUser.getFirstname());
    updateDataObject.getValues().put("lastname", updatedUser.getLastname());
    updateDataObject.getValues().put("phone", updatedUser.getPhone());

    dbDriver.where(whereDataObject);
    dbDriver.update(tableName, updateDataObject);
  }

  public void insertUser(User newUser) throws DBDriverProcessException {
    DBDataObject dbDataObject = new DBDataObject();
    dbDataObject.getValues().put("firstname", newUser.getFirstname());
    dbDataObject.getValues().put("lastname", newUser.getLastname());
    dbDataObject.getValues().put("phone", newUser.getPhone());

    dbDriver.insert(tableName, dbDataObject);
  }

  public void deleteUser(User user) throws DBDriverProcessException {
    DBDataObject dbDataObject = new DBDataObject();
    dbDataObject.getValues().put("_id", user.getId());

    dbDriver.delete(tableName, dbDataObject);
  }
}
